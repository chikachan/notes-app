package tabitabi.picco.persistence.repository;


import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import tabitabi.picco.model.Note2;

public interface NotesRepository extends CrudRepository<Note2, Long> {
  
	Set<Note2>findByAccountId(long accountId);
  
}