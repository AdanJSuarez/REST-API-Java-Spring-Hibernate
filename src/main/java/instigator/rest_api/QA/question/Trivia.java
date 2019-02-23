package instigator.rest_api.QA.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import instigator.rest_api.QA.answer.Answer;

@Entity
public class Trivia implements Question {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String questionType = "Trivia";
    private String question;
    @OneToOne
    private Answer answerOffered;
    @OneToOne
    private Answer answerReturned;
    @OneToMany
    private List<Answer> recordAnswerReturned;
	
	public Trivia() {
		question = "Lalala";
		answerOffered = new Answer();
		answerReturned = new Answer();
		recordAnswerReturned = new ArrayList<>();
	}
    
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
	public Answer getAnswerOffered() {
		return answerOffered;
	}
	public void setAnswerOffered(Answer answerOffered) {
		this.answerOffered = answerOffered;
	}
	public Answer getAnswerReturned() {
		return answerReturned;
	}
	public void setAnswerReturned(Answer answerReturned) {
		this.answerReturned = answerReturned;
	}
	public void addRecordAnswerReturned(Answer answer) {
		recordAnswerReturned.add(answer);
	}
	public List<Answer> getRecordAnswerReturned() {
		return recordAnswerReturned;
	}
    
}