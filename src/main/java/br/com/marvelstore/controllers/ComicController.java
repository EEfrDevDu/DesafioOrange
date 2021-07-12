package br.com.marvelstore.controllers;

import br.com.marvelstore.integracao.feign.ComicsFeign;
import br.com.marvelstore.integracao.models.feignResponse;
import br.com.marvelstore.model.Comic;
import br.com.marvelstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/comics")
public class ComicController {

	private String privateKey = "3e7ed8449420d4641bf975831bec49c831c607b2";
	private String apiKey = "a0339a4666b53ab3a1eed65eb5848aca";
	private String ts = "1625975267";
	private String hash = "b1236ac53f6b1adbbc89be068e7caf27";
	
	@Autowired
	private ComicsFeign comicFeign;
	
	@PersistenceContext
	private EntityManager em;
	

	@PostMapping("{comicId}/user/{userId}")
	@Transactional
	public ResponseEntity<?> cadastraComicDoCliente(@PathVariable Long comicId,@PathVariable Long userId, UriComponentsBuilder uriBuilder){

		ResponseEntity<feignResponse>	comicResponse = comicFeign.getComics(comicId, ts, apiKey , hash);

		Comic comic = new Comic(comicResponse);
		User user = em.find(User.class,userId);
		
		user.setComic(comic);
		em.persist(user);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

}
