package common.exception;;

public class InternalServerErrorException extends PaymentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
		super("ERROR: Internal Server Error!");
	}

}
