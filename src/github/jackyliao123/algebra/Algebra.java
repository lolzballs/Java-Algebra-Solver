package github.jackyliao123.algebra;

/**
 * Used for solving system of linear equations
 * @author jackyliao123
 */
public class Algebra{
	private Algebra(){
	}
	/**
	 * Solves the given system of linear equations
	 * @param unknowns the number of unknown characters in the system of equations
	 * @param unknownc all the unknown characters in the equations
	 * @param lines every equation in the system of equations
	 * @return the key to the equations
	 * @throws ParserException when the equations cannot be parsed
	 * @throws InfiniteSolutionException when there are infinite solutions to these equations
	 * @throws NoSolutionException when there is no solution found for these equations
	 */
	public static Rational[] solve(int unknowns, char[] unknownc, String[] lines){
			Rational[][] matrix = new Rational[unknowns][unknowns + 1];
			for(int i = 0; i < unknowns; i ++){
				try{
					String[] equations = lines[i].split("=");
					Evaluate e = new Evaluate(equations[0], unknownc);
					Num num1 = e.evaluateExpression();
					e = new Evaluate(equations[1], unknownc);
					Num num2 = e.evaluateExpression();
					for(int j = 0; j < unknowns; j ++){
						matrix[i][j] = num1.unknown[j].subtract(num2.unknown[j]);
					}
					matrix[i][unknowns] = num2.number.subtract(num1.number);
				}
				catch(RuntimeException e){
					throw new ParserException(e, i);
				}
			}
			Matrix m = new Matrix(matrix, unknowns);
			return m.solve();
	}
}