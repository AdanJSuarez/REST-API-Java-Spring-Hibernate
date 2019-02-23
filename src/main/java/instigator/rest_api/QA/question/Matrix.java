package instigator.rest_api.QA.question;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import instigator.rest_api.QA.answer.Answer;

@Entity
public class Matrix implements Question {

	@Id
	private Integer id;
	private String questionType = "Matrix";
	private String question;
	@OneToOne
	private Answer answerOffered;
	@OneToOne
	private Answer answerReturned;
	@OneToMany
	private List<Answer> recordAnswerReturned;
	
	public Matrix() {
		id = 1;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Answer getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(Answer answerOffered) {
		this.answerOffered = answerOffered;
	}
	public Answer getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(Answer answerReturned) {
		this.answerReturned = answerReturned;
	}
	public List<Answer> getRecordAnswerReturned() {
		return recordAnswerReturned;
	}
	public void addAnswerReturned(Answer answer) {
		recordAnswerReturned.add(answer);
	}
	
}
