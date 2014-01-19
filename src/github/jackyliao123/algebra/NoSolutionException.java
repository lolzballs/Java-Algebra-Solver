package github.jackyliao123.algebra;

public class NoSolutionException extends ArithmeticException {

    private static final long serialVersionUID = 3081060131961709034L;

    public NoSolutionException() {
        super("No Solution Found!");
    }

}