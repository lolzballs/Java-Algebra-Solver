package github.jackyliao123.algebra;

/**
 * Used for exception that occurred when parsing the equation
 * @author jackyliao123
 */
public class ParserException extends RuntimeException{
	private static final long serialVersionUID = -6707418866327691431L;
	private RuntimeException ex;
	private int line;
	/**
	 * Exception happened when parsing the equation
	 * @param ex exception occurred
	 * @param line the line that the exception occurred
	 */
	public ParserException(RuntimeException ex, int line){
		super(ex.toString() + " occurred at parsing line " + (line + 1));
		this.ex = ex;
		this.line = line;
	}
	/**
	 * Gets the exception occurred
	 * @return the exception occurred
	 */
	public RuntimeException getException(){
		return ex;
	}
	/**
	 * Gets the line that the exception occurred
	 * @return the line that the exception occurred
	 */
	public int getLineOccurred(){
		return line;
	}
}