package cube;

import java.util.HashMap;
import java.util.Map;

public class BrickStateChecker {

	/**
	 * White center at the down side
	 */
	public static boolean isWhiteCenterDown(Cube cube) {
		return (cube.getSideDown().getBrickColor(5) == Brick.Color.WHITE);
	}

	/**
	 * Right cross brick position
	 */
	public static boolean isCrossBrick(Cube cube, Brick.Color color) {
		return (cube.getSideDown().getBrickColor(2) == Brick.Color.WHITE && cube.getSideFront().getBrickColor(8) == color);
	}

	/**
	 * Right cross orientation
	 */
	public static boolean isCrossRightOriented(Cube cube) {
		return (cube.getSideFront().getBrickColor(8) == cube.getSideFront().getBrickColor(5));
	}

	/**
	 * Right f2l angle
	 */
	public static boolean isRightAngleF2L(Cube cube) {

		Side f = cube.getSideFront();
		Side r = cube.getSideRight();
		Side d = cube.getSideDown();

		return (f.getBrickColor(5) == f.getBrickColor(6) && f.getBrickColor(5) == f.getBrickColor(9)
				&& r.getBrickColor(5) == r.getBrickColor(4) && r.getBrickColor(5) == r.getBrickColor(7)
				&& d.getBrickColor(3) == Brick.Color.WHITE);
	}

	/**
	 * Count right f2l angles
	 */
	public static int countRightAnglesF2L(Cube cube) {
		CubeController cc = CubeController.getNewInstance(cube);

		int i = 0;

		if (isRightAngleF2L(cube))
			i++;
		cc.Y();
		if (isRightAngleF2L(cube))
			i++;
		cc.Y();
		if (isRightAngleF2L(cube))
			i++;
		cc.Y();
		if (isRightAngleF2L(cube))
			i++;
		cc.Y();

		return i;
	}

	/**
	 * Right cross bricks position
	 */
	public static Map<Brick.Color, Boolean> isCrossBricksRightPositioned(Cube cube) {
		CubeController cc = CubeController.getNewInstance(cube);

		Map<Brick.Color, Boolean> m = new HashMap<>();

		m.put(Brick.Color.RED, isCrossBrick(cube, Brick.Color.RED));
		cc.Dr();
		m.put(Brick.Color.GREEN, isCrossBrick(cube, Brick.Color.GREEN));
		cc.Dr();
		m.put(Brick.Color.ORANGE, isCrossBrick(cube, Brick.Color.ORANGE));
		cc.Dr();
		m.put(Brick.Color.BLUE, isCrossBrick(cube, Brick.Color.BLUE));
		cc.Dr();

		return m;
	}

}
