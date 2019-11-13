package cube;

import lombok.Data;

@Data
public class BrickAngle implements Brick {
	private String color1;
	private String color2;
	private String color3;

	public BrickAngle() {
	}

	public BrickAngle(String color1, String color2, String color3) {
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
	}

	public String getColor(int colorNum) {
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

}
