package cube;

import static cube.BrickStateChecker.colorMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;

import rubick.rubick.RubickMain;

public class Solver {

	private NewCube cube;
	private NewCube cubeClone1;
	private NewCube cubeClone2;
	private NewCube.Color baseColor;
	private List<String> solvingAlgorithm = new ArrayList<>();
	private List<String> algorithmBuffer = new ArrayList<>();
	private Map<String, List<String[]>> iniMapping = new HashMap<>();
	private Wini ini;

	public Solver(NewCube cube) {
		this.cube = cube;
		this.cubeClone1 = NewCube.getNewEmptyInstance();
		this.cubeClone2 = NewCube.getNewEmptyInstance();
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
			iniMapping.put("pll_end", getIniSection("pll_end"));
			iniMapping.put("compressor", getIniSection("compressor", "\\|"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String[]> getIniSection(String group) {
		return getIniSection(group, " ");
	}

	private List<String[]> getIniSection(String group, String splitter) {
		Section section = ini.get(group);

		List<String[]> list = new ArrayList<>();

		for (String index : section.keySet()) {
			if (splitter.equals("\\|")) {

				String[] compressor = section.get(index).split(splitter);
				compressor[1] = compressor[1].replace("0", "");

				list.add(compressor);
			} else {
				list.add(section.get(index).split(splitter));
			}
		}

		return list;
	}

	// TODO solve()
	public List<String> solve() throws CannotSolveException {
		boolean isFirstLoop = true;
		solvingAlgorithm.clear();
		int count2 = 1000;

		for (NewCube.Color color : NewCube.Color.values()) {

			baseColor = color;

			for (int i = 0; i < 4; i++) {
				BrickStateChecker.rotateColorMapping();

				List<String> solvingAlgorithmTemp = solve2();
				solvingAlgorithmTemp = compress(solvingAlgorithmTemp);

				int bufferCount2 = 0;

				for (String move : solvingAlgorithmTemp) {
					if (move.contains("2")) {
						bufferCount2++;
					}
				}

				if (isFirstLoop || solvingAlgorithmTemp.size() < solvingAlgorithm.size()
						|| (solvingAlgorithmTemp.size() == solvingAlgorithm.size() && bufferCount2 < count2)) {
					count2 = bufferCount2;
					solvingAlgorithm.clear();
					solvingAlgorithm.addAll(solvingAlgorithmTemp);
				}
				isFirstLoop = false;
			}

		}

		return solvingAlgorithm;
	}

	public List<String> solve2() throws CannotSolveException {
		if (ini == null) {
			throw new CannotSolveException();
		}

		cubeClone1.copyFrom(cube);
		List<String> solvingAlgorithm = new ArrayList<>();
		solvingAlgorithm.addAll(cross());
		solvingAlgorithm.addAll(f2l());
		solvingAlgorithm.addAll(oll());
		solvingAlgorithm.addAll(pll());

		return solvingAlgorithm;
	}

	// TODO compress
	public List<String> compress(List<String> alrorithm) {

		if (!(alrorithm.isEmpty() || alrorithm.size() == 1)) {
			String alg = "";
			for (String move : alrorithm) {
				alg += " " + move;
			}

			int lenPrev = 10000;
			alg = alg.trim();

			while (alg.length() < lenPrev) {
				lenPrev = alg.length();

				alg = alg + " ";
				for (String[] compressor : iniMapping.get("compressor")) {
					alg = alg.replaceAll(compressor[0], compressor[1] + " ");
					alg = alg.replaceAll("\\s+", " ");
				}
				alg = alg.trim();
			}

			return Arrays.asList(alg.split(" "));
		}

		return alrorithm;
	}

	/**
	 * Set white center side at the down
	 */
	private List<String> baseOrientation(NewCube cube) throws CannotSolveException {

		if (BrickStateChecker.isBaseCenterDown(cube, baseColor)) {
			return new ArrayList<>();
		}

		NewCube cubeClone = NewCube.getNewEmptyInstance();

		for (String[] algorithm : iniMapping.get("base_orientation")) {

			cubeClone.copyFrom(cube);
			cubeClone.doAlgorithm(algorithm);

			if (BrickStateChecker.isBaseCenterDown(cubeClone, baseColor)) {
				cube.doAlgorithm(algorithm);
				return Arrays.asList(algorithm);
			}
		}

		throw new CannotSolveException("Base orientation error");

	}

	private boolean isCorrectCross(NewCube cube) {

		Map<NewCube.Color, Boolean> m;
		String moveD = "D";

		boolean isCorrectCross = false;

		cube = cube.clone();

		for (int i = 0; i < 4; i++) {
			cube.doAlgorithm(moveD);
			m = BrickStateChecker.isCrossBricksRightPositioned(cube, baseColor);
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
		Map<NewCube.Color, Boolean> m;

		String moveD = "D";
		String moveDr = "D'";
		String moveD2 = "D2";

		boolean notFound;

		if (!isCorrectCross(cubeClone1)) {

			// 1. Set WHITE-RED brick

			if (!BrickStateChecker.isCrossBrick(cubeClone1, baseColor, colorMapping.get(baseColor).get(0))) {

				notFound = true;
				for (String[] algorithm : iniMapping.get("cross")) {

					cubeClone2.copyFrom(cubeClone1);
					cubeClone2.doAlgorithm(algorithm);

					if (BrickStateChecker.isCrossBrick(cubeClone2, baseColor, colorMapping.get(baseColor).get(0))) {
						cubeClone1.doAlgorithm(algorithm);
						algorithmBuffer.addAll(Arrays.asList(algorithm));
						notFound = false;
						break;
					}
				}

				if (notFound)
					throw new CannotSolveException("Cross 1 step error");
			}

			m = BrickStateChecker.isCrossBricksRightPositioned(cubeClone1, baseColor);

			// 2. Set WHITE-GREEN brick

			if (!m.get(colorMapping.get(baseColor).get(1))) {

				cubeClone1.doAlgorithm(moveDr);
				algorithmBuffer.add(moveDr);

				notFound = true;
				int i = -1;
				for (String[] algorithm : iniMapping.get("cross")) {
					i++;
					if (i == 0 || i == 1 || i == 2 || i == 24 || i == 25)
						continue;

					cubeClone2.copyFrom(cubeClone1);
					cubeClone2.doAlgorithm(algorithm);

					if (BrickStateChecker.isCrossBrick(cubeClone2, baseColor, colorMapping.get(baseColor).get(1))) {
						cubeClone1.doAlgorithm(algorithm);
						algorithmBuffer.addAll(Arrays.asList(algorithm));
						notFound = false;
						break;
					}
				}

				if (notFound)
					throw new CannotSolveException("Cross 2 step error");

			}

			// 3. Set WHITE-ORANGE brick

			if (!m.get(colorMapping.get(baseColor).get(2))) {

				if (m.get(colorMapping.get(baseColor).get(1))) {
					cubeClone1.doAlgorithm(moveD2);
					algorithmBuffer.add(moveD2);
				} else {
					cubeClone1.doAlgorithm(moveDr);
					algorithmBuffer.add(moveDr);
				}

				if (!BrickStateChecker.isCrossBrick(cubeClone1, baseColor, colorMapping.get(baseColor).get(2))) {

					notFound = true;
					int i = -1;
					for (String[] algorithm : iniMapping.get("cross")) {
						i++;
						if (i == 0 || i == 1 || i == 2 || i == 24 || i == 25 || i == 22 || i == 23)
							continue;

						cubeClone2.copyFrom(cubeClone1);
						cubeClone2.doAlgorithm(algorithm);

						if (BrickStateChecker.isCrossBrick(cubeClone2, baseColor, colorMapping.get(baseColor).get(2))) {
							cubeClone1.doAlgorithm(algorithm);
							algorithmBuffer.addAll(Arrays.asList(algorithm));
							notFound = false;
							break;
						}
					}

					if (notFound)
						throw new CannotSolveException("Cross 3 step error");
				}
			}

			// 4. Set WHITE-BLUE brick

			if (!m.get(colorMapping.get(baseColor).get(3))) {

				if (m.get(colorMapping.get(baseColor).get(1)) && m.get(colorMapping.get(baseColor).get(2))) {
					cubeClone1.doAlgorithm(moveD);
					algorithmBuffer.add(moveD);
				} else if (m.get(colorMapping.get(baseColor).get(2))) {
					cubeClone1.doAlgorithm(moveD2);
					algorithmBuffer.add(moveD2);
				} else {
					cubeClone1.doAlgorithm(moveDr);
					algorithmBuffer.add(moveDr);
				}

				if (!BrickStateChecker.isCrossBrick(cubeClone1, baseColor, colorMapping.get(baseColor).get(3))) {

					notFound = true;
					int i = -1;
					for (String[] algorithm : iniMapping.get("cross")) {
						i++;
						if (i == 0 || i == 1 || i == 2 || i == 24 || i == 25 || i == 22 || i == 23 || i == 20 || i == 21)
							continue;

						cubeClone2.copyFrom(cubeClone1);
						cubeClone2.doAlgorithm(algorithm);

						if (isCorrectCross(cubeClone2)
								&& BrickStateChecker.isCrossBrick(cubeClone2, baseColor, colorMapping.get(baseColor).get(3))) {
							cubeClone1.doAlgorithm(algorithm);
							algorithmBuffer.addAll(Arrays.asList(algorithm));
							notFound = false;
							break;
						}
					}

					if (notFound)
						throw new CannotSolveException("Cross 4 step error");
				}
			}
		}

		// 5. Right cross orientation

		if (!BrickStateChecker.isCrossRightOriented(cubeClone1) || !isCorrectCross(cubeClone1)) {

			notFound = true;
			for (String[] algorithm : iniMapping.get("cross")) {

				cubeClone2.copyFrom(cubeClone1);
				cubeClone2.doAlgorithm(algorithm);

				if (isCorrectCross(cubeClone2) && BrickStateChecker.isCrossRightOriented(cubeClone2)) {
					cubeClone1.doAlgorithm(algorithm);
					algorithmBuffer.addAll(Arrays.asList(algorithm));
					notFound = false;
					break;
				}
			}

			if (notFound)
				throw new CannotSolveException("Right cross orientation error");
		}

		return algorithmBuffer;
	}

	/**
	 * TODO F2L solving (done)
	 */
	private List<String> f2l() throws CannotSolveException {
		algorithmBuffer.clear();

		if (BrickStateChecker.countRightAnglesF2L(cubeClone1, baseColor) != 4) {

			int i = 0;
			boolean found = false;

			while (BrickStateChecker.countRightAnglesF2L(cubeClone1, baseColor) != 4) {
				i++;
				if (i == 5)
					throw new CannotSolveException();

				found = false;

				for (String[] f2l_help : iniMapping.get("f2l_help")) {

					if (f2l_help[0].equals("-"))
						f2l_help = null;

					if (solveF2L_4(f2l_help)) {
						found = true;
						break;
					}
				}

				if (!found)
					throw new CannotSolveException("F2L error");

			}
		}

		return algorithmBuffer;
	}

	private boolean solveF2L_4(String[] f2l_help) throws CannotSolveException {

		boolean found = false;

		for (String[] y : iniMapping.get("y")) {

			if (y[0].equals("-"))
				y = null;

			if (solveF2L_3(f2l_help, y)) {
				found = true;
				break;
			}
		}

		return found;
	}

	private boolean solveF2L_3(String[] f2l_help, String[] y) throws CannotSolveException {

		boolean found = false;

		for (String[] u : iniMapping.get("U")) {

			if (u[0].equals("-")) {
				u = null;
			}

			if (solveF2L_2(f2l_help, y, u)) {
				found = true;
				break;
			}
		}

		return found;
	}

	private boolean solveF2L_2(String[] f2l_help, String[] y, String[] u) throws CannotSolveException {

		boolean found = false;

		for (String[] algorithm : iniMapping.get("f2l")) {

			if (solveF2L_1(f2l_help, y, u, algorithm)) {
				found = true;
				break;
			}

		}

		return found;
	}

	private boolean solveF2L_1(String[] f2l_help, String[] y, String[] u, String[] algorithm) throws CannotSolveException {

		boolean found = false;

		cubeClone2.copyFrom(cubeClone1);
		cubeClone2.doAlgorithm(y);

		if (!BrickStateChecker.isRightAngleF2L(cubeClone2, baseColor)) {

			int countRightF2L = BrickStateChecker.countRightAnglesF2L(cubeClone2, baseColor);

			cubeClone2.doAlgorithm(f2l_help);
			cubeClone2.doAlgorithm(u);
			cubeClone2.doAlgorithm(algorithm);

			if (BrickStateChecker.countRightAnglesF2L(cubeClone2, baseColor) > countRightF2L) {

				if (cubeClone1.doAlgorithm(y))
					algorithmBuffer.addAll(Arrays.asList(y));
				if (cubeClone1.doAlgorithm(f2l_help))
					algorithmBuffer.addAll(Arrays.asList(f2l_help));
				if (cubeClone1.doAlgorithm(u))
					algorithmBuffer.addAll(Arrays.asList(u));
				cubeClone1.doAlgorithm(algorithm);
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

		if (!cubeClone1.isSideCompleted(cubeClone1.sideUp)) {

			boolean found = false;

			for (String[] algorithm : iniMapping.get("oll")) {

				if (solveOLL_2(algorithm)) {
					found = true;
					break;
				}

			}

			if (!found)
				throw new CannotSolveException("OLL error");

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

		cubeClone2.copyFrom(cubeClone1);

		cubeClone2.doAlgorithm(u);
		cubeClone2.doAlgorithm(algorithm);
		baseOrientation(cubeClone2);

		if (cubeClone2.isSideCompleted(cubeClone2.sideUp)) {

			if (cubeClone1.doAlgorithm(u))
				algorithmBuffer.addAll(Arrays.asList(u));
			cubeClone1.doAlgorithm(algorithm);
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

		if (!cubeClone1.isCubeCompleted()) {

			boolean found = false;

			for (String[] algorithm : iniMapping.get("pll")) {

				if (solvePLL_2(algorithm)) {
					found = true;
					break;
				}

			}

			if (!found)
				throw new CannotSolveException("PLL error");

		}

		return algorithmBuffer;
	}

	private boolean solvePLL_2(String[] algorithm) throws CannotSolveException {

		if (algorithm[0].equals("-")) {
			algorithm = null;
		}

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

		for (String[] pll_end : iniMapping.get("pll_end")) {

			if (pll_end[0].equals("-")) {
				pll_end = null;
			}

			cubeClone2.copyFrom(cubeClone1);

			cubeClone2.doAlgorithm(u);
			cubeClone2.doAlgorithm(algorithm);
			cubeClone2.doAlgorithm(pll_end);

			if (cubeClone2.isCubeCompleted()) {

				if (cubeClone1.doAlgorithm(u))
					algorithmBuffer.addAll(Arrays.asList(u));
				if (cubeClone1.doAlgorithm(algorithm))
					algorithmBuffer.addAll(Arrays.asList(algorithm));
				if (cubeClone1.doAlgorithm(pll_end))
					algorithmBuffer.addAll(Arrays.asList(pll_end));

				found = true;
				break;
			}

		}

		return found;
	}

	public static Solver getNewInstance(Cube cube) {
		return new Solver(NewCube.getNewEmptyInstance().copyFrom(cube));
	}

}
