package question;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trivia implements Question {
    
	@Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String questionType = "Trivia";
    private String question;
    @OneToMany
    private Map<String,Boolean> answerOffered;
    private Boolean answerReturned; 
	@OneToMany
    private List<Boolean> recordAnswerReturned;
    
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Map<String, Boolean> getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(Map<String, Boolean> answerOffered) {
		this.answerOffered = answerOffered;
	}
	public Boolean getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(Boolean answerReturned) {
		this.answerReturned = answerReturned;
	}
	public void addRecordAnswerReturned(Boolean answer) {
		recordAnswerReturned.add(answer);
	}
	public List<Boolean> getRecordAnswerReturned() {
		return recordAnswerReturned;
	}
    
}