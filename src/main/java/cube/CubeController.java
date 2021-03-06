package cube;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import rubick.rubick.RubickMain;
import view.View;

@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CubeController {

	@Autowired
	private Cube cube;
	private NewCube newCube = NewCube.getNewEmptyInstance();
	public static Map<String, Rotate> rotationMapping;

	private Cube cubeSolved;
	private List<String> solvingAlgorithm = new ArrayList<>();
	private int nextMoveIndex = 0;
	private boolean isNext = true;

	{
		rotationMapping = new HashMap<>();
		updateRotationMapping();
	}

	private void updateRotationMapping() {
		rotationMapping.put("R", this::R);
		rotationMapping.put("R'", this::Rr);
		rotationMapping.put("R2", this::R2);
		rotationMapping.put("Rw", this::Rw);
		rotationMapping.put("Rw'", this::Rwr);
		rotationMapping.put("Rw2", this::Rw2);

		rotationMapping.put("L", this::L);
		rotationMapping.put("L'", this::Lr);
		rotationMapping.put("L2", this::L2);
		rotationMapping.put("Lw", this::Lw);
		rotationMapping.put("Lw'", this::Lwr);
		rotationMapping.put("Lw2", this::Lw2);

		rotationMapping.put("U", this::U);
		rotationMapping.put("U'", this::Ur);
		rotationMapping.put("U2", this::U2);
		rotationMapping.put("Uw", this::Uw);
		rotationMapping.put("Uw'", this::Uwr);
		rotationMapping.put("Uw2", this::Uw2);

		rotationMapping.put("D", this::D);
		rotationMapping.put("D'", this::Dr);
		rotationMapping.put("D2", this::D2);
		rotationMapping.put("Dw", this::Dw);
		rotationMapping.put("Dw'", this::Dwr);
		rotationMapping.put("Dw2", this::Dw2);

		rotationMapping.put("F", this::F);
		rotationMapping.put("F'", this::Fr);
		rotationMapping.put("F2", this::F2);
		rotationMapping.put("Fw", this::Fw);
		rotationMapping.put("Fw'", this::Fwr);
		rotationMapping.put("Fw2", this::Fw2);

		rotationMapping.put("B", this::B);
		rotationMapping.put("B'", this::Br);
		rotationMapping.put("B2", this::B2);
		rotationMapping.put("Bw", this::Bw);
		rotationMapping.put("Bw'", this::Bwr);
		rotationMapping.put("Bw2", this::Bw2);

		rotationMapping.put("M", this::M);
		rotationMapping.put("M'", this::Mr);
		rotationMapping.put("M2", this::M2);

		rotationMapping.put("S", this::S);
		rotationMapping.put("S'", this::Sr);
		rotationMapping.put("S2", this::S2);

		rotationMapping.put("E", this::E);
		rotationMapping.put("E'", this::Er);
		rotationMapping.put("E2", this::E2);

		rotationMapping.put("x", this::X);
		rotationMapping.put("x'", this::Xr);
		rotationMapping.put("x2", this::X2);

		rotationMapping.put("y", this::Y);
		rotationMapping.put("y'", this::Yr);
		rotationMapping.put("y2", this::Y2);

		rotationMapping.put("z", this::Z);
		rotationMapping.put("z'", this::Zr);
		rotationMapping.put("z2", this::Z2);
	}

	public CubeController() {
	}

	public CubeController(Cube cube) {
		this.cube = cube;
	}

	public static CubeController getNewInstanceWithCube() {
		return RubickMain.appContext.getBean("cubeController", CubeController.class);
	}

	public static CubeController getNewInstance() {
		return new CubeController();
	}

	public static CubeController getNewInstance(Cube cube) {
		return new CubeController(cube);
	}

	public boolean doAlgorithm(List<String> algorithmList) {
		newCube.copyFrom(this.cube);
		boolean isDo = newCube.doAlgorithm(algorithmList);
		newCube.copyTo(this.cube);
		return isDo;
	}

	public boolean doAlgorithm(String[] algorithmArray) {
		newCube.copyFrom(this.cube);
		boolean isDo = newCube.doAlgorithm(algorithmArray);
		newCube.copyTo(this.cube);
		return isDo;
	}

	public boolean doAlgorithm(String rotation) {
		newCube.copyFrom(this.cube);
		boolean isDo = newCube.doAlgorithm(rotation);
		newCube.copyTo(this.cube);
		return isDo;
	}

	public void resetCube() {

		try {
			File cubeFile = new File("reset_cube.json");

			if (!cubeFile.exists())
				cubeFile = new File(RubickMain.class.getClassLoader().getResource("solved_cube.json").toURI());

			CubeJSON.getNewInstance().getCubeFromJSON(cubeFile, cube);

		} catch (Exception e) {
			cube.getSideDown().setSideColor(Brick.Color.WHITE);
			cube.getSideUp().setSideColor(Brick.Color.YELLOW);
			cube.getSideFront().setSideColor(Brick.Color.RED);
			cube.getSideBack().setSideColor(Brick.Color.ORANGE);
			cube.getSideLeft().setSideColor(Brick.Color.BLUE);
			cube.getSideRight().setSideColor(Brick.Color.GREEN);
		}

	}

	public boolean cubeCompleted() {

		/*
		 * for (Field side : Cube.class.getDeclaredFields()) { if (!sideCompleted((Side)
		 * side.get(cube))) { return false; } }
		 */

		if (!sideCompleted(cube.getSideBack()) || !sideCompleted(cube.getSideDown()) || !sideCompleted(cube.getSideFront())
				|| !sideCompleted(cube.getSideLeft()) || !sideCompleted(cube.getSideRight()) || !sideCompleted(cube.getSideUp()))
			return false;

		return true;
	}

	// TODO completeCube()
	public List<String> completeCube() {

		try {
			solvingAlgorithm.clear();
			solvingAlgorithm.addAll(Solver.getNewInstance(cube).solve());
			nextMoveIndex = 0;
			isNext = true;

			cubeSolved = cube.clone();
		} catch (CannotSolveException e) {
			e.printStackTrace();
			solvingAlgorithm.clear();
			solvingAlgorithm.add(e.getMessage());
		}

		return solvingAlgorithm;
	}

	public String showSolvingAlgorithm() {

		String alg = "";
		int i = 0;
		for (String move : solvingAlgorithm) {

			if (i == nextMoveIndex) {

				if (isNext) {
					move = ">>>> " + move;
				} else {
					move = move + " <<<<";
				}

			}

			alg += " " + move;
			i++;
		}

		return alg.trim();
	}

	public void doSolve(boolean isNext) {
		if (cubeSolved != null && !solvingAlgorithm.isEmpty() && solvingAlgorithm.size() > 1) {
			boolean notEquals = isNext != this.isNext;

			this.isNext = isNext;

			if (notEquals) {
				incrementNextMoveIndex(0);
			}
			while (this.isNext == isNext) {
				doSolveMove(isNext);
			}
		}
	}

	public void doSolveMove(boolean isNext) {

		if (cubeSolved != null && !solvingAlgorithm.isEmpty()) {
			boolean notEquals = isNext != this.isNext;

			this.isNext = isNext;

			if (notEquals) {
				incrementNextMoveIndex(0);
				return;
			}

			cube = cubeSolved;
			View.cube = cube;

			if (isNext) {
				doAlgorithm(solvingAlgorithm.get(nextMoveIndex));
			} else {
				doAlgorithm(solvingAlgorithm.get(nextMoveIndex));
				doAlgorithm(solvingAlgorithm.get(nextMoveIndex));
				doAlgorithm(solvingAlgorithm.get(nextMoveIndex));
			}

			cubeSolved = cube.clone();

			incrementNextMoveIndex(0);

		}

	}

	/**
	 * @param direction -1 - begin, 0 - one step, 1 - end
	 */
	public void incrementNextMoveIndex(int direction) {

		boolean hasNext = true;
		boolean hasPrev = true;

		try {
			solvingAlgorithm.get(nextMoveIndex + 1);
		} catch (Exception e) {
			hasNext = false;
		}

		try {
			solvingAlgorithm.get(nextMoveIndex - 1);
		} catch (Exception e) {
			hasPrev = false;
		}

		switch (direction) {
		case -1:
			nextMoveIndex = 0;
			isNext = true;

			break;
		case 0:

			if (isNext) {

				if (hasNext) {
					nextMoveIndex++;
				} else {
					isNext = false;
				}

			} else {

				if (hasPrev) {
					nextMoveIndex--;
				} else {
					isNext = true;
				}

			}

			break;
		case 1:
			nextMoveIndex = solvingAlgorithm.size() - 1;
			isNext = false;

			break;
		default:
			break;
		}

	}

	public boolean sideCompleted(Side side) {
		Brick.Color color = side.getBrickColor(9);
		for (int i = 1; i < 9; i++) {
			if (color != side.getBrickColor(i)) {
				return false;
			}
		}
		return true;
	}

	public void showCube(int millis) throws InterruptedException, IllegalArgumentException, IllegalAccessException {
		Thread.sleep(millis);
		showCube();
	}

	public void showCube() {
		Side sideF, sideD, sideL, sideU, sideR, sideB;
		sideF = cube.getSideFront();
		sideD = cube.getSideDown();
		sideL = cube.getSideLeft();
		sideU = cube.getSideUp();
		sideR = cube.getSideRight();
		sideB = cube.getSideBack();
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
		System.out.println("         " + sideU.getBrickColor(1) + " " + sideU.getBrickColor(2) + " " + sideU.getBrickColor(3));
		System.out.println("         " + sideU.getBrickColor(4) + " " + sideU.getBrickColor(5) + " " + sideU.getBrickColor(6));
		System.out.println("         " + sideU.getBrickColor(7) + " " + sideU.getBrickColor(8) + " " + sideU.getBrickColor(9));
		System.out.println();
		System.out.println(sideL.getBrickColor(1) + " " + sideL.getBrickColor(2) + " " + sideL.getBrickColor(3) + "    "
				+ sideF.getBrickColor(1) + " " + sideF.getBrickColor(2) + " " + sideF.getBrickColor(3) + "    "
				+ sideR.getBrickColor(1) + " " + sideR.getBrickColor(2) + " " + sideR.getBrickColor(3) + "    "
				+ sideB.getBrickColor(1) + " " + sideB.getBrickColor(2) + " " + sideB.getBrickColor(3));
		System.out.println(sideL.getBrickColor(4) + " " + sideL.getBrickColor(5) + " " + sideL.getBrickColor(6) + "    "
				+ sideF.getBrickColor(4) + " " + sideF.getBrickColor(5) + " " + sideF.getBrickColor(6) + "    "
				+ sideR.getBrickColor(4) + " " + sideR.getBrickColor(5) + " " + sideR.getBrickColor(6) + "    "
				+ sideB.getBrickColor(4) + " " + sideB.getBrickColor(5) + " " + sideB.getBrickColor(6));
		System.out.println(sideL.getBrickColor(7) + " " + sideL.getBrickColor(8) + " " + sideL.getBrickColor(9) + "    "
				+ sideF.getBrickColor(7) + " " + sideF.getBrickColor(8) + " " + sideF.getBrickColor(9) + "    "
				+ sideR.getBrickColor(7) + " " + sideR.getBrickColor(8) + " " + sideR.getBrickColor(9) + "    "
				+ sideB.getBrickColor(7) + " " + sideB.getBrickColor(8) + " " + sideB.getBrickColor(9));
		System.out.println();
		System.out.println("         " + sideD.getBrickColor(1) + " " + sideD.getBrickColor(2) + " " + sideD.getBrickColor(3));
		System.out.println("         " + sideD.getBrickColor(4) + " " + sideD.getBrickColor(5) + " " + sideD.getBrickColor(6));
		System.out.println("         " + sideD.getBrickColor(7) + " " + sideD.getBrickColor(8) + " " + sideD.getBrickColor(9));
		for (int i = 0; i < 4; i++) {
			System.out.println();
		}
		System.out.println(cubeCompleted() ? "Completed!!!" : "");
	}

	// --------------------------------------------------[Algorithms]
	public void pifPaf(int millis) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
		R();
		showCube(millis);
		U();
		showCube(millis);
		Rr();
		showCube(millis);
		Ur();
		showCube(millis);
	}

	public void T(int millis) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
		pifPaf(millis);

		Rr();
		showCube(millis);
		F();
		showCube(millis);
		R2();
		showCube(millis);
		Ur();
		showCube(millis);
		Rr();
		showCube(millis);
		Ur();
		showCube(millis);
		R();
		showCube(millis);
		U();
		showCube(millis);
		Rr();
		showCube(millis);
		Fr();
		showCube(millis);
	}

	public void changeCenterColors(int millis) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
		S();
		showCube(millis);
		M();
		showCube(millis);
		Sr();
		showCube(millis);
		Mr();
		showCube(millis);
	}

	// --------------------------------------------------[X]
	public void X() {
		Side sideBuffer, sideF, sideD, sideU, sideB;
		sideF = cube.getSideFront();
		sideD = cube.getSideDown();
		sideU = cube.getSideUp();
		sideB = cube.getSideBack();

		cube.getSideRight().rotateSelf(1);
		cube.getSideLeft().rotateSelf(3);

		sideU.rotateSelf(2);
		sideB.rotateSelf(2);

		sideBuffer = sideF;
		cube.setSideFront(sideD);
		cube.setSideDown(sideB);
		cube.setSideBack(sideU);
		cube.setSideUp(sideBuffer);
	}

	public void X(int quantity) {
		for (int i = 0; i < quantity; i++) {
			X();
		}
	}

	public void X2() {
		X(2);
	}

	public void Xr() {
		X(3);
	}

	// --------------------------------------------------[Y]
	public void Y() {
		Side sideBuffer, sideF, sideL, sideR, sideB;
		sideF = cube.getSideFront();
		sideL = cube.getSideLeft();
		sideR = cube.getSideRight();
		sideB = cube.getSideBack();

		cube.getSideUp().rotateSelf(1);
		cube.getSideDown().rotateSelf(3);

		sideBuffer = sideF;
		cube.setSideFront(sideR);
		cube.setSideRight(sideB);
		cube.setSideBack(sideL);
		cube.setSideLeft(sideBuffer);
	}

	public void Y(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Y();
		}
	}

	public void Y2() {
		Y(2);
	}

	public void Yr() {
		Y(3);
	}

	// --------------------------------------------------[Z]
	public void Z() {
		Side sideBuffer, sideD, sideL, sideU, sideR;
		sideD = cube.getSideDown();
		sideL = cube.getSideLeft();
		sideU = cube.getSideUp();
		sideR = cube.getSideRight();

		cube.getSideFront().rotateSelf(1);
		cube.getSideBack().rotateSelf(3);

		sideD.rotateSelf(1);
		sideR.rotateSelf(1);
		sideU.rotateSelf(1);
		sideL.rotateSelf(1);

		sideBuffer = sideD;
		cube.setSideDown(sideR);
		cube.setSideRight(sideU);
		cube.setSideUp(sideL);
		cube.setSideLeft(sideBuffer);
	}

	public void Z(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Z();
		}
	}

	public void Z2() {
		Z(2);
	}

	public void Zr() {
		Z(3);
	}

	// --------------------------------------------------[F]
	public void F() {
		F(1);
	}

	public void F2() {
		F(2);
	}

	public void Fr() {
		F(3);
	}

	public void F(int quantity) {
		Side sideRotated, sideUp, sideRight, sideDown, sideLeft;
		sideRotated = cube.getSideFront();
		sideUp = cube.getSideUp();
		sideRight = cube.getSideRight();
		sideDown = cube.getSideDown();
		sideLeft = cube.getSideLeft();

		for (int i = 0; i < quantity; i++) {
			sideRotated.rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void Fw() {
		Fw(1);
	}

	public void Fw2() {
		Fw(2);
	}

	public void Fwr() {
		Fw(3);
	}

	public void Fw(int quantity) {
		for (int i = 0; i < quantity; i++) {
			S();
			F();
		}
	}

	// --------------------------------------------------[D]
	public void D() {
		D(1);
	}

	public void D2() {
		D(2);
	}

	public void Dr() {
		D(3);
	}

	public void D(int quantity) {
		Side sideRotated, sideUp, sideRight, sideDown, sideLeft;
		sideRotated = cube.getSideDown();
		sideUp = cube.getSideFront();
		sideRight = cube.getSideRight();
		sideDown = cube.getSideBack();
		sideLeft = cube.getSideLeft();

		for (int i = 0; i < quantity; i++) {
			sideRotated.rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void Dw() {
		Dw(1);
	}

	public void Dw2() {
		Dw(2);
	}

	public void Dwr() {
		Dw(3);
	}

	public void Dw(int quantity) {
		for (int i = 0; i < quantity; i++) {
			E();
			D();
		}
	}

	// --------------------------------------------------[L]
	public void L() {
		L(1);
	}

	public void L2() {
		L(2);
	}

	public void Lr() {
		L(3);
	}

	public void L(int quantity) {
		Side sideRotated, sideUp, sideRight, sideDown, sideLeft;
		sideRotated = cube.getSideLeft();
		sideUp = cube.getSideUp();
		sideRight = cube.getSideFront();
		sideDown = cube.getSideDown();
		sideLeft = cube.getSideBack();

		for (int i = 0; i < quantity; i++) {
			sideRotated.rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void Lw() {
		Lw(1);
	}

	public void Lw2() {
		Lw(2);
	}

	public void Lwr() {
		Lw(3);
	}

	public void Lw(int quantity) {
		for (int i = 0; i < quantity; i++) {
			M();
			L();
		}
	}

	// --------------------------------------------------[U]
	public void U() {
		U(1);
	}

	public void U2() {
		U(2);
	}

	public void Ur() {
		U(3);
	}

	public void U(int quantity) {
		Side sideRotated, sideUp, sideRight, sideDown, sideLeft;
		sideRotated = cube.getSideUp();
		sideUp = cube.getSideBack();
		sideRight = cube.getSideRight();
		sideDown = cube.getSideFront();
		sideLeft = cube.getSideLeft();

		for (int i = 0; i < quantity; i++) {
			sideRotated.rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void Uw() {
		Uw(1);
	}

	public void Uw2() {
		Uw(2);
	}

	public void Uwr() {
		Uw(3);
	}

	public void Uw(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Er();
			U();
		}
	}

	// --------------------------------------------------[R]
	public void R() {
		R(1);
	}

	public void R2() {
		R(2);
	}

	public void Rr() {
		R(3);
	}

	public void R(int quantity) {
		Side sideRotated, sideUp, sideRight, sideDown, sideLeft;
		sideRotated = cube.getSideRight();
		sideUp = cube.getSideUp();
		sideRight = cube.getSideBack();
		sideDown = cube.getSideDown();
		sideLeft = cube.getSideFront();

		for (int i = 0; i < quantity; i++) {
			sideRotated.rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void Rw() {
		Rw(1);
	}

	public void Rw2() {
		Rw(2);
	}

	public void Rwr() {
		Rw(3);
	}

	public void Rw(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Mr();
			R();
		}
	}

	// --------------------------------------------------[B]
	public void B() {
		B(1);
	}

	public void B2() {
		B(2);
	}

	public void Br() {
		B(3);
	}

	public void B(int quantity) {
		Side sideRotated, sideUp, sideRight, sideDown, sideLeft;
		sideRotated = cube.getSideBack();
		sideUp = cube.getSideUp();
		sideRight = cube.getSideLeft();
		sideDown = cube.getSideDown();
		sideLeft = cube.getSideRight();

		for (int i = 0; i < quantity; i++) {
			sideRotated.rotate(sideUp, sideRight, sideDown, sideLeft);
		}
	}

	public void Bw() {
		Bw(1);
	}

	public void Bw2() {
		Bw(2);
	}

	public void Bwr() {
		Bw(3);
	}

	public void Bw(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Sr();
			B();
		}
	}

	// --------------------------------------------------[M]
	public void M() {
		M(1);
	}

	public void M2() {
		M(2);
	}

	public void Mr() {
		M(3);
	}

	public void M(int quantity) {
		for (int i = 0; i < quantity; i++) {
			R();
			Lr();
			Xr();
		}
	}

	// --------------------------------------------------[S]
	public void S() {
		S(1);
	}

	public void S2() {
		S(2);
	}

	public void Sr() {
		S(3);
	}

	public void S(int quantity) {
		for (int i = 0; i < quantity; i++) {
			B();
			Fr();
			Z();
		}
	}

	// --------------------------------------------------[E]
	public void E() {
		E(1);
	}

	public void E2() {
		E(2);
	}

	public void Er() {
		E(3);
	}

	public void E(int quantity) {
		for (int i = 0; i < quantity; i++) {
			U();
			Dr();
			Yr();
		}
	}

}
