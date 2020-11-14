package cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickStateChecker {

	public static final Map<NewCube.Color, List<NewCube.Color>> colorMapping;

	static {

		colorMapping = new HashMap<>();

		List<NewCube.Color> aroundWhite = new ArrayList<>();
		aroundWhite.add(NewCube.Color.RED);
		aroundWhite.add(NewCube.Color.GREEN);
		aroundWhite.add(NewCube.Color.ORANGE);
		aroundWhite.add(NewCube.Color.BLUE);

		List<NewCube.Color> aroundYellow = new ArrayList<>();
		aroundYellow.add(NewCube.Color.ORANGE);
		aroundYellow.add(NewCube.Color.GREEN);
		aroundYellow.add(NewCube.Color.RED);
		aroundYellow.add(NewCube.Color.BLUE);

		List<NewCube.Color> aroundGreen = new ArrayList<>();
		aroundGreen.add(NewCube.Color.YELLOW);
		aroundGreen.add(NewCube.Color.ORANGE);
		aroundGreen.add(NewCube.Color.WHITE);
		aroundGreen.add(NewCube.Color.RED);

		List<NewCube.Color> aroundBlue = new ArrayList<>();
		aroundBlue.add(NewCube.Color.YELLOW);
		aroundBlue.add(NewCube.Color.RED);
		aroundBlue.add(NewCube.Color.WHITE);
		aroundBlue.add(NewCube.Color.ORANGE);

		List<NewCube.Color> aroundRed = new ArrayList<>();
		aroundRed.add(NewCube.Color.YELLOW);
		aroundRed.add(NewCube.Color.GREEN);
		aroundRed.add(NewCube.Color.WHITE);
		aroundRed.add(NewCube.Color.BLUE);

		List<NewCube.Color> aroundOrange = new ArrayList<>();
		aroundOrange.add(NewCube.Color.YELLOW);
		aroundOrange.add(NewCube.Color.BLUE);
		aroundOrange.add(NewCube.Color.WHITE);
		aroundOrange.add(NewCube.Color.GREEN);

		colorMapping.put(NewCube.Color.WHITE, aroundWhite);
		colorMapping.put(NewCube.Color.YELLOW, aroundYellow);
		colorMapping.put(NewCube.Color.GREEN, aroundGreen);
		colorMapping.put(NewCube.Color.BLUE, aroundBlue);
		colorMapping.put(NewCube.Color.RED, aroundRed);
		colorMapping.put(NewCube.Color.ORANGE, aroundOrange);

	}

	public static void rotateColorMapping() {
		colorMapping.values().forEach(colorList -> {
			List<NewCube.Color> colorBuffer = new ArrayList<>();
			colorBuffer.add(colorList.get(1));
			colorBuffer.add(colorList.get(2));
			colorBuffer.add(colorList.get(3));
			colorBuffer.add(colorList.get(0));

			colorList.clear();
			colorList.addAll(colorBuffer);
		});
	}

	/**
	 * Base center at the down side
	 */
	public static boolean isBaseCenterDown(NewCube cube, NewCube.Color baseColor) {
		return (cube.sideDown[0] == baseColor.ordinal());
	}

	/**
	 * Right cross brick position
	 */
	public static boolean isCrossBrick(NewCube cube, NewCube.Color baseColor, NewCube.Color frontColor) {
		return (cube.sideDown[2] == baseColor.ordinal() && cube.sideFront[6] == frontColor.ordinal());
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
	public static boolean isRightAngleF2L(NewCube cube, NewCube.Color baseColor) {

		byte[] f = cube.sideFront;
		byte[] r = cube.sideRight;
		byte[] d = cube.sideDown;

		return (f[0] == f[4] && f[0] == f[5] && r[0] == r[8] && r[0] == r[7] && d[3] == baseColor.ordinal());
	}

	/**
	 * Count right f2l angles
	 */
	public static int countRightAnglesF2L(NewCube cube, NewCube.Color baseColor) {
		int i = 0;

		if (isRightAngleF2L(cube, baseColor))
			i++;
		cube.Y();
		if (isRightAngleF2L(cube, baseColor))
			i++;
		cube.Y();
		if (isRightAngleF2L(cube, baseColor))
			i++;
		cube.Y();
		if (isRightAngleF2L(cube, baseColor))
			i++;
		cube.Y();

		return i;
	}

	/**
	 * Right cross bricks position
	 */
	public static Map<NewCube.Color, Boolean> isCrossBricksRightPositioned(NewCube cube, NewCube.Color baseColor) {
		Map<NewCube.Color, Boolean> m = new HashMap<>();

		m.put(colorMapping.get(baseColor).get(0), isCrossBrick(cube, baseColor, colorMapping.get(baseColor).get(0)));
		cube.Dr();
		m.put(colorMapping.get(baseColor).get(1), isCrossBrick(cube, baseColor, colorMapping.get(baseColor).get(1)));
		cube.Dr();
		m.put(colorMapping.get(baseColor).get(2), isCrossBrick(cube, baseColor, colorMapping.get(baseColor).get(2)));
		cube.Dr();
		m.put(colorMapping.get(baseColor).get(3), isCrossBrick(cube, baseColor, colorMapping.get(baseColor).get(3)));
		cube.Dr();

		return m;
	}

}
