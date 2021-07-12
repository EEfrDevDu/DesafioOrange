package br.com.marvelstore.controllers;

import br.com.marvelstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository  extends JpaRepository<User,Long>{

}
