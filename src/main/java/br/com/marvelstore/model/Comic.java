package br.com.marvelstore.model;

import br.com.marvelstore.integracao.models.Comics;
import br.com.marvelstore.integracao.models.Creator;
import br.com.marvelstore.integracao.models.Price;
import br.com.marvelstore.integracao.models.feignResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comic {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long comicId;
	private String title;
	private String isbn;
	private Boolean validPromo = false;
	@Length(max = 1000)
	private String description;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Criador> criadores = new ArrayList<>();
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Preco> precos = new ArrayList<>(); ;
	
	public Comic(ResponseEntity<feignResponse> c) {
		var in = c.getBody().getData().getResults().get(0);
		this.comicId = in.getId();
		this.title = in.getTitle();
		this.isbn = in.getIsbn();
		this.description = in.getDescription();
		
		for(Creator x: in.getCreators().getItems()) {
			Criador criador = new Criador(x);
			this.criadores.add(criador);	

		}
		
		for(Price p: in.getPrices()) {
			Preco preco = new Preco(p);
			this.precos.add(preco);
		}
	}

	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getDescription() {
		return description;
	}
	public List<Preco> getPrecos() {
		return precos;
	}
	public List<Criador> getCriadores() {
		return criadores;
	}
	public Long getComicId() {
		return comicId;
	}
	
	public Comic(Long comicId, String title, String isbn, String description, List<Criador> criadores,
			List<Preco> precos) {
		this.comicId = comicId;
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.criadores = criadores;
		this.precos = precos;
	}
	public Comic(Comics in) {
		this.comicId = in.getId();
		this.title = in.getTitle();
		this.isbn = in.getIsbn();
		this.description = in.getDescription();

		for(Creator c: in.getCreators().getItems()) {
			Criador criador = new Criador(c);
			this.criadores.add(criador);
		}
		
		for(Price p: in.getPrices()) {
			Preco preco = new Preco(p);
			this.precos.add(preco);
		}

	}
	public Comic() {
	}

	public void setDesconto(Boolean bol) {
		this.validPromo = bol;
	}

	public Boolean getValidPromo() {
		return validPromo;
	}
	
}
