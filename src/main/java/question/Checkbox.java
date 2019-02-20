package question;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Checkbox {

	@Id
	private Integer id;
	private String question;
	private List<String> answerOffered;
	private List<List<String>> answerReturned;
	
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
	public List<String> getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(List<String> answerOffered) {
		this.answerOffered = answerOffered;
	}
	public List<List<String>> getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(List<List<String>> answerReturned) {
		this.answerReturned = answerReturned;
	}
	
	
	
}
