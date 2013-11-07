package tabitabi.picco.service;

import org.springframework.stereotype.Service;

import tabitabi.picco.model.Note;

@Service
public class NoteJPADataService extends AbstractJPADataService<Note, Long>
		implements NoteDataService {

}
