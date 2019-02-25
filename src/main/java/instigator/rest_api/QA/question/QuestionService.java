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
	@Autowired
	private QuestionNextRepository questionNextRepository;
	private IQuestion question;
	private QuestionRecord questionRecord;
	private QuestionNext questionNext;
	
	
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
	/**
	 * Set new question to the database.
	 * @param uuid
	 * @param newQuestion
	 * @return
	 */
	public String setNewQuestion(String uuid, NewQuestion newQuestion) {
		Question question = new Question();
		Answer answer = new Answer();
		QuestionNext questionNext;
		String questionType = newQuestion.getQuestionType();
		try {
			questionRecord = questionRecordRepository.findById(uuid).get();
		} catch (NoSuchElementException e) {
			questionRecord = new QuestionRecord(uuid);
		}
		try {
			questionNext = questionRecord.getQuestionNext();
		} catch (Exception e1) {
			questionNext = new QuestionNext();
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
			questionRecord.getQuestionNext().setTriviaIndex(0);
			break;
		case "Poll":
			questionRecord.addPoll(question);
			questionRecord.getQuestionNext().setPollIndex(0);
			break;
		case "Checkbox":
			questionRecord.addCheckbox(question);
			questionRecord.getQuestionNext().setCheckboxIndex(0);
			break;
		default:
			questionRecord.addMatrix(question);
			questionRecord.getQuestionNext().setMatrixIndex(0);
			break;
		}
		questionNextRepository.save(questionNext);
		questionRecordRepository.save(questionRecord);
		System.out.println("--- Question saved");
		return "--- Question saved!";
	}
	/**
	 * Delete question with id given.
	 * @param uuid
	 * @param qId
	 * @return
	 */
	public IQuestion deleteQuestion(String uuid, String qId) {
		Question question;
		Integer questionId = Integer.parseInt(qId);;
		try {
			question = questionRepository.findById(questionId).get();
		} catch (NoSuchElementException e) {
			return new QuestionEmpty(uuid);
		}
		questionRecord = questionRecordRepository.findById(uuid).get();
		questionNext = questionRecord.getQuestionNext();
		String questionType = question.getQuestionType();
		questionRepository.deleteById(questionId);
		answerRepository.deleteById(question.getAnswerOffered().getId());
		switch (questionType) {
		case "Trivia":
			questionRecord.removeTrivia(question);
			if (questionRecord.getListOfTrivia().size() > 0) questionNext.setTriviaIndex(0);
			else questionNext.setTriviaIndex(null);
			break;
		case "Poll":
			questionRecord.removePoll(question);
			if (questionRecord.getListOfPoll().size() > 0) questionNext.setPollIndex(0);
			else questionNext.setPollIndex(null);
			break;
		case "Checkbox":
			questionRecord.removeCheckbox(question);
			if (questionRecord.getListOfCheckbox().size() > 0) questionNext.setCheckboxIndex(0);
			else questionNext.setCheckboxIndex(null);
			break;
		default:
			questionRecord.removeMatrix(question);
			if (questionRecord.getListOfMatrix().size() > 0) questionNext.setMatrixIndex(0);
			else questionNext.setMatrixIndex(null);
			break;
		}
		questionNextRepository.save(questionNext);
		System.out.println("--- Question deleted!");
		return new QuestionEmpty("--- Question deleted!");
	}
	/**
	 * Return question based of the index record.
	 * @return IQuestion
	 */
	private IQuestion getQuestion() { 
		switch (questionRecord.getNextQuestionType()) {
		case "Trivia":
			return questionRecord.getTriviaByIndex(questionRecord.getQuestionNext().getTriviaIndex());
		case "Poll":
			return questionRecord.getPollByIndex(questionRecord.getQuestionNext().getPollIndex());
		case "Checkbox":
			return questionRecord.getCheckboxByIndex(questionRecord.getQuestionNext().getCheckboxIndex());
		default:
			return questionRecord.getMatrixByIndex(questionRecord.getQuestionNext().getMatrixIndex());
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
		questionNext = questionRecord.getQuestionNext();
		switch (newQuestionType) {
		case "Trivia":
			index = (questionNext.getTriviaIndex() + 1) % questionRecord.getListOfTrivia().size();
			questionNext.setTriviaIndex(index);
			break;
		case "Poll":
			index = (questionNext.getPollIndex() + 1) % questionRecord.getListOfPoll().size();
			questionNext.setPollIndex(index);
			break;
		case "Checkbox":
			index = (questionNext.getCheckboxIndex() + 1) % questionRecord.getListOfCheckbox().size();
			questionNext.setCheckboxIndex(index);
			break;
		default:
			index = (questionNext.getMatrixIndex() + 1) % questionRecord.getListOfMatrix().size();
			questionNext.setMatrixIndex(index);
			break;
		}
		questionNextRepository.save(questionNext);
	}
	
}
