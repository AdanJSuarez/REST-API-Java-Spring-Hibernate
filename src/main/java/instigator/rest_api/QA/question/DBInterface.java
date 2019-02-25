package instigator.rest_api.QA.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import instigator.rest_api.QA.answer.Answer;
import instigator.rest_api.QA.answer.AnswerRepository;

public class DBInterface {
	
	private String questionType;
	private String question;
	private List<String> answers;
	
	public DBInterface() {
		question = null;
		questionType = null;
		answers = null;
	}
	
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswers() {};

	
	
	
	
	
	
	
	
	
	
	
	
	
//	private void initializeQuestion() {
//		Map<String, String> answerTrivia = new HashMap<>();
//		answerTrivia.put("Falcons", "");
//		answerTrivia.put("Patriots", "true");
//		Answer answerOfferedTrivia = new Answer();
//		answerOfferedTrivia.setAnswer(answerTrivia);
//		answerRepository.save(answerOfferedTrivia);
//		
//		Map<String, String> answerPoll1 = new HashMap<>();
//		answerPoll1.put("Nissan", "");
//		answerPoll1.put("Honda", "");
//		answerPoll1.put("Audi", "");
//		answerPoll1.put("Seat", "");
//		Answer answerOfferedPoll = new Answer();
//		answerOfferedPoll.setAnswer(answerPoll1);
//		answerRepository.save(answerOfferedPoll);
//		
////		Map<String, String> answerPoll2 = new HashMap<>();
////		answerPoll2.put("Nissan", "");
////		answerPoll2.put("Honda", "");
////		answerPoll2.put("Audi", "true");
////		answerPoll2.put("Seat", "");
////		Answer answerReturned2 = new Answer();
////		answerReturned2.setAnswer(answerPoll2);
////		answerRepository.save(answerReturned2);
//		
//		Map<String, String> answerCheckbox1 = new HashMap<>();
//		answerCheckbox1.put("Red", "");
//		answerCheckbox1.put("Blue", "");
//		answerCheckbox1.put("Yellow", "");
//		answerCheckbox1.put("Green", "");
//		answerCheckbox1.put("Black", "");
//		answerCheckbox1.put("Purple", "");
//		Answer answerOfferedCheckbox = new Answer();
//		answerOfferedCheckbox.setAnswer(answerCheckbox1);
//		answerRepository.save(answerOfferedCheckbox);
//		
//		Map<String, String> answerMatrix1 = new HashMap<>();
//		answerMatrix1.put("Age/Gender", "<18|18 to 30|35 to 55|>55");
//		answerMatrix1.put("Male", "|||");
//		answerMatrix1.put("Female", "|||");
//		Answer answerOfferedMatrix = new Answer();
//		answerOfferedMatrix.setAnswer(answerMatrix1);
//		answerRepository.save(answerOfferedMatrix);
//		
//		Question trivia1 = new Question();
//		trivia1.setQuestionType("Trivia");
//		trivia1.setQuestion("Which team won the 2017 super bowl?");
//		trivia1.setAnswerOffered(answerOfferedTrivia);
//		System.out.println(trivia1.toString());
//		questionRepository.save(trivia1);
//		System.out.println("Trivia questions initialized");
//		
//		Question poll1 = new Question();
//		poll1.setQuestionType("Poll");
//		poll1.setQuestion("Witch car do you preffer?");
//		poll1.setAnswerOffered(answerOfferedPoll);
//		System.out.println(poll1.toString());
//		questionRepository.save(poll1);
//		System.out.println("Poll questions initialized");
//		
//		Question checkbox1 = new Question();
//		checkbox1.setQuestionType("Checkbox");
//		checkbox1.setQuestion("What are the color do you like?");
//		checkbox1.setAnswerOffered(answerOfferedCheckbox);
//		System.out.println(checkbox1.toString());
//		questionRepository.save(checkbox1);
//		System.out.println("Checkbox questions initialized");
//		
//		Question matrix1 = new Question();
//		matrix1.setQuestionType("Matrix");
//		matrix1.setQuestion("Please tell us a bit about yourself?");
//		matrix1.setAnswerOffered(answerOfferedMatrix);
////		matrix1.addRecordAnswerReturned(answerOfferedMatrix);
//		System.out.println(matrix1.toString());
//		questionRepository.save(matrix1);
//		System.out.println("Matrix questions initialized");
//		
//		QuestionRecord questionRecord = new QuestionRecord("aa1");
//		questionRecord.addTrivia(trivia1);
//		questionRecord.addPoll(poll1);
//		questionRecord.addCheckbox(checkbox1);
//		questionRecord.addMatrix(matrix1);
//		questionRecordRepository.save(questionRecord);
//	}

}
