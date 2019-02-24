package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import instigator.rest_api.QA.answer.Answer;
import instigator.rest_api.QA.answer.AnswerRepository;
@Service
public class QuestionService {
	
	private List<String> QUESTIONS = new ArrayList<>();
	
	@Autowired 
	private QuestionRecordRepository questionRecordRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	private IQuestion question;
	private QuestionRecord questionRecord;
	
	/**
	 * Return the next question of the specific user UUID. Return QuestionEmpty when
	 * there is not the specific UUID.
	 * @param uuid
	 * @return
	 */
	public IQuestion getNextQuestion(String uuid) {
		try {
			questionRecord = questionRecordRepository.findById(uuid).get();
		} catch (NoSuchElementException e) {
			return new QuestionEmpty(uuid);
		}
		question = getQuestion(); 
		updateQuestionRecord();
		return question;
	}
	
	/**
	 * Set answer returned in answer record of specific type of question.
	 * @param uuid
	 * @param questionReturned
	 */
	public void updateAnswerRecord(IQuestion questionReturned) {
		Integer questionId = questionReturned.getId();
		Answer answerReturned = questionReturned.getAnswerReturned();
		Answer newAnswer = new Answer(answerReturned.getAnswer());
		answerRepository.save(newAnswer);
		Question question = questionRepository.findById(questionId).get();
		question.addRecordAnswerReturned(newAnswer);
		questionRepository.save(question);
		System.out.println("--- Answer stored");
	}
	/**
	 * Return question based of the index record.
	 * @return IQuestion
	 */
	private IQuestion getQuestion() { 
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
	/**
	 * Set question record with answer returned added.
	 * @param uuid
	 */
	private void updateQuestionRecord() { 
		String newQuestionType;
		newQuestionType = getNewQuestionType(questionRecord.getNextQuestionType());
		questionRecord.setNextQuestionType(newQuestionType);
		updateNextQuestionIndex(newQuestionType);
		questionRecordRepository.save(questionRecord);
		//TODO: Modify to accept less than 4 questions
	}
	
	/**
	 * Return the next type of question in an sequential order of questions included.
	 * @param newTypeQuestion
	 * @return String
	 */
	private String getNewQuestionType(String newTypeQuestion) {
		if(questionRecord.getListOfTrivia().size() > 0) {
			QUESTIONS.add("Trivia");
		}
		if(questionRecord.getListOfPoll().size() > 0) {
			QUESTIONS.add("Poll");
		}
		if(questionRecord.getListOfCheckbox().size() > 0) {
			QUESTIONS.add("Checkbox");
		}
		if(questionRecord.getListOfMatrix().size() > 0) {
			QUESTIONS.add("Matrix");
		}
		int index = QUESTIONS.indexOf(newTypeQuestion);
		int ix = (index + 1) % QUESTIONS.size();
		return QUESTIONS.get(ix);
	}

	/**
	 * Set the list of index in questionRecord to the next index.
	 * @param newQuestionType
	 */
	private void updateNextQuestionIndex(String newQuestionType) {
		Integer index;
		switch (newQuestionType) {
		case "Trivia":
			index = (questionRecord.getListOfIndex().get(0) + 1) % questionRecord.getListOfTrivia().size();
			questionRecord.setNextQuestionIndex(0, index);
			break;
		case "Poll":
			index = (questionRecord.getListOfIndex().get(1) + 1) % questionRecord.getListOfPoll().size();
			questionRecord.setNextQuestionIndex(1, index);
			break;
		case "Checkbox":
			index = (questionRecord.getListOfIndex().get(2) + 1) % questionRecord.getListOfCheckbox().size();
			questionRecord.setNextQuestionIndex(2, index);
			break;
		default:
			index = (questionRecord.getListOfIndex().get(3) + 1) % questionRecord.getListOfMatrix().size();
			questionRecord.setNextQuestionIndex(3, index);
			break;
		}
	}
	
}
