package question;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionRecord {
	
	@Id
	private String uuid;
	private Integer lastQuestionID;
	private String typeQuestion;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getLastQuestionID() {
		return lastQuestionID;
	}
	public void setLastQuestionID(Integer lastQuestionID) {
		this.lastQuestionID = lastQuestionID;
	}
	public String getTypeQuestion() {
		return typeQuestion;
	}
	public void setTypeQuestion(String typeQuestion) {
		this.typeQuestion = typeQuestion;
	}
	
}
