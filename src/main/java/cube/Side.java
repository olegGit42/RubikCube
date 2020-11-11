package cube;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cube.Brick.Color;
import lombok.Data;
import rubick.rubick.RubickMain;

@Data
public class Side implements Cloneable {
	public static Map<String, Integer> fieldIndexMapping;

	private Brick brickAngle1;
	private Brick brickAngle2;
	private Brick brickAngle3;
	private Brick brickAngle4;

	private Brick brickSide1;
	private Brick brickSide2;
	private Brick brickSide3;
	private Brick brickSide4;

	private Brick brickCenter;

	private int[] brickColor;

	static {
		fieldIndexMapping = new HashMap<String, Integer>();

		fieldIndexMapping.put("brickAngle1", 0);
		fieldIndexMapping.put("brickAngle2", 1);
		fieldIndexMapping.put("brickAngle3", 2);
		fieldIndexMapping.put("brickAngle4", 3);

		fieldIndexMapping.put("brickSide1", 4);
		fieldIndexMapping.put("brickSide2", 5);
		fieldIndexMapping.put("brickSide3", 6);
		fieldIndexMapping.put("brickSide4", 7);
	}

	public Side() {
	}

	public Side(BrickAngle brickAngle1, BrickAngle brickAngle2, BrickAngle brickAngle3, BrickAngle brickAngle4,
			BrickSide brickSide1, BrickSide brickSide2, BrickSide brickSide3, BrickSide brickSide4, BrickCenter brickCenter,
			int[] brickColor) {
		this.brickAngle1 = brickAngle1;
		this.brickAngle2 = brickAngle2;
		this.brickAngle3 = brickAngle3;
		this.brickAngle4 = brickAngle4;
		this.brickSide1 = brickSide1;
		this.brickSide2 = brickSide2;
		this.brickSide3 = brickSide3;
		this.brickSide4 = brickSide4;
		this.brickCenter = brickCenter;
		this.brickColor = brickColor;
	}

	public Brick getBrick(int brickNum) {
		switch (brickNum) {
		case 1:
			return brickAngle1;
		case 2:
			return brickSide1;
		case 3:
			return brickAngle2;
		case 4:
			return brickSide4;
		case 5:
			return brickCenter;
		case 6:
			return brickSide2;
		case 7:
			return brickAngle4;
		case 8:
			return brickSide3;
		case 9:
			return brickAngle3;
		default:
			return null;
		}
	}

	public Brick.Color getBrickColor(int brickNum) {
		switch (brickNum) {
		case 1:
			return brickAngle1.getColor(brickColor[0]);
		case 2:
			return brickSide1.getColor(brickColor[4]);
		case 3:
			return brickAngle2.getColor(brickColor[1]);
		case 4:
			return brickSide4.getColor(brickColor[7]);
		case 5:
			return brickCenter.getColor(1);
		case 6:
			return brickSide2.getColor(brickColor[5]);
		case 7:
			return brickAngle4.getColor(brickColor[3]);
		case 8:
			return brickSide3.getColor(brickColor[6]);
		case 9:
			return brickAngle3.getColor(brickColor[2]);
		default:
			return null;
		}
	}

	public Brick.Color changeBrickColor(int brickNum, Color color) {
		switch (brickNum) {
		case 1:
			return brickAngle1.changeColor(brickColor[0], color);
		case 2:
			return brickSide1.changeColor(brickColor[4], color);
		case 3:
			return brickAngle2.changeColor(brickColor[1], color);
		case 4:
			return brickSide4.changeColor(brickColor[7], color);
		case 5:
			return brickCenter.changeColor(1, color);
		case 6:
			return brickSide2.changeColor(brickColor[5], color);
		case 7:
			return brickAngle4.changeColor(brickColor[3], color);
		case 8:
			return brickSide3.changeColor(brickColor[6], color);
		case 9:
			return brickAngle3.changeColor(brickColor[2], color);
		default:
			return null;
		}
	}

