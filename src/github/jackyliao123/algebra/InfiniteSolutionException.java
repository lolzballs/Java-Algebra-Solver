package github.jackyliao123.algebra;

/**
 * Thrown when infinite solutions are found
 * @author jackyliao123
 */
public class InfiniteSolutionException extends ArithmeticException{
	private static final long serialVersionUID = 3207127429913063778L;
	public InfiniteSolutionException(){
		super("Infinite Solutions Found!");
	}
}