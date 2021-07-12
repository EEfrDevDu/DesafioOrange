package br.com.marvelstore.controllers;

import br.com.marvelstore.model.User;
import br.com.marvelstore.model.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/user")
public class ClienteController {

	@PersistenceContext
	private EntityManager entityManager;

	@PostMapping
	@Transactional
	public ResponseEntity<?> postClient(@Valid @RequestBody UserRequest clienteRequest, UriComponentsBuilder uriBuilder) {

		User user = clienteRequest.map();
		entityManager.persist(user);
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@GetMapping("{id}/desconto")
	@Transactional
	public ResponseEntity<?> listaClientePromo(@PathVariable Long id) {

		User user = entityManager.find(User.class, id);

		habilitaDesconto(user);

		aplicaDesconto(user);


		entityManager.persist(user);

		return ResponseEntity.ok(user);
	}

	private void habilitaDesconto(User user) {
		for (int i = 0; i < user.getComics().size(); i++) {

			String isbn = user.getComics().get(i).getIsbn();
			Boolean bol = validaPromocao(isbn);
			user.getComics().get(i).setDesconto(bol);
		}
	}

	private void aplicaDesconto(User user) {
		for (int i = 0; i < user.getComics().size(); i++) {
			if (user.getComics().get(i).getValidPromo() == true) {
				user.getComics().get(i).getPrecos().get(0).setPreco();
			}
		}
	}

	public char lastChar(String isbn) {
		char fimIsbn = isbn.charAt(isbn.length() - 1);
		if(fimIsbn == 1) {
			System.out.println("FIm do isbn é 1");
		}
		return fimIsbn;

	}

	public Boolean validaPromocao(String isbn) {
		/*String data = LocalDate.now().getDayOfWeek().toString();

		String data = "MONDAY";
		switch (data) {
			case "MONDAY":
				if(c == 0 || c==1) {
					return true;
				}
				return false;
			case "TUESDAY":
				if(c == 2 || c == 3) {
					return true;
				}
				return false;
			case "WEDNESDAY":
				if(c == 4 || c == 5) {
					return true;
				}
				return false;
			case "THURSDAY":
				if(c == 6 || c == 7) {
					return true;
				}
				return false;
			case "FRIDAY":
				if(c == 8 || c == 9) {
					return true;
				}
				return false;
		}*/
		return false;
	}
}