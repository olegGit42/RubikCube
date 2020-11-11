package cube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;

import rubick.rubick.RubickMain;

@SuppressWarnings("unused")
public class Solver {

	private Cube cube;
	private Cube cubeClone1;
	private Cube cubeClone2;
	private CubeController cubeController = CubeController.getNewInstance();
	private List<String> solvingAlgorithm = new ArrayList<>();
	private List<String> algorithmBuffer = new ArrayList<>();
	private Map<String, List<String[]>> iniMapping = new HashMap<>();
	private Wini ini;

	public Solver(Cube cube) {
		this.cube = cube;
		try {
			this.ini = new Wini(RubickMain.class.getClassLoader().getResource("algorithms.ini"));

			iniMapping.put("base_orientation", getIniSection("base_orientation"));
			iniMapping.put("cross", getIniSection("cross"));
			iniMapping.put("f2l", getIniSection("f2l"));
			iniMapping.put("f2l_help", getIniSection("f2l_help"));
			iniMapping.put("U", getIniSection("U"));
			iniMapping.put("y", getIniSection("y"));
			iniMapping.put("oll", getIniSection("oll"));
			iniMapping.put("pll", getIniSection("pll"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String[]> getIniSection(String group) {
		Section section = ini.get(group);

		List<String[]> list = new ArrayList<>();

		for (String index : section.keySet()) {
			list.add(section.get(index).split(" "));
		}

		return list;
	}

	// TODO solve()
	public List<String> solve() throws CannotSolveException {
		if (ini == null) {
			throw new CannotSolveException();
		}

		cubeClone1 = cube.clone();
		solvingAlgorithm.clear();
		solvingAlgorithm.addAll(cross());
		solvingAlgorithm.addAll(f2l());
		solvingAlgorithm.addAll(oll());
		solvingAlgorithm.addAll(pll());

		return solvingAlgorithm;
	}

	/**
	 * Set white center side at the down
	 */
	private List<String> baseOrientation(Cube cube) throws CannotSolveException {

		if (BrickStateChecker.isWhiteCenterDown(cube)) {
			return new ArrayList<>();
		}

		Cube cubeClone;

		for (String[] algorithm : iniMapping.get("base_orientation")) {

			cubeClone = cube.clone();
			doAlgorithm(cubeClone, algorithm);

			if (BrickStateChecker.isWhiteCenterDown(cubeClone)) {
				doAlgorithm(cube, algorithm);
				return Arrays.asList(algorithm);
			}
		}

		throw new CannotSolveException();

	}

	private boolean isCorrectCross(Cube cube) {

		Map<Brick.Color, Boolean> m;
		String moveD = "D";

		boolean isCorrectCross = false;

		cube = cube.clone();

		for (int i = 0; i < 4; i++) {
			doAlgorithm(cube, moveD);
			m = BrickStateChecker.isCrossBricksRightPositioned(cube);
			isCorrectCross = true;
			for (boolean isCorrectPosition : m.values()) {
				if (!isCorrectPosition) {
					isCorrectCross = false;
				}
			}
			if (isCorrectCross)
				break;
		}

		return isCorrectCross;

	}

	/**
	 * TODO Cross solving (done)
	 */
	private List<String> cross() throws CannotSolveException {
		algorithmBuffer.clear();
		algorithmBuffer.addAll(baseOrientation(cubeClone1));
		Map<Brick.Color, Boolean> m;

		String moveD = "D";
		String moveDr = "D'";
		String moveD2 = "D2";

		boolean notFound;

		if (!isCorrectCross(cubeClone1)) {

			// 1. Set WHITE-RED brick

			if (!BrickStateChecker.isCrossBrick(cubeClone1, Brick.Color.RED)) {

				notFound = true;
				for (String[] algorithm : iniMapping.get("cross")) {

					cubeClone2 = cubeClone1.clone();
					doAlgorithm(cubeClone2, algorithm);

					if (BrickStateChecker.isCrossBrick(cubeClone2, Brick.Color.RED)) {
						doAlgorithm(cubeClone1, algorithm);
						algorithmBuffer.addAll(Arrays.asList(algorithm));
						notFound = false;
						break;
					}
				}

				if (notFound)
					throw new CannotSolveException();
			}

			m = BrickStateChecker.isCrossBricksRightPositioned(cubeClone1);

			// 2. Set WHITE-GREEN brick

			if (!m.get(Brick.Color.GREEN)) {

				doAlgorithm(cubeClone1, moveDr);
				algorithmBuffer.add(moveDr);

				notFound = true;
				int i = -1;
				for (String[] algorithm : iniMapping.get("cross")) {
					i++;
					if (i == 0 || i == 1 || i == 2 || i == 24 || i == 25)
						continue;

					cubeClone2 = cubeClone1.clone();
					doAlgorithm(cubeClone2, algorithm);

					if (BrickStateChecker.isCrossBrick(cubeClone2, Brick.Color.GREEN)) {
						doAlgorithm(cubeClone1, algorithm);
						algorithmBuffer.addAll(Arrays.asList(algorithm));
						notFound = false;
						break;
					}
				}

				if (notFound)
					throw new CannotSolveException();

			}

			// 3. Set WHITE-ORANGE brick

			if (!m.get(Brick.Color.ORANGE)) {

				if (m.get(Brick.Color.GREEN)) {
					doAlgorithm(cubeClone1, moveD2);
					algorithmBuffer.add(moveD2);
				} else {
					doAlgorithm(cubeClone1, moveDr);
					algorithmBuffer.add(moveDr);
				}

				if (!BrickStateChecker.isCrossBrick(cubeClone1, Brick.Color.ORANGE)) {

					notFound = true;
					int i = -1;
					for (String[] algorithm : iniMapping.get("cross")) {
						i++;
						if (i == 0 || i == 1 || i == 2 || i == 24 || i == 25 || i == 22 || i == 23)
							continue;

						cubeClone2 = cubeClone1.clone();
						doAlgorithm(cubeClone2, algorithm);

						if (BrickStateChecker.isCrossBrick(cubeClone2, Brick.Color.ORANGE)) {
							doAlgorithm(cubeClone1, algorithm);
							algorithmBuffer.addAll(Arrays.asList(algorithm));
							notFound = false;
							break;
						}
					}

					if (notFound)
						throw new CannotSolveException();
				}
			}

			// 4. Set WHITE-BLUE brick

			if (!m.get(Brick.Color.BLUE)) {

				if (m.get(Brick.Color.GREEN) && m.get(Brick.Color.ORANGE)) {
					doAlgorithm(cubeClone1, moveD);
					algorithmBuffer.add(moveD);
				} else if (m.get(Brick.Color.ORANGE)) {
					doAlgorithm(cubeClone1, moveD2);
					algorithmBuffer.add(moveD2);
				} else {
					doAlgorithm(cubeClone1, moveDr);
					algorithmBuffer.add(moveDr);
				}

				if (!BrickStateChecker.isCrossBrick(cubeClone1, Brick.Color.BLUE)) {

					notFound = true;
					int i = -1;
					for (String[] algorithm : iniMapping.get("cross")) {
						i++;
						if (i == 0 || i == 1 || i == 2 || i == 24 || i == 25 || i == 22 || i == 23 || i == 20 || i == 21)
							continue;

						cubeClone2 = cubeClone1.clone();
						doAlgorithm(cubeClone2, algorithm);

						if (isCorrectCross(cubeClone2) && BrickStateChecker.isCrossBrick(cubeClone2, Brick.Color.BLUE)) {
							doAlgorithm(cubeClone1, algorithm);
							algorithmBuffer.addAll(Arrays.asList(algorithm));
							notFound = false;
							break;
						}
					}

					if (notFound)
						throw new CannotSolveException("Cross error.");
				}
			}
		}

		// 5. Right cross orientation

		if (!BrickStateChecker.isCrossRightOriented(cubeClone1) || !isCorrectCross(cubeClone1)) {

			notFound = true;
			for (String[] algorithm : iniMapping.get("cross")) {

				cubeClone2 = cubeClone1.clone();
				doAlgorithm(cubeClone2, algorithm);

				if (isCorrectCross(cubeClone2) && BrickStateChecker.isCrossRightOriented(cubeClone2)) {
					doAlgorithm(cubeClone1, algorithm);
					algorithmBuffer.addAll(Arrays.asList(algorithm));
					notFound = false;
					break;
				}
			}

			if (notFound)
				throw new CannotSolveException("Right cross orientation error.");
		}

		return algorithmBuffer;
	}

	/**
	 * TODO F2L solving (done)
	 */
	private List<String> f2l() throws CannotSolveException {
		algorithmBuffer.clear();

		if (BrickStateChecker.countRightAnglesF2L(cubeClone1) != 4) {

			int i = 0;
			boolean found = false;

			while (BrickStateChecker.countRightAnglesF2L(cubeClone1) != 4) {
				i++;
				if (i == 5)
					throw new CannotSolveException();

				found = false;

				for (String[] y : iniMapping.get("y")) {

					if (y[0].equals("-"))
						y = null;

					if (solveF2L_4(y)) {
						found = true;
						break;
					}
				}

				if (!found)
					throw new CannotSolveException("F2L error.");

			}
		}

		return algorithmBuffer;
	}

	private boolean solveF2L_4(String[] y) throws CannotSolveException {

		boolean found = false;

		for (String[] algorithm : iniMapping.get("f2l")) {

			if (solveF2L_3(y, algorithm)) {
				found = true;
				break;
			}

		}

		return found;
	}

	private boolean solveF2L_3(String[] y, String[] algorithm) throws CannotSolveException {

		boolean found = false;

		for (String[] help : iniMapping.get("f2l_help")) {

			if (help[0].equals("-"))
				help = null;

			if (solveF2L_2(y, help, algorithm)) {
				found = true;
				break;
			}
		}

		return found;
	}

	private boolean solveF2L_2(String[] y, String[] f2l_help, String[] algorithm) throws CannotSolveException {

		boolean found = false;

		for (String[] u : iniMapping.get("U")) {

			if (u[0].equals("-")) {
				u = null;
			}

			if (solveF2L_1(y, f2l_help, u, algorithm)) {
				found = true;
				break;
			}
		}

		return found;
	}

	private boolean solveF2L_1(String[] y, String[] f2l_help, String[] u, String[] algorithm) throws CannotSolveException {

		boolean found = false;

		cubeClone2 = cubeClone1.clone();
		doAlgorithm(cubeClone2, y);

		if (!BrickStateChecker.isRightAngleF2L(cubeClone2)) {

			int countRightF2L = BrickStateChecker.countRightAnglesF2L(cubeClone2);

			doAlgorithm(cubeClone2, f2l_help);
			doAlgorithm(cubeClone2, u);
			doAlgorithm(cubeClone2, algorithm);

			if (BrickStateChecker.countRightAnglesF2L(cubeClone2) > countRightF2L) {

				if (doAlgorithm(cubeClone1, y))
					algorithmBuffer.addAll(Arrays.asList(y));
				if (doAlgorithm(cubeClone1, f2l_help))
					algorithmBuffer.addAll(Arrays.asList(f2l_help));
				if (doAlgorithm(cubeClone1, u))
					algorithmBuffer.addAll(Arrays.asList(u));
				doAlgorithm(cubeClone1, algorithm);
				algorithmBuffer.addAll(Arrays.asList(algorithm));

				found = true;
			}
		}

		return found;
	}

	/**
	 * TODO OLL solving (done)
	 */
	private List<String> oll() throws CannotSolveException {
		algorithmBuffer.clear();

		if (!cubeController.sideCompleted(cubeClone1.getSideUp())) {

			boolean found = false;

			for (String[] algorithm : iniMapping.get("oll")) {

				if (solveOLL_2(algorithm)) {
					found = true;
					break;
				}

			}

			if (!found)
				throw new CannotSolveException("OLL error.");

		}

		return algorithmBuffer;
	}

	private boolean solveOLL_2(String[] algorithm) throws CannotSolveException {

		boolean found = false;

		for (String[] u : iniMapping.get("U")) {

			if (u[0].equals("-")) {
				u = null;
			}

			if (solveOLL_1(u, algorithm)) {
				found = true;
				break;
			}
		}

		return found;
	}

	private boolean solveOLL_1(String[] u, String[] algorithm) throws CannotSolveException {

		boolean found = false;

		cubeClone2 = cubeClone1.clone();

		doAlgorithm(cubeClone2, u);
		doAlgorithm(cubeClone2, algorithm);
		baseOrientation(cubeClone2);

		if (cubeController.sideCompleted(cubeClone2.getSideUp())) {

			if (doAlgorithm(cubeClone1, u))
				algorithmBuffer.addAll(Arrays.asList(u));
			doAlgorithm(cubeClone1, algorithm);
			algorithmBuffer.addAll(Arrays.asList(algorithm));
			algorithmBuffer.addAll(baseOrientation(cubeClone1));

			found = true;
		}

		return found;
	}

	/**
	 * TODO PLL solving (done)
	 */
	private List<String> pll() throws CannotSolveException {
		algorithmBuffer.clear();

		if (!cubeCompleted(cubeClone1)) {

			boolean found = false;

			for (String[] algorithm : iniMapping.get("pll")) {

				if (solvePLL_2(algorithm)) {
					found = true;
					break;
				}

			}

			if (!found)
				throw new CannotSolveException("OLL error.");

		}

		return algorithmBuffer;
	}

	private boolean solvePLL_2(String[] algorithm) throws CannotSolveException {

		boolean found = false;

		for (String[] u : iniMapping.get("U")) {

			if (u[0].equals("-")) {
				u = null;
			}

			if (solvePLL_1(u, algorithm)) {
				found = true;
				break;
			}
		}

		return found;
	}

	private boolean solvePLL_1(String[] u, String[] algorithm) throws CannotSolveException {

		boolean found = false;

		for (String[] u_end : iniMapping.get("U")) {

			if (u_end[0].equals("-")) {
				u_end = null;
			}

			cubeClone2 = cubeClone1.clone();

			doAlgorithm(cubeClone2, u);
			doAlgorithm(cubeClone2, algorithm);
			baseOrientation(cubeClone2);
			doAlgorithm(cubeClone2, u_end);

			if (cubeCompleted(cubeClone2)) {

				if (doAlgorithm(cubeClone1, u))
					algorithmBuffer.addAll(Arrays.asList(u));
				doAlgorithm(cubeClone1, algorithm);
				algorithmBuffer.addAll(Arrays.asList(algorithm));
				algorithmBuffer.addAll(baseOrientation(cubeClone1));
				if (doAlgorithm(cubeClone1, u_end))
					algorithmBuffer.addAll(Arrays.asList(u_end));

				found = true;
				break;
			}

		}

		return found;
	}

	private boolean doAlgorithm(Cube cube, List<String> algorithmList) {
		cubeController.setCube(cube);
		return cubeController.doAlgorithm(algorithmList);
	}

	private boolean doAlgorithm(Cube cube, String[] algorithmArray) {
		cubeController.setCube(cube);
		return cubeController.doAlgorithm(algorithmArray);
	}

	private boolean doAlgorithm(Cube cube, String rotation) {
		cubeController.setCube(cube);
		return cubeController.doAlgorithm(rotation);
	}

	public static Solver getNewInstance(Cube cube) {
		return new Solver(cube);
	}

	public boolean cubeCompleted(Cube cube) {
		cubeController.setCube(cube);
		try {
			return cubeController.cubeCompleted();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

}
