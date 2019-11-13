package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cube.Cube;
import cube.CubeController;
import cube.Rotate;
import cube.Side;
import rubick.Rubick.RubickMain;

public class View extends JFrame {
	private static final long serialVersionUID = -8028973046728553519L;

	public static CubeController cubeController = RubickMain.appContext.getBean("cubeController", CubeController.class);
	public static Cube cube = cubeController.getCube();
	public static Map<String, Color> colorMapping;
	public static Map<Integer, Rotate> rotateMapping;

	private JPanel contentPane;
	private JPanel sideF;
	private JPanel brickF1;
	private JPanel brickF2;
	private JPanel brickF3;
	private JPanel brickF4;
	private JPanel brickF5;
	private JPanel brickF6;
	private JPanel brickF7;
	private JPanel brickF8;
	private JPanel brickF9;
	private JPanel sideD;
	private JPanel brickD1;
	private JPanel brickD2;
	private JPanel brickD3;
	private JPanel brickD4;
	private JPanel brickD5;
	private JPanel brickD6;
	private JPanel brickD7;
	private JPanel brickD8;
	private JPanel brickD9;
	private JPanel sideU;
	private JPanel brickU1;
	private JPanel brickU2;
	private JPanel brickU3;
	private JPanel brickU4;
	private JPanel brickU5;
	private JPanel brickU6;
	private JPanel brickU7;
	private JPanel brickU8;
	private JPanel brickU9;
	private JPanel sideL;
	private JPanel brickL1;
	private JPanel brickL2;
	private JPanel brickL3;
	private JPanel brickL4;
	private JPanel brickL5;
	private JPanel brickL6;
	private JPanel brickL7;
	private JPanel brickL8;
	private JPanel brickL9;
	private JPanel sideR;
	private JPanel brickR1;
	private JPanel brickR2;
	private JPanel brickR3;
	private JPanel brickR4;
	private JPanel brickR5;
	private JPanel brickR6;
	private JPanel brickR7;
	private JPanel brickR8;
	private JPanel brickR9;
	private JPanel sideB;
	private JPanel brickB1;
	private JPanel brickB2;
	private JPanel brickB3;
	private JPanel brickB4;
	private JPanel brickB5;
	private JPanel brickB6;
	private JPanel brickB7;
	private JPanel brickB8;
	private JPanel brickB9;
	private JButton btnR;
	private JButton btnL;
	private JButton btnU;
	private JButton btnD;
	private JButton btnF;
	private JButton btnB;
	private JButton btnSolve;
	private JButton btnLr;
	private JButton btnRr;
	private JButton btnUr;
	private JButton btnDr;
	private JButton btnFr;
	private JButton btnBr;
	private JButton btnL2;
	private JButton btnR2;
	private JButton btnU2;
	private JButton btnD2;
	private JButton btnF2;
	private JButton btnB2;
	private JButton btnLw;
	private JButton btnRw;
	private JButton btnUw;
	private JButton btnDw;
	private JButton btnFw;
	private JButton btnBw;
	private JButton btnLwr;
	private JButton btnRwr;
	private JButton btnUwr;
	private JButton btnDwr;
	private JButton btnFwr;
	private JButton btnBwr;
	private JButton btnLw2;
	private JButton btnRw2;
	private JButton btnUw2;
	private JButton btnDw2;
	private JButton btnFw2;
	private JButton btnBw2;
	private JButton btnScramble;

