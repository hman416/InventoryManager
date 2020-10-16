package inventoryman;

@SuppressWarnings("serial")
public class InvalidOrderException extends Exception{
	public InvalidOrderException(String exp) {
		super(exp);
	}
	
}
