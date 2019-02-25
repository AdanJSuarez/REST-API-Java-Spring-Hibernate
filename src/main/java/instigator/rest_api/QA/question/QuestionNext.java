package instigator.rest_api.QA.question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionNext {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer triviaIndex;
	private Integer pollIndex;
	private Integer checkboxIndex;
	private Integer matrixIndex;
	
	public QuestionNext() {
		triviaIndex = null;
		pollIndex = null;
		checkboxIndex = null;
		matrixIndex = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTriviaIndex() {
		return triviaIndex;
	}

	public void setTriviaIndex(Integer trivia) {
		this.triviaIndex = trivia;
	}

	public Integer getPollIndex() {
		return pollIndex;
	}

	public void setPollIndex(Integer poll) {
		this.pollIndex = poll;
	}

	public Integer getCheckboxIndex() {
		return checkboxIndex;
	}

	public void setCheckboxIndex(Integer checkbox) {
		this.checkboxIndex = checkbox;
	}

	public Integer getMatrixIndex() {
		return matrixIndex;
	}

	public void setMatrixIndex(Integer matrix) {
		this.matrixIndex = matrix;
	}
	public Integer getSizeQuestionNext() {
		Integer result = 0;
		if (triviaIndex != null) result++;
		if (pollIndex != null) result++;
		if (checkboxIndex != null) result++;
		if (matrixIndex != null) result++;
		return result;
	}

	@Override
	public String toString() {
		return "QuestionNext [id=" + id + ", triviaIndex=" + triviaIndex + ", pollIndex=" + pollIndex
				+ ", checkboxIndex=" + checkboxIndex + ", matrixIndex=" + matrixIndex + "]";
	}
	
	
}
