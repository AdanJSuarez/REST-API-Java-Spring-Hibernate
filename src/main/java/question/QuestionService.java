package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	
	private List<String> QUESTIONS = new ArrayList<>(Arrays.asList("Trivia", "Poll", "Checkbox", "Matrix"));
	
	@Autowired 
	private QuestionRecordRepository questionRecordRepository;
	@Autowired
	private TriviaRepository triviaRepository;
	@Autowired
	private PollRepository pollRepository;
	@Autowired
	private CheckboxRepository checkboxRepository;
	@Autowired
	private MatrixRepository matrixRepository;
	
	private Question question;
	private QuestionRecord questionRecord;
	
	public Question getNextQuestion(String uuid) {
		questionRecord = questionRecordRepository.findById(uuid).get(); //Get to solve Optional returned by findById
		Integer nextQuestionId =questionRecord.getNextQuestionId();
		String  nextQuestionType = questionRecord.getNextQuestionType();
		question = getQuestion(nextQuestionId, nextQuestionType);
		
		//Update questionRecord
		updateQuestionRecord(uuid, nextQuestionId, nextQuestionType);
		
		return question;
	}
	public void updateAnswerRecord(String uuid, Question questionReturned) {
		Integer questionId = questionReturned.getId();
		String questionType = questionReturned.getQuestionType();
		switch (questionType) {
		case "Trivia":
			Trivia triviaQuestionReturned = (Trivia) questionReturned;
			Boolean triviaAnswerReturned = triviaQuestionReturned.getAnswerReturned();
			Trivia triviaQuestion = triviaRepository.findById(questionId).get();
			triviaQuestion.addRecordAnswerReturned(triviaAnswerReturned);
			triviaRepository.save(triviaQuestion);
			break;
		case "Poll":
			Poll pollQuestionReturned = (Poll) questionReturned;
			String pollAnswerReturned = pollQuestionReturned.getAnswerReturned();
			Poll pollQuestion = pollRepository.findById(questionId).get();
			pollQuestion.addRecordAnswerReturned(pollAnswerReturned);
			pollRepository.save(pollQuestion);
			break;
		case "Checkbox":
			Checkbox checkboxQuestionReturned = (Checkbox) questionReturned;
			List<String> checkboxAnswerReturned = checkboxQuestionReturned.getAnswerReturned();
			Checkbox checkboxQuestion = checkboxRepository.findById(questionId).get();
			checkboxQuestion.addRecordAnswerReturned(checkboxAnswerReturned);
			checkboxRepository.save(checkboxQuestion);
			break;
		default:
			Matrix matrixQuestionReturned = (Matrix) questionReturned;
			Map<String, String> matrixAnswerReturned = matrixQuestionReturned.getAnswerReturned();
			Matrix matrixQuestion = matrixRepository.findById(questionId).get();
			matrixQuestion.addAnswerReturned(matrixAnswerReturned);
			matrixRepository.save(matrixQuestion);
			break;
		}
	}

	private void updateQuestionRecord(String id, Integer nextQuestionId, String nextQuestionType) {
		int newQuestionId;
		String newQuestionType;
		newQuestionType = getNewQuestionType(nextQuestionType);
		newQuestionId = getNewQuestionIndex(nextQuestionId, newQuestionType);
		QuestionRecord nextQuestionRecord = new QuestionRecord();
		nextQuestionRecord.setUuid(id);
		nextQuestionRecord.setNextQuestionId(newQuestionId);
		nextQuestionRecord.setNextQuestionType(newQuestionType);
		questionRecordRepository.save(nextQuestionRecord);
	}
	
	private String getNewQuestionType(String newTypeQuestion) {
		int index = QUESTIONS.indexOf(newTypeQuestion);
		index = (index + 1) % QUESTIONS.size();
		return QUESTIONS.get(index);
	}

	private int getNewQuestionIndex(Integer nextQuestionId, String newQuestionType) {
		Integer index = nextQuestionId + 1;
		switch (newQuestionType) {
		case "Trivia":
			if (triviaRepository.existsById(index)) return index;
			else return 1;
		case "Poll":
			if (pollRepository.existsById(index)) return index;
			else return 1;
		case "Checkbox":
			if (checkboxRepository.existsById(index)) return index;
			else return 1;
		default:
			if (matrixRepository.existsById(index)) return index;
			else return 1;
		}
	}
	private Question getQuestion(Integer nextQuestionId, String nextQuestionType) {
		switch (nextQuestionType) {
		case "Trivia":
			return triviaRepository.findById(nextQuestionId).get();
		case "Poll":
			return pollRepository.findById(nextQuestionId).get();
		case "Checkbox":
			return checkboxRepository.findById(nextQuestionId).get();
		default:
			return matrixRepository.findById(nextQuestionId).get();
		}
	}
	
}
