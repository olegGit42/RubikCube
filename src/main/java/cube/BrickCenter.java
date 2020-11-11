package cube;

import lombok.Data;

@Data
public class BrickCenter implements Brick {
	private Color color1;

	public BrickCenter() {
	}

	public BrickCenter(Color color1) {
		this.color1 = color1;
	}

	@Override
	public Color getColor(int colorNum) {
		return this.color1;
	}

	@Override
	public Color changeColor(int colorNum, Color color) {
		if (color == null) {
			// color1 = Brick.getNextColor(color1);
		} else {
			color1 = color;
		}
		return color1;
	}

}
