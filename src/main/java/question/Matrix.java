package question;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Matrix implements Question {

	@Id
	private Integer id;
	private String question;
	private Map<String, List<Boolean>> answerOffered;
	private Map<String, List<Boolean>> answerReturned;
	
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
	public Map<String, List<Boolean>> getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(Map<String, List<Boolean>> answerOffered) {
		this.answerOffered = answerOffered;
	}
	public Map<String, List<Boolean>> getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(Map<String, List<Boolean>> answerReturned) {
		this.answerReturned = answerReturned;
	} 
	
}
