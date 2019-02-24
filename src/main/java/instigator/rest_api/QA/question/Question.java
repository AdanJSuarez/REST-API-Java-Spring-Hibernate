package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import instigator.rest_api.QA.answer.Answer;

@Entity
public class Question implements IQuestion {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String questionType;
    private String question;
    @OneToOne
    private Answer answerOffered;
    @OneToOne
    private Answer answerReturned;
    @ManyToMany
    private List<Answer> recordAnswerReturned;
	
	public Question() {
		answerOffered = null;
		answerReturned = null;
		recordAnswerReturned = new ArrayList<>();
	}
    
    public Question(String questionType, String question) {
		this.questionType = questionType;
    	this.question = question;
		answerOffered = null;
		answerReturned = null;
		recordAnswerReturned = new ArrayList<>();
	}
    
    //Getters and Setters
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getQuestionType() {
		return questionType;
	}
	@Override
	public Answer getAnswerReturned() {
		return answerReturned;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Answer getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(Answer answerOffered) {
		this.answerOffered = answerOffered;
	}
	public void setAnswerReturned(Answer answerReturned) {
		this.answerReturned = answerReturned;
	}
	public List<Answer> getRecordAnswerReturned() {
		return recordAnswerReturned;
	}
	
	/**
	 * Add returned answer to list of answer.
	 * @param answer
	 */
	public void addRecordAnswerReturned(Answer answer) {
		recordAnswerReturned.add(answer);
	}
	@Override
	public void setRecordAnswerReturned(List<Answer> recordAnswerReturned) {
		this.recordAnswerReturned = recordAnswerReturned;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionType=" + questionType + ", question=" + question + ", answerOffered="
				+ answerOffered + ", answerReturned=" + answerReturned + ", recordAnswerReturned="
				+ recordAnswerReturned + "]";
	}
	
    
}