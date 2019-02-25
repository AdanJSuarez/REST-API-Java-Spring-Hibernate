package instigator.rest_api.QA.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import instigator.rest_api.QA.answer.Answer;

public class NewQuestion implements IQuestion {
	
	private String questionType;
	private String question;
	private Map<String, String> answerOffered;
	
	public NewQuestion() {
		questionType = null;
		question = null;
		answerOffered = new HashMap<>();
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Map<String, String> getAnswerOffered() {
		return answerOffered;
	}

	public void setAnswerOffered(Map<String, String> answerOffered) {
		this.answerOffered = answerOffered;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Override
	public Integer getId() {
		return null;
	}

	@Override
	public String getQuestionType() {
		return questionType;
	}

	@Override
	public Answer getAnswerReturned() {
		return null;
	}

	@Override
	public void setRecordAnswerReturned(List<Answer> recordAnswerReturned) {
		// TODO Auto-generated method stub

	}

}
