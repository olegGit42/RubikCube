package rubick.rubick;

import java.awt.EventQueue;
import java.io.File;
import java.util.Arrays;

import javax.swing.UIManager;

import org.ini4j.Wini;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cube.Cube;
import cube.CubeController;
import cube.CubeJSON;
import cube.ReadCubeException;
import cube.WriteCubeException;
import view.View;

@SuppressWarnings("unused")
public class RubickMain {
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");

	public static boolean console = false; // false true
	public static boolean view = true;

	public static void main(String[] args) {
		try {

			if (view) {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Throwable e) {
					e.printStackTrace();
				}

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							View frame = new View();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			if (console) {
				// testCube();
				// testIniReader();

				testCubeJSON();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testCubeJSON() {
		try {
			CubeJSON cubeJSON = CubeJSON.getNewInstance();

			CubeController cc = CubeController.getNewInstanceWithCube();
			Cube cube = cc.getCube();

			cc.Br();
			cc.R();
			cc.F();
			cc.Lr();

			String cj = cubeJSON.getJSONFromCube(cube);

			System.out.println(cj);

			CubeController cc2 = CubeController.getNewInstanceWithCube();
			Cube cube2 = cc2.getCube();

			cc2.showCube();

			cubeJSON.getCubeFromJSON(cj, cube2);

			cc2.showCube();

		} catch (WriteCubeException | IllegalArgumentException | IllegalAccessException | ReadCubeException e) {
			e.printStackTrace();
		}
	}

	private static void testCube() {
		try {

			CubeController cube = CubeController.getNewInstanceWithCube();

			int millis = 50;
			int quantity = 3;

			cube.Zr();
			cube.Xr();
			cube.showCube();

			for (int i = 0; i < quantity; i++) {
				cube.T(millis);
				cube.changeCenterColors(millis);
				cube.T(millis);
				for (int j = 0; j < 6; j++) {
					cube.pifPaf(millis);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testIniReader() {
		try {

			Wini ini = new Wini(new File(RubickMain.class.getClassLoader().getResource("algorithms.ini").getPath()));
			String[] algorithm = ini.get("cross1", "2").split(" ");

			System.out.println("algorithm: " + Arrays.toString(algorithm));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
