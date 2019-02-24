/**
 * 
 */
package instigator.rest_api.QA.question;

import java.util.List;

import instigator.rest_api.QA.answer.Answer;

public interface IQuestion {

	Integer getId();

	String getQuestionType();
	
	Answer getAnswerReturned();

	void setRecordAnswerReturned(List<Answer> recordAnswerReturned);
	
}
