package dominio.excepcion;

//Helps to manage the exceptions returning a message
public class GarantiaExtendidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public GarantiaExtendidaException(String message) {
		super(message);
	}
}
