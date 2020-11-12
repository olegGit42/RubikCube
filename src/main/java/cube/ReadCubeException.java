package cube;

@SuppressWarnings("serial")
public class ReadCubeException extends Exception {

	public ReadCubeException() {
		super("Cannot read this cube");
	}

}
