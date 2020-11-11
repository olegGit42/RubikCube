package cube;

import lombok.Data;

@Data
public class BrickSide implements Brick {
	private Color color1;
	private Color color2;

	public BrickSide() {
	}

	public BrickSide(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public Color getColor(int colorNum) {
		switch (colorNum) {
		case 1:
			return color1;
		case 2:
			return color2;
		default:
			return null;
		}
	}

	@Override
	public Color changeColor(int colorNum, Color color) {
		switch (colorNum) {
		case 1:
			if (color == null) {
				color1 = Brick.getNextColor(color1);
			} else {
				color1 = color;
			}
			return color1;
		case 2:
			if (color == null) {
				color2 = Brick.getNextColor(color2);
			} else {
				color2 = color;
			}
			return color2;
		default:
			return null;
		}
	}

}
