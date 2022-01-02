package common.exception;;

/**
 * The AimsException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class AimsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AimsException() {

	}

	public AimsException(String message) {
		super(message);
	}
}