package instigator.rest_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import question.Question;
import question.QuestionService;

@Controller    // This means that this class is a Controller
public class MainController {
	
//	@Autowired 
//	private QuestionRepository questionRepository;
	@Autowired
	private QuestionService questionService;

	@RequestMapping(method=RequestMethod.GET, value="/UUID/{id}")
	public Question getQuestion (@PathVariable("id") String id) {
		Question question = questionService.getNextQuestion(id);
		return  question;
	}

	@RequestMapping(method=RequestMethod.POST, value="/UUID/{id}") //Map only POST requests
	public void setAnswer(@RequestBody Question question) {
		//TODO: Complite this method and correct paramater
	}
//	@RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),String.format(template, name));
//    }
	
}