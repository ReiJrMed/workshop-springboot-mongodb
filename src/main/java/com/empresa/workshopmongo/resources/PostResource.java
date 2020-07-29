package com.empresa.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.workshopmongo.domain.Post;
import com.empresa.workshopmongo.resources.util.URL;
import com.empresa.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;	
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		//@RequestParam indica que será passado um valor por parâmetro
		//tipo isso: localhost:8080/titlesearch?text=Bom%20dia sendo o "bom dia" o valor passado e o "?" indica que será usado parâmetro
		//depois do "?" usa-se o nome do parâmetro
		
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		
		//nesse caso a busca é assim no Postman: localhost:8080/posts/fullsearch?text=aproveite&minDate=2018-03-22&maxDate=2018-03-30
		//nesse caso você pode indicar ou deixar de indicar qualquer data já que as datas tem defaultValue
		//localhost:8080/posts/fullsearch?text=aproveite
		
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));//new Date(0L) pega a data inicial do Date que é da década de 70
		Date max = URL.convertDate(maxDate, new Date());// new Date() pega a data atual do sistema
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
