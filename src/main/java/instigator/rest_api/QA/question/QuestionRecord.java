package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@ElementCollection
	private List<Integer> listOfIndex;
	private String nextQuestionType;
	
	public QuestionRecord() {
		uuid = "aaa";
		listOfTrivia = new ArrayList<>();
		listOfPoll = new ArrayList<>();
		listOfCheckbox = new ArrayList<>();
		listOfMatrix = new ArrayList<>();
		listOfIndex = new ArrayList<>(Arrays.asList(0, 0, 0, 0)); 
	}
	
	public QuestionRecord(String uuid) {
		this.uuid = uuid;
		listOfTrivia = new ArrayList<>();
		listOfPoll = new ArrayList<>();
		listOfCheckbox = new ArrayList<>();
		listOfMatrix = new ArrayList<>();
		listOfIndex = new ArrayList<>(Arrays.asList(0, 0, 0, 0)); 
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
	public void addTrivia(Question trivia) {
		this.listOfTrivia.add(trivia);
		nextQuestionType = "Trivia";
	}
	public Question getPollByIndex(int index) {
		return listOfPoll.get(index);
	}
	public void addPoll(Question poll) {
		this.listOfPoll.add(poll);
		nextQuestionType = "Poll";
	}
	public Question getCheckboxByIndex(int index) {
		return listOfCheckbox.get(index);
	}
	public void addCheckbox(Question checkbox) {
		this.listOfCheckbox.add(checkbox);
		nextQuestionType = "Checkbox";
	}
	public Question getMatrixByIndex(int index) {
		return listOfMatrix.get(index);
	}
	public void addMatrix(Question matrix) {
		this.listOfMatrix.add(matrix);
		nextQuestionType = "Matrix";
	}
	
	/**
	 * Return list of index where:
	 * [0] is for trivia, [1] for poll, [2] for checkbox and [3] for matrix
	 * @return List<Integer>
	 */
	public List<Integer> getListOfIndex() {
		return listOfIndex;
	}
	public void setNextQuestionIndex(int ix, Integer index) {
		this.listOfIndex.set(ix, index);
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

	@Override
	public String toString() {
		return "QuestionRecord [uuid=" + uuid + ", listOfTrivia=" + listOfTrivia + ", listOfPoll=" + listOfPoll
				+ ", listOfCheckbox=" + listOfCheckbox + ", listOfMatrix=" + listOfMatrix + ", listOfIndex="
				+ listOfIndex + ", nextQuestionType=" + nextQuestionType + "]";
	}
	
	
}
