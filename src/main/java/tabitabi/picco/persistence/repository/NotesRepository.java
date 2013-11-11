package tabitabi.picco.persistence.repository;


import org.springframework.data.repository.CrudRepository;

import tabitabi.picco.model.Note2;

public interface NotesRepository extends CrudRepository<Note2, Long> {
  Note2 findById(Long key);
}