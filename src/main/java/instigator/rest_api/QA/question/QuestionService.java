package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import instigator.rest_api.QA.answer.Answer;

@Service
public class QuestionService {
	
	@Autowired 
	private QuestionRecordRepository questionRecordRepository;
	
	private List<String> QUESTIONS = new ArrayList<>(Arrays.asList("Trivia", "Poll", "Checkbox", "Matrix"));

	private Question question;
	private QuestionRecord questionRecord;
	
	public Question getNextQuestion(String uuid) {
		
		Trivia trivia = new Trivia();
		//FIXME: before save qr we need to save trivia because it is an entity too.
		QuestionRecord qr= new QuestionRecord("aa1");
		qr.addTrivia(trivia);
		System.out.println("--- Previous to save QuestionRecord");
		questionRecordRepository.save(qr);
		System.out.println("--- Saved");
	
		questionRecord = questionRecordRepository.findById(uuid).get();
		question = getQuestion(); 
		updateQuestionRecord(uuid);
		return question;
	}
	
	public void updateAnswerRecord(String uuid, Question questionReturned) {
		Integer questionId = questionReturned.getId();
		String questionType = questionReturned.getQuestionType();
		questionRecord = questionRecordRepository.findById(uuid).get();
		switch (questionType) {
		case "Trivia":
			Trivia triviaQuestionReturned = (Trivia) questionReturned;
			Answer triviaAnswerReturned = triviaQuestionReturned.getAnswerReturned();
			//TODO: Need to solve the index problems.
			Trivia triviaQuestion = questionRecord.getListOfTrivia().get(questionId - 1);
			triviaQuestion.addRecordAnswerReturned(triviaAnswerReturned);
			questionRecordRepository.save(questionRecord);
			break;
		case "Poll":
			Poll pollQuestionReturned = (Poll) questionReturned;
			Answer pollAnswerReturned = pollQuestionReturned.getAnswerReturned();
			//TODO: Need to solve the index problems.
			Poll pollQuestion = questionRecord.getListOfPoll().get(questionId - 1);
			pollQuestion.addRecordAnswerReturned(pollAnswerReturned);
			questionRecordRepository.save(questionRecord);
			break;
		case "Checkbox":
			Checkbox checkboxQuestionReturned = (Checkbox) questionReturned;
			Answer checkboxAnswerReturned = checkboxQuestionReturned.getAnswerReturned();
			//TODO: Need to solve the index problems.
			Checkbox checkboxQuestion = questionRecord.getListOfCheckbox().get(questionId - 1);
			checkboxQuestion.addRecordAnswerReturned(checkboxAnswerReturned);
			questionRecordRepository.save(questionRecord);
			break;
		default:
			Matrix matrixQuestionReturned = (Matrix) questionReturned;
			Answer matrixAnswerReturned = matrixQuestionReturned.getAnswerReturned();
			//TODO: Need to solve the index problems.
			Matrix matrixQuestion = questionRecord.getListOfMatrix().get(questionId - 1);
			matrixQuestion.addAnswerReturned(matrixAnswerReturned);
			questionRecordRepository.save(questionRecord);
			break;
		}
	}
	private Question getQuestion() { 
		switch (questionRecord.getNextQuestionType()) {
		case "Trivia":
			return questionRecord.getTriviaByIndex(questionRecord.getListOfIndex().get(0));
		case "Poll":
			return questionRecord.getPollByIndex(questionRecord.getListOfIndex().get(1));
		case "Checkbox":
			return questionRecord.getCheckboxByIndex(questionRecord.getListOfIndex().get(2));
		default:
			return questionRecord.getMatrixByIndex(questionRecord.getListOfIndex().get(3));
		}
	}
	private void updateQuestionRecord(String uuid) { 
		String newQuestionType;
		newQuestionType = getNewQuestionType(questionRecord.getNextQuestionType());
		updateNextQuestionIndex(newQuestionType);
		questionRecordRepository.save(questionRecord);
	}
	
	private String getNewQuestionType(String newTypeQuestion) {
		int index = QUESTIONS.indexOf(newTypeQuestion);
		index = (index + 1) % QUESTIONS.size();
		return QUESTIONS.get(index);
	}

	private void updateNextQuestionIndex(String newQuestionType) {
		Integer index;
		switch (newQuestionType) {
		case "Trivia":
			index = (questionRecord.getListOfIndex().get(0) + 1) % questionRecord.getListOfTrivia().size();
			questionRecord.setNextQuestionIndex(0, index);
			break;
		case "Poll":
			index = (questionRecord.getListOfIndex().get(1) + 1) % questionRecord.getListOfTrivia().size();
			questionRecord.setNextQuestionIndex(1, index);
			break;
		case "Checkbox":
			index = (questionRecord.getListOfIndex().get(2) + 1) % questionRecord.getListOfTrivia().size();
			questionRecord.setNextQuestionIndex(2, index);
			break;
		default:
			index = (questionRecord.getListOfIndex().get(3) + 1) % questionRecord.getListOfTrivia().size();
			questionRecord.setNextQuestionIndex(3, index);
			break;
		}
	}
	
}
