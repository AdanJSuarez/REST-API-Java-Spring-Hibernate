package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class QuestionRecord { 
	
	@Id
	private String uuid;
	@OneToMany
	private List<Question> listOfTrivia;
	@OneToMany
	private List<Question> listOfPoll;
	@OneToMany
	private List<Question> listOfCheckbox;
	@OneToMany
	private List<Question> listOfMatrix;
	@OneToOne
	private QuestionNext questionNext;
	private String nextQuestionType;
	
	public QuestionRecord() {
		uuid = "aaa";
		listOfTrivia = new ArrayList<>();
		listOfPoll = new ArrayList<>();
		listOfCheckbox = new ArrayList<>();
		listOfMatrix = new ArrayList<>();
		questionNext = new QuestionNext();
	}
	
	public QuestionRecord(String uuid) {
		this.uuid = uuid;
		listOfTrivia = new ArrayList<>();
		listOfPoll = new ArrayList<>();
		listOfCheckbox = new ArrayList<>();
		listOfMatrix = new ArrayList<>();
		questionNext = new QuestionNext();
		System.out.println("--- QuestionRecord initialized");
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Question getTriviaByIndex(int index) {
		return listOfTrivia.get(index);
	}
	/**
	 * Add question Trivia to list of trivia and set nextQuestionType to Trivia.
	 * @param trivia
	 */
	public void addTrivia(Question trivia) {
		this.listOfTrivia.add(trivia);
		nextQuestionType = "Trivia";
	}
	public Question getPollByIndex(int index) {
		return listOfPoll.get(index);
	}
	/**
	 * Add question Poll to list of poll and set nextQuestionType to Poll.
	 * @param poll
	 */
	public void addPoll(Question poll) {
		this.listOfPoll.add(poll);
		nextQuestionType = "Poll";
	}
	public Question getCheckboxByIndex(int index) {
		return listOfCheckbox.get(index);
	}
	/**
	 * Add question Checkbox to list of checkbox and set nextQuestionType to Checkbox.
	 * @param checkbox
	 */
	public void addCheckbox(Question checkbox) {
		this.listOfCheckbox.add(checkbox);
		nextQuestionType = "Checkbox";
	}
	public Question getMatrixByIndex(int index) {
		return listOfMatrix.get(index);
	}
	/**
	 * Add question Matrix to list of matrix and set nextQuestionType to Matrix.
	 * @param matrix
	 */
	public void addMatrix(Question matrix) {
		this.listOfMatrix.add(matrix);
		nextQuestionType = "Matrix";
	}
	
	/**
	 * Return list of index where:
	 * [0] is for trivia, [1] for poll, [2] for checkbox and [3] for matrix
	 * @return List<Integer>
	 */
	public QuestionNext getQuestionNext() {
		return questionNext;
	}
	public void setQuestionNext(QuestionNext questionNext) {
		this.questionNext = questionNext;
	}

	public String getNextQuestionType() {
		return nextQuestionType;
	}
	public void setNextQuestionType(String typeQuestion) {
		this.nextQuestionType = typeQuestion;
	}
	public List<Question> getListOfTrivia() {
		return listOfTrivia;
	}
	public List<Question> getListOfPoll() {
		return listOfPoll;
	}
	public List<Question> getListOfCheckbox() {
		return listOfCheckbox;
	}
	public List<Question> getListOfMatrix() {
		return listOfMatrix;
	}
	public void removeTrivia(Question question) {
		listOfTrivia.remove(question);		
	}
	public void removePoll(Question question) {
		listOfPoll.remove(question);
	}
	public void removeCheckbox(Question question) {
		listOfCheckbox.remove(question);
	}
	public void removeMatrix(Question question) {
		listOfMatrix.remove(question);
	}

	@Override
	public String toString() {
		return "QuestionRecord [uuid=" + uuid + ", listOfTrivia=" + listOfTrivia + ", listOfPoll=" + listOfPoll
				+ ", listOfCheckbox=" + listOfCheckbox + ", listOfMatrix=" + listOfMatrix + ", questionNext="
				+ questionNext + ", nextQuestionType=" + nextQuestionType + "]";
	}
	
	
}
