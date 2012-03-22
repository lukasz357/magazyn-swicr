package siwc.magazyn;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import siwc.magazyn.panels.MapaMagazynu;
import siwc.magazyn.utils.MagazynUtils;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Magazyn {
	private static Logger log = Logger.getLogger(Magazyn.class);
	private JFrame frame;
	private JMenuItem saveFile;
	private JMenuItem openFile;
	private JMenuItem saveAsFile;
	private JMenuItem closeWindow;
	private JMenu mnZmienStyl;
	private JFileChooser fileChooser;
	private JLabel lblNewLabel = new JLabel("Magazyn");
	private MapaMagazynu mapa;
	private JButton btnRandomRegal1;
	private JButton btnRandomRegal2;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnRight;
	private JButton btnLeft;
	private JButton btnPlus;
	private JButton btnMinus;
	private JTextField levelTextField;

	private JButton btnStop;
	private JButton btnStart;
	private JLabel lblStatystyki;
	private JLabel lblZamwienia;
	private JButton btnDodaj;
	private JButton btnEdytuj;
	private JPanel panel;
	private JLabel lblPietro;
	private JPanel panel_1;
	private JButton btnRandomRegal3;
	private JButton btnRightRegal1;
	private JButton btnLeftRegal1;
	private JButton btnRightRegal2;
	private JButton btnRightRegal3;
	private JButton btnLeftRegal2;
	private JButton btnLeftRegal3;
	private JScrollPane KonsolaScrollPane;
	private JList KonsolaList;

	/* Listy konsoli/zamowien */
	private static DefaultListModel<String> konsolaListModel = new DefaultListModel<String>();
	private JLabel lblKonsola;
	private JLabel lblR;
	private JLabel lblR_1;
	private JLabel lblR_2;
	private JButton liftUp;
	private JButton liftDown;

	private int pietro = 0;
	private JRadioButton regal1_rb1;
	private JRadioButton regal1_rb2;
	private JCheckBox chckBoxRegal1;
	private JCheckBox chckBoxRegal2;
	private JRadioButton regal2_rb2;
	private JRadioButton regal2_rb1;
	private JCheckBox chckBoxRegal3;
	private JRadioButton regal3_rb2;
	private JRadioButton regal3_rb1;
	private JTextField textFieldRegal1;
	private JTextField textFieldRegal2;
	private JTextField textFieldRegal3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Magazyn window = new Magazyn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* temp */
	public void tymczasowoWypelnijKonsole() {
		konsolaListModel.addElement("Magazyn trollollololo");
	}

	/**
	 * Create the application.
	 */
	public Magazyn() {
		tymczasowoWypelnijKonsole();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lblNewLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 16));
		frame = new JFrame();
		frame.setTitle(MagazynUtils.frameTitle);
		frame.setSize(MagazynUtils.frameWidth, MagazynUtils.frameHeight);

		if (MagazynUtils.screenSize.getWidth() <= 1366)
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setResizable(false);
		// frame.setBounds(50, 50, MagazynUtils.frameWidth, MagazynUtils.frameHeight);
		log.info("Rozmiar okna: " + MagazynUtils.frameWidth + "x" + MagazynUtils.frameHeight + "\t" + frame.getWidth() + "x" + frame.getHeight());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// int result = JOptionPane.showConfirmDialog(frame, "Zamknąć program?", "Zamknąc?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				// if (result == JOptionPane.YES_OPTION) {
				// frame.dispose();
				// }
				// TODO
				frame.dispose();
				System.exit(0);
			}

		});
		ustawStyl();

		fileChooser = new JFileChooser();
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Plik tekstowy (*.txt)", "txt");
		fileChooser.setFileFilter(txtFilter);
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(true);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuPlik = new JMenu("Plik");
		menuBar.add(menuPlik);

		openFile = new JMenuItem("Otwórz plik");
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					log.info("Wybrano: " + fileChooser.getSelectedFile());

					saveFile.setEnabled(true);
					saveAsFile.setEnabled(true);
				}
			}
		});
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuPlik.add(openFile);
		menuPlik.addSeparator();

		saveFile = new JMenuItem("Zapisz");
		saveFile.setEnabled(false);
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO - ogarnac forme zapisywania
				int result = fileChooser.showSaveDialog(frame);

				if (result == JFileChooser.APPROVE_OPTION) {
					log.info("Zapisz jako: " + fileChooser.getSelectedFile().getName());
				}
			}
		});
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuPlik.add(saveFile);

		saveAsFile = new JMenuItem("Zapisz jako");
		saveAsFile.setEnabled(false);
		saveAsFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO - ogarnac forme zapisywania
				int result = fileChooser.showSaveDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					log.info("Zapisz jako: " + fileChooser.getSelectedFile().getName());
				}
			}
		});
		saveAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuPlik.add(saveAsFile);

		menuPlik.addSeparator();

		closeWindow = new JMenuItem("Zakończ");
		closeWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Zamknąć program?", "Zamknąc?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		closeWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuPlik.add(closeWindow);

		JMenu mnOkno = new JMenu("Okno");
		menuBar.add(mnOkno);

		mnZmienStyl = new JMenu("Zmień styl");
		mnOkno.add(mnZmienStyl);

		mapa = new MapaMagazynu();

		mapa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		/* STOP MAGAZYNU */
		btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.info("Magazyn został zatrzymany");

				/*
				 * TODO:
				 * 
				 * dodac wpis do konsoli
				 * 
				 * zatrzymac magazyn
				 */
				dodajWpisDoKonsoli("Magazyn został zatrzymany" + new Date().toString());
			}
		});

		/* START MAGAZYNU */
		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				log.info("Magazyn został uruchomiony" + new Date().toString());
				dodajWpisDoKonsoli("Magazyn został uruchomiony" + new Date().toString());
				/*
				 * TODO: dodac wpis do konsoli
				 * 
				 * uruchomic magazyn ;p
				 */
			}
		});

		lblStatystyki = new JLabel("Statystyki");

		lblZamwienia = new JLabel("Zamówienia");

		/* DODAJ ZAMOWIENIE */
		btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * TODO
				 * 
				 * dodaj zamowienie zaloguj w konsoli
				 */
				log.info("Dodano zamówienie");
			}
		});

		/* EDYTUJ ZAMOWIENIE */
		btnEdytuj = new JButton("Edytuj");
		btnEdytuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * TODO
				 * 
				 * edytuj zamowienie zaloguj w konsoli
				 */
				log.info("Edytowano zamówienie zamówienie");
			}
		});

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Panel testowy", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		KonsolaScrollPane = new JScrollPane();

		lblKonsola = new JLabel("Konsola");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
										groupLayout.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE).addPreferredGap(
												ComponentPlacement.RELATED).addComponent(panel, 0, 0, Short.MAX_VALUE)).addComponent(mapa, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE).addGroup(
										groupLayout.createSequentialGroup().addGap(10).addGroup(
												groupLayout.createParallelGroup(Alignment.LEADING).addComponent(KonsolaScrollPane, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE).addGroup(
														groupLayout.createSequentialGroup().addComponent(lblKonsola).addGap(743)))))).addGap(318).addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
								groupLayout.createSequentialGroup().addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
												groupLayout.createSequentialGroup().addComponent(btnDodaj).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnEdytuj)).addGroup(
												groupLayout.createSequentialGroup().addComponent(lblZamwienia).addGap(74)).addGroup(
												groupLayout.createSequentialGroup().addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE).addPreferredGap(
														ComponentPlacement.UNRELATED).addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))).addContainerGap())
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblStatystyki).addGap(39)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addComponent(panel,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
								groupLayout.createSequentialGroup().addGap(49).addComponent(lblZamwienia).addPreferredGap(ComponentPlacement.RELATED).addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnDodaj).addComponent(btnEdytuj))).addGroup(
								groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED).addComponent(mapa, GroupLayout.PREFERRED_SIZE, 396, 396))).addPreferredGap(
						ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
								groupLayout.createSequentialGroup().addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lblKonsola).addPreferredGap(ComponentPlacement.RELATED).addComponent(KonsolaScrollPane,
										GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)).addGroup(
								groupLayout.createSequentialGroup().addComponent(lblStatystyki).addGap(94).addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE, false).addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addComponent(
												btnStop, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)).addGap(75))).addGap(14)));
		// KonsolaList = new JList(konsolaLista.toArray());

		KonsolaList = new JList<>();
		KonsolaList.setModel(konsolaListModel);

		KonsolaScrollPane.setViewportView(KonsolaList);

		btnLeft = new JButton("left");

		btnUp = new JButton("up");
		btnUp.setMnemonic(KeyEvent.VK_UP);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.moveLiftUp();
			}
		});

		btnRight = new JButton("right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.moveLiftRight();
			}
		});

		btnDown = new JButton("down");
		btnDown.setMnemonic(KeyEvent.VK_DOWN);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.moveLiftDown();

			}
		});

		lblR = new JLabel("R1");
		lblR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		lblR_1 = new JLabel("R2");
		lblR_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		lblR_2 = new JLabel("R3");
		lblR_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		textFieldRegal1 = new JTextField();
		textFieldRegal1.setText("5");
		textFieldRegal1.setColumns(10);

		textFieldRegal2 = new JTextField();
		textFieldRegal2.setText("6");
		textFieldRegal2.setColumns(10);

		textFieldRegal3 = new JTextField();
		textFieldRegal3.setText("7");
		textFieldRegal3.setColumns(10);

		liftUp = new JButton("góra");
		liftUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.liftUp();
			}
		});

		liftDown = new JButton("dół");
		liftDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.lifDown();
			}
		});
		// Przyciski do liczby wolnych boxow w regale
		ButtonGroup regal1ButtonGroup = new ButtonGroup();

		regal1_rb1 = new JRadioButton("1");
		regal1_rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal1.setEnabled(false);
				mapa.setRegal1FreeBoxes(1);
			}
		});
		regal1_rb1.setSelected(mapa.getRegal1FreeBoxes() == 1);

		regal1_rb2 = new JRadioButton("2");
		regal1_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal1.setEnabled(true);
				mapa.setRegal1FreeBoxes(2);
			}
		});
		regal1_rb2.setSelected(mapa.getRegal1FreeBoxes() == 2);

		regal1ButtonGroup.add(regal1_rb1);
		regal1ButtonGroup.add(regal1_rb2);

		chckBoxRegal1 = new JCheckBox("Dolny regał");
		chckBoxRegal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chckBoxRegal1.setEnabled(mapa.getRegal1FreeBoxes() == 2);

		ButtonGroup regal2ButtonGroup = new ButtonGroup();

		regal2_rb1 = new JRadioButton("1");
		regal2_rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal2.setEnabled(false);
				mapa.setRegal2FreeBoxes(1);
				
			}
		});
		regal2_rb1.setSelected(mapa.getRegal2FreeBoxes() == 1);

		regal2_rb2 = new JRadioButton("2");
		regal2_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal2.setEnabled(true);
				mapa.setRegal2FreeBoxes(2);
			}
		});
		regal2_rb2.setSelected(mapa.getRegal2FreeBoxes() == 2);

		regal2ButtonGroup.add(regal2_rb1);
		regal2ButtonGroup.add(regal2_rb2);

		chckBoxRegal2 = new JCheckBox("Dolny regał");
		chckBoxRegal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		chckBoxRegal2.setEnabled(mapa.getRegal2FreeBoxes() == 2);

		ButtonGroup regal3ButtonGroup = new ButtonGroup();

		regal3_rb1 = new JRadioButton("1");
		regal3_rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal3.setEnabled(false);
				mapa.setRegal3FreeBoxes(1);
			}
		});
		regal3_rb1.setSelected(mapa.getRegal3FreeBoxes() == 1);

		regal3_rb2 = new JRadioButton("2");
		regal3_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal3.setEnabled(true);
				mapa.setRegal3FreeBoxes(2);
			}
		});
		regal3_rb2.setSelected(mapa.getRegal3FreeBoxes() == 2);

		regal3ButtonGroup.add(regal3_rb1);
		regal3ButtonGroup.add(regal3_rb2);

		chckBoxRegal3 = new JCheckBox("Dolny regał");
		chckBoxRegal3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chckBoxRegal3.setEnabled(mapa.getRegal3FreeBoxes() == 2);

		// TODO MOCK ZMAIAN W BOXACH - do wywalenia soon
		final Color[] colors = new Color[4];
		colors[0] = Color.WHITE;
		colors[1] = Color.BLACK;
		colors[2] = Color.RED;
		colors[3] = Color.YELLOW;

		btnRandomRegal2 = new JButton("random");
		btnRandomRegal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();

				char prefix = (char) (65 + rand.nextInt(MagazynUtils.rzedowWRegale));
				int sufix = rand.nextInt(MagazynUtils.kolumnWRegale);
				int c = rand.nextInt(colors.length);

				String position = prefix + Integer.toString(sufix + 1);

				mapa.zmienKolorBoksu("regal2", position, colors[c]);
			}
		});

		btnRandomRegal1 = new JButton("random");
		btnRandomRegal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();

				char prefix = (char) (65 + rand.nextInt(MagazynUtils.rzedowWRegale));
				int sufix = rand.nextInt(MagazynUtils.kolumnWRegale);
				int c = rand.nextInt(colors.length);

				String position = prefix + Integer.toString(sufix + 1);

				mapa.zmienKolorBoksu("regal1", position, colors[c]);
			}
		});

		btnRandomRegal3 = new JButton("random");
		btnRandomRegal3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();

				char prefix = (char) (65 + rand.nextInt(MagazynUtils.rzedowWRegale));
				int sufix = rand.nextInt(MagazynUtils.kolumnWRegale);
				int c = rand.nextInt(colors.length);

				String position = prefix + Integer.toString(sufix + 1);

				mapa.zmienKolorBoksu("regal3", position, colors[c]);
				log.info("R3: [" + position + "] color=" + c + "\t linia 192 jak nie wiesz jaki kolor");
			}
		});

		btnRightRegal1 = new JButton("prawo");
		btnRightRegal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldRegal1.getText().equals("") ? "1" : textFieldRegal1.getText();
				final int numbers  = Integer.parseInt(text) == 0 ? 1: Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom =  chckBoxRegal1.isSelected();
						for (int i = 0; i < numbers; i++) {
							MagazynUtils.sleep(MagazynUtils.boxMovingSleepTime);
							mapa.przesunBoxWPrawo("regal1", pietro, bottom);
						}

					}

				});
				t.start();
			}
		});

		btnRightRegal2 = new JButton("prawo");
		btnRightRegal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldRegal2.getText().equals("") ? "1" : textFieldRegal2.getText();
				final int numbers  = Integer.parseInt(text) == 0 ? 1: Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom =  chckBoxRegal2.isSelected();
						for (int i = 0; i < numbers; i++) {
							MagazynUtils.sleep(MagazynUtils.boxMovingSleepTime);
							mapa.przesunBoxWPrawo("regal2", pietro, bottom);
						}

					}

				});
				t.start();
			}
		});

		btnRightRegal3 = new JButton("prawo");
		btnRightRegal3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldRegal3.getText().equals("") ? "1" : textFieldRegal3.getText();
				final int numbers  = Integer.parseInt(text) == 0 ? 1: Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom =  chckBoxRegal3.isSelected();
						for (int i = 0; i < numbers; i++) {
							MagazynUtils.sleep(MagazynUtils.boxMovingSleepTime);
							mapa.przesunBoxWPrawo("regal3", pietro, bottom);
						}

					}

				});
				t.start();
				
			}
		});

		btnLeftRegal1 = new JButton("lewo");
		btnLeftRegal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldRegal1.getText().equals("") ? "1" : textFieldRegal1.getText();
				final int numbers  = Integer.parseInt(text) == 0 ? 1: Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom =  chckBoxRegal1.isSelected();
						for (int i = 0; i < numbers; i++) {
							MagazynUtils.sleep(MagazynUtils.boxMovingSleepTime);
							mapa.przesunBoxWLewo("regal1", pietro, bottom);
						}

					}

				});
				t.start();
			}
		});

		btnLeftRegal2 = new JButton("lewo");
		btnLeftRegal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = textFieldRegal2.getText().equals("") ? "1" : textFieldRegal2.getText();
				final int numbers  = Integer.parseInt(text) == 0 ? 1: Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						boolean bottom =  chckBoxRegal2.isSelected();
						for (int i = 0; i < numbers ; i++) {
							MagazynUtils.sleep(MagazynUtils.boxMovingSleepTime);
							mapa.przesunBoxWLewo("regal2", pietro, bottom);
						}

					}

				});
				t.start();
			}
		});

		btnLeftRegal3 = new JButton("lewo");
		btnLeftRegal3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = textFieldRegal3.getText().equals("") ? "1" : textFieldRegal3.getText();
				final int numbers  = Integer.parseInt(text) == 0 ? 1: Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom =  chckBoxRegal3.isSelected();
						for (int i = 0; i < numbers; i++) {
							MagazynUtils.sleep(MagazynUtils.boxMovingSleepTime);
							mapa.przesunBoxWLewo("regal3", pietro, bottom);
						}

					}

				});
				t.start();
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panel_1.createSequentialGroup().addGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
								gl_panel_1.createSequentialGroup().addGap(55).addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGroup(
								gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnLeft).addGap(51).addComponent(btnRight)).addGroup(
								gl_panel_1.createSequentialGroup().addGap(56).addComponent(btnDown))).addPreferredGap(ComponentPlacement.RELATED).addGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING, false).addComponent(liftDown, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(liftUp,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE).addGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING, false).addGroup(
								gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
										Alignment.TRAILING,
										gl_panel_1.createSequentialGroup().addComponent(lblR_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textFieldRegal2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)).addGroup(
										Alignment.TRAILING,
										gl_panel_1.createSequentialGroup().addComponent(lblR, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textFieldRegal1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))).addGroup(
								gl_panel_1.createSequentialGroup().addComponent(lblR_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(textFieldRegal3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))).addPreferredGap(
						ComponentPlacement.UNRELATED).addGroup(
						gl_panel_1.createParallelGroup(Alignment.TRAILING, false).addGroup(
								gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(regal1_rb1).addComponent(regal2_rb1)).addPreferredGap(
										ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(regal1_rb2).addComponent(regal2_rb2))).addGroup(
								gl_panel_1.createSequentialGroup().addComponent(regal3_rb1).addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(
										regal3_rb2))).addPreferredGap(ComponentPlacement.RELATED).addGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(chckBoxRegal1).addComponent(chckBoxRegal2).addComponent(chckBoxRegal3)).addPreferredGap(
						ComponentPlacement.RELATED).addGroup(
						gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(
								gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addComponent(btnLeftRegal3).addComponent(btnLeftRegal2))
										.addPreferredGap(ComponentPlacement.RELATED).addGroup(
												gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(
														gl_panel_1.createSequentialGroup().addComponent(btnRightRegal2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRandomRegal2))
														.addGroup(
																gl_panel_1.createSequentialGroup().addComponent(btnRightRegal3).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
																		btnRandomRegal3)))).addGroup(
								gl_panel_1.createSequentialGroup().addComponent(btnLeftRegal1).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRightRegal1).addPreferredGap(
										ComponentPlacement.UNRELATED).addComponent(btnRandomRegal1))).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_1.createSequentialGroup().addGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
								gl_panel_1.createSequentialGroup().addGroup(
										gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnRandomRegal1).addComponent(btnRightRegal1).addComponent(btnLeftRegal1).addComponent(
												chckBoxRegal1).addComponent(regal1_rb2).addComponent(regal1_rb1).addComponent(textFieldRegal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(lblR)).addPreferredGap(ComponentPlacement.RELATED).addGroup(
										gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnRandomRegal2).addComponent(btnRightRegal2).addComponent(btnLeftRegal2).addComponent(
												chckBoxRegal2).addComponent(regal2_rb2).addComponent(regal2_rb1).addComponent(textFieldRegal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(lblR_1)).addPreferredGap(ComponentPlacement.RELATED).addGroup(
										gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnRandomRegal3).addComponent(btnRightRegal3).addComponent(btnLeftRegal3).addComponent(
												chckBoxRegal3).addComponent(regal3_rb2).addComponent(regal3_rb1).addComponent(lblR_2).addComponent(textFieldRegal3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addGroup(
								gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnUp).addPreferredGap(ComponentPlacement.RELATED).addGroup(
										gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnLeft).addComponent(btnRight).addComponent(liftUp)).addPreferredGap(
										ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnDown).addComponent(liftDown)))).addContainerGap(
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.moveLiftLeft();

			}
		});

		btnMinus = new JButton("-");
		btnMinus.setEnabled(false);
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pietro > 0) {
					pietro--;
					levelTextField.setText(Integer.toString(pietro));
					mapa.pokazPietro(pietro);

					if (pietro == 0)
						btnMinus.setEnabled(false);
					if (pietro < MagazynUtils.liczbaPieter - 1)
						btnPlus.setEnabled(true);
				}
			}
		});

		btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pietro < MagazynUtils.liczbaPieter - 1) {
					pietro++;
					levelTextField.setText(Integer.toString(pietro));
					mapa.pokazPietro(pietro);

					if (pietro == MagazynUtils.liczbaPieter - 1)
						btnPlus.setEnabled(false);
					if (pietro > 0)
						btnMinus.setEnabled(true);
				}
			}
		});

		levelTextField = new JTextField();
		levelTextField.setColumns(10);
		levelTextField.setText(Integer.toString(pietro));
		levelTextField.setEditable(false);
		levelTextField.setBackground(Color.WHITE);

		lblPietro = new JLabel("Piętro:");
		lblPietro.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lblPietro).addGap(18).addComponent(btnMinus).addPreferredGap(
						ComponentPlacement.RELATED).addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(
						btnPlus).addGap(16)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnMinus).addComponent(lblPietro).addComponent(levelTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnPlus)).addContainerGap()));
		panel.setLayout(gl_panel);

		frame.getContentPane().setLayout(groupLayout);

		ButtonGroup styleGroup = new ButtonGroup();
		JRadioButtonMenuItem styleRadioMenu;
		for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName()) || "GTK+".equals(info.getName()) || "Windows".equals(info.getName())) {
				styleRadioMenu = new JRadioButtonMenuItem(info.getName());
				styleRadioMenu.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						ustawStyl(info.getClassName());
						fileChooser.updateUI();
					}
				});
				if (info.getClassName().equals(getLookAndFeelStyle()))
					styleRadioMenu.setSelected(true);
				else
					styleRadioMenu.setSelected(false);
				styleGroup.add(styleRadioMenu);
				mnZmienStyl.add(styleRadioMenu);
			}
		}

	}

	private void ustawStyl() {
		ustawStyl(getLookAndFeelStyle());
	}

	private void ustawStyl(String name) {
		try {
			UIManager.setLookAndFeel(name);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);
	}

	private String getLookAndFeelStyle() {
		String windowsStyle = null;
		String gtkStyle = null;
		String nimbusStyle = null;

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName()))
				nimbusStyle = info.getClassName();
			else if ("GTK+".equals(info.getName()))
				gtkStyle = info.getClassName();
			else if ("Windows".equals(info.getName()))
				windowsStyle = info.getClassName();
		}

		if (gtkStyle != null)
			return gtkStyle;
		else if (windowsStyle != null)
			return windowsStyle;
		else if (nimbusStyle != null)
			return nimbusStyle;

		return UIManager.getCrossPlatformLookAndFeelClassName();
	}

	/* metody do konsoli */
	public static void dodajWpisDoKonsoli(String wpis) {
		konsolaListModel.addElement(wpis);
	}

}