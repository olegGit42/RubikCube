package rubick.Rubick;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cube.CubeController;
import view.View;

public class RubickMain {
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");

	public static boolean console = false;
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
				CubeController cube = appContext.getBean("cubeController", CubeController.class);

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
			}

		} catch (Exception e) {
			System.err.println("Error in RubickMain");
		}

	}
}
