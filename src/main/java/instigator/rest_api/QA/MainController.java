package instigator.rest_api.QA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import instigator.rest_api.QA.question.IQuestion;
import instigator.rest_api.QA.question.NewQuestion;
import instigator.rest_api.QA.question.Question;
import instigator.rest_api.QA.question.QuestionService;


@RestController 
public class MainController {
	
	@Autowired
	private QuestionService questionService;

	/**
	 * REST. Return string to test that server is running.
	 * @return String
	 */
	@RequestMapping(method=RequestMethod.GET, value="/test")
	public @ResponseBody String response () {
		System.out.println("--- test");
		return "--- Done!!!";
	}
	
	/**
	 * REST. Return the next question in sequential order. Start again went hit the end
	 * of the list of questions. 
	 * @param uuid
	 * @return IQuestion
	 */
	@RequestMapping(method=RequestMethod.GET, value="/UUID/{uuid}")
	public IQuestion getQuestion (@PathVariable("uuid") String uuid) {
		System.out.print("--- Requested question of UUID: ");
		System.out.println(uuid);
		IQuestion question = questionService.getNextQuestion(uuid);
		System.out.println(question);
		question.setRecordAnswerReturned(null);
		return question;
	}

	/**
	 * REST. Save the answer given by the embed in the database.
	 * @param uuid
	 * @param questionReturned
	 */
	@RequestMapping(method=RequestMethod.POST, value="/UUID/{uuid}")
	public void setAnswerRecord(@PathVariable("uuid") String uuid, @RequestBody Question questionReturned) {
		questionService.updateAnswerRecord(questionReturned);
	}
	
	/**
	 * REST. Save the new question in the database. REST interface for the db.
	 * @param uuid
	 * @param newQuestion
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/newQuestion/UUID/{uuid}")
	public @ResponseBody String setNewQuestion(@PathVariable("uuid") String uuid, @RequestBody NewQuestion newQuestion) {
		return questionService.setNewQuestion(uuid, newQuestion);
	}
	
	/**
	 * REST. Delete the question given by id from the database. REST interface for the db.
	 * @param uuid
	 * @param questionId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/deleteQuestion/{questionId}/UUID/{uuid}")
	public IQuestion deleteQuestion(@PathVariable("uuid") String uuid, @PathVariable("questionId") String questionId) {
		return questionService.deleteQuestion(uuid, questionId);
	}
	
}