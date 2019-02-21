package question;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionRecord implements Question {
	
	@Id
	private String uuid;
	private Integer nextQuestionId;
	private String nextQuestionType;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getNextQuestionId() {
		return nextQuestionId;
	}
	public void setNextQuestionId(Integer lastQuestionId) {
		this.nextQuestionId = lastQuestionId;
	}
	public String getNextQuestionType() {
		return nextQuestionType;
	}
	public void setNextQuestionType(String typeQuestion) {
		this.nextQuestionType = typeQuestion;
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getQuestionType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
