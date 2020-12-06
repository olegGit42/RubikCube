package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import cube.Brick;
import cube.Cube;
import cube.CubeController;
import cube.CubeJSON;
import cube.NewCube;
import cube.ReadCubeException;
import cube.Rotate;
import cube.Side;
import cube.WriteCubeException;
import rubick.rubick.RubickMain;

public class View extends JFrame {
	private static final long serialVersionUID = -8028973046728553519L;

	public static CubeController cubeController = RubickMain.appContext.getBean("cubeController", CubeController.class);
	public static Cube cube = cubeController.getCube();
	public static Map<Brick.Color, Color> colorMapping;
	public static final NewCube newCube;
	public static final Map<Integer, Rotate> rotateMapping;

	private Brick.Color brickColorTemplate = null;

	private JPanel contentPane;
	private JTextArea solvingAlgorithm;
	private JTextArea notification;
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
	private static List<JPanel> colorTemplatesList = new ArrayList<>();
	private JPanel colorTemplates;
	private JPanel templateWhite;
	private JPanel templateBlue;
	private JPanel templateRed;
	private JPanel templateGreen;
	private JPanel templateOrange;
	private JPanel templateYellow;
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

	private Webcam webcam;
	private WebcamPanel webcamPanel;
	private JButton btnPhotoB;
	private JButton btnPhotoF;
	private JButton btnPhotoU;
	private JButton btnPhotoD;
	private JButton btnPhotoR;
	private JButton btnPhotoL;