	public void rotate(Side sideUp, Side sideRight, Side sideDown, Side sideLeft, int quantity)
			throws IllegalArgumentException, IllegalAccessException {
		for (int i = 0; i < quantity; i++) {
			rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void rotate(Side sideUp, Side sideRight, Side sideDown, Side sideLeft) {
		Side clone = this.clone();
		Side cloneUp = sideUp.clone();
		Side cloneRight = sideRight.clone();
		Side cloneDown = sideDown.clone();
		Side cloneLeft = sideLeft.clone();
		rotateSelf();
		try {
			this.changeSides(clone, sideUp, cloneUp, cloneLeft);
			this.changeSides(clone, sideRight, cloneRight, cloneUp);
			this.changeSides(clone, sideDown, cloneDown, cloneRight);
			this.changeSides(clone, sideLeft, cloneLeft, cloneDown);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void changeSides(Side clone, Side sideUp, Side sideUpClone, Side sideLeftClone)
			throws IllegalArgumentException, IllegalAccessException {
		List<Brick> brickListUp = getEqualBricks(clone, sideUpClone);
		List<Brick> brickListLeft = getEqualBricks(clone, sideLeftClone);

		Class<Side> side = Side.class;
		int brickColorIndex = 0;
		int brickLeftColorValue = 0;
		boolean flag;

		for (Brick brickLeft : brickListLeft) {

			for (Field brick : side.getDeclaredFields()) {
				if (brick.get(sideLeftClone) == brickLeft) {
					brickColorIndex = fieldIndexMapping.get(brick.getName());
					brickLeftColorValue = sideLeftClone.getBrickColor()[brickColorIndex];
					break;
				}
			}

			for (Brick brickUp : brickListUp) {

				if (brickUp instanceof BrickSide && brickLeft instanceof BrickSide) {
					setNewBrick(sideUp, brickUp, brickLeft, brickLeftColorValue);
					break;
				} else if (brickUp instanceof BrickAngle && brickLeft instanceof BrickAngle) {
					flag = false;
					for (Brick brickUp2 : brickListUp) {

						if (brickUp != brickUp2 && brickLeft == brickUp && brickLeft != brickUp2
								&& brickUp2 instanceof BrickAngle) {
							deleteBrick(sideUp, brickUp);
							setNewBrick(sideUp, brickUp2, /* brickUp */ brickLeft, brickLeftColorValue);
							flag = true;
							break;
						}

					}

					if (flag) {
						break;
					}

				}
			}
		}

		for (Brick brickLeft : brickListLeft) {

			for (Field brick : side.getDeclaredFields()) {
				if (brick.get(sideLeftClone) == brickLeft) {
					brickColorIndex = fieldIndexMapping.get(brick.getName());
					brickLeftColorValue = sideLeftClone.getBrickColor()[brickColorIndex];
					break;
				}
			}

			for (Brick brickUp : brickListUp) {
				if (brickUp instanceof BrickAngle && brickLeft instanceof BrickAngle) {

					flag = false;
					for (Brick brickUp2 : brickListUp) {
						if (brickUp2 instanceof BrickAngle) {
							if (brickUp != brickUp2 && brickLeft != brickUp && brickLeft != brickUp2) {
								setNewBrick(sideUp, null, brickLeft, brickLeftColorValue);
								flag = true;
								break;
							}
						}
					}

					if (flag) {
						break;
					}
				}
			}
		}
	}

	public void deleteBrick(Side side, Brick brickDel) throws IllegalArgumentException, IllegalAccessException {
		for (Field brick : Side.class.getDeclaredFields()) {
			if (brick.get(side) == brickDel) {
				brick.setAccessible(true);
				brick.set(side, null);
				break;
			}
		}
	}

	public void setNewBrick(Side sideUp, Brick brickUp, Brick brickLeft, int brickLeftColorValue)
			throws IllegalArgumentException, IllegalAccessException {
		int brickColorIndex;
		for (Field brick : Side.class.getDeclaredFields()) {
			if (brick.get(sideUp) == brickUp) {
				brickColorIndex = fieldIndexMapping.get(brick.getName());
				sideUp.getBrickColor()[brickColorIndex] = brickLeftColorValue;
				brick.setAccessible(true);
				brick.set(sideUp, brickLeft);
			}
		}
	}

	public List<Brick> getEqualBricks(Side side1, Side side2) throws IllegalArgumentException, IllegalAccessException {
		List<Brick> brickList = new ArrayList<Brick>();
		Class<Side> side = Side.class;
		for (Field brick1 : side.getDeclaredFields()) {
			for (Field brick2 : side.getDeclaredFields()) {
				if (brick1.get(side1) == brick2.get(side2) && brick1.get(side1) instanceof Brick) {
					brickList.add((Brick) brick1.get(side1));
					break;
				}
			}
		}

		return brickList;
	}

	public void rotateSelf() {
		rotateSelf(1);
	}

	public void rotateSelf(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Brick brickAngle = brickAngle1;
			brickAngle1 = brickAngle4;
			brickAngle4 = brickAngle3;
			brickAngle3 = brickAngle2;
			brickAngle2 = brickAngle;

			Brick brickSide = brickSide1;
			brickSide1 = brickSide4;
			brickSide4 = brickSide3;
			brickSide3 = brickSide2;
			brickSide2 = brickSide;

			rotateColorIndexes();
		}
	}

	public void rotateColorIndexes() {
		int index = brickColor[0];
		brickColor[0] = brickColor[3];
		brickColor[3] = brickColor[2];
		brickColor[2] = brickColor[1];
		brickColor[1] = index;

		index = brickColor[4];
		brickColor[4] = brickColor[7];
		brickColor[7] = brickColor[6];
		brickColor[6] = brickColor[5];
		brickColor[5] = index;
	}

	public void setSideColor(Brick.Color color) {
		for (int i = 1; i < 10; i++) {
			changeBrickColor(i, color);
		}
	}

	@Override
	public Side clone() {
		Side side = RubickMain.appContext.getBean("side", Side.class);

		side.brickAngle1 = this.brickAngle1;
		side.brickAngle2 = this.brickAngle2;
		side.brickAngle3 = this.brickAngle3;
		side.brickAngle4 = this.brickAngle4;
		side.brickSide1 = this.brickSide1;
		side.brickSide2 = this.brickSide2;
		side.brickSide3 = this.brickSide3;
		side.brickSide4 = this.brickSide4;
		side.brickCenter = this.brickCenter;
		side.brickColor = Arrays.copyOf(this.brickColor, this.brickColor.length);

		return side;
	}

}
