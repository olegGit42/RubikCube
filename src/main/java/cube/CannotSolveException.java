package cube;

@SuppressWarnings("serial")
public class CannotSolveException extends Exception {

	public CannotSolveException() {
		super("Cannot solve this cube. Check that all the colors are correctly positioned");
	}

	public CannotSolveException(String message) {
		super("Cannot solve this cube. Check that all the colors are correctly positioned. " + message);
	}

}
