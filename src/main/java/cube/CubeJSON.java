package cube;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class CubeJSON {

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	private Brick.Color[] sideDown;
	private Brick.Color[] sideUp;
	private Brick.Color[] sideRight;
	private Brick.Color[] sideLeft;
	private Brick.Color[] sideFront;
	private Brick.Color[] sideBack;

	public static CubeJSON getNewInstance() {
		return new CubeJSON();
	}

	public Cube getCubeFromJSON(String json) throws ReadCubeException {
		return getCubeFromJSON(json, Cube.getNewInstance());
	}

	public Cube getCubeFromJSON(File jsonFile, Cube cube) throws ReadCubeException {
		return getCubeFromJSON(readJSONFromFile(jsonFile), cube);
	}

	public Cube getCubeFromJSON(String json, Cube cube) throws ReadCubeException {
		readJSON(json);

		cube.getSideUp().changeSideColors(sideUp);
		cube.getSideDown().changeSideColors(sideDown);
		cube.getSideLeft().changeSideColors(sideLeft);
		cube.getSideRight().changeSideColors(sideRight);
		cube.getSideFront().changeSideColors(sideFront);
		cube.getSideBack().changeSideColors(sideBack);

		return cube;
	}

	public String getJSONFromCube(Cube cube) throws WriteCubeException {
		readCube(cube);
		try {
			return jsonMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new WriteCubeException();
		}
	}

	public CubeJSON readCube(Cube cube) {
		sideUp = cube.getSideUp().getSideColors();
		sideDown = cube.getSideDown().getSideColors();
		sideLeft = cube.getSideLeft().getSideColors();
		sideRight = cube.getSideRight().getSideColors();
		sideFront = cube.getSideFront().getSideColors();
		sideBack = cube.getSideBack().getSideColors();

		return this;
	}

	private String readJSONFromFile(File jsonFile) throws ReadCubeException {
		try {
			return jsonMapper.writeValueAsString(jsonMapper.readValue(jsonFile, CubeJSON.class));
		} catch (IOException e) {
			throw new ReadCubeException();
		}
	}

	private void readJSON(String json) throws ReadCubeException {
		try {
			CubeJSON cubeJSON = jsonMapper.readValue(json, CubeJSON.class);

			sideUp = cubeJSON.getSideUp();
			sideDown = cubeJSON.getSideDown();
			sideLeft = cubeJSON.getSideLeft();
			sideRight = cubeJSON.getSideRight();
			sideFront = cubeJSON.getSideFront();
			sideBack = cubeJSON.getSideBack();

		} catch (IOException e) {
			throw new ReadCubeException();
		}
	}

}
