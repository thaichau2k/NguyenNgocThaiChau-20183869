package common.exception;;

public class InvalidCardException extends PaymentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCardException() {
		super("ERROR: Invalid card!");
	}
}
