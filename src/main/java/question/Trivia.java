package question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Trivia implements Question {
    
	@Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String question;
    private Map<String,Boolean> answerOffered;
    private List<String>answerReturned; 
    
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
	public Map<String, Boolean> getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(HashMap<String, Boolean> answerOffered) {
		this.answerOffered = answerOffered;
	}
	public void setAnswerReturned(String answer) {
		answerReturned.add(answer);
	}
    
    
}