package br.com.marvelstore.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty @NotNull
    @Size(min=3)
    private String nome;

    @NotEmpty @NotNull
    @Size(min=3, max=50)
    @Email
    private String email;

    @CPF
    @NotEmpty @NotNull
    @Size(min=3, max=50)
    private String cpf;

    private LocalDate dataNascimento;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Comic> comics;
    
    public User(String nome, String email, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }


    public Long getId() {
		return id;
	}


	public List<Comic> getComics() {
		return comics;
	}


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Deprecated
    public User() {
    }
    

	public void setComic(Comic comic) {
			this.comics.add(comic);
	}
}