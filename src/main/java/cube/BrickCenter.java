package cube;

import lombok.Data;

@Data
public class BrickCenter implements Brick {
	private String color1;

	public BrickCenter() {
	}

	public BrickCenter(String color1) {
		this.color1 = color1;
	}

	@Override
	public String getColor(int colorNum) {
		return this.color1;
	}

}
