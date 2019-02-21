package question;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Matrix implements Question {

	@Id
	private Integer id;
	private String questionType = "Matrix";
	private String question;
	@OneToMany
	private Map<String, List<String>> answerOffered;
	private Map<String, String>answerReturned;
	@OneToMany
	private List<Map<String, String>> recordAnswerReturned;
	
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
	public Map<String, List<String>> getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(Map<String, List<String>> answerOffered) {
		this.answerOffered = answerOffered;
	}
	public Map<String, String> getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(Map<String, String> answerReturned) {
		this.answerReturned = answerReturned;
	}
	public List<Map<String, String>> getRecordAnswerReturned() {
		return recordAnswerReturned;
	}
	public void addAnswerReturned(Map<String, String> answer) {
		recordAnswerReturned.add(answer);
	}
	
}
