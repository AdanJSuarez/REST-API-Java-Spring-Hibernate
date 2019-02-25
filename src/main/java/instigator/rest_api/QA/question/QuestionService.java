package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.Index;

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
		System.out.println("--- Answer saved");
	}
	public String setNewQuestion(String uuid, NewQuestion newQuestion) {
		Integer index;
		Question question = new Question();
		Answer answer = new Answer();
		String questionType = newQuestion.getQuestionType();
		try {
			questionRecord = questionRecordRepository.findById(uuid).get();
		} catch (NoSuchElementException e) {
			questionRecord = new QuestionRecord(uuid);
		}
		question.setQuestionType(questionType);
		question.setQuestion(newQuestion.getQuestion());
		answer.setAnswer(newQuestion.getAnswerOffered());
		question.setAnswerOffered(answer);
		answerRepository.save(answer);
		questionRepository.save(question);
		switch (questionType) {
		case "Trivia":
			questionRecord.addTrivia(question);
			try {
				index = questionRecord.getListOfIndex().get(0) + 1;
			} catch (Exception e) {
				index = 0;
			}
			questionRecord.setNextQuestionIndex(0, index);
			questionRecordRepository.save(questionRecord);
			break;
		case "Poll":
			questionRecord.addPoll(question);
			try {
				index = questionRecord.getListOfIndex().get(1) + 1;
			} catch (Exception e) {
				index = 0;
			}
			questionRecord.setNextQuestionIndex(1, index);
			questionRecordRepository.save(questionRecord);
			break;
		case "Checkbox":
			questionRecord.addCheckbox(question);
			try {
				index = questionRecord.getListOfIndex().get(2) + 1;
			} catch (Exception e) {
				index = 0;
			}
			questionRecord.setNextQuestionIndex(2, index);
			questionRecordRepository.save(questionRecord);
			break;
		default:
			questionRecord.addMatrix(question);
			try {
				index = questionRecord.getListOfIndex().get(3) + 1;
			} catch (Exception e) {
				index = 0;
			}
			questionRecord.setNextQuestionIndex(3, index);
			questionRecordRepository.save(questionRecord);
			break;
		}
		System.out.println("--- Question saved");
		return "--- Question saved!";
	}
	public IQuestion deleteQuestion(String uuid, String qId) {
		Question question;
		Integer questionId = Integer.parseInt(qId);;
		try {
			question = questionRepository.findById(questionId).get();
		} catch (NoSuchElementException e) {
			return new QuestionEmpty(uuid);
		}
		questionRecord = questionRecordRepository.findById(uuid).get();
		String questionType = question.getQuestionType();
		System.out.println(questionId);
		questionRepository.deleteById(questionId);
		answerRepository.deleteById(question.getAnswerOffered().getId());
		switch (questionType) {
		case "Trivia":
			if (questionRecord.getListOfIndex().get(0) == questionId) {
			questionRecord.setNextQuestionIndex(0, questionRecord.getListOfTrivia().get(0).getId());
			}
			
			break;
		case "Poll":
			if (questionRecord.getListOfIndex().get(1) == questionId) {
			questionRecord.setNextQuestionIndex(1, questionRecord.getListOfPoll().get(0).getId());
			}
			break;
		case "Checkbox":
			if (questionRecord.getListOfIndex().get(2) == questionId) {
			questionRecord.setNextQuestionIndex(2, questionRecord.getListOfCheckbox().get(0).getId());
			}
			break;
		default:
			if (questionRecord.getListOfIndex().get(3) == questionId) {
				questionRecord.setNextQuestionIndex(3, questionRecord.getListOfMatrix().get(0).getId());
				}
			break;
		}
		return new QuestionEmpty("--- Question deleted!");
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
