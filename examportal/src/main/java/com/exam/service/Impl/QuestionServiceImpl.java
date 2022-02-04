package com.exam.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.QuestionRepository;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Question updaQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long qid) {
		
		return this.questionRepository.findById(qid).get();
	}

	@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz) {
		
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		
		Question question=new Question();
		question.setQuesId(quesId);
		this.questionRepository.delete(question);
		
	}

}
