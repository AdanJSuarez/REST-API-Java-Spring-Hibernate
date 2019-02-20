package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public Question getNextQuestion(String id) {
		questionRecord = questionRecordRepository.findById(id).get(); //Get to solve Optional returned by findById
		Integer nextQuestionId =questionRecord.getNextQuestionId();
		String  nextQuestionType = questionRecord.getNextTypeQuestion();
		question = getQuestion(nextQuestionId, nextQuestionType);
		
		//Update questionRecord
		updateQuestion(id, nextQuestionId, nextQuestionType);
		
		return question;
	}

	private void updateQuestion(String id, Integer nextQuestionId, String nextQuestionType) {
		int newQuestionId;
		String newQuestionType;
		newQuestionType = getNewQuestionType(nextQuestionType);
		newQuestionId = getNewQuestionIndex(nextQuestionId, newQuestionType);
		QuestionRecord nextQuestionRecord = new QuestionRecord();
		nextQuestionRecord.setUuid(id);
		nextQuestionRecord.setNextQuestionID(newQuestionId);
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