	static {
		colorMapping = new HashMap<>();
		colorMapping.put(Brick.Color.WHITE, Color.WHITE);
		colorMapping.put(Brick.Color.BLUE, Color.BLUE);
		colorMapping.put(Brick.Color.RED, Color.RED);
		colorMapping.put(Brick.Color.GREEN, Color.GREEN);
		colorMapping.put(Brick.Color.ORANGE, Color.ORANGE);
		colorMapping.put(Brick.Color.YELLOW, Color.YELLOW);

		newCube = NewCube.getNewEmptyInstance();

		rotateMapping = new HashMap<>();
		rotateMapping.put(1, newCube::R);
		rotateMapping.put(2, newCube::L);
		rotateMapping.put(3, newCube::U);
		rotateMapping.put(4, newCube::D);
		rotateMapping.put(5, newCube::F);
		rotateMapping.put(6, newCube::B);
		rotateMapping.put(7, newCube::Rr);
		rotateMapping.put(8, newCube::Lr);
		rotateMapping.put(9, newCube::Ur);
		rotateMapping.put(10, newCube::Dr);
		rotateMapping.put(11, newCube::Fr);
		rotateMapping.put(12, newCube::Br);
		rotateMapping.put(13, newCube::M);
		rotateMapping.put(14, newCube::S);
		rotateMapping.put(15, newCube::E);
		rotateMapping.put(13, newCube::Mr);
		rotateMapping.put(14, newCube::Sr);
		rotateMapping.put(15, newCube::Er);
		rotateMapping.put(16, newCube::R2);
		rotateMapping.put(17, newCube::L2);
		rotateMapping.put(18, newCube::U2);
		rotateMapping.put(19, newCube::D2);
		rotateMapping.put(20, newCube::F2);
		rotateMapping.put(21, newCube::B2);
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Rubik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 510);
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
		brickD1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 1);
			}
		});

		brickD2 = new JPanel();
		brickD2.setBackground(Color.RED);
		sideD.add(brickD2);
		brickD2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 2);
			}
		});

		brickD3 = new JPanel();
		brickD3.setBackground(Color.RED);
		sideD.add(brickD3);
		brickD3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 3);
			}
		});

		brickD4 = new JPanel();
		brickD4.setBackground(Color.RED);
		sideD.add(brickD4);
		brickD4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 4);
			}
		});

		brickD5 = new JPanel();
		brickD5.setBackground(Color.RED);
		sideD.add(brickD5);
		brickD5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 5);
			}
		});

		brickD6 = new JPanel();
		brickD6.setBackground(Color.RED);
		sideD.add(brickD6);
		brickD6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 6);
			}
		});

		brickD7 = new JPanel();
		brickD7.setBackground(Color.RED);
		sideD.add(brickD7);
		brickD7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 7);
			}
		});

		brickD8 = new JPanel();
		brickD8.setBackground(Color.RED);
		sideD.add(brickD8);
		brickD8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 8);
			}
		});

		brickD9 = new JPanel();
		brickD9.setBackground(Color.RED);
		sideD.add(brickD9);
		brickD9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideDown(), 9);
			}
		});

		sideU = new JPanel();
		sideU.setBounds(141, 5, 120, 120);
		sideU.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideU.setLayout(new GridLayout(0, 3, 2, 2));

		// TODO webcam
		try {
			webcam = Webcam.getDefault();
			// webcam.setViewSize(WebcamResolution.VGA.getSize());

			webcamPanel = new WebcamPanel(webcam, false);
			webcamPanel.setMirrored(false);
			webcamPanel.setBounds(393, 5, 120, 120);
			webcamPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			webcamPanel.setLayout(new GridLayout(0, 3, 2, 2));
		} catch (Exception e) {
			webcam = null;
			webcamPanel = null;
		}

		JToggleButton webcamToggleButton = new JToggleButton();
		webcamToggleButton.setMargin(new Insets(0, 0, 0, 0));
		webcamToggleButton.setBounds(15, 5, 80, 40);

		webcamToggleButton.setLayout(new BorderLayout());
		JLabel lblWebcamName = new JLabel("Webcam", JLabel.CENTER);
		JLabel lblWebcamActive = new JLabel("OFF", JLabel.CENTER);
		webcamToggleButton.add(BorderLayout.NORTH, lblWebcamName);
		webcamToggleButton.add(BorderLayout.SOUTH, lblWebcamActive);

		webcamToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (webcamToggleButton.isSelected()) {
						webcam.open();
						webcamPanel.start();
						lblWebcamActive.setText("ON");
						setVisibleWebcamControls(true);
					} else {
						webcam.close();
						lblWebcamActive.setText("OFF");
						setVisibleWebcamControls(false);
					}
				} catch (Exception e2) {
					lblWebcamActive.setText("OFF");
					setVisibleWebcamControls(false);
				}
			}
		});

		btnPhotoB = new JButton("B");
		btnPhotoB.setMargin(new Insets(0, 0, 0, 0));
		btnPhotoB.setBounds(473, 257, 40, 23);
		btnPhotoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSideColorsFromWebcam(cube.getSideBack());
			}
		});

		btnPhotoF = new JButton("F");
		btnPhotoF.setMargin(new Insets(0, 0, 0, 0));
		btnPhotoF.setBounds(267, 102, 40, 23);
		btnPhotoF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSideColorsFromWebcam(cube.getSideFront());
			}
		});

		btnPhotoU = new JButton("U");
		btnPhotoU.setMargin(new Insets(0, 0, 0, 0));
		btnPhotoU.setBounds(267, 5, 40, 23);
		btnPhotoU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSideColorsFromWebcam(cube.getSideUp());
			}
		});

		btnPhotoD = new JButton("D");
		btnPhotoD.setMargin(new Insets(0, 0, 0, 0));
		btnPhotoD.setBounds(95, 354, 40, 23);
		btnPhotoD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSideColorsFromWebcam(cube.getSideDown());
			}
		});

		btnPhotoR = new JButton("R");
		btnPhotoR.setMargin(new Insets(0, 0, 0, 0));
		btnPhotoR.setBounds(347, 257, 40, 23);
		btnPhotoR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSideColorsFromWebcam(cube.getSideRight());
			}
		});

		btnPhotoL = new JButton("L");
		btnPhotoL.setMargin(new Insets(0, 0, 0, 0));
		btnPhotoL.setBounds(15, 102, 40, 23);
		btnPhotoL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSideColorsFromWebcam(cube.getSideLeft());
			}
		});

		setVisibleWebcamControls(false);

		brickU1 = new JPanel();
		brickU1.setBackground(Color.ORANGE);
		sideU.add(brickU1);
		brickU1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 1);
			}
		});

		brickU2 = new JPanel();
		brickU2.setBackground(Color.ORANGE);
		sideU.add(brickU2);
		brickU2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 2);
			}
		});

		brickU3 = new JPanel();
		brickU3.setBackground(Color.ORANGE);
		sideU.add(brickU3);
		brickU3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 3);
			}
		});

		brickU4 = new JPanel();
		brickU4.setBackground(Color.ORANGE);
		sideU.add(brickU4);
		brickU4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 4);
			}
		});

		brickU5 = new JPanel();
		brickU5.setBackground(Color.ORANGE);
		sideU.add(brickU5);
		brickU5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 5);
			}
		});

		brickU6 = new JPanel();
		brickU6.setBackground(Color.ORANGE);
		sideU.add(brickU6);
		brickU6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 6);
			}
		});

		brickU7 = new JPanel();
		brickU7.setBackground(Color.ORANGE);
		sideU.add(brickU7);
		brickU7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 7);
			}
		});

		brickU8 = new JPanel();
		brickU8.setBackground(Color.ORANGE);
		sideU.add(brickU8);
		brickU8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 8);
			}
		});

		brickU9 = new JPanel();
		brickU9.setBackground(Color.ORANGE);
		sideU.add(brickU9);
		brickU9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideUp(), 9);
			}
		});

		sideL = new JPanel();
		sideL.setBounds(15, 131, 120, 120);
		sideL.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideL.setLayout(new GridLayout(0, 3, 2, 2));

		brickL1 = new JPanel();
		brickL1.setBackground(Color.GREEN);
		sideL.add(brickL1);
		brickL1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 1);
			}
		});

		brickL2 = new JPanel();
		brickL2.setBackground(Color.GREEN);
		sideL.add(brickL2);
		brickL2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 2);
			}
		});

		brickL3 = new JPanel();
		brickL3.setBackground(Color.GREEN);
		sideL.add(brickL3);
		brickL3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 3);
			}
		});

		brickL4 = new JPanel();
		brickL4.setBackground(Color.GREEN);
		sideL.add(brickL4);
		brickL4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 4);
			}
		});

		brickL5 = new JPanel();
		brickL5.setBackground(Color.GREEN);
		sideL.add(brickL5);
		brickL5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 5);
			}
		});

		brickL6 = new JPanel();
		brickL6.setBackground(Color.GREEN);
		sideL.add(brickL6);
		brickL6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 6);
			}
		});

		brickL7 = new JPanel();
		brickL7.setBackground(Color.GREEN);
		sideL.add(brickL7);
		brickL7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 7);
			}
		});

		brickL8 = new JPanel();
		brickL8.setBackground(Color.GREEN);
		sideL.add(brickL8);
		brickL8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 8);
			}
		});

		brickL9 = new JPanel();
		brickL9.setBackground(Color.GREEN);
		sideL.add(brickL9);
		brickL9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideLeft(), 9);
			}
		});

		sideR = new JPanel();
		sideR.setBounds(267, 131, 120, 120);
		sideR.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideR.setLayout(new GridLayout(0, 3, 2, 2));

		brickR1 = new JPanel();
		brickR1.setBackground(Color.BLUE);
		sideR.add(brickR1);
		brickR1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 1);
			}
		});

		brickR2 = new JPanel();
		brickR2.setBackground(Color.BLUE);
		sideR.add(brickR2);
		brickR2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 2);
			}
		});

		brickR3 = new JPanel();
		brickR3.setBackground(Color.BLUE);
		sideR.add(brickR3);
		brickR3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 3);
			}
		});

		brickR4 = new JPanel();
		brickR4.setBackground(Color.BLUE);
		sideR.add(brickR4);
		brickR4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 4);
			}
		});

		brickR5 = new JPanel();
		brickR5.setBackground(Color.BLUE);
		sideR.add(brickR5);
		brickR5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 5);
			}
		});

		brickR6 = new JPanel();
		brickR6.setBackground(Color.BLUE);
		sideR.add(brickR6);
		brickR6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 6);
			}
		});

		brickR7 = new JPanel();
		brickR7.setBackground(Color.BLUE);
		sideR.add(brickR7);
		brickR7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 7);
			}
		});

		brickR8 = new JPanel();
		brickR8.setBackground(Color.BLUE);
		sideR.add(brickR8);
		brickR8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 8);
			}
		});

		brickR9 = new JPanel();
		brickR9.setBackground(Color.BLUE);
		sideR.add(brickR9);
		brickR9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideRight(), 9);
			}
		});

		// colorTemplates begin

		colorTemplates = new JPanel();
		colorTemplates.setBounds(393, 297, 120, 80);
		colorTemplates.setBorder(new LineBorder(new Color(0, 0, 0)));
		colorTemplates.setLayout(new GridLayout(0, 3, 2, 2));

		templateWhite = new JPanel();
		templateWhite.setBackground(Color.WHITE);
		templateWhite.setBorder(new LineBorder(Color.GRAY, 10));
		colorTemplates.add(templateWhite);
		templateWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBrickColorTemplate(templateWhite, Brick.Color.WHITE);
			}
		});

		templateBlue = new JPanel();
		templateBlue.setBackground(Color.BLUE);
		templateBlue.setBorder(new LineBorder(Color.GRAY, 10));
		colorTemplates.add(templateBlue);
		templateBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBrickColorTemplate(templateBlue, Brick.Color.BLUE);
			}
		});

		templateRed = new JPanel();
		templateRed.setBackground(Color.RED);
		templateRed.setBorder(new LineBorder(Color.GRAY, 10));
		colorTemplates.add(templateRed);
		templateRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBrickColorTemplate(templateRed, Brick.Color.RED);
			}
		});

		templateGreen = new JPanel();
		templateGreen.setBackground(Color.GREEN);
		templateGreen.setBorder(new LineBorder(Color.GRAY, 10));
		colorTemplates.add(templateGreen);
		templateGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBrickColorTemplate(templateGreen, Brick.Color.GREEN);
			}
		});

		templateOrange = new JPanel();
		templateOrange.setBackground(Color.ORANGE);
		templateOrange.setBorder(new LineBorder(Color.GRAY, 10));
		colorTemplates.add(templateOrange);
		templateOrange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBrickColorTemplate(templateOrange, Brick.Color.ORANGE);
			}
		});

		templateYellow = new JPanel();
		templateYellow.setBackground(Color.YELLOW);
		templateYellow.setBorder(new LineBorder(Color.GRAY, 10));
		colorTemplates.add(templateYellow);
		templateYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBrickColorTemplate(templateYellow, Brick.Color.YELLOW);
			}
		});

		colorTemplatesList.clear();
		colorTemplatesList.add(templateWhite);
		colorTemplatesList.add(templateBlue);
		colorTemplatesList.add(templateRed);
		colorTemplatesList.add(templateGreen);
		colorTemplatesList.add(templateOrange);
		colorTemplatesList.add(templateYellow);

		// colorTemplates end

		sideB = new JPanel();
		sideB.setBounds(393, 131, 120, 120);
		sideB.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideB.setLayout(new GridLayout(0, 3, 2, 2));

		brickB1 = new JPanel();
		brickB1.setBackground(Color.YELLOW);
		sideB.add(brickB1);
		brickB1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 1);
			}
		});

		brickB2 = new JPanel();
		brickB2.setBackground(Color.YELLOW);
		sideB.add(brickB2);
		brickB2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 2);
			}
		});

		brickB3 = new JPanel();
		brickB3.setBackground(Color.YELLOW);
		sideB.add(brickB3);
		brickB3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 3);
			}
		});

		brickB4 = new JPanel();
		brickB4.setBackground(Color.YELLOW);
		sideB.add(brickB4);
		brickB4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 4);
			}
		});

		brickB5 = new JPanel();
		brickB5.setBackground(Color.YELLOW);
		sideB.add(brickB5);
		brickB5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 5);
			}
		});

		brickB6 = new JPanel();
		brickB6.setBackground(Color.YELLOW);
		sideB.add(brickB6);
		brickB6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 6);
			}
		});

		brickB7 = new JPanel();
		brickB7.setBackground(Color.YELLOW);
		sideB.add(brickB7);
		brickB7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 7);
			}
		});

		brickB8 = new JPanel();
		brickB8.setBackground(Color.YELLOW);
		sideB.add(brickB8);
		brickB8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 8);
			}
		});

		brickB9 = new JPanel();
		brickB9.setBackground(Color.YELLOW);
		sideB.add(brickB9);
		brickB9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideBack(), 9);
			}
		});

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

		// TODO btnSolve
		btnSolve = new JButton("Solve");
		btnSolve.setBounds(824, 354, 100, 23);
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// scramble();
				completeCube();
			}
		});
		sideF.setLayout(new GridLayout(0, 3, 2, 2));

		brickF1 = new JPanel();
		brickF1.setBackground(Color.WHITE);
		sideF.add(brickF1);
		brickF1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 1);
			}
		});

		brickF2 = new JPanel();
		brickF2.setBackground(Color.WHITE);
		sideF.add(brickF2);
		brickF2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 2);
			}
		});

		brickF3 = new JPanel();
		brickF3.setBackground(Color.WHITE);
		sideF.add(brickF3);
		brickF3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 3);
			}
		});

		brickF4 = new JPanel();
		brickF4.setBackground(Color.WHITE);
		sideF.add(brickF4);
		brickF4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 4);
			}
		});

		brickF5 = new JPanel();
		brickF5.setBackground(Color.WHITE);
		sideF.add(brickF5);
		brickF5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 5);
			}
		});

		brickF6 = new JPanel();
		brickF6.setBackground(Color.WHITE);
		sideF.add(brickF6);
		brickF6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 6);
			}
		});

		brickF7 = new JPanel();
		brickF7.setBackground(Color.WHITE);
		sideF.add(brickF7);
		brickF7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 7);
			}
		});

		brickF8 = new JPanel();
		brickF8.setBackground(Color.WHITE);
		sideF.add(brickF8);
		brickF8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 8);
			}
		});

		brickF9 = new JPanel();
		brickF9.setBackground(Color.WHITE);
		sideF.add(brickF9);
		brickF9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorChange(cubeController.getCube().getSideFront(), 9);
			}
		});

		contentPane.setLayout(null);
		try {
			contentPane.add(webcamPanel);
		} catch (Exception e) {
		}
		contentPane.add(webcamToggleButton);
		contentPane.add(btnPhotoB);
		contentPane.add(btnPhotoF);
		contentPane.add(btnPhotoU);
		contentPane.add(btnPhotoD);
		contentPane.add(btnPhotoR);
		contentPane.add(btnPhotoL);
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
		contentPane.add(colorTemplates);

		btnLr = new JButton("L'");
		btnLr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Lr();
				showCubeView();
			}
		});
		btnLr.setBounds(559, 44, 55, 23);
		contentPane.add(btnLr);

		btnRr = new JButton("R'");
		btnRr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Rr();
				showCubeView();
			}
		});
		btnRr.setBounds(621, 44, 55, 23);
		contentPane.add(btnRr);

		btnUr = new JButton("U'");
		btnUr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Ur();
				showCubeView();
			}
		});
		btnUr.setBounds(683, 44, 55, 23);
		contentPane.add(btnUr);

		btnDr = new JButton("D'");
		btnDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Dr();
				showCubeView();
			}
		});
		btnDr.setBounds(745, 44, 55, 23);
		contentPane.add(btnDr);

		btnFr = new JButton("F'");
		btnFr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Fr();
				showCubeView();
			}
		});
		btnFr.setBounds(807, 44, 55, 23);
		contentPane.add(btnFr);

		btnBr = new JButton("B'");
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

		btnLwr = new JButton("Lw'");
		btnLwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Lwr();
				showCubeView();
			}
		});
		btnLwr.setBounds(559, 144, 55, 23);
		contentPane.add(btnLwr);

		btnRwr = new JButton("Rw'");
		btnRwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Rwr();
				showCubeView();
			}
		});
		btnRwr.setBounds(621, 144, 55, 23);
		contentPane.add(btnRwr);

		btnUwr = new JButton("Uw'");
		btnUwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Uwr();
				showCubeView();
			}
		});
		btnUwr.setBounds(683, 144, 55, 23);
		contentPane.add(btnUwr);

		btnDwr = new JButton("Dw'");
		btnDwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Dwr();
				showCubeView();
			}
		});
		btnDwr.setBounds(745, 144, 55, 23);
		contentPane.add(btnDwr);

		btnFwr = new JButton("Fw'");
		btnFwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Fwr();
				showCubeView();
			}
		});
		btnFwr.setBounds(807, 144, 55, 23);
		contentPane.add(btnFwr);

		btnBwr = new JButton("Bw'");
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

		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.X();
				showCubeView();
			}
		});
		btnX.setBounds(745, 220, 55, 23);
		contentPane.add(btnX);

		JButton btnY = new JButton("y");
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Y();
				showCubeView();
			}
		});
		btnY.setBounds(807, 220, 55, 23);
		contentPane.add(btnY);

		JButton btnZ = new JButton("z");
		btnZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Z();
				showCubeView();
			}
		});
		btnZ.setBounds(869, 220, 55, 23);
		contentPane.add(btnZ);

		JButton btnMr = new JButton("M'");
		btnMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Mr();
				showCubeView();
			}
		});
		btnMr.setBounds(559, 248, 55, 23);
		contentPane.add(btnMr);

		JButton btnSr = new JButton("S'");
		btnSr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Sr();
				showCubeView();
			}
		});
		btnSr.setBounds(621, 248, 55, 23);
		contentPane.add(btnSr);

		JButton btnEr = new JButton("E'");
		btnEr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Er();
				showCubeView();
			}
		});
		btnEr.setBounds(683, 248, 55, 23);
		contentPane.add(btnEr);

		JButton btnXr = new JButton("x'");
		btnXr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Xr();
				showCubeView();
			}
		});
		btnXr.setBounds(745, 248, 55, 23);
		contentPane.add(btnXr);

		JButton btnYr = new JButton("y'");
		btnYr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Yr();
				showCubeView();
			}
		});
		btnYr.setBounds(807, 248, 55, 23);
		contentPane.add(btnYr);

		JButton btnZr = new JButton("z'");
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

		JButton btnX2 = new JButton("x2");
		btnX2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.X2();
				showCubeView();
			}
		});
		btnX2.setBounds(745, 276, 55, 23);
		contentPane.add(btnX2);

		JButton btnY2 = new JButton("y2");
		btnY2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.Y2();
				showCubeView();
			}
		});
		btnY2.setBounds(807, 276, 55, 23);
		contentPane.add(btnY2);

		JButton btnZ2 = new JButton("z2");
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
		btnScramble.setBounds(559, 354, 100, 23);
		contentPane.add(btnScramble);

		JButton btnReadCube = new JButton("Read cube");
		btnReadCube.setMargin(new Insets(0, 0, 0, 0));
		btnReadCube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CubeJSON.getNewInstance().getCubeFromJSON(solvingAlgorithm.getText().trim(), cube);
					notification.setText(null);
					showCubeView();
				} catch (ReadCubeException e1) {
					e1.printStackTrace();
					notification.setText(e1.getMessage());
				}
			}
		});
		btnReadCube.setBounds(278, 354, 100, 23);
		contentPane.add(btnReadCube);

		JButton btnWriteCube = new JButton("Write cube");
		btnWriteCube.setMargin(new Insets(0, 0, 0, 0));
		btnWriteCube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					solvingAlgorithm.setText(CubeJSON.getNewInstance().getJSONFromCube(cube));
				} catch (WriteCubeException e1) {
					e1.printStackTrace();
					notification.setText(e1.getMessage());
				}
			}
		});
		btnWriteCube.setBounds(278, 330, 100, 23);
		contentPane.add(btnWriteCube);

		// TODO JButton("<<")
		JButton btnMoveBegin = new JButton("<<");
		btnMoveBegin.setMargin(new Insets(0, 0, 0, 0));
		btnMoveBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.doSolve(false);
				solvingAlgorithm.setText(cubeController.showSolvingAlgorithm());
				notification.setText(cubeController.getSolvingAlgorithm().isEmpty() ? ""
						: cubeController.getSolvingAlgorithm().get(cubeController.getNextMoveIndex()));

				showCubeView();
			}
		});
		btnMoveBegin.setBounds(664, 354, 42, 23);
		contentPane.add(btnMoveBegin);

		JButton btnMovePrev = new JButton("<");
		btnMovePrev.setMargin(new Insets(0, 0, 0, 0));
		btnMovePrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.doSolveMove(false);
				solvingAlgorithm.setText(cubeController.showSolvingAlgorithm());
				notification.setText(cubeController.getSolvingAlgorithm().isEmpty() ? ""
						: ((cubeController.isNext() ? "" : "reverted ")
								+ cubeController.getSolvingAlgorithm().get(cubeController.getNextMoveIndex())));

				showCubeView();
			}
		});
		btnMovePrev.setBounds(706, 354, 35, 23);
		contentPane.add(btnMovePrev);

		JButton btnMoveNext = new JButton(">");
		btnMoveNext.setMargin(new Insets(0, 0, 0, 0));
		btnMoveNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.doSolveMove(true);
				solvingAlgorithm.setText(cubeController.showSolvingAlgorithm());
				notification.setText(cubeController.getSolvingAlgorithm().isEmpty() ? ""
						: ((cubeController.isNext() ? "" : "reverted ")
								+ cubeController.getSolvingAlgorithm().get(cubeController.getNextMoveIndex())));

				cube = cubeController.getCube();
				showCubeView();
			}
		});
		btnMoveNext.setBounds(741, 354, 35, 23);
		contentPane.add(btnMoveNext);

		JButton btnMoveEnd = new JButton(">>");
		btnMoveEnd.setMargin(new Insets(0, 0, 0, 0));
		btnMoveEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.doSolve(true);
				solvingAlgorithm.setText(cubeController.showSolvingAlgorithm());
				notification.setText(cubeController.getSolvingAlgorithm().isEmpty() ? ""
						: ((cubeController.isNext() ? "" : "reverted ")
								+ cubeController.getSolvingAlgorithm().get(cubeController.getNextMoveIndex())));

				cube = cubeController.getCube();
				showCubeView();
			}
		});
		btnMoveEnd.setBounds(778, 354, 42, 23);
		contentPane.add(btnMoveEnd);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeController.resetCube();
				showCubeView();
			}
		});
		btnReset.setBounds(15, 354, 70, 23);
		contentPane.add(btnReset);

		solvingAlgorithm = new JTextArea();
		solvingAlgorithm.setLineWrap(true);

		JScrollPane solvingAlgorithmScrollPane = new JScrollPane(solvingAlgorithm);
		solvingAlgorithmScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		solvingAlgorithmScrollPane.setBounds(15, 380, 909, 88);

		contentPane.add(solvingAlgorithmScrollPane);

		notification = new JTextArea();
		notification.setLineWrap(true);

		JScrollPane notificationScrollPane = new JScrollPane(notification);
		notificationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notificationScrollPane.setBounds(559, 300, 365, 55);

		contentPane.add(notificationScrollPane);

		cubeController.resetCube();

		showCubeView();
	}

	// TODO Color recognizing
	public void changeSideColorsFromWebcam(Side side) {
		if (webcam != null && webcam.isOpen()) {
			side.changeSideColors(getWebcamColors());
			showCubeView();
		} else {
			notification.setText("Webcam is OFF");
		}
	}

	public Brick.Color[] getWebcamColors() {
		Brick.Color[] brickColors = new Brick.Color[9];

		BufferedImage image = webcam.getImage();

		int delta = Math.abs(image.getWidth() - image.getHeight());
		int x = 0;
		int y = 0;
		int step = 0;

		if (image.getWidth() > image.getHeight()) {
			x = delta;
			step = image.getHeight() / 4;
		} else {
			y = delta;
			step = image.getWidth() / 4;
		}

		Color[][] colors = loadPixelsFromImage(image);

		brickColors[0] = recognizeColor(colors[x + step][y + step]);
		brickColors[1] = recognizeColor(colors[x + step * 2][y + step]);
		brickColors[2] = recognizeColor(colors[x + step * 3][y + step]);
		brickColors[3] = recognizeColor(colors[x + step][y + step * 2]);
		brickColors[4] = recognizeColor(colors[x + step * 2][y + step * 2]);
		brickColors[5] = recognizeColor(colors[x + step * 3][y + step * 2]);
		brickColors[6] = recognizeColor(colors[x + step][y + step * 3]);
		brickColors[7] = recognizeColor(colors[x + step * 2][y + step * 3]);
		brickColors[8] = recognizeColor(colors[x + step * 3][y + step * 3]);

		return brickColors;
	}

	public static Color[][] loadPixelsFromImage(BufferedImage image) {

		Color[][] colors = new Color[image.getWidth()][image.getHeight()];

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				colors[x][y] = new Color(image.getRGB(x, y));
			}
		}

		return colors;
	}

	public static Brick.Color recognizeColor(Color color) {

		float hsb[] = new float[3];
		int r = (color.getRGB() >> 16) & 0xFF;
		int g = (color.getRGB() >> 8) & 0xFF;
		int b = (color.getRGB()) & 0xFF;
		Color.RGBtoHSB(r, g, b, hsb);

		if (hsb[1] < 0.1 && hsb[2] > 0.9)
			return Brick.Color.WHITE;
		else if (hsb[2] < 0.1)
			return Brick.Color.RED;
		else {
			float deg = hsb[0] * 360;
			if (deg >= 0 && deg < 10)
				return Brick.Color.RED;
			else if (deg >= 10 && deg < 62)
				return Brick.Color.ORANGE;
			else if (deg >= 62 && deg < 120)
				return Brick.Color.YELLOW;
			else if (deg >= 120 && deg < 165)
				return Brick.Color.GREEN;
			else if (deg >= 165 && deg < 210)
				return Brick.Color.WHITE;
			else if (deg >= 210 && deg < 270)
				return Brick.Color.BLUE;
			else
				return Brick.Color.RED;
		}

		/*
		 * if (hsb[1] < 0.1 && hsb[2] > 0.9) nearlyWhite(); else if (hsb[2] < 0.1)
		 * nearlyBlack(); else { float deg = hsb[0]*360; if (deg >= 0 && deg < 30)
		 * red(); else if (deg >= 30 && deg < 90) yellow(); else if (deg >= 90 && deg <
		 * 150) green(); else if (deg >= 150 && deg < 210) cyan(); else if (deg >= 210
		 * && deg < 270) blue(); else if (deg >= 270 && deg < 330) magenta(); else
		 * red(); }
		 */
	}

	public void setVisibleWebcamControls(boolean isVisible) {
		try {
			webcamPanel.setVisible(isVisible);
		} catch (Exception e) {
		}
		btnPhotoB.setVisible(isVisible);
		btnPhotoF.setVisible(isVisible);
		btnPhotoU.setVisible(isVisible);
		btnPhotoD.setVisible(isVisible);
		btnPhotoR.setVisible(isVisible);
		btnPhotoL.setVisible(isVisible);
	}

	public void colorChange(Side side, int brickIndex) {
		side.changeBrickColor(brickIndex, brickColorTemplate);
		showCubeView();
	}

	public void setBrickColorTemplate(JPanel panel, Brick.Color color) {
		if (panel.getBorder() == null) {
			brickColorTemplate = null;
			panel.setBorder(new LineBorder(Color.GRAY, 10));
		} else {
			colorTemplatesList.forEach(p -> p.setBorder(new LineBorder(Color.GRAY, 10)));
			panel.setBorder(null);
			brickColorTemplate = color;
		}
	}

	public void showCubeView(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
		showCubeView();
	}

	public void showCubeView() {
		Side side = cube.getSideFront();
		brickF1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickF2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickF3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickF4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickF5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickF6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickF7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickF8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickF9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideDown();
		brickD1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickD2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickD3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickD4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickD5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickD6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickD7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickD8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickD9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideUp();
		brickU1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickU2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickU3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickU4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickU5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickU6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickU7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickU8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickU9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideLeft();
		brickL1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickL2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickL3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickL4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickL5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickL6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickL7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickL8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickL9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideRight();
		brickR1.setBackground(colorMapping.get(side.getBrickColor(1)));
		brickR2.setBackground(colorMapping.get(side.getBrickColor(2)));
		brickR3.setBackground(colorMapping.get(side.getBrickColor(3)));
		brickR4.setBackground(colorMapping.get(side.getBrickColor(4)));
		brickR5.setBackground(colorMapping.get(side.getBrickColor(5)));
		brickR6.setBackground(colorMapping.get(side.getBrickColor(6)));
		brickR7.setBackground(colorMapping.get(side.getBrickColor(7)));
		brickR8.setBackground(colorMapping.get(side.getBrickColor(8)));
		brickR9.setBackground(colorMapping.get(side.getBrickColor(9)));

		side = cube.getSideBack();
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

	// TODO completeCube()
	public void completeCube() {
		long start = System.currentTimeMillis();
		cubeController.completeCube();
		solvingAlgorithm.setText(cubeController.showSolvingAlgorithm());

		@SuppressWarnings("unused")
		String duration = false ? "Duration: " + String.valueOf((System.currentTimeMillis() - start) / 1000) + " sec\n" : "";

		notification.setText(duration + (cubeController.getSolvingAlgorithm().isEmpty() ? ""
				: cubeController.getSolvingAlgorithm().get(cubeController.getNextMoveIndex())));
		showCubeView();
	}

	public void scramble2() {
		int i = 0;
		try {
			do {
				rotateMapping.get(1 + (int) (Math.random() * 21)).rotate();
				if (i++ % 10000 == 0) {
					System.out.println(i);
				}
			} while (!cubeController.cubeCompleted());

			System.out.println("Solved");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public void scramble() {
		newCube.copyFrom(cube);
		for (int i = 0; i < (1000 + (int) (Math.random() * 1000)); i++) {
			rotateMapping.get(1 + (int) (Math.random() * 21)).rotate();
		}
		newCube.copyTo(cube);
		showCubeView();
	}

}
