package instigator.rest_api.QA.answer;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ElementCollection
	private Map<String, String> answer;
	
	public Answer() {
		answer = new HashMap<>();
	}
	public Answer(Map<String, String> answer) {
		this.answer = answer;
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Map<String, String> getAnswer() {
		return answer;
	}
	public void setAnswer(Map<String, String> answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + "]";
	}
	
	
	
}
