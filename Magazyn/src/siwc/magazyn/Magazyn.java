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
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.logic.IOLogic;
import siwc.magazyn.panels.MapaMagazynu;
import siwc.magazyn.panels.RegalPanel;
import siwc.magazyn.utils.MagazynUtils;

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
	private JButton btnWczytajZamowienia;
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
	private static DefaultListModel<String> zamowieniaListModel = new DefaultListModel<String>();
	private static DefaultListModel<String> produktyListModel = new DefaultListModel<String>();
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
	private JPanel panelStatystyki;
	private JPanel panelLegenda;
	private JLabel lblLiczbaZamowienTekst;
	private static JLabel lblLiczbaZamowien;

	/* statystyki */
	private static int liczbaZamowien = 0;
	private static int liczbaZamowienZrealizowany = 0;
	private static int liczbaMiejscZajetych = 0;
	private JLabel lblLiczbaZamwienZrealizowanychTekst;
	private static JLabel lblLiczbaZamowienZrealizowanych;
	private JLabel lblIloPrzedmiotwTekst;
	private static JLabel lblLbliloscprzedmiotow;
	private JLabel lblIloscWszystkichMiejscTekst;
	private static JLabel lblLiczbaMiejscZajetych;
	private JLabel lblSredniCzasRealizacjiTekst;
	private static JLabel lblWszystkieMiejsca;
	private JLabel lblMiejscaZajete;
	private JLabel lblSredniCzasRealizacji;
	private JLabel lblPustaPka;
	private JLabel lblPkaTransferowa;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_5;
	private JLabel lblWzek;
	private JPanel panel_6;
	private JScrollPane scrollPaneListaZamowien;
	private static JList listZamowienia;
	private JPanel panel_4;
	private JLabel lblBoksZajety;
	private JPanel panel_7_produkty;
	private JButton btnWczytajProdukty;
	private JScrollPane scrollPaneProdukty;
	private ArrayList<RegalPanel> regaly;
	private static JList<String> listProdukty;
	private HashMap<String, TowarTO> towaryNaMagazynie;

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
		lblNewLabel.setBounds(10, 11, 585, 50);
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
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik CSV (*.csv)", "csv"));
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(true);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuPlik = new JMenu("Plik");
		menuBar.add(menuPlik);
		
		regaly = new ArrayList<>();
		towaryNaMagazynie = new HashMap<>();

		for (int i = 0; i < MagazynUtils.liczbaRegalow; i++) {
			regaly.add(new RegalPanel(MagazynUtils.defaultFreeBoxes));
		}
		
		mapa = new MapaMagazynu(regaly);
		mapa.setBounds(10, 72, 792, 396);

		mapa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		
		openFile = new JMenuItem("Otwórz plik");
		openFile.addActionListener(new ActionListener() {
			private MagazynTO magazyn;

			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frame);

				if (result == JFileChooser.APPROVE_OPTION) {
					IOLogic logic = new IOLogic();
					
					logic.readFileToRegalPanelArray(fileChooser.getSelectedFile(), regaly, towaryNaMagazynie);
					magazyn = logic.convertToMagazynTO(regaly);
					dodajProdukty(towaryNaMagazynie);
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
				if (fileChooser.getSelectedFile() == null) {
					int result = fileChooser.showSaveDialog(frame);
					if (result != JFileChooser.APPROVE_OPTION)
						return;
				}
				IOLogic logic = new IOLogic();
				logic.saveToFile(fileChooser.getSelectedFile(), regaly);

			}
		});
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuPlik.add(saveFile);

		saveAsFile = new JMenuItem("Zapisz jako");
		saveAsFile.setEnabled(false);
		saveAsFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = fileChooser.showSaveDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					IOLogic logic = new IOLogic();
					logic.saveToFile(fileChooser.getSelectedFile(), regaly);
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

		/* STOP MAGAZYNU */
		btnStop = new JButton("STOP");
		btnStop.setBounds(1103, 650, 115, 40);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.info("Magazyn został zatrzymany");

				/*
				 * TODO: zatrzymac magazyn()
				 */
				dodajWpisDoKonsoli("Magazyn został zatrzymany" + new Date().toString());
			}
		});

		/* START MAGAZYNU */
		btnStart = new JButton("START");
		btnStart.setBounds(1228, 650, 109, 40);
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

		/* DODAJ ZAMOWIENIE */

		/* EDYTUJ ZAMOWIENIE */

		panel = new JPanel();
		panel.setBounds(599, 11, 203, 50);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_1 = new JPanel();
		panel_1.setBounds(10, 474, 792, 119);
		panel_1.setBorder(new TitledBorder(null, "Panel testowy", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		KonsolaScrollPane = new JScrollPane();
		KonsolaScrollPane.setBounds(20, 619, 782, 71);

		lblKonsola = new JLabel("Konsola");
		lblKonsola.setBounds(20, 599, 37, 14);

		panelStatystyki = new JPanel();
		panelStatystyki.setBounds(820, 72, 203, 183);
		panelStatystyki.setBorder(new TitledBorder(null, "Statystyki", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelLegenda = new JPanel();
		panelLegenda.setBounds(830, 599, 203, 148);
		panelLegenda.setBorder(new TitledBorder(null, "Legenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelStatystyki.setLayout(null);

		lblLiczbaZamowienTekst = new JLabel("Liczba zamówień :");
		lblLiczbaZamowienTekst.setBounds(10, 34, 94, 14);
		panelStatystyki.add(lblLiczbaZamowienTekst);

		lblLiczbaZamowien = new JLabel("0");
		lblLiczbaZamowien.setFont(new Font("Tahoma", Font.BOLD, 11));
		// lblLiczbaZamowien.set
		lblLiczbaZamowien.setBounds(185, 34, 19, 14);
		panelStatystyki.add(lblLiczbaZamowien);

		lblLiczbaZamwienZrealizowanychTekst = new JLabel("Liczba zamówien zrealizowanych :");
		lblLiczbaZamwienZrealizowanychTekst.setBounds(10, 59, 165, 14);
		panelStatystyki.add(lblLiczbaZamwienZrealizowanychTekst);

		lblLiczbaZamowienZrealizowanych = new JLabel("0");
		lblLiczbaZamowienZrealizowanych.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLiczbaZamowienZrealizowanych.setBounds(185, 59, 19, 14);
		panelStatystyki.add(lblLiczbaZamowienZrealizowanych);

		lblIloPrzedmiotwTekst = new JLabel("Ilość przedmiotów :");
		lblIloPrzedmiotwTekst.setBounds(10, 84, 165, 14);
		panelStatystyki.add(lblIloPrzedmiotwTekst);

		lblLbliloscprzedmiotow = new JLabel("0");
		lblLbliloscprzedmiotow.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLbliloscprzedmiotow.setBounds(185, 84, 19, 14);
		panelStatystyki.add(lblLbliloscprzedmiotow);

		lblIloscWszystkichMiejscTekst = new JLabel("Wszystkie miejsca :");
		lblIloscWszystkichMiejscTekst.setBounds(10, 109, 134, 14);
		panelStatystyki.add(lblIloscWszystkichMiejscTekst);

		lblLiczbaMiejscZajetych = new JLabel("Miejsca Zajęte :");
		lblLiczbaMiejscZajetych.setBounds(10, 134, 94, 14);
		panelStatystyki.add(lblLiczbaMiejscZajetych);

		lblSredniCzasRealizacjiTekst = new JLabel("Średni czas realizacji :");
		lblSredniCzasRealizacjiTekst.setBounds(10, 159, 165, 14);
		panelStatystyki.add(lblSredniCzasRealizacjiTekst);

		lblWszystkieMiejsca = new JLabel("0");
		lblWszystkieMiejsca.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWszystkieMiejsca.setBounds(185, 109, 19, 14);
		panelStatystyki.add(lblWszystkieMiejsca);

		lblMiejscaZajete = new JLabel("0");
		lblMiejscaZajete.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMiejscaZajete.setBounds(185, 134, 19, 14);
		panelStatystyki.add(lblMiejscaZajete);
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
				mapa.setRegalFreeBoxes(0, 1);
			}
		});
		regal1_rb1.setSelected(mapa.getRegalFreeBoxes(0) == 1);

		regal1_rb2 = new JRadioButton("2");
		regal1_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal1.setEnabled(true);
				mapa.setRegalFreeBoxes(0, 2);
			}
		});
		regal1_rb2.setSelected(mapa.getRegalFreeBoxes(0) == 2);

		regal1ButtonGroup.add(regal1_rb1);
		regal1ButtonGroup.add(regal1_rb2);

		chckBoxRegal1 = new JCheckBox("Dolny regał");
		chckBoxRegal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chckBoxRegal1.setEnabled(mapa.getRegalFreeBoxes(0) == 2);

		ButtonGroup regal2ButtonGroup = new ButtonGroup();

		regal2_rb1 = new JRadioButton("1");
		regal2_rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal2.setEnabled(false);
				mapa.setRegalFreeBoxes(1, 1);

			}
		});
		regal2_rb1.setSelected(mapa.getRegalFreeBoxes(1) == 1);

		regal2_rb2 = new JRadioButton("2");
		regal2_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal2.setEnabled(true);
				mapa.setRegalFreeBoxes(1, 2);
			}
		});
		regal2_rb2.setSelected(mapa.getRegalFreeBoxes(1) == 2);

		regal2ButtonGroup.add(regal2_rb1);
		regal2ButtonGroup.add(regal2_rb2);

		chckBoxRegal2 = new JCheckBox("Dolny regał");
		chckBoxRegal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		chckBoxRegal2.setEnabled(mapa.getRegalFreeBoxes(1) == 2);

		ButtonGroup regal3ButtonGroup = new ButtonGroup();

		regal3_rb1 = new JRadioButton("1");
		regal3_rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal3.setEnabled(false);
				mapa.setRegalFreeBoxes(2, 1);
			}
		});
		regal3_rb1.setSelected(mapa.getRegalFreeBoxes(2) == 1);

		regal3_rb2 = new JRadioButton("2");
		regal3_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal3.setEnabled(true);
				mapa.setRegalFreeBoxes(2, 2);
			}
		});
		regal3_rb2.setSelected(mapa.getRegalFreeBoxes(2) == 2);

		regal3ButtonGroup.add(regal3_rb1);
		regal3ButtonGroup.add(regal3_rb2);

		chckBoxRegal3 = new JCheckBox("Dolny regał");
		chckBoxRegal3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chckBoxRegal3.setEnabled(mapa.getRegalFreeBoxes(2) == 2);

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

				mapa.zmienKolorBoksu(1, position, colors[c]);
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

				mapa.zmienKolorBoksu(0, position, colors[c]);
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

				mapa.zmienKolorBoksu(2, position, colors[c]);
				log.info("R3: [" + position + "] color=" + c + "\t linia 192 jak nie wiesz jaki kolor");
			}
		});

		btnRightRegal1 = new JButton("prawo");
		btnRightRegal1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldRegal1.getText().equals("") ? "1" : textFieldRegal1.getText();
				final int numbers = Integer.parseInt(text) == 0 ? 1 : Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom = chckBoxRegal1.isSelected();
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
				final int numbers = Integer.parseInt(text) == 0 ? 1 : Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom = chckBoxRegal2.isSelected();
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
				final int numbers = Integer.parseInt(text) == 0 ? 1 : Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom = chckBoxRegal3.isSelected();
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
				final int numbers = Integer.parseInt(text) == 0 ? 1 : Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom = chckBoxRegal1.isSelected();
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
				final int numbers = Integer.parseInt(text) == 0 ? 1 : Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom = chckBoxRegal2.isSelected();
						for (int i = 0; i < numbers; i++) {
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
				final int numbers = Integer.parseInt(text) == 0 ? 1 : Integer.parseInt(text);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean bottom = chckBoxRegal3.isSelected();
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
				gl_panel_1
						.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addGap(55).addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnLeft).addGap(51).addComponent(btnRight)).addGroup(gl_panel_1.createSequentialGroup().addGap(56).addComponent(btnDown)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false).addComponent(liftDown, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(liftUp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
						.addGroup(
								gl_panel_1.createParallelGroup(Alignment.LEADING, false).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup().addComponent(lblR_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldRegal2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)).addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup().addComponent(lblR, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldRegal1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblR_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(textFieldRegal3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false).addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(regal1_rb1).addComponent(regal2_rb1)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(regal1_rb2).addComponent(regal2_rb2))).addGroup(gl_panel_1.createSequentialGroup().addComponent(regal3_rb1).addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(regal3_rb2))).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(chckBoxRegal1).addComponent(chckBoxRegal2).addComponent(chckBoxRegal3)).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addComponent(btnLeftRegal3).addComponent(btnLeftRegal2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1.createSequentialGroup().addComponent(btnRightRegal2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRandomRegal2)).addGroup(gl_panel_1.createSequentialGroup().addComponent(btnRightRegal3).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRandomRegal3)))).addGroup(gl_panel_1.createSequentialGroup().addComponent(btnLeftRegal1).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRightRegal1).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRandomRegal1))).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addGroup(
								gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnRandomRegal1).addComponent(btnRightRegal1).addComponent(btnLeftRegal1).addComponent(chckBoxRegal1).addComponent(regal1_rb2).addComponent(regal1_rb1).addComponent(textFieldRegal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(lblR)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnRandomRegal2).addComponent(btnRightRegal2).addComponent(btnLeftRegal2).addComponent(chckBoxRegal2).addComponent(regal2_rb2).addComponent(regal2_rb1).addComponent(textFieldRegal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(lblR_1)).addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnRandomRegal3).addComponent(btnRightRegal3).addComponent(btnLeftRegal3).addComponent(chckBoxRegal3).addComponent(regal3_rb2).addComponent(regal3_rb1).addComponent(lblR_2).addComponent(textFieldRegal3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnUp).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnLeft).addComponent(btnRight).addComponent(liftUp)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnDown).addComponent(liftDown)))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lblPietro).addGap(18).addComponent(btnMinus).addPreferredGap(ComponentPlacement.RELATED).addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPlus).addGap(16)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnMinus).addComponent(lblPietro).addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnPlus)).addContainerGap()));
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(mapa);
		frame.getContentPane().add(KonsolaScrollPane);
		frame.getContentPane().add(lblKonsola);
		frame.getContentPane().add(panelStatystyki);

		lblSredniCzasRealizacji = new JLabel("0");
		lblSredniCzasRealizacji.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSredniCzasRealizacji.setBounds(185, 159, 19, 14);
		panelStatystyki.add(lblSredniCzasRealizacji);
		frame.getContentPane().add(panelLegenda);
		panelLegenda.setLayout(null);

		lblPustaPka = new JLabel("boks wolny");
		lblPustaPka.setBounds(38, 22, 70, 14);
		panelLegenda.add(lblPustaPka);

		lblPkaTransferowa = new JLabel("boks do przesuwania");
		lblPkaTransferowa.setBounds(38, 47, 102, 14);
		panelLegenda.add(lblPkaTransferowa);

		panel_2 = new JPanel();
		panel_2.setBackground(MagazynUtils.defaultBoxBackground);
		panel_2.setBounds(10, 20, MagazynUtils.boxSize, MagazynUtils.boxSize);
		panelLegenda.add(panel_2);

		panel_3 = new JPanel();
		panel_3.setBackground(MagazynUtils.freeBoxBackround);
		panel_3.setBounds(10, (int) (panel_2.getBounds().getY() + MagazynUtils.boxSize + 5), MagazynUtils.boxSize, MagazynUtils.boxSize);
		panelLegenda.add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBackground(MagazynUtils.busyBoxBackground);
		panel_4.setBounds(10, (int) (panel_3.getBounds().getY() + MagazynUtils.boxSize + 5), MagazynUtils.boxSize, MagazynUtils.boxSize);
		panelLegenda.add(panel_4);
		
		lblBoksZajety = new JLabel("boks zajęty");
		lblBoksZajety.setBounds(38, 70, 89, 14);
		panelLegenda.add(lblBoksZajety);
		
		panel_5 = new JPanel();
		panel_5.setBackground(MagazynUtils.liftBackground);
		panel_5.setBounds(10, (int) (panel_4.getBounds().getY() + MagazynUtils.boxSize + 5), MagazynUtils.liftSizeX, MagazynUtils.liftSizeY);
		panelLegenda.add(panel_5);

		lblWzek = new JLabel("wózek");
		lblWzek.setBounds(38, 99, 46, 14);
		panelLegenda.add(lblWzek);
		
		
		
		frame.getContentPane().add(btnStop);
		frame.getContentPane().add(btnStart);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Zam\u00F3wienia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(1030, 31, 212, 336);
		frame.getContentPane().add(panel_6);
		panel_6.setLayout(null);
		btnWczytajZamowienia = new JButton("Wczytaj");
		btnWczytajZamowienia.setBounds(58, 23, 71, 23);
		panel_6.add(btnWczytajZamowienia);
		btnEdytuj = new JButton("Edytuj");
		btnEdytuj.setBounds(139, 23, 63, 23);
		panel_6.add(btnEdytuj);

		scrollPaneListaZamowien = new JScrollPane();
		scrollPaneListaZamowien.setBounds(10, 69, 192, 261);
		panel_6.add(scrollPaneListaZamowien);

		listZamowienia = new JList();
		listZamowienia.setModel(zamowieniaListModel);
		scrollPaneListaZamowien.setViewportView(listZamowienia);
		
		panel_7_produkty = new JPanel();
		panel_7_produkty.setBorder(new TitledBorder(null, "Produkty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7_produkty.setBounds(812, 266, 212, 268);
		frame.getContentPane().add(panel_7_produkty);
		panel_7_produkty.setLayout(null);
		
		/* wczytaj produkty */
		btnWczytajProdukty = new JButton("Wczytaj");
		btnWczytajProdukty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.info("wczytywanie produktów");
				
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					log.info("Wybrano: " + fileChooser.getSelectedFile());
					
				}
				
				
				
				dodajWpisDoKonsoli("Wczytano produkty z pliku : " + fileChooser.getSelectedFile());
			}
		});
		
		btnWczytajProdukty.setBounds(113, 33, 89, 23);
		panel_7_produkty.add(btnWczytajProdukty);
		
		scrollPaneProdukty = new JScrollPane();
		scrollPaneProdukty.setBounds(10, 67, 192, 190);
		panel_7_produkty.add(scrollPaneProdukty);
		
		listProdukty = new JList<>();
		scrollPaneProdukty.setViewportView(listProdukty);
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
		btnWczytajZamowienia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * TODO
				 * 
				 * dodaj zamowienie zaloguj w konsoli
				 */
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					log.info("Wybrano: " + fileChooser.getSelectedFile());
					
				}
				log.info("Wczytano zamówienia");
				
				dodajWpisDoKonsoli("Wczytano zamówienia z pliku :" + fileChooser.getSelectedFile());
			}
		});

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

	/* metody do statystyk */

	/* liczba zamowien */
	public static void zwiekszLiczbeZamowien() {
		liczbaZamowien++;
		lblLiczbaZamowien.setText(String.valueOf(liczbaZamowien));
	}

	public static void zmniejszLiczbeZamowien() {
		liczbaZamowien--;
		lblLiczbaZamowien.setText(String.valueOf(liczbaZamowien));
	}

	/* liczba zamowien zrealizowanych */
	public static void zwiekszLiczbeZamowienZrealizowanych() {
		liczbaZamowienZrealizowany++;
		lblLiczbaZamowienZrealizowanych.setText(String.valueOf(liczbaZamowienZrealizowany));
	}

	public static void zmniejszLiczbeZamowienZrealizowanych() {
		liczbaZamowienZrealizowany--;
		lblLiczbaZamowienZrealizowanych.setText(String.valueOf(liczbaZamowienZrealizowany));
	}

	/* liczba miejsc zajetych */

	public static void zwiekszLiczbeMiejscZajetych() {
		liczbaMiejscZajetych++;
		lblLiczbaMiejscZajetych.setText(String.valueOf(liczbaMiejscZajetych));
	}

	public static void zmniejszLiczbeMiejscZajetych() {
		liczbaMiejscZajetych--;
		lblLiczbaMiejscZajetych.setText(String.valueOf(liczbaMiejscZajetych));
	}

	/* ilosc przedmiotow */
	public static void ustawLiczbePrzedmiotow(int liczbaPrzedmiotow) {
		lblLbliloscprzedmiotow.setText(String.valueOf(liczbaPrzedmiotow));
	}

	/* liczba wszystkich miejsc */
	public static void ustawLiczbeWszystkichMiejsc(int liczbaWszystkichMiejsc) {
		lblWszystkieMiejsca.setText(String.valueOf(liczbaWszystkichMiejsc));
	}

	/* Dodaj zamowienia */
	public static void dodajZamowienia(List<String> listaZamowien) {

		if (listaZamowien != null){
			for (String s : listaZamowien) {
				zamowieniaListModel.addElement(s);
			}
			listZamowienia.setModel(zamowieniaListModel);
		}else{
			log.error("przekazana pusta lista zamowien");
		}
	}
	
	/* produkty */
	public static void dodajProdukty(HashMap<String, TowarTO> listaProduktow){
		if(listaProduktow != null){
			for(TowarTO t: listaProduktow.values())
				produktyListModel.addElement(t.getKodTowaru()+": "+t.getNazwa() + " - " + t.getIlosc() +" szt.");
			listProdukty.setModel(produktyListModel);
		}else{
			log.error("pusta lista produktow");
		}
		
	}

	public HashMap<String, TowarTO> getTowaryNaMagazynie() {
		return towaryNaMagazynie;
	}

	public void setTowaryNaMagazynie(HashMap<String, TowarTO> towaryNaMagazynie) {
		this.towaryNaMagazynie = towaryNaMagazynie;
	}
}
