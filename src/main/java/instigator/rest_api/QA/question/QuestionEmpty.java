package instigator.rest_api.QA.question;

import java.util.List;

import instigator.rest_api.QA.answer.Answer;

public class QuestionEmpty implements IQuestion {
	
	private String questionNotFound;
	
	public QuestionEmpty(String uuid) {
		setQuestionNotFound("--- Not found UUID: " + uuid);
	}

	@Override
	public Integer getId() {
		return null;
	}

	@Override
	public String getQuestionType() {
		return null;
	}

	@Override
	public Answer getAnswerReturned() {
		return null;
	}

	public String getQuestionNotFound() {
		return questionNotFound;
	}

	public void setQuestionNotFound(String questionNotFound) {
		this.questionNotFound = questionNotFound;
	}

	@Override
	public void setRecordAnswerReturned(List<Answer> recordAnswerReturned) {
		// Do nothing
		
	}

}
