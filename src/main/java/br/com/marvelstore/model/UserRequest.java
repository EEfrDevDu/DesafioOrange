package br.com.marvelstore.model;

import br.com.marvelstore.validation.CampoUnico;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserRequest {
 @NotEmpty
 @NotNull
 @Size(min = 3)
 private String nome;

 @NotEmpty
 @NotNull
 @Size(min = 3, max = 50)
 @Email
 @CampoUnico(atributo = "email", classe = User.class)
 private String email;

 @CPF
 @NotEmpty
 @NotNull
 @Size(min = 3, max = 50)
 @CampoUnico(atributo = "cpf", classe = User.class)
 private String cpf;

 @NotNull
 @Valid
 private LocalDate dataNascimento;

 public User map() {
 return new User(nome, email, cpf, dataNascimento);
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
}