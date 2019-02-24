package instigator.rest_api.QA.question;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import instigator.rest_api.QA.answer.Answer;
import instigator.rest_api.QA.answer.AnswerRepository;

public class InitializeEntities {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	public InitializeEntities() {
		initializeTrivia();
		initializePoll();
		initializeCheckbox();
		initializeMatrix();
	}

	/**
	 * Initialize trivia for testing
	 */
	private void initializeTrivia() {
		Map<String, String> answer1 = new HashMap<>();
		answer1.put("Falcons", "correct");
		answer1.put("Patriots", "incorrect");
		Answer answerOffered = new Answer();
		answerOffered.setAnswer(answer1);
		answerRepository.save(answerOffered);
		
		Question trivia1 = new Question();
		trivia1.setQuestionType("Trivia");
		trivia1.setQuestion("Which team won the 2017 super bowl?");
		trivia1.setAnswerOffered(answerOffered);
		trivia1.setAnswerReturned(answerOffered);
		System.out.println(trivia1.toString());
		questionRepository.save(trivia1);
		System.out.println("Trivia questions initialized");
	}
	private void initializePoll() {
		// TODO Auto-generated method stub

	}
	private void initializeCheckbox() {
		// TODO Auto-generated method stub

	}
	private void initializeMatrix() {


	}

}
