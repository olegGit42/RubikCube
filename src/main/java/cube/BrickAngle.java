package cube;

import lombok.Data;

@Data
public class BrickAngle implements Brick {
	private Color color1;
	private Color color2;
	private Color color3;

	public BrickAngle() {
	}

	public BrickAngle(Color color1, Color color2, Color color3) {
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
	}

	@Override
	public Color getColor(int colorNum) {
		switch (colorNum) {
		case 1:
			return color1;
		case 2:
			return color2;
		case 3:
			return color3;
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
		case 3:
			if (color == null) {
				color3 = Brick.getNextColor(color3);
			} else {
				color3 = color;
			}
			return color3;
		default:
			return null;
		}
	}

}
