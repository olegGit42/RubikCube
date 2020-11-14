package cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used clockwise elements positioning in arrays: [0] - center tile; [1] -
 * left-up-angle tile; [2] - up-edge tile; [3] - right-up-angle tile; [4] -
 * right-edge tile and etc.
 */
public final class NewCube implements Cloneable {

	public final byte[] sideUp;
	public final byte[] sideDown;
	public final byte[] sideRight;
	public final byte[] sideLeft;
	public final byte[] sideFront;
	public final byte[] sideBack;

	public final byte[] layerM;
	public final byte[] layerS;
	public final byte[] layerE;

	private final Map<String, Rotate> rotationMapping;

	private final Map<byte[], List<Store>> sideMap;

	// TODO doAlgorithm()
	public boolean doAlgorithm(List<String> algorithmList) {
		try {
			algorithmList.forEach(rotation -> rotationMapping.get(rotation).rotate());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean doAlgorithm(String[] algorithmArray) {
		try {
			for (String rotation : algorithmArray) {
				rotationMapping.get(rotation).rotate();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean doAlgorithm(String rotation) {
		try {
			rotationMapping.get(rotation).rotate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// TODO Completion check

	public boolean isCubeCompleted() {
		return (isSideCompleted(sideUp) && isSideCompleted(sideDown) && isSideCompleted(sideRight) && isSideCompleted(sideLeft)
				&& isSideCompleted(sideFront) && isSideCompleted(sideBack));
	}

	public boolean isSideCompleted(byte[] side) {
		boolean isCompleted = true;
		byte centerColor = side[0];

		for (int i = 1; i < side.length; i++) {
			if (side[i] != centerColor) {
				isCompleted = false;
				break;
			}
		}

		return isCompleted;
	}

	// TODO sides and layers rotation
	private void rotateSideClockwise(byte[] sideRotated) {
		rotateAroundSidesColorsClockwise(sideMap.get(sideRotated));
		rotateSideColorsClockwise(sideRotated);
	}

	private void rotateSideAntiClockwise(byte[] sideRotated) {
		rotateAroundSidesColorsAntiClockwise(sideMap.get(sideRotated));
		rotateSideColorsAntiClockwise(sideRotated);
	}

	private void rotateSideTwice(byte[] sideRotated) {
		rotateAroundSidesColorsTwice(sideMap.get(sideRotated));
		rotateSideColorsTwice(sideRotated);
	}

	private void rotateLayerClockwise(byte[] layerRotated) {
		rotateAroundSidesColorsClockwise(sideMap.get(layerRotated));
	}

	private void rotateLayerAntiClockwise(byte[] layerRotated) {
		rotateAroundSidesColorsAntiClockwise(sideMap.get(layerRotated));
	}

	private void rotateLayerTwice(byte[] layerRotated) {
		rotateAroundSidesColorsTwice(sideMap.get(layerRotated));
	}

	// TODO rotateAroundSidesColors
	private void rotateAroundSidesColorsClockwise(List<Store> aroundSides) {
		rotateAroundSidesColorsBase(aroundSides, true, false);
	}

	private void rotateAroundSidesColorsAntiClockwise(List<Store> aroundSides) {
		rotateAroundSidesColorsBase(aroundSides, true, true);
	}

	private void rotateAroundSidesColorsTwice(List<Store> aroundSides) {
		rotateAroundSidesColorsBase(aroundSides, false, true);
	}

	private void rotateAroundSidesColorsBase(List<Store> aroundSides, boolean isOneRotation, boolean isForward) {

		aroundSides.forEach(Store::fillColorsBuffer);

		for (byte i = 0; i < aroundSides.size(); i++) {

			Store sampleSide = aroundSides.get(aroundSideIndexCounter(i, isOneRotation, isForward));
			Store side = aroundSides.get(i);

			side.setColorsInSide(sampleSide.colorsBuffer);
		}
	}

	// TODO rotateSideColors
	private void rotateSideColorsClockwise(byte[] sideRotated) {
		rotateSideColorsBase(sideRotated, true, true);
	}

	private void rotateSideColorsAntiClockwise(byte[] sideRotated) {
		rotateSideColorsBase(sideRotated, true, false);
	}

	private void rotateSideColorsTwice(byte[] sideRotated) {
		rotateSideColorsBase(sideRotated, false, true);
	}

	private void rotateSideColorsBase(byte[] sideRotated, boolean isOneRotation, boolean isForward) {
		byte[] sideClone = sideRotated.clone();

		for (byte i = 1; i < sideClone.length; i++) {
			sideRotated[sideIndexCounter(i, isOneRotation, isForward)] = sideClone[i];
		}
	}

	// TODO Index counters
	private byte sideIndexCounter(byte startIndex, boolean isOneRotation, boolean isForward) {

		int direction = (isForward ? 1 : (-1));
		int delta = (-8) * direction;

		int finalIndex = startIndex + direction * (isOneRotation ? 1 : 2) * 2;

		if (finalIndex > 8 || finalIndex < 1) {
			finalIndex += delta;
		}

		return (byte) finalIndex;
	}

	private byte aroundSideIndexCounter(byte startIndex, boolean isOneRotation, boolean isForward) {

		int direction = (isForward ? 1 : (-1));
		int delta = (-4) * direction;

		int finalIndex = startIndex + direction * (isOneRotation ? 1 : 2);

		if (finalIndex > 3 || finalIndex < 0) {
			finalIndex += delta;
		}

		return (byte) finalIndex;
	}

	// TODO Initializators
	public static NewCube getNewEmptyInstance() {
		return new NewCube();
	}

	public static NewCube getNewFullInstance() {
		NewCube cube = getNewEmptyInstance();

		for (int i = 0; i < cube.sideUp.length; i++) {
			cube.sideDown[i] = 0;
			cube.sideUp[i] = 1;
			cube.sideRight[i] = 2;
			cube.sideLeft[i] = 3;
			cube.sideFront[i] = 4;
			cube.sideBack[i] = 5;
		}

		return cube;
	}

	private NewCube() {
		this.sideUp = new byte[9];
		this.sideDown = new byte[9];
		this.sideRight = new byte[9];
		this.sideLeft = new byte[9];
		this.sideFront = new byte[9];
		this.sideBack = new byte[9];

		this.layerM = new byte[0];
		this.layerS = new byte[0];
		this.layerE = new byte[0];

		this.sideMap = new HashMap<>();
		updateSideMap();
	}

	// TODO Utils
	@Override
	public NewCube clone() {
		NewCube cube = getNewEmptyInstance();
		copyTo(cube);
		return cube;
	}

	public void copyTo(NewCube cube) {
		System.arraycopy(this.sideUp, 0, cube.sideUp, 0, this.sideUp.length);
		System.arraycopy(this.sideDown, 0, cube.sideDown, 0, this.sideUp.length);
		System.arraycopy(this.sideRight, 0, cube.sideRight, 0, this.sideUp.length);
		System.arraycopy(this.sideLeft, 0, cube.sideLeft, 0, this.sideUp.length);
		System.arraycopy(this.sideFront, 0, cube.sideFront, 0, this.sideUp.length);
		System.arraycopy(this.sideBack, 0, cube.sideBack, 0, this.sideUp.length);
	}

	public void copyTo(Cube cube) {
		Side u = cube.getSideUp();
		Side d = cube.getSideDown();
		Side r = cube.getSideRight();
		Side l = cube.getSideLeft();
		Side f = cube.getSideFront();
		Side b = cube.getSideBack();

		u.changeBrickColor(5, Brick.Color.values()[sideUp[0]]);
		u.changeBrickColor(1, Brick.Color.values()[sideUp[1]]);
		u.changeBrickColor(2, Brick.Color.values()[sideUp[2]]);
		u.changeBrickColor(3, Brick.Color.values()[sideUp[3]]);
		u.changeBrickColor(6, Brick.Color.values()[sideUp[4]]);
		u.changeBrickColor(9, Brick.Color.values()[sideUp[5]]);
		u.changeBrickColor(8, Brick.Color.values()[sideUp[6]]);
		u.changeBrickColor(7, Brick.Color.values()[sideUp[7]]);
		u.changeBrickColor(4, Brick.Color.values()[sideUp[8]]);

		d.changeBrickColor(5, Brick.Color.values()[sideDown[0]]);
		d.changeBrickColor(1, Brick.Color.values()[sideDown[1]]);
		d.changeBrickColor(2, Brick.Color.values()[sideDown[2]]);
		d.changeBrickColor(3, Brick.Color.values()[sideDown[3]]);
		d.changeBrickColor(6, Brick.Color.values()[sideDown[4]]);
		d.changeBrickColor(9, Brick.Color.values()[sideDown[5]]);
		d.changeBrickColor(8, Brick.Color.values()[sideDown[6]]);
		d.changeBrickColor(7, Brick.Color.values()[sideDown[7]]);
		d.changeBrickColor(4, Brick.Color.values()[sideDown[8]]);

		r.changeBrickColor(5, Brick.Color.values()[sideRight[0]]);
		r.changeBrickColor(1, Brick.Color.values()[sideRight[1]]);
		r.changeBrickColor(2, Brick.Color.values()[sideRight[2]]);
		r.changeBrickColor(3, Brick.Color.values()[sideRight[3]]);
		r.changeBrickColor(6, Brick.Color.values()[sideRight[4]]);
		r.changeBrickColor(9, Brick.Color.values()[sideRight[5]]);
		r.changeBrickColor(8, Brick.Color.values()[sideRight[6]]);
		r.changeBrickColor(7, Brick.Color.values()[sideRight[7]]);
		r.changeBrickColor(4, Brick.Color.values()[sideRight[8]]);

		l.changeBrickColor(5, Brick.Color.values()[sideLeft[0]]);
		l.changeBrickColor(1, Brick.Color.values()[sideLeft[1]]);
		l.changeBrickColor(2, Brick.Color.values()[sideLeft[2]]);
		l.changeBrickColor(3, Brick.Color.values()[sideLeft[3]]);
		l.changeBrickColor(6, Brick.Color.values()[sideLeft[4]]);
		l.changeBrickColor(9, Brick.Color.values()[sideLeft[5]]);
		l.changeBrickColor(8, Brick.Color.values()[sideLeft[6]]);
		l.changeBrickColor(7, Brick.Color.values()[sideLeft[7]]);
		l.changeBrickColor(4, Brick.Color.values()[sideLeft[8]]);

		f.changeBrickColor(5, Brick.Color.values()[sideFront[0]]);
		f.changeBrickColor(1, Brick.Color.values()[sideFront[1]]);
		f.changeBrickColor(2, Brick.Color.values()[sideFront[2]]);
		f.changeBrickColor(3, Brick.Color.values()[sideFront[3]]);
		f.changeBrickColor(6, Brick.Color.values()[sideFront[4]]);
		f.changeBrickColor(9, Brick.Color.values()[sideFront[5]]);
		f.changeBrickColor(8, Brick.Color.values()[sideFront[6]]);
		f.changeBrickColor(7, Brick.Color.values()[sideFront[7]]);
		f.changeBrickColor(4, Brick.Color.values()[sideFront[8]]);

		b.changeBrickColor(5, Brick.Color.values()[sideBack[0]]);
		b.changeBrickColor(1, Brick.Color.values()[sideBack[1]]);
		b.changeBrickColor(2, Brick.Color.values()[sideBack[2]]);
		b.changeBrickColor(3, Brick.Color.values()[sideBack[3]]);
		b.changeBrickColor(6, Brick.Color.values()[sideBack[4]]);
		b.changeBrickColor(9, Brick.Color.values()[sideBack[5]]);
		b.changeBrickColor(8, Brick.Color.values()[sideBack[6]]);
		b.changeBrickColor(7, Brick.Color.values()[sideBack[7]]);
		b.changeBrickColor(4, Brick.Color.values()[sideBack[8]]);

	}

	public NewCube copyFrom(NewCube cube) {
		System.arraycopy(cube.sideUp, 0, this.sideUp, 0, this.sideUp.length);
		System.arraycopy(cube.sideDown, 0, this.sideDown, 0, this.sideUp.length);
		System.arraycopy(cube.sideRight, 0, this.sideRight, 0, this.sideUp.length);
		System.arraycopy(cube.sideLeft, 0, this.sideLeft, 0, this.sideUp.length);
		System.arraycopy(cube.sideFront, 0, this.sideFront, 0, this.sideUp.length);
		System.arraycopy(cube.sideBack, 0, this.sideBack, 0, this.sideUp.length);

		return this;
	}

	public NewCube copyFrom(Cube cube) {
		CubeJSON cj = CubeJSON.getNewInstance().readCube(cube);

		Brick.Color[] u = cj.getSideUp();
		Brick.Color[] d = cj.getSideDown();
		Brick.Color[] r = cj.getSideRight();
		Brick.Color[] l = cj.getSideLeft();
		Brick.Color[] f = cj.getSideFront();
		Brick.Color[] b = cj.getSideBack();

		sideUp[0] = (byte) u[4].ordinal();
		sideUp[1] = (byte) u[0].ordinal();
		sideUp[2] = (byte) u[1].ordinal();
		sideUp[3] = (byte) u[2].ordinal();
		sideUp[4] = (byte) u[5].ordinal();
		sideUp[5] = (byte) u[8].ordinal();
		sideUp[6] = (byte) u[7].ordinal();
		sideUp[7] = (byte) u[6].ordinal();
		sideUp[8] = (byte) u[3].ordinal();

		sideDown[0] = (byte) d[4].ordinal();
		sideDown[1] = (byte) d[0].ordinal();
		sideDown[2] = (byte) d[1].ordinal();
		sideDown[3] = (byte) d[2].ordinal();
		sideDown[4] = (byte) d[5].ordinal();
		sideDown[5] = (byte) d[8].ordinal();
		sideDown[6] = (byte) d[7].ordinal();
		sideDown[7] = (byte) d[6].ordinal();
		sideDown[8] = (byte) d[3].ordinal();

		sideRight[0] = (byte) r[4].ordinal();
		sideRight[1] = (byte) r[0].ordinal();
		sideRight[2] = (byte) r[1].ordinal();
		sideRight[3] = (byte) r[2].ordinal();
		sideRight[4] = (byte) r[5].ordinal();
		sideRight[5] = (byte) r[8].ordinal();
		sideRight[6] = (byte) r[7].ordinal();
		sideRight[7] = (byte) r[6].ordinal();
		sideRight[8] = (byte) r[3].ordinal();

		sideLeft[0] = (byte) l[4].ordinal();
		sideLeft[1] = (byte) l[0].ordinal();
		sideLeft[2] = (byte) l[1].ordinal();
		sideLeft[3] = (byte) l[2].ordinal();
		sideLeft[4] = (byte) l[5].ordinal();
		sideLeft[5] = (byte) l[8].ordinal();
		sideLeft[6] = (byte) l[7].ordinal();
		sideLeft[7] = (byte) l[6].ordinal();
		sideLeft[8] = (byte) l[3].ordinal();

		sideFront[0] = (byte) f[4].ordinal();
		sideFront[1] = (byte) f[0].ordinal();
		sideFront[2] = (byte) f[1].ordinal();
		sideFront[3] = (byte) f[2].ordinal();
		sideFront[4] = (byte) f[5].ordinal();
		sideFront[5] = (byte) f[8].ordinal();
		sideFront[6] = (byte) f[7].ordinal();
		sideFront[7] = (byte) f[6].ordinal();
		sideFront[8] = (byte) f[3].ordinal();

		sideBack[0] = (byte) b[4].ordinal();
		sideBack[1] = (byte) b[0].ordinal();
		sideBack[2] = (byte) b[1].ordinal();
		sideBack[3] = (byte) b[2].ordinal();
		sideBack[4] = (byte) b[5].ordinal();
		sideBack[5] = (byte) b[8].ordinal();
		sideBack[6] = (byte) b[7].ordinal();
		sideBack[7] = (byte) b[6].ordinal();
		sideBack[8] = (byte) b[3].ordinal();

		return this;
	}

	private void updateSideMap() {
		sideMap.clear();

		List<Store> aroundSideUp = new ArrayList<>();
		aroundSideUp.add(Store.get(sideBack, new byte[] { 1, 2, 3 }));
		aroundSideUp.add(Store.get(sideRight, new byte[] { 1, 2, 3 }));
		aroundSideUp.add(Store.get(sideFront, new byte[] { 1, 2, 3 }));
		aroundSideUp.add(Store.get(sideLeft, new byte[] { 1, 2, 3 }));

		sideMap.put(sideUp, aroundSideUp);

		List<Store> aroundSideDown = new ArrayList<>();
		aroundSideDown.add(Store.get(sideFront, new byte[] { 5, 6, 7 }));
		aroundSideDown.add(Store.get(sideRight, new byte[] { 5, 6, 7 }));
		aroundSideDown.add(Store.get(sideBack, new byte[] { 5, 6, 7 }));
		aroundSideDown.add(Store.get(sideLeft, new byte[] { 5, 6, 7 }));

		sideMap.put(sideDown, aroundSideDown);

		List<Store> aroundSideRight = new ArrayList<>();
		aroundSideRight.add(Store.get(sideUp, new byte[] { 3, 4, 5 }));
		aroundSideRight.add(Store.get(sideBack, new byte[] { 7, 8, 1 }));
		aroundSideRight.add(Store.get(sideDown, new byte[] { 3, 4, 5 }));
		aroundSideRight.add(Store.get(sideFront, new byte[] { 3, 4, 5 }));

		sideMap.put(sideRight, aroundSideRight);

		List<Store> aroundSideLeft = new ArrayList<>();
		aroundSideLeft.add(Store.get(sideUp, new byte[] { 7, 8, 1 }));
		aroundSideLeft.add(Store.get(sideFront, new byte[] { 7, 8, 1 }));
		aroundSideLeft.add(Store.get(sideDown, new byte[] { 7, 8, 1 }));
		aroundSideLeft.add(Store.get(sideBack, new byte[] { 3, 4, 5 }));

		sideMap.put(sideLeft, aroundSideLeft);

		List<Store> aroundSideFront = new ArrayList<>();
		aroundSideFront.add(Store.get(sideUp, new byte[] { 5, 6, 7 }));
		aroundSideFront.add(Store.get(sideRight, new byte[] { 7, 8, 1 }));
		aroundSideFront.add(Store.get(sideDown, new byte[] { 1, 2, 3 }));
		aroundSideFront.add(Store.get(sideLeft, new byte[] { 3, 4, 5 }));

		sideMap.put(sideFront, aroundSideFront);

		List<Store> aroundSideBack = new ArrayList<>();
		aroundSideBack.add(Store.get(sideUp, new byte[] { 1, 2, 3 }));
		aroundSideBack.add(Store.get(sideLeft, new byte[] { 7, 8, 1 }));
		aroundSideBack.add(Store.get(sideDown, new byte[] { 5, 6, 7 }));
		aroundSideBack.add(Store.get(sideRight, new byte[] { 3, 4, 5 }));

		sideMap.put(sideBack, aroundSideBack);

		// layers

		List<Store> aroundLayerM = new ArrayList<>();
		aroundLayerM.add(Store.get(sideUp, new byte[] { 2, 0, 6 }));
		aroundLayerM.add(Store.get(sideFront, new byte[] { 2, 0, 6 }));
		aroundLayerM.add(Store.get(sideDown, new byte[] { 2, 0, 6 }));
		aroundLayerM.add(Store.get(sideBack, new byte[] { 6, 0, 2 }));

		sideMap.put(layerM, aroundLayerM);

		List<Store> aroundLayerS = new ArrayList<>();
		aroundLayerS.add(Store.get(sideUp, new byte[] { 8, 0, 4 }));
		aroundLayerS.add(Store.get(sideRight, new byte[] { 2, 0, 6 }));
		aroundLayerS.add(Store.get(sideDown, new byte[] { 4, 0, 8 }));
		aroundLayerS.add(Store.get(sideLeft, new byte[] { 6, 0, 2 }));

		sideMap.put(layerS, aroundLayerS);

		List<Store> aroundLayerE = new ArrayList<>();
		aroundLayerE.add(Store.get(sideFront, new byte[] { 8, 0, 4 }));
		aroundLayerE.add(Store.get(sideRight, new byte[] { 8, 0, 4 }));
		aroundLayerE.add(Store.get(sideBack, new byte[] { 8, 0, 4 }));
		aroundLayerE.add(Store.get(sideLeft, new byte[] { 8, 0, 4 }));

		sideMap.put(layerE, aroundLayerE);

	}

	public enum Color {
		WHITE, YELLOW, GREEN, BLUE, RED, ORANGE
	}

	private final static class Store {

		private final byte[] side;
		private final byte[] tileIndexes;
		private final byte[] colorsBuffer;

		private Store(byte[] side, byte[] tileIndexes) {
			this.side = side;
			this.tileIndexes = tileIndexes;
			this.colorsBuffer = new byte[3];
		}

		private static Store get(byte[] side, byte[] tileIndexes) {
			return new Store(side, tileIndexes);
		}

		public void fillColorsBuffer() {
			for (int i = 0; i < colorsBuffer.length; i++) {
				colorsBuffer[i] = side[tileIndexes[i]];
			}
		}

		public void setColorsInSide(byte[] colors) {
			for (int i = 0; i < colors.length; i++) {
				side[tileIndexes[i]] = colors[i];
			}
		}

	}

	public void showCube() {

		System.out.println();

		System.out.println("         " + sideUp[1] + " " + sideUp[2] + " " + sideUp[3]);
		System.out.println("         " + sideUp[8] + " " + sideUp[0] + " " + sideUp[4]);
		System.out.println("         " + sideUp[7] + " " + sideUp[6] + " " + sideUp[5]);
		System.out.println();
		System.out.println(sideLeft[1] + " " + sideLeft[2] + " " + sideLeft[3] + "    " + sideFront[1] + " " + sideFront[2] + " "
				+ sideFront[3] + "    " + sideRight[1] + " " + sideRight[2] + " " + sideRight[3] + "    " + sideBack[1] + " "
				+ sideBack[2] + " " + sideBack[3]);
		System.out.println(sideLeft[8] + " " + sideLeft[0] + " " + sideLeft[4] + "    " + sideFront[8] + " " + sideFront[0] + " "
				+ sideFront[4] + "    " + sideRight[8] + " " + sideRight[0] + " " + sideRight[4] + "    " + sideBack[8] + " "
				+ sideBack[0] + " " + sideBack[4]);
		System.out.println(sideLeft[7] + " " + sideLeft[6] + " " + sideLeft[5] + "    " + sideFront[7] + " " + sideFront[6] + " "
				+ sideFront[5] + "    " + sideRight[7] + " " + sideRight[6] + " " + sideRight[5] + "    " + sideBack[7] + " "
				+ sideBack[6] + " " + sideBack[5]);
		System.out.println();
		System.out.println("         " + sideDown[1] + " " + sideDown[2] + " " + sideDown[3]);
		System.out.println("         " + sideDown[8] + " " + sideDown[0] + " " + sideDown[4]);
		System.out.println("         " + sideDown[7] + " " + sideDown[6] + " " + sideDown[5]);

	}

	// TODO moves

	// --------------------------------------------------[X]
	public void X() {
		Rw();
		Lr();
	}

	public void X2() {
		Rw2();
		L2();
	}

	public void Xr() {
		Rwr();
		L();
	}

	// --------------------------------------------------[Y]
	public void Y() {
		Uw();
		Dr();
	}

	public void Y2() {
		Uw2();
		D2();
	}

	public void Yr() {
		Uwr();
		D();
	}

	// --------------------------------------------------[Z]
	public void Z() {
		Fw();
		Br();
	}

	public void Z2() {
		Fw2();
		B2();
	}

	public void Zr() {
		Fwr();
		B();
	}

	// --------------------------------------------------[F]
	public void F() {
		rotateSideClockwise(sideFront);
	}

	public void F2() {
		rotateSideTwice(sideFront);
	}

	public void Fr() {
		rotateSideAntiClockwise(sideFront);
	}

	public void Fw() {
		F();
		S();
	}

	public void Fw2() {
		F2();
		S2();
	}

	public void Fwr() {
		Fr();
		Sr();
	}

	// --------------------------------------------------[D]
	public void D() {
		rotateSideClockwise(sideDown);
	}

	public void D2() {
		rotateSideTwice(sideDown);
	}

	public void Dr() {
		rotateSideAntiClockwise(sideDown);
	}

	public void Dw() {
		D();
		E();
	}

	public void Dw2() {
		D2();
		E2();
	}

	public void Dwr() {
		Dr();
		Er();
	}

	// --------------------------------------------------[L]
	public void L() {
		rotateSideClockwise(sideLeft);
	}

	public void L2() {
		rotateSideTwice(sideLeft);
	}

	public void Lr() {
		rotateSideAntiClockwise(sideLeft);
	}

	public void Lw() {
		L();
		M();
	}

	public void Lw2() {
		L2();
		M2();
	}

	public void Lwr() {
		Lr();
		Mr();
	}

	// --------------------------------------------------[U]
	public void U() {
		rotateSideClockwise(sideUp);
	}

	public void U2() {
		rotateSideTwice(sideUp);
	}

	public void Ur() {
		rotateSideAntiClockwise(sideUp);
	}

	public void Uw() {
		U();
		Er();
	}

	public void Uw2() {
		U2();
		E2();
	}

	public void Uwr() {
		Ur();
		E();
	}

	// --------------------------------------------------[R]
	public void R() {
		rotateSideClockwise(sideRight);
	}

	public void R2() {
		rotateSideTwice(sideRight);
	}

	public void Rr() {
		rotateSideAntiClockwise(sideRight);
	}

	public void Rw() {
		R();
		Mr();
	}

	public void Rw2() {
		R2();
		M2();
	}

	public void Rwr() {
		Rr();
		M();
	}

	// --------------------------------------------------[B]
	public void B() {
		rotateSideClockwise(sideBack);
	}

	public void B2() {
		rotateSideTwice(sideBack);
	}

	public void Br() {
		rotateSideAntiClockwise(sideBack);
	}

	public void Bw() {
		B();
		Sr();
	}

	public void Bw2() {
		B2();
		S2();
	}

	public void Bwr() {
		Br();
		S();
	}

	// --------------------------------------------------[M]
	public void M() {
		rotateLayerClockwise(layerM);
	}

	public void M2() {
		rotateLayerTwice(layerM);
	}

	public void Mr() {
		rotateLayerAntiClockwise(layerM);
	}

	// --------------------------------------------------[S]
	public void S() {
		rotateLayerClockwise(layerS);
	}

	public void S2() {
		rotateLayerTwice(layerS);
	}

	public void Sr() {
		rotateLayerAntiClockwise(layerS);
	}

	// --------------------------------------------------[E]
	public void E() {
		rotateLayerClockwise(layerE);
	}

	public void E2() {
		rotateLayerTwice(layerE);
	}

	public void Er() {
		rotateLayerAntiClockwise(layerE);
	}

	// TODO rotation mapping

	{
		rotationMapping = new HashMap<>();

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

}
