package cn.nubia.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.nubia.demo.model.Book;

@RestController("/api")
public class ApiController {

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public Book get() {

		return null; // book;
	}

	@RequestMapping(value = "/book.html", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getBookHtml() {
		// Test HTML view
		return "example";
	}
	
	/*
	
	@RequestMapping("/jsonfeed")
	public @ResponseBody Object getJSON(Model model) {
		List<TournamentContent> tournamentList = new ArrayList<TournamentContent>();
		tournamentList.add(TournamentContent.generateContent("FIFA", new Date(), "World Cup", "www.fifa.com/worldcup/"));
		tournamentList.add(TournamentContent.generateContent("FIFA", new Date(), "U-20 World Cup", "www.fifa.com/u20worldcup/"));
		tournamentList.add(TournamentContent.generateContent("FIFA", new Date(), "U-17 World Cup", "www.fifa.com/u17worldcup/"));
		tournamentList.add(TournamentContent.generateContent("中超", new Date(), "中超", "www.fifa.com/confederationscup/"));
		model.addAttribute("items", tournamentList);
		model.addAttribute("status", 0);
		return model;
	}
	
	*/
}
