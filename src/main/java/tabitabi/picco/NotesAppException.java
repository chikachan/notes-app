package tabitabi.picco;

public class NotesAppException extends RuntimeException {

	private static final long serialVersionUID = -1058619077552899890L;
	
	
	public NotesAppException(){
		
	}
	
	public NotesAppException(final String msg){
		super(msg);
	}
	
	public NotesAppException(final Throwable throwable){
		super(throwable);
	}
	
	public NotesAppException(final String msg, final Throwable throwable){
		super(msg,throwable);
	}

}
