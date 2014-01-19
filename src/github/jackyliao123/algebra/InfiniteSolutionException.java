package github.jackyliao123.algebra;

public class InfiniteSolutionException extends ArithmeticException {
    private static final long serialVersionUID = 3207127429913063778L;

    public InfiniteSolutionException() {
        super("Infinite Solutions Found!");
    }

}