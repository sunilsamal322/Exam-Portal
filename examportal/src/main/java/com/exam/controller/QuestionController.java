package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question qusetion)
	{
		return ResponseEntity.ok(this.questionService.addQuestion(qusetion));
	}
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question qusetion)
	{
		return ResponseEntity.ok(this.questionService.updaQuestion(qusetion));
	}
	//get all questions of any quiz id 
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQusetionsOfQuiz(@PathVariable("qId") Long qId)
	{
//		Quiz quiz=new Quiz();
//		quiz.setQid(qId);
//		Set<Question> questionOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
//		return ResponseEntity.ok(questionOfQuiz);
		
		Quiz quiz=this.quizService.getQuiz(qId);
		Set<Question> questions=quiz.getQusetions();
		
		List<Question> list=new ArrayList<>(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions()))
		{
			 list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));	
		}
		list.forEach(q->q.setAnswer(""));
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/quiz/all/{qId}")
	public ResponseEntity<?> getQusetionsOfAdmin(@PathVariable("qId") Long qId)
	{
		Quiz quiz=new Quiz();
		quiz.setQid(qId);
		Set<Question> questionOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);
	}
	
	//get single question
	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable("quesId") Long quesId)
	{
		return this.questionService.getQuestion(quesId);
	}
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable("quesId") Long quesId)
	{
		this.questionService.deleteQuestion(quesId);
	}
	
	//evaluate quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{
		double marksGot=0.0;
		int correctAnswers=0;
		int attempted=0;
		for(Question q:questions){
			//single question
			Question question=this.questionService.getQuestion(q.getQuesId());
			if(question.getAnswer().equals(q.getGivenAnswer()))
			{
				correctAnswers++;
				double markSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot+=markSingle;
			}
			if(q.getGivenAnswer()!=null)
			{
				attempted++;
			}
		}
		
		Map<String, Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
}
