package github.jackyliao123.algebra;

/**
 * Thrown when no solution is found
 * @author jackyliao123
 */
public class NoSolutionException extends ArithmeticException{
	private static final long serialVersionUID = 3081060131961709034L;
	public NoSolutionException(){
		super("No Solution Found!");
	}
}