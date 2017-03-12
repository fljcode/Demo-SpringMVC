package fan.createxml;

public class AnalysisException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnalysisException() {
	}

	public AnalysisException(String message) {
		super(message);
	}

	public AnalysisException(Throwable cause) {
		super(cause);
	}

	public AnalysisException(String message, Throwable cause) {
		super(message, cause);
	}


}
