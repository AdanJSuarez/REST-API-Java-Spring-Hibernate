package question;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionRecord implements Question {
	
	@Id
	private String uuid;
	private Integer nextQuestionID;
	private String nextTypeQuestion;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getNextQuestionId() {
		return nextQuestionID;
	}
	public void setNextQuestionID(Integer lastQuestionID) {
		this.nextQuestionID = lastQuestionID;
	}
	public String getNextTypeQuestion() {
		return nextTypeQuestion;
	}
	public void setNextQuestionType(String typeQuestion) {
		this.nextTypeQuestion = typeQuestion;
	}
	
}
