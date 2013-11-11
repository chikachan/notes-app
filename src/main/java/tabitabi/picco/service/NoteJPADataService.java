package tabitabi.picco.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import tabitabi.picco.model.Note;

@Service
public class NoteJPADataService extends AbstractJPADataService<Note, Long>
		implements NoteDataService {

	@Override
	public Collection<Note> findAll(){
		return em.createQuery("Select note from Note note", Note.class).getResultList();
	}
	
}
