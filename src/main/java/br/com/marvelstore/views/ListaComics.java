package br.com.marvelstore.views;

import br.com.marvelstore.integracao.feign.ComicsFeign;
import br.com.marvelstore.integracao.models.Comics;
import br.com.marvelstore.integracao.models.feignResponse;
import br.com.marvelstore.model.Comic;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ListaComics {
	
	private List<Long> comicIds;

	public List<Long> getComicId() {
		return comicIds;
	}

	public List<Comic> map(EntityManager em, ComicsFeign comicFeign, String ts, String apiKey, String hash) {
		    List<Comic> listaComics = new ArrayList<>();
		    
		    if(comicIds == null) {
		    	return null;
		    }
			for (int i = 0; i < comicIds.size(); i++) {
				
			ResponseEntity<feignResponse>	c = comicFeign.getComics(comicIds.get(i), ts, apiKey , hash);
					for(Comics in: c.getBody().getData().getResults()) {
						Comic comic = new Comic(in);
						listaComics.add(comic);
					}
			}			
			return listaComics;
	}


	@Deprecated
	public ListaComics() {
	}


}
