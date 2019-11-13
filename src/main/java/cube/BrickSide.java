package cube;

import lombok.Data;

@Data
public class BrickSide implements Brick {
	private String color1;
	private String color2;

	public BrickSide() {
	}

	public BrickSide(String color1, String color2) {
		this.color1 = color1;
		this.color2 = color2;
	}

	public String getColor(int colorNum) {
		switch (colorNum) {
		case 1:
			return color1;
		case 2:
			return color2;
		default:
			return null;
		}
	}

}
