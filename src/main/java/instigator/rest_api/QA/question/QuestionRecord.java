package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionRecord {
	
	@Id
	private String uuid;
	@OneToMany
	private List<Trivia> listOfTrivia;
	@OneToMany
	private List<Poll> listOfPoll;
	@OneToMany
	private List<Checkbox> listOfCheckbox;
	@OneToMany
	private List<Matrix> listOfMatrix;
	@ElementCollection
	private List<Integer> listOfIndex; 
	private String nextQuestionType;
	
	public QuestionRecord() {}
	
	public QuestionRecord(String uuid) {
		this.uuid = uuid;
		listOfTrivia = new ArrayList<Trivia>();
		listOfPoll = new ArrayList<>();
		listOfCheckbox = new ArrayList<>();
		listOfMatrix = new ArrayList<>();
		listOfIndex = new ArrayList<>(); 
		nextQuestionType = "Trivia";
		System.out.println("--- QuestionRecord initialized");
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Trivia getTriviaByIndex(int index) {
		return listOfTrivia.get(index);
	}
	public void addTrivia(Trivia trivia) {
		this.listOfTrivia.add(trivia);
	}
	public Poll getPollByIndex(int index) {
		return listOfPoll.get(index);
	}
	public void addPoll(Poll poll) {
		this.listOfPoll.add(poll);
	}
	public Checkbox getCheckboxByIndex(int index) {
		return listOfCheckbox.get(index);
	}
	public void addCheckbox(Checkbox checkbox) {
		this.listOfCheckbox.add(checkbox);
	}
	public Matrix getMatrixByIndex(int index) {
		return listOfMatrix.get(index);
	}
	public void addMatrix(Matrix matrix) {
		this.listOfMatrix.add(matrix);
	}
	/*
	 * Return list of index where:
	 *  [0] is for trivia, [1] for poll, [2] for checkbox and [3] for matrix
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
	public List<Trivia> getListOfTrivia() {
		return listOfTrivia;
	}
	public List<Poll> getListOfPoll() {
		return listOfPoll;
	}
	public List<Checkbox> getListOfCheckbox() {
		return listOfCheckbox;
	}
	public List<Matrix> getListOfMatrix() {
		return listOfMatrix;
	}
	
}
