package cube;

public interface Brick {

	public static Color getNextColor(Color color) {
		try {
			return Color.values()[color.ordinal() + 1];
		} catch (Exception e) {
			return Color.values()[0];
		}
	}

	public Color getColor(int colorNum);

	public Color changeColor(int colorNum, Color color);

	public enum Color {
		WHITE, BLUE, RED, GREEN, ORANGE, YELLOW
	}
}
