import io.*;
public class EquationSolverMain
{
  public static void main(String [] args)
  {
    EquationSolver equationsolver = new EquationSolver();
    String equation = ConsoleInput.readLine("Enter Equation (no spaces)");
    equationsolver.solve(equation);
  }
}
