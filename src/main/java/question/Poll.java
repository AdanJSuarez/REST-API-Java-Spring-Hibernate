package question;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Poll implements Question {
	
	@Id
	private Integer id;
	private String questionType = "Poll";
	private String question;
	@OneToMany
	private List<String> answerOffered;
	private String answerReturned;
	@OneToMany
	private List<String> recordAnswerReturned; 
	
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(List<String> answerOffered) {
		this.answerOffered = answerOffered;
	}
	public String getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(String answerReturned) {
		this.answerReturned = answerReturned;
	}
	public List<String> getRecordAnswerReturned() {
		return recordAnswerReturned;
	}
	public void addRecordAnswerReturned(String recordAnswerReturned) {
		this.recordAnswerReturned.add(recordAnswerReturned);
	}
	
}
