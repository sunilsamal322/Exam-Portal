package com.exam.controller;

import java.util.List;

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

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	@GetMapping("/")
	public ResponseEntity<?> getQuizess()
	{
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Long quizId)
	{
		return ResponseEntity.ok(this.quizService.getQuiz(quizId));
	}
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") long quizId)
	{
		this.quizService.deleteQuiz(quizId);
	}
	
	@GetMapping("/category/{catId}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("catId") Long catId)
	{
		Category category=new Category();
		category.setCid(catId);
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	
	//get active quizzes
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes()
	{
		return this.quizService.getActiveQuizzes();
	}
	@GetMapping("/category/active/{catId}")
	public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("catId") Long CatId)
	{
		Category category=new Category();
		category.setCid(CatId);
		return this.quizService.getActiveQuizzesOfCategory(category);
	}
}
