package br.com.marvelstore.integracao.models;

import javax.persistence.Column;
import java.util.ArrayList;

public class Comics {

	private Long id;
	private String title;
	private String isbn;
	@Column(length = 1000)
	private String description;
	private ArrayList<Price> prices ;
	private Creators creators;
	
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
	public ArrayList<Price> getPrices() {
		return prices;
	}
	public Creators getCreators() {
		return creators;
	}

}



