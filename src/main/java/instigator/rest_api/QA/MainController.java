package instigator.rest_api.QA;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import instigator.rest_api.QA.question.Question;
import instigator.rest_api.QA.question.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
public class MainController {
	
	@Autowired
	private QuestionService questionService;

	@RequestMapping(method=RequestMethod.GET, value="/test")
	public @ResponseBody String response () {
		System.out.println("--- No specific path");
		return "Done!!!";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/UUID/{uuid}")
	public Question getQuestion (@PathVariable("uuid") String uuid) {
		System.out.print("--- Requested question of UUID: ");
		System.out.println(uuid);
		
		Question question = questionService.getNextQuestion(uuid);
		System.out.print("--- Question returned: ");
		System.out.println(question);
		return  question;
	}

	@RequestMapping(method=RequestMethod.POST, value="/UUID/{uuid}") //Map only POST requests
	public void setAnswerRecord(@PathVariable("uuid") String uuid, @RequestBody Question questionReturned) {
		questionService.updateAnswerRecord(uuid, questionReturned);
	}
	
	
}