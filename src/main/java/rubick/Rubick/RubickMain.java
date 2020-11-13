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

	public static boolean view = true;// false true

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
			} else {
				// testCube();
				// testIniReader();
				// testCubeJSON();

				testNewCube();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// TODO testNewCube()
	private static void testNewCube() {

		// Copy test 2
		/*
		 * CubeController cc = CubeController.getNewInstanceWithCube();
		 * 
		 * cc.showCube();
		 * 
		 * NewCube newCube = NewCube.getNewFullInstance(); newCube.R();
		 * newCube.showCube();
		 * 
		 * newCube.copyTo(cc.getCube());
		 * 
		 * cc.showCube();
		 */

		// Copy test 1
		/*
		 * CubeController cc = CubeController.getNewInstanceWithCube();
		 * 
		 * cc.showCube();
		 * 
		 * NewCube newCube = NewCube.getNewFullInstance();
		 * 
		 * newCube.showCube();
		 * 
		 * NewCube newCube2 = NewCube.getNewEmptyInstance();
		 * 
		 * newCube2.showCube();
		 * 
		 * newCube2.copyFrom(newCube).showCube();
		 */

		// performance test
		/*
		 * NewCube newCube = NewCube.getNewFullInstance();
		 * 
		 * long start = System.currentTimeMillis();
		 * 
		 * for (int i = 0; i < 10000000; i++) { newCube.R(); newCube.U(); newCube.Rr();
		 * newCube.Ur(); }
		 * 
		 * System.out.println("New cube time: " + (System.currentTimeMillis() - start) /
		 * 1000 + " sec");
		 * 
		 * start = System.currentTimeMillis();
		 * 
		 * for (int i = 0; i < 10000; i++) { cc.R(); cc.U(); cc.Rr(); cc.Ur(); }
		 * 
		 * System.out.println("Old cube time: " + (System.currentTimeMillis() - start) /
		 * 1000 + " sec");
		 */

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

		} catch (WriteCubeException | IllegalArgumentException | ReadCubeException e) {
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