	static {
		colorMapping = new HashMap<>();
		colorMapping.put("w", Color.WHITE);
		colorMapping.put("b", Color.BLUE);
		colorMapping.put("r", Color.RED);
		colorMapping.put("g", Color.GREEN);
		colorMapping.put("o", Color.ORANGE);
		colorMapping.put("y", Color.YELLOW);

		rotateMapping = new HashMap<>();
		rotateMapping.put(1, cubeController::R);
		rotateMapping.put(2, cubeController::L);
		rotateMapping.put(3, cubeController::U);
		rotateMapping.put(4, cubeController::D);
		rotateMapping.put(5, cubeController::F);
		rotateMapping.put(6, cubeController::B);
		rotateMapping.put(7, cubeController::Rr);
		rotateMapping.put(8, cubeController::Lr);
		rotateMapping.put(9, cubeController::Ur);
		rotateMapping.put(10, cubeController::Dr);
		rotateMapping.put(11, cubeController::Fr);
		rotateMapping.put(12, cubeController::Br);
		rotateMapping.put(13, cubeController::M);
		rotateMapping.put(14, cubeController::S);
		rotateMapping.put(15, cubeController::E);
		rotateMapping.put(13, cubeController::Mr);
		rotateMapping.put(14, cubeController::Sr);
		rotateMapping.put(15, cubeController::Er);
		rotateMapping.put(16, cubeController::R2);
		rotateMapping.put(17, cubeController::L2);
		rotateMapping.put(18, cubeController::U2);
		rotateMapping.put(19, cubeController::D2);
		rotateMapping.put(20, cubeController::F2);
		rotateMapping.put(21, cubeController::B2);
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Rubick");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		sideF = new JPanel();
		sideF.setBounds(141, 131, 120, 120);
		sideF.setBorder(new LineBorder(new Color(0, 0, 0)));

		sideD = new JPanel();
		sideD.setBounds(141, 257, 120, 120);
		sideD.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideD.setLayout(new GridLayout(0, 3, 2, 2));

		brickD1 = new JPanel();
		brickD1.setBackground(Color.RED);
		sideD.add(brickD1);

		brickD2 = new JPanel();
		brickD2.setBackground(Color.RED);
		sideD.add(brickD2);

		brickD3 = new JPanel();
		brickD3.setBackground(Color.RED);
		sideD.add(brickD3);

		brickD4 = new JPanel();
		brickD4.setBackground(Color.RED);
		sideD.add(brickD4);

		brickD5 = new JPanel();
		brickD5.setBackground(Color.RED);
		sideD.add(brickD5);

		brickD6 = new JPanel();
		brickD6.setBackground(Color.RED);
		sideD.add(brickD6);

		brickD7 = new JPanel();
		brickD7.setBackground(Color.RED);
		sideD.add(brickD7);

		brickD8 = new JPanel();
		brickD8.setBackground(Color.RED);
		sideD.add(brickD8);

		brickD9 = new JPanel();
		brickD9.setBackground(Color.RED);
		sideD.add(brickD9);

		sideU = new JPanel();
		sideU.setBounds(141, 5, 120, 120);
		sideU.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideU.setLayout(new GridLayout(0, 3, 2, 2));

		brickU1 = new JPanel();
		brickU1.setBackground(Color.ORANGE);
		sideU.add(brickU1);

		brickU2 = new JPanel();
		brickU2.setBackground(Color.ORANGE);
		sideU.add(brickU2);

		brickU3 = new JPanel();
		brickU3.setBackground(Color.ORANGE);
		sideU.add(brickU3);

		brickU4 = new JPanel();
		brickU4.setBackground(Color.ORANGE);
		sideU.add(brickU4);

		brickU5 = new JPanel();
		brickU5.setBackground(Color.ORANGE);
		sideU.add(brickU5);

		brickU6 = new JPanel();
		brickU6.setBackground(Color.ORANGE);
		sideU.add(brickU6);

		brickU7 = new JPanel();
		brickU7.setBackground(Color.ORANGE);
		sideU.add(brickU7);

		brickU8 = new JPanel();
		brickU8.setBackground(Color.ORANGE);
		sideU.add(brickU8);

		brickU9 = new JPanel();
		brickU9.setBackground(Color.ORANGE);
		sideU.add(brickU9);

		sideL = new JPanel();
		sideL.setBounds(15, 131, 120, 120);
		sideL.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideL.setLayout(new GridLayout(0, 3, 2, 2));

		brickL1 = new JPanel();
		brickL1.setBackground(Color.GREEN);
		sideL.add(brickL1);

		brickL2 = new JPanel();
		brickL2.setBackground(Color.GREEN);
		sideL.add(brickL2);

		brickL3 = new JPanel();
		brickL3.setBackground(Color.GREEN);
		sideL.add(brickL3);

		brickL4 = new JPanel();
		brickL4.setBackground(Color.GREEN);
		sideL.add(brickL4);

		brickL5 = new JPanel();
		brickL5.setBackground(Color.GREEN);
		sideL.add(brickL5);

		brickL6 = new JPanel();
		brickL6.setBackground(Color.GREEN);
		sideL.add(brickL6);

		brickL7 = new JPanel();
		brickL7.setBackground(Color.GREEN);
		sideL.add(brickL7);

		brickL8 = new JPanel();
		brickL8.setBackground(Color.GREEN);
		sideL.add(brickL8);

		brickL9 = new JPanel();
		brickL9.setBackground(Color.GREEN);
		sideL.add(brickL9);

		sideR = new JPanel();
		sideR.setBounds(267, 131, 120, 120);
		sideR.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideR.setLayout(new GridLayout(0, 3, 2, 2));

		brickR1 = new JPanel();
		brickR1.setBackground(Color.BLUE);
		sideR.add(brickR1);

		brickR2 = new JPanel();
		brickR2.setBackground(Color.BLUE);
		sideR.add(brickR2);

		brickR3 = new JPanel();
		brickR3.setBackground(Color.BLUE);
		sideR.add(brickR3);

		brickR4 = new JPanel();
		brickR4.setBackground(Color.BLUE);
		sideR.add(brickR4);

		brickR5 = new JPanel();
		brickR5.setBackground(Color.BLUE);
		sideR.add(brickR5);

		brickR6 = new JPanel();
		brickR6.setBackground(Color.BLUE);
		sideR.add(brickR6);

		brickR7 = new JPanel();
		brickR7.setBackground(Color.BLUE);
		sideR.add(brickR7);

		brickR8 = new JPanel();
		brickR8.setBackground(Color.BLUE);
		sideR.add(brickR8);

		brickR9 = new JPanel();
		brickR9.setBackground(Color.BLUE);
		sideR.add(brickR9);

		sideB = new JPanel();
		sideB.setBounds(393, 131, 120, 120);
		sideB.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideB.setLayout(new GridLayout(0, 3, 2, 2));

		brickB1 = new JPanel();
		brickB1.setBackground(Color.YELLOW);
		sideB.add(brickB1);

		brickB2 = new JPanel();
		brickB2.setBackground(Color.YELLOW);
		sideB.add(brickB2);

		brickB3 = new JPanel();
		brickB3.setBackground(Color.YELLOW);
		sideB.add(brickB3);

		brickB4 = new JPanel();
		brickB4.setBackground(Color.YELLOW);
		sideB.add(brickB4);

		brickB5 = new JPanel();
		brickB5.setBackground(Color.YELLOW);
		sideB.add(brickB5);

		brickB6 = new JPanel();
		brickB6.setBackground(Color.YELLOW);
		sideB.add(brickB6);

		brickB7 = new JPanel();
		brickB7.setBackground(Color.YELLOW);
		sideB.add(brickB7);

		brickB8 = new JPanel();
		brickB8.setBackground(Color.YELLOW);
		sideB.add(brickB8);

		brickB9 = new JPanel();
		brickB9.setBackground(Color.YELLOW);
		sideB.add(brickB9);

		btnR = new JButton("R");
		btnR.setBounds(621, 16, 55, 23);
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.R();
				showCubeView();
			}
		});

		btnL = new JButton("L");
		btnL.setBounds(559, 16, 55, 23);
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.L();
				showCubeView();
			}
		});

		btnU = new JButton("U");
		btnU.setBounds(683, 16, 55, 23);
		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.U();
				showCubeView();
			}
		});

		btnD = new JButton("D");
		btnD.setBounds(745, 16, 55, 23);
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.D();
				showCubeView();
			}
		});

		btnF = new JButton("F");
		btnF.setBounds(807, 16, 55, 23);
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.F();
				showCubeView();
			}
		});

		btnB = new JButton("B");
		btnB.setBounds(869, 16, 55, 23);
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.B();
				showCubeView();
			}
		});

		btnSolve = new JButton("Solve");
		btnSolve.setBounds(807, 354, 117, 23);
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completeCube();
			}
		});
		sideF.setLayout(new GridLayout(0, 3, 2, 2));

		brickF1 = new JPanel();
		brickF1.setBackground(Color.WHITE);
		sideF.add(brickF1);

		brickF2 = new JPanel();
		brickF2.setBackground(Color.WHITE);
		sideF.add(brickF2);

		brickF3 = new JPanel();
		brickF3.setBackground(Color.WHITE);
		sideF.add(brickF3);

		brickF4 = new JPanel();
		brickF4.setBackground(Color.WHITE);
		sideF.add(brickF4);

		brickF5 = new JPanel();
		brickF5.setBackground(Color.WHITE);
		sideF.add(brickF5);

		brickF6 = new JPanel();
		brickF6.setBackground(Color.WHITE);
		sideF.add(brickF6);

		brickF7 = new JPanel();
		brickF7.setBackground(Color.WHITE);
		sideF.add(brickF7);

		brickF8 = new JPanel();
		brickF8.setBackground(Color.WHITE);
		sideF.add(brickF8);

		brickF9 = new JPanel();
		brickF9.setBackground(Color.WHITE);
		sideF.add(brickF9);
		contentPane.setLayout(null);
		contentPane.add(sideU);
		contentPane.add(sideD);
		contentPane.add(sideL);
		contentPane.add(sideF);
		contentPane.add(sideR);
		contentPane.add(sideB);
		contentPane.add(btnSolve);
		contentPane.add(btnR);
		contentPane.add(btnL);
		contentPane.add(btnU);
		contentPane.add(btnD);
		contentPane.add(btnF);
		contentPane.add(btnB);

		btnLr = new JButton("Lr");
		btnLr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Lr();
				showCubeView();
			}
		});
		btnLr.setBounds(559, 44, 55, 23);
		contentPane.add(btnLr);

		btnRr = new JButton("Rr");
		btnRr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Rr();
				showCubeView();
			}
		});
		btnRr.setBounds(621, 44, 55, 23);
		contentPane.add(btnRr);

		btnUr = new JButton("Ur");
		btnUr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Ur();
				showCubeView();
			}
		});
		btnUr.setBounds(683, 44, 55, 23);
		contentPane.add(btnUr);

		btnDr = new JButton("Dr");
		btnDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Dr();
				showCubeView();
			}
		});
		btnDr.setBounds(745, 44, 55, 23);
		contentPane.add(btnDr);

		btnFr = new JButton("Fr");
		btnFr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Fr();
				showCubeView();
			}
		});
		btnFr.setBounds(807, 44, 55, 23);
		contentPane.add(btnFr);

		btnBr = new JButton("Br");
		btnBr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Br();
				showCubeView();
			}
		});
		btnBr.setBounds(869, 44, 55, 23);
		contentPane.add(btnBr);

		btnL2 = new JButton("L2");
		btnL2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.L2();
				showCubeView();
			}
		});
		btnL2.setBounds(559, 72, 55, 23);
		contentPane.add(btnL2);

		btnR2 = new JButton("R2");
		btnR2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.R2();
				showCubeView();
			}
		});
		btnR2.setBounds(621, 72, 55, 23);
		contentPane.add(btnR2);

		btnU2 = new JButton("U2");
		btnU2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.U2();
				showCubeView();
			}
		});
		btnU2.setBounds(683, 72, 55, 23);
		contentPane.add(btnU2);

		btnD2 = new JButton("D2");
		btnD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.D2();
				showCubeView();
			}
		});
		btnD2.setBounds(745, 72, 55, 23);
		contentPane.add(btnD2);

		btnF2 = new JButton("F2");
		btnF2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.F2();
				showCubeView();
			}
		});
		btnF2.setBounds(807, 72, 55, 23);
		contentPane.add(btnF2);

		btnB2 = new JButton("B2");
		btnB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.B2();
				showCubeView();
			}
		});
		btnB2.setBounds(869, 72, 55, 23);
		contentPane.add(btnB2);

		btnLw = new JButton("Lw");
		btnLw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Lw();
				showCubeView();
			}
		});
		btnLw.setBounds(559, 116, 55, 23);
		contentPane.add(btnLw);

		btnRw = new JButton("Rw");
		btnRw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Rw();
				showCubeView();
			}
		});
		btnRw.setBounds(621, 116, 55, 23);
		contentPane.add(btnRw);

		btnUw = new JButton("Uw");
		btnUw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Uw();
				showCubeView();
			}
		});
		btnUw.setBounds(683, 116, 55, 23);
		contentPane.add(btnUw);

		btnDw = new JButton("Dw");
		btnDw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Dw();
				showCubeView();
			}
		});
		btnDw.setBounds(745, 116, 55, 23);
		contentPane.add(btnDw);

		btnFw = new JButton("Fw");
		btnFw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Fw();
				showCubeView();
			}
		});
		btnFw.setBounds(807, 116, 55, 23);
		contentPane.add(btnFw);

		btnBw = new JButton("Bw");
		btnBw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Bw();
				showCubeView();
			}
		});
		btnBw.setBounds(869, 116, 55, 23);
		contentPane.add(btnBw);

		btnLwr = new JButton("Lwr");
		btnLwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Lwr();
				showCubeView();
			}
		});
		btnLwr.setBounds(559, 144, 55, 23);
		contentPane.add(btnLwr);

		btnRwr = new JButton("Rwr");
		btnRwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Rwr();
				showCubeView();
			}
		});
		btnRwr.setBounds(621, 144, 55, 23);
		contentPane.add(btnRwr);

		btnUwr = new JButton("Uwr");
		btnUwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Uwr();
				showCubeView();
			}
		});
		btnUwr.setBounds(683, 144, 55, 23);
		contentPane.add(btnUwr);

		btnDwr = new JButton("Dwr");
		btnDwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Dwr();
				showCubeView();
			}
		});
		btnDwr.setBounds(745, 144, 55, 23);
		contentPane.add(btnDwr);

		btnFwr = new JButton("Fwr");
		btnFwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Fwr();
				showCubeView();
			}
		});
		btnFwr.setBounds(807, 144, 55, 23);
		contentPane.add(btnFwr);

		btnBwr = new JButton("Bwr");
		btnBwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Bwr();
				showCubeView();
			}
		});
		btnBwr.setBounds(869, 144, 55, 23);
		contentPane.add(btnBwr);

		btnLw2 = new JButton("Lw2");
		btnLw2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Lw2();
				showCubeView();
			}
		});
		btnLw2.setBounds(559, 172, 55, 23);
		contentPane.add(btnLw2);

		btnRw2 = new JButton("Rw2");
		btnRw2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Rw2();
				showCubeView();
			}
		});
		btnRw2.setBounds(621, 172, 55, 23);
		contentPane.add(btnRw2);

		btnUw2 = new JButton("Uw2");
		btnUw2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Uw2();
				showCubeView();
			}
		});
		btnUw2.setBounds(683, 172, 55, 23);
		contentPane.add(btnUw2);

		btnDw2 = new JButton("Dw2");
		btnDw2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Dw2();
				showCubeView();
			}
		});
		btnDw2.setBounds(745, 172, 55, 23);
		contentPane.add(btnDw2);

		btnFw2 = new JButton("Fw2");
		btnFw2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Fw2();
				showCubeView();
			}
		});
		btnFw2.setBounds(807, 172, 55, 23);
		contentPane.add(btnFw2);

		btnBw2 = new JButton("Bw2");
		btnBw2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Bw2();
				showCubeView();
			}
		});
		btnBw2.setBounds(869, 172, 55, 23);
		contentPane.add(btnBw2);

		JButton btnM = new JButton("M");
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.M();
				showCubeView();
			}
		});
		btnM.setBounds(559, 220, 55, 23);
		contentPane.add(btnM);

		JButton btnS = new JButton("S");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.S();
				showCubeView();
			}
		});
		btnS.setBounds(621, 220, 55, 23);
		contentPane.add(btnS);

		JButton btnE = new JButton("E");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.E();
				showCubeView();
			}
		});
		btnE.setBounds(683, 220, 55, 23);
		contentPane.add(btnE);

		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.X();
				showCubeView();
			}
		});
		btnX.setBounds(745, 220, 55, 23);
		contentPane.add(btnX);

		JButton btnY = new JButton("Y");
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Y();
				showCubeView();
			}
		});
		btnY.setBounds(807, 220, 55, 23);
		contentPane.add(btnY);

		JButton btnZ = new JButton("Z");
		btnZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Z();
				showCubeView();
			}
		});
		btnZ.setBounds(869, 220, 55, 23);
		contentPane.add(btnZ);

		JButton btnMr = new JButton("Mr");
		btnMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Mr();
				showCubeView();
			}
		});
		btnMr.setBounds(559, 248, 55, 23);
		contentPane.add(btnMr);

		JButton btnSr = new JButton("Sr");
		btnSr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Sr();
				showCubeView();
			}
		});
		btnSr.setBounds(621, 248, 55, 23);
		contentPane.add(btnSr);

		JButton btnEr = new JButton("Er");
		btnEr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Er();
				showCubeView();
			}
		});
		btnEr.setBounds(683, 248, 55, 23);
		contentPane.add(btnEr);

		JButton btnXr = new JButton("Xr");
		btnXr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Xr();
				showCubeView();
			}
		});
		btnXr.setBounds(745, 248, 55, 23);
		contentPane.add(btnXr);

		JButton btnYr = new JButton("Yr");
		btnYr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Yr();
				showCubeView();
			}
		});
		btnYr.setBounds(807, 248, 55, 23);
		contentPane.add(btnYr);

		JButton btnZr = new JButton("Zr");
		btnZr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Zr();
				showCubeView();
			}
		});
		btnZr.setBounds(869, 248, 55, 23);
		contentPane.add(btnZr);

		JButton btnM2 = new JButton("M2");
		btnM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.M2();
				showCubeView();
			}
		});
		btnM2.setBounds(559, 276, 55, 23);
		contentPane.add(btnM2);

		JButton btnS2 = new JButton("S2");
		btnS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.S2();
				showCubeView();
			}
		});
		btnS2.setBounds(621, 276, 55, 23);
		contentPane.add(btnS2);

		JButton btnE2 = new JButton("E2");
		btnE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.E2();
				showCubeView();
			}
		});
		btnE2.setBounds(683, 276, 55, 23);
		contentPane.add(btnE2);

		JButton btnX2 = new JButton("X2");
		btnX2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.X2();
				showCubeView();
			}
		});
		btnX2.setBounds(745, 276, 55, 23);
		contentPane.add(btnX2);

		JButton btnY2 = new JButton("Y2");
		btnY2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Y2();
				showCubeView();
			}
		});
		btnY2.setBounds(807, 276, 55, 23);
		contentPane.add(btnY2);

		JButton btnZ2 = new JButton("Z2");
		btnZ2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Z2();
				showCubeView();
			}
		});
		btnZ2.setBounds(869, 276, 55, 23);
		contentPane.add(btnZ2);

		btnScramble = new JButton("Scramble");
		btnScramble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scramble();
			}
		});
		btnScramble.setBounds(559, 354, 117, 23);
		contentPane.add(btnScramble);
	}

	public void showCubeView(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
		showCubeView();
	}

	public void showCubeView() {
		Side side = cube.getSideF();
		brickF1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickF2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickF3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickF4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickF5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickF6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickF7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickF8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickF9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideD();
		brickD1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickD2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickD3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickD4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickD5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickD6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickD7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickD8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickD9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideU();
		brickU1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickU2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickU3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickU4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickU5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickU6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickU7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickU8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickU9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideL();
		brickL1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickL2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickL3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickL4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickL5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickL6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickL7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickL8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickL9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideR();
		brickR1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickR2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickR3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickR4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickR5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickR6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickR7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickR8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickR9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideB();
		brickB1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickB2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickB3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickB4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickB5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickB6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickB7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickB8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickB9.setBackground(colorMapping.get(side.getBrickColor(9)));
	}

	public void rotateTest() throws IllegalArgumentException, IllegalAccessException {
		int millis = 200;
		int quantity = 1;

		showCubeView(millis);
		cubeController.Xr();
		showCubeView(millis);

		for (int i = 0; i < quantity; i++) {
			cubeController.R();
			showCubeView(millis);
			cubeController.L();
			showCubeView(millis);
			cubeController.U();
			showCubeView(millis);
			cubeController.Rr();
			showCubeView(millis);
			cubeController.Ur();
			showCubeView(millis);
		}
	}

	public void completeCube() {
		cube = cubeController.completeCube();
		showCubeView();
	}

	public void scramble() {
		for (int i = 0; i < (100 + (int) (Math.random() * 50)); i++) {
			rotateMapping.get(1 + (int) (Math.random() * 21)).rotate();
		}
		showCubeView();
	}

}
