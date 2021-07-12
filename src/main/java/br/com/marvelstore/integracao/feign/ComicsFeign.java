package br.com.marvelstore.integracao.feign;


import br.com.marvelstore.integracao.models.feignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comicsMarvel", url="https://gateway.marvel.com/v1/public/")
public interface ComicsFeign {

	@GetMapping("comics/{comicId}?ts={ts}&apikey={apikey}&hash={hash}")
	ResponseEntity<feignResponse> getComics(@PathVariable("comicId") Long comicId,
											@PathVariable("ts") String ts,
											@PathVariable("apikey") String apiKey,
											@PathVariable("hash") String hash);

}
