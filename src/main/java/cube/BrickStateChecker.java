package cube;

import java.util.HashMap;
import java.util.Map;

public class BrickStateChecker {

	/**
	 * White center at the down side
	 */
	public static boolean isWhiteCenterDown(NewCube cube) {
		return (cube.sideDown[0] == NewCube.Color.WHITE.ordinal());
	}

	/**
	 * Right cross brick position
	 */
	public static boolean isCrossBrick(NewCube cube, NewCube.Color color) {
		return (cube.sideDown[2] == NewCube.Color.WHITE.ordinal() && cube.sideFront[6] == color.ordinal());
	}

	/**
	 * Right cross orientation
	 */
	public static boolean isCrossRightOriented(NewCube cube) {
		return (cube.sideFront[6] == cube.sideFront[0]);
	}

	/**
	 * Right f2l angle
	 */
	public static boolean isRightAngleF2L(NewCube cube) {

		byte[] f = cube.sideFront;
		byte[] r = cube.sideRight;
		byte[] d = cube.sideDown;

		return (f[0] == f[4] && f[0] == f[5] && r[0] == r[8] && r[0] == r[7] && d[3] == NewCube.Color.WHITE.ordinal());
	}

	/**
	 * Count right f2l angles
	 */
	public static int countRightAnglesF2L(NewCube cube) {
		int i = 0;

		if (isRightAngleF2L(cube))
			i++;
		cube.Y();
		if (isRightAngleF2L(cube))
			i++;
		cube.Y();
		if (isRightAngleF2L(cube))
			i++;
		cube.Y();
		if (isRightAngleF2L(cube))
			i++;
		cube.Y();

		return i;
	}

	/**
	 * Right cross bricks position
	 */
	public static Map<NewCube.Color, Boolean> isCrossBricksRightPositioned(NewCube cube) {
		Map<NewCube.Color, Boolean> m = new HashMap<>();

		m.put(NewCube.Color.RED, isCrossBrick(cube, NewCube.Color.RED));
		cube.Dr();
		m.put(NewCube.Color.GREEN, isCrossBrick(cube, NewCube.Color.GREEN));
		cube.Dr();
		m.put(NewCube.Color.ORANGE, isCrossBrick(cube, NewCube.Color.ORANGE));
		cube.Dr();
		m.put(NewCube.Color.BLUE, isCrossBrick(cube, NewCube.Color.BLUE));
		cube.Dr();

		return m;
	}

}
