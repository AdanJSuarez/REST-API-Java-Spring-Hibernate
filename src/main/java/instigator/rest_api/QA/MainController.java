package instigator.rest_api.QA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import instigator.rest_api.QA.answer.AnswerRepository;
import instigator.rest_api.QA.question.IQuestion;
import instigator.rest_api.QA.question.NewQuestion;
import instigator.rest_api.QA.question.Question;
import instigator.rest_api.QA.question.QuestionRecordRepository;
import instigator.rest_api.QA.question.QuestionRepository;
import instigator.rest_api.QA.question.QuestionService;


@RestController 
public class MainController {
	
	@Autowired
	private QuestionService questionService;

	@RequestMapping(method=RequestMethod.GET, value="/test")
	public @ResponseBody String response () {
		System.out.println("--- test");
		return "--- Done!!!";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/UUID/{uuid}")
	public IQuestion getQuestion (@PathVariable("uuid") String uuid) {
		System.out.print("--- Requested question of UUID: ");
		System.out.println(uuid);
		IQuestion question = questionService.getNextQuestion(uuid);
		System.out.println(question);
		question.setRecordAnswerReturned(null);
		return question;
	}

	@RequestMapping(method=RequestMethod.POST, value="/UUID/{uuid}")
	public void setAnswerRecord(@PathVariable("uuid") String uuid, @RequestBody Question questionReturned) {
		questionService.updateAnswerRecord(questionReturned);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/newQuestion/UUID/{uuid}")
	public @ResponseBody String setNewQuestion(@PathVariable("uuid") String uuid, @RequestBody NewQuestion newQuestion) {
		return questionService.setNewQuestion(uuid, newQuestion);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/deleteQuestion/{questionId}/UUID/{uuid}")
	public IQuestion deleteQuestion(@PathVariable("uuid") String uuid, @PathVariable("questionId") String questionId) {
		return questionService.deleteQuestion(uuid, questionId);
	}
	
}