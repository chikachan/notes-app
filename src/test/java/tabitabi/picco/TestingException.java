package tabitabi.picco;

public class TestingException extends RuntimeException {

	private static final long serialVersionUID = 7753231750829210685L;

	public TestingException() {

	}

	public TestingException(Throwable thr) {
		super(thr);
	}

	public TestingException(String message) {
		super(message);
	}

}
