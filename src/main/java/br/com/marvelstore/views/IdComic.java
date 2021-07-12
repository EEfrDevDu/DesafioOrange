package br.com.marvelstore.views;

import br.com.marvelstore.integracao.models.Comics;
import br.com.marvelstore.model.Comic;

import javax.validation.constraints.NotNull;

public class IdComic {
	@NotNull
	private Long idComic;

	public Long getIdComic() {
		return idComic;
	}

	public Comic map(Comics comics) {
		Comics comic = new Comics();
		return new Comic(comic);
	}
	
}
