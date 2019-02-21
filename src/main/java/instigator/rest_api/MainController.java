package instigator.rest_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import question.Question;
import question.QuestionService;

@Controller    // This means that this class is a Controller
public class MainController {
	
	@Autowired
	private QuestionService questionService;

	@RequestMapping(method=RequestMethod.GET, value="/UUID/{uuid}")
	public Question getQuestion (@PathVariable("uuid") String uuid) {
		Question question = questionService.getNextQuestion(uuid);
		return  question;
	}

	@RequestMapping(method=RequestMethod.POST, value="/UUID/{uuid}") //Map only POST requests
	public void setAnswerRecord(@PathVariable("uuid") String uuid, @RequestBody Question questionReturned) {
		questionService.updateAnswerRecord(uuid, questionReturned);
	}
	
}