package instigator.rest_api.QA.question;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionNext {

	@Id
	private Integer id;
	private Integer trivia;
	private Integer poll;
	private Integer checkbox;
	private Integer matrix;
	
	public QuestionNext() {
		id = 1;
		trivia = 0;
		poll = 0;
		checkbox = 0;
		matrix = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTrivia() {
		return trivia;
	}

	public void setTrivia(Integer trivia) {
		this.trivia = trivia;
	}

	public Integer getPoll() {
		return poll;
	}

	public void setPoll(Integer poll) {
		this.poll = poll;
	}

	public Integer getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Integer checkbox) {
		this.checkbox = checkbox;
	}

	public Integer getMatrix() {
		return matrix;
	}

	public void setMatrix(Integer matrix) {
		this.matrix = matrix;
	}
	
}
