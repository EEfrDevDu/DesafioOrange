package br.com.marvelstore.model;

import br.com.marvelstore.integracao.models.Creator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Criador {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String role;
	
	public Criador(Creator c) {
		this.name = c.getName();
		this.role = c.getRole();
	}

	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}

	@Deprecated
	public Criador() {

	}
	
	
}
