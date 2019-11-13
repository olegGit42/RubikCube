package cube;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import rubick.Rubick.RubickMain;

@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CubeController {

	@Autowired
	Cube cube;

	public CubeController() {
	}

	public CubeController(Cube cube) {
		this.cube = cube;
	}

	public boolean cubeCompleted() throws IllegalArgumentException, IllegalAccessException {

		for (Field side : Cube.class.getDeclaredFields()) {
			if (!sideCompleted((Side) side.get(cube))) {
				return false;
			}
		}
		return true;
	}

	public Cube completeCube() {
		cube = RubickMain.appContext.getBean("cube", Cube.class);
		return cube;
	}

	public boolean sideCompleted(Side side) {
		String color = side.getBrickColor(9);
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

	public void showCube() throws IllegalArgumentException, IllegalAccessException {
		Side sideF, sideD, sideL, sideU, sideR, sideB;
		sideF = cube.getSideF();
		sideD = cube.getSideD();
		sideL = cube.getSideL();
		sideU = cube.getSideU();
		sideR = cube.getSideR();
		sideB = cube.getSideB();
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
		sideF = cube.getSideF();
		sideD = cube.getSideD();
		sideU = cube.getSideU();
		sideB = cube.getSideB();

		cube.getSideR().rotateSelf(1);
		cube.getSideL().rotateSelf(3);

		sideU.rotateSelf(2);
		sideB.rotateSelf(2);

		sideBuffer = sideF;
		cube.setSideF(sideD);
		cube.setSideD(sideB);
		cube.setSideB(sideU);
		cube.setSideU(sideBuffer);
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
		sideF = cube.getSideF();
		sideL = cube.getSideL();
		sideR = cube.getSideR();
		sideB = cube.getSideB();

		cube.getSideU().rotateSelf(1);
		cube.getSideD().rotateSelf(3);

		sideBuffer = sideF;
		cube.setSideF(sideR);
		cube.setSideR(sideB);
		cube.setSideB(sideL);
		cube.setSideL(sideBuffer);
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
		sideD = cube.getSideD();
		sideL = cube.getSideL();
		sideU = cube.getSideU();
		sideR = cube.getSideR();

		cube.getSideF().rotateSelf(1);
		cube.getSideB().rotateSelf(3);

		sideD.rotateSelf(1);
		sideR.rotateSelf(1);
		sideU.rotateSelf(1);
		sideL.rotateSelf(1);

		sideBuffer = sideD;
		cube.setSideD(sideR);
		cube.setSideR(sideU);
		cube.setSideU(sideL);
		cube.setSideL(sideBuffer);
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
		sideRotated = cube.getSideF();
		sideUp = cube.getSideU();
		sideRight = cube.getSideR();
		sideDown = cube.getSideD();
		sideLeft = cube.getSideL();

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
		sideRotated = cube.getSideD();
		sideUp = cube.getSideF();
		sideRight = cube.getSideR();
		sideDown = cube.getSideB();
		sideLeft = cube.getSideL();

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
		sideRotated = cube.getSideL();
		sideUp = cube.getSideU();
		sideRight = cube.getSideF();
		sideDown = cube.getSideD();
		sideLeft = cube.getSideB();

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
		sideRotated = cube.getSideU();
		sideUp = cube.getSideB();
		sideRight = cube.getSideR();
		sideDown = cube.getSideF();
		sideLeft = cube.getSideL();

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
		sideRotated = cube.getSideR();
		sideUp = cube.getSideU();
		sideRight = cube.getSideB();
		sideDown = cube.getSideD();
		sideLeft = cube.getSideF();

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
		sideRotated = cube.getSideB();
		sideUp = cube.getSideU();
		sideRight = cube.getSideL();
		sideDown = cube.getSideD();
		sideLeft = cube.getSideR();

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
