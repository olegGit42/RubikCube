package cube;

@SuppressWarnings("serial")
public class WriteCubeException extends Exception {

	public WriteCubeException() {
		super("Cannot write this cube");
	}

}
