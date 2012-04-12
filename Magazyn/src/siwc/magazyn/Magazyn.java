package siwc.magazyn;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import siwc.magazyn.dto.ListTowarTO;
import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.dto.ZamowienieTO;
import siwc.magazyn.logic.Algorithm;
import siwc.magazyn.logic.IOLogic;
import siwc.magazyn.panels.MapaMagazynu;
import siwc.magazyn.panels.RegalPanel;
import siwc.magazyn.thirdparty.Clock;
import siwc.magazyn.utils.MagazynUtils;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.Dimension;

public class Magazyn {
	private static Logger log = Logger.getLogger(Magazyn.class);
	private JFrame frmSystemyWbudowaneI;
	private JMenuItem saveFile;
	private JMenuItem openFile;
	private JMenuItem saveAsFile;
	private JMenuItem closeWindow;
	private JMenu mnZmienStyl;
	private JFileChooser fileChooser;
	private JLabel lblNewLabel = new JLabel("Systemy wbudowane i czasu rzeczywistego - Magazyn");
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
	private JSpinner spinnerScaleTime;

	private JButton btnStop;
	private JButton btnStart;
	private JButton btnWczytajZamowienia;
	private JButton btnDodajZamowienie;
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
	private JRadioButton radioButton1Box;
	private JRadioButton radioButton2Boxy;
	private JScrollPane KonsolaScrollPane;
	private JList<String> KonsolaList;
	
	private Clock zegar;

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
	private static int liczbaPrzedmiotow = 0;
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
	private static MyList listZamowienia;
	private JPanel panel_4;
	private JLabel lblBoksZajety;
	private JPanel panel_7_produkty;
	private JButton btnWczytajProdukty;
	private JScrollPane scrollPaneProdukty;
	private ArrayList<RegalPanel> regaly;
	private static JList<String> listProdukty;
	private HashMap<String, ListTowarTO> towaryNaMagazynie; // tylko do listy po prawej stronie
	private HashMap<Integer, ZamowienieTO> zamowienia;
	private List<ZamowienieTO> zamowieniaLista; //tylko do listy po prawej stronie
	private int idPolaCounter;

	private MagazynTO magazyn;
	Algorithm algorithm;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Magazyn window = new Magazyn();
					window.frmSystemyWbudowaneI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Magazyn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		regaly = new ArrayList<>();
		towaryNaMagazynie = new HashMap<>();
		zamowienia = new HashMap<>();
		idPolaCounter = 0;
		
		lblNewLabel.setBounds(10, 11, 585, 50);
		lblNewLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 16));
		frmSystemyWbudowaneI = new JFrame();
		frmSystemyWbudowaneI.setTitle("Systemy wbudowane i czasu rzeczywistego - Magazyn");
		frmSystemyWbudowaneI.setSize(1303, 893);

		if (MagazynUtils.screenSize.getWidth() <= 1366)
			frmSystemyWbudowaneI.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frmSystemyWbudowaneI.setResizable(true);
		// frame.setBounds(50, 50, MagazynUtils.frameWidth, MagazynUtils.frameHeight);
		log.info("Rozmiar okna: " + MagazynUtils.frameWidth + "x" + MagazynUtils.frameHeight + "\t" + frmSystemyWbudowaneI.getWidth() + "x" + frmSystemyWbudowaneI.getHeight());
		frmSystemyWbudowaneI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSystemyWbudowaneI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// int result = JOptionPane.showConfirmDialog(frame, "Zamknąć program?", "Zamknąc?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				// if (result == JOptionPane.YES_OPTION) {
				// frame.dispose();
				// }
				// TODO
				frmSystemyWbudowaneI.dispose();
				System.exit(0);
			}

		});
		ustawStyl();
		
		fileChooser = new JFileChooser();
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Plik CSV (*.csv)", "csv");
		fileChooser.setFileFilter(txtFilter);
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik tekstowy (*.txt)", "txt"));
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(true);

		JMenuBar menuBar = new JMenuBar();
		frmSystemyWbudowaneI.setJMenuBar(menuBar);

		JMenu menuPlik = new JMenu("Plik");
		menuBar.add(menuPlik);

		for (int i = 0; i < MagazynUtils.liczbaRegalow; i++) {
			regaly.add(new RegalPanel(MagazynUtils.defaultFreeBoxes, idPolaCounter));
			idPolaCounter += MagazynUtils.liczbaBoxowWRegale;
		}
		
		mapa = new MapaMagazynu(regaly);
		mapa.setBounds(10, 72, 792, 396);

		mapa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		
		openFile = new JMenuItem("Otwórz plik");
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frmSystemyWbudowaneI);

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
					int result = fileChooser.showSaveDialog(frmSystemyWbudowaneI);
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

				int result = fileChooser.showSaveDialog(frmSystemyWbudowaneI);
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
				int result = JOptionPane.showConfirmDialog(frmSystemyWbudowaneI, "Zamknąć program?", "Zamknąc?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
		btnStart = new JButton("START");
		btnStop = new JButton("STOP");
		btnStop.setBounds(1034, 656, 115, 40);
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.info("Magazyn został zatrzymany");

				algorithm.setStop(1);
				zegar.stopClock();
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				dodajWpisDoKonsoli("Magazyn został zatrzymany" + new Date().toString());
			}
		});

		/* START MAGAZYNU */

		btnStart.setBounds(903, 656, 109, 40);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				log.info("Magazyn został uruchomiony" + new Date().toString());
				zegar.startClock();
				dodajWpisDoKonsoli("Magazyn został uruchomiony " + new Date().toString());
				log.info("Clicked");
				final 
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						algorithm = new Algorithm(mapa, magazyn) {
							
							@Override
							public void odswiezZamowienia(ZamowienieTO zamowienie) {
								zamowienia.remove(zamowienie);
								zamowieniaListModel.removeElement(zamowienie);
								listZamowienia.setModel(zamowieniaListModel);
							}
							
							@Override
							public List<ZamowienieTO> getNoweZamowienia() {
								return new ArrayList<ZamowienieTO>(zamowienia.values());
							}
							
							@Override
							public int getTimeIntValue() {
								return zegar.getTimeIntValue();
							}
						};
						algorithm.startAlgorithm();		
					}
				});
				t.start();
				
			}
		});
		
		/* ZEGAR */

		/* DODAJ ZAMOWIENIE */

		/* EDYTUJ ZAMOWIENIE */

		panel = new JPanel();
		panel.setBounds(599, 11, 203, 50);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_1 = new JPanel();
		panel_1.setBounds(10, 474, 792, 119);
		panel_1.setBorder(new TitledBorder(null, "Panel testowy", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		KonsolaScrollPane = new JScrollPane();
		KonsolaScrollPane.setBounds(20, 619, 782, 121);

		lblKonsola = new JLabel("Konsola");
		lblKonsola.setBounds(20, 599, 37, 14);

		panelStatystyki = new JPanel();
		panelStatystyki.setBounds(812, 462, 212, 183);
		panelStatystyki.setBorder(new TitledBorder(null, "Statystyki", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelLegenda = new JPanel();
		panelLegenda.setBounds(1034, 462, 212, 183);
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
				try {
					mapa.moveLiftUp();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnRight = new JButton("right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mapa.moveLiftRight();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnDown = new JButton("down");
		btnDown.setMnemonic(KeyEvent.VK_DOWN);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mapa.moveLiftDown();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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
				idPolaCounter = 0;
				mapa.setRegalFreeBoxes(0, 1);
			}
		});
		regal1_rb1.setSelected(mapa.getRegalFreeBoxes(0) == 1);

		regal1_rb2 = new JRadioButton("2");
		regal1_rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckBoxRegal1.setEnabled(true);
				idPolaCounter = 760;
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

				mapa.zmienKolorBoksu(1, pietro, position, colors[c]);
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

				mapa.zmienKolorBoksu(0, pietro, position, colors[c]);
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

				mapa.zmienKolorBoksu(2, pietro, position, colors[c]);
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
//							mapa.przesunBoxWPrawo(0, pietro, null, bottom);
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
//							mapa.przesunBoxWPrawo(1, pietro, null, bottom);
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
//							mapa.przesunBoxWPrawo(2, pietro, "", bottom);
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
//							mapa.przesunBoxWLewo("regal1", pietro, bottom);
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
//							mapa.przesunBoxWLewo("regal2", pietro, bottom);
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
//							mapa.przesunBoxWLewo("regal3", pietro, bottom);
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
				try {
					mapa.moveLiftLeft();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

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
		frmSystemyWbudowaneI.getContentPane().setLayout(null);
		frmSystemyWbudowaneI.getContentPane().add(panel_1);
		frmSystemyWbudowaneI.getContentPane().add(lblNewLabel);
		frmSystemyWbudowaneI.getContentPane().add(panel);
		frmSystemyWbudowaneI.getContentPane().add(mapa);
		frmSystemyWbudowaneI.getContentPane().add(KonsolaScrollPane);
		frmSystemyWbudowaneI.getContentPane().add(lblKonsola);
		frmSystemyWbudowaneI.getContentPane().add(panelStatystyki);

		lblSredniCzasRealizacji = new JLabel("0");
		lblSredniCzasRealizacji.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSredniCzasRealizacji.setBounds(185, 159, 19, 14);
		panelStatystyki.add(lblSredniCzasRealizacji);
		frmSystemyWbudowaneI.getContentPane().add(panelLegenda);
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
		
		
		
		frmSystemyWbudowaneI.getContentPane().add(btnStop);
		frmSystemyWbudowaneI.getContentPane().add(btnStart);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Zam\u00F3wienia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(1034, 72, 212, 366);
		frmSystemyWbudowaneI.getContentPane().add(panel_6);
		btnWczytajZamowienia = new JButton("Wczytaj");
		btnDodajZamowienie = new JButton("Dodaj");
		btnDodajZamowienie.setMinimumSize(new Dimension(71, 23));
		btnDodajZamowienie.setMaximumSize(new Dimension(71, 23));

		scrollPaneListaZamowien = new JScrollPane();

		listZamowienia = new MyList();
		listZamowienia.setModel(zamowieniaListModel);
		scrollPaneListaZamowien.setViewportView(listZamowienia);
		
		JButton btnUsunZamowienia = new JButton("Usuń");
		btnUsunZamowienia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] indexy = listZamowienia.getSelectedIndices();
				ArrayList<String> listObjects = new ArrayList<>();
				for(int i = 0; i < indexy.length; i++){
					String el = zamowieniaListModel.getElementAt(indexy[i]);
					listObjects.add(el);
					String idZam = el.substring(0, el.indexOf(':'));
					int idZamowienia = -1;
					try{
						idZamowienia = Integer.parseInt(idZam.trim());
					}catch(NumberFormatException ex){
						log.error("Nieudane parsowanie identyfikatora zamówienia: "+idZamowienia);
						continue;
					}
					ZamowienieTO zamowienie = zamowienia.get(idZamowienia);
					List<TowarTO> towary = zamowienie.getTowary();
					if(towary != null && towary.size() > 0){
						for(TowarTO t : towary){
							t.setZarezerwowany(false);
							if(towaryNaMagazynie.get(t.getKodTowaru()) == null){
								towaryNaMagazynie.put(t.getKodTowaru(), new ListTowarTO(t));
							}
							else
								towaryNaMagazynie.get(t.getKodTowaru()).zwiekszIlosc();
						}
					}
				}
				
				for(String obj : listObjects){
					zamowieniaListModel.removeElement(obj);
				}
				listZamowienia.setModel(zamowieniaListModel);
				aktualizujProdukty(towaryNaMagazynie);
			}
		});
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(btnWczytajZamowienia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDodajZamowienie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
				.addComponent(scrollPaneListaZamowien, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(btnUsunZamowienia)
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWczytajZamowienia)
						.addComponent(btnDodajZamowienie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneListaZamowien, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnUsunZamowienia))
		);
		panel_6.setLayout(gl_panel_6);
		
		panel_7_produkty = new JPanel();
		panel_7_produkty.setBorder(new TitledBorder(null, "Produkty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7_produkty.setBounds(812, 72, 212, 366);

		frmSystemyWbudowaneI.getContentPane().add(panel_7_produkty);
		
		/* wczytaj produkty */
		btnWczytajProdukty = new JButton("Wczytaj");
		btnWczytajProdukty.setToolTipText("Wczytaj produkty z pliku");
		btnWczytajProdukty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = fileChooser.showOpenDialog(frmSystemyWbudowaneI);

				if (result == JFileChooser.APPROVE_OPTION) {
					IOLogic logic = new IOLogic();
					
					logic.readFileToRegalPanelArray(fileChooser.getSelectedFile(), regaly, towaryNaMagazynie);
					magazyn = logic.convertToMagazynTO(regaly);
					dodajProdukty(towaryNaMagazynie);
					saveFile.setEnabled(true);
					saveAsFile.setEnabled(true);
					dodajWpisDoKonsoli("Wczytano produkty z pliku : " + fileChooser.getSelectedFile());
					if(towaryNaMagazynie.size() > 0){
						btnDodajZamowienie.setEnabled(true);
						btnWczytajZamowienia.setEnabled(true);
						btnDodajZamowienie.setToolTipText("Dodaj zamówienie");
						btnWczytajZamowienia.setToolTipText("Wczytaj zamówienia z pliku");
					}
				}
			}
		});
		
		scrollPaneProdukty = new JScrollPane();
		
		listProdukty = new JList<>();
		scrollPaneProdukty.setViewportView(listProdukty);
		
		JButton btnDodajProdukt = new JButton("Dodaj");
		btnDodajProdukt.setMinimumSize(new Dimension(71, 23));
		btnDodajProdukt.setMaximumSize(new Dimension(71, 23));
		btnDodajProdukt.setToolTipText("Dodaj produkt");
		btnDodajProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddProductBox(frmSystemyWbudowaneI, true){
					private static final long serialVersionUID = 8696383312762375207L;

					@Override
					public void addProductActionPerformed() {
						String error = "";
						boolean OK = true;
						int regalID = (int) spinnerNumerRegalu.getValue() - 1;
						int pietro = (int) spinnerNumerPietra.getValue();
						String pozycja = (String)comboBoxPositionA_C.getSelectedItem()+ comboBoxPosition1_38.getSelectedItem();
						String nazwa = textFieldNazwa.getText();
						String producent = textFieldProducent.getText();
						String kodTowaru = textFieldKod.getText();
						
						RegalPanel rp = regaly.get(regalID);
						if(rp.getBoxColor(pietro, pozycja).equals(MagazynUtils.freeBoxBackround)){
							error += "Box \""+pozycja+ "\" musi pozostac pusty\n";
							OK = false;
						}
						
						if(nazwa == null || nazwa.length() < 1) {
							error += "Nie wypelniono pola \"Nazwa\"\n";
							OK = false;
						}
						if(producent == null || producent.length() == 0) {
							error += "Nie wypelniono pola \"Producent\"\n";
							OK = false;
						}
						if(kodTowaru == null || kodTowaru.length() == 0) {
							error += "Nie wypelniono pola \"Kod\"\n";
							OK = false;
						}
						if(rp.getTowar(pietro, pozycja).isZarezerwowany()){
							error +="Na wybranej pozycji znajduje się zarezerwowany towar.";
							OK = false;
						}
						int ilosc = -1;
						try {
							ilosc = Integer.parseInt(textFieldIloscWPaczce.getText());
						}catch(NumberFormatException e){
							error += "Nieprawidlowa liczba w polu \"Ilosc w paczce\"\n";
							OK = false;
						}

						if(!OK) {
							JOptionPane.showMessageDialog(frmSystemyWbudowaneI, error,
		                    "Błąd", JOptionPane.ERROR_MESSAGE);
						}
						else{
							rp.zmienKolorBoksu(pietro, pozycja, MagazynUtils.busyBoxBackground);
							TowarTO towar = rp.getTowar(pietro, pozycja);
							if (towar != null) {
								String oldKodTowaru = towar.getKodTowaru();
								if(oldKodTowaru != null && oldKodTowaru.length() > 0)
									if(towaryNaMagazynie.get(oldKodTowaru).getIlePaczek() == 1)
										towaryNaMagazynie.remove(oldKodTowaru);
									else 
										towaryNaMagazynie.get(oldKodTowaru).zmniejszIlosc(); 
								towar.setNazwa(nazwa);
								towar.setProducent(producent);
								towar.setKodTowaru(kodTowaru);
								towar.setIlosc(ilosc);
							}
							
							rp.zmienToolTipTextBoxu(pietro, pozycja, "<html>"+pozycja+"<br>"+towar.getOpis());
							if(towaryNaMagazynie.containsKey(kodTowaru)){
								int staraLiczbaPaczek = towaryNaMagazynie.get(kodTowaru).getIlePaczek();
								towaryNaMagazynie.get(kodTowaru).setIlePaczek(staraLiczbaPaczek + 1) ;
							}
							else {
								towaryNaMagazynie.put(kodTowaru, new ListTowarTO(towar, 1));
							}
							IOLogic logic = new IOLogic();
							logic.convertToMagazynTO(regaly);
							produktyListModel.clear();
							dodajProdukty(towaryNaMagazynie);
							dodajWpisDoKonsoli("Dodano produkt: "+kodTowaru + " - "+nazwa + " 1 x "+ilosc);
							closeAddPBox();
							
							if(towaryNaMagazynie.size() > 0){
								btnDodajZamowienie.setEnabled(true);
								btnWczytajZamowienia.setEnabled(true);
							}
						}
					}
				};
			}
		});
		
		JButton btnUsunProdukty = new JButton("Usuń");
		GroupLayout gl_panel_7_produkty = new GroupLayout(panel_7_produkty);
		gl_panel_7_produkty.setHorizontalGroup(
			gl_panel_7_produkty.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7_produkty.createSequentialGroup()
					.addComponent(btnWczytajProdukty)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDodajProdukt, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
				.addComponent(scrollPaneProdukty, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel_7_produkty.createSequentialGroup()
					.addComponent(btnUsunProdukty)
					.addContainerGap())
		);
		gl_panel_7_produkty.setVerticalGroup(
			gl_panel_7_produkty.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7_produkty.createSequentialGroup()
					.addGroup(gl_panel_7_produkty.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWczytajProdukty)
						.addComponent(btnDodajProdukt))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneProdukty, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUsunProdukty)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_7_produkty.setLayout(gl_panel_7_produkty);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Czas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(1034, 0, 212, 70);
		frmSystemyWbudowaneI.getContentPane().add(panel_7);
		zegar = new Clock();
		zegar.setFont(new Font("SansSerif", Font.PLAIN, 30));
		
		spinnerScaleTime = new JSpinner();
		spinnerScaleTime.setModel(new SpinnerListModel(new String[] {"Real", "10x", "20x", "30x", "50x", "100x", "1000x"}));
		spinnerScaleTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		spinnerScaleTime.getEditor().setEnabled(false);
		spinnerScaleTime.addChangeListener(new ChangeListener() {

	        @Override
	        public void stateChanged(ChangeEvent e) {
	        	String value = (String)spinnerScaleTime.getValue();
	            if(value.equals("Real")){
	            	zegar.rescaleTime(1000);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(1200);
	            }
	            else if(value.equals("10x")){
	            	zegar.rescaleTime(100);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(120);
	            }
	            else if(value.equals("20x")){
	            	zegar.rescaleTime(50);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(60);
	            }
	            else if(value.equals("30x")){
	            	zegar.rescaleTime(50);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(40);
	            }
	            else if(value.equals("50x")){
	            	zegar.rescaleTime(20);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(24);
	            }
	            else if(value.equals("100x")){
	            	zegar.rescaleTime(10);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(12);
	            }
	            else if(value.equals("1000x")){
	            	zegar.rescaleTime(1);
	            	if(algorithm != null)
	            		algorithm.setJakiSleep(1);
	            }
	            else {
	            	log.error("Błąd przy reskalowaniu zegara");
	            }
	        }
	    });
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(zegar, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerScaleTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(251, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
						.addComponent(spinnerScaleTime, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addComponent(zegar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(27))
		);
		panel_7.setLayout(gl_panel_7);
		btnDodajZamowienie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddOrderBox(frmSystemyWbudowaneI, true) {
					private static final long serialVersionUID = 8786018651594226410L;
					private String imieINazwisko = "";
					
					@Override
					public void dodajZamowienieAction() {
						if(zamowienia != null) {
							if(zamowienia.size() > 0){
								index = getMaxKey(zamowienia) + 1;
							}
							else
								index = 1;
						}
						
						imieINazwisko = textFieldImieINazwisko.getText();
						
						int priorytet = -1;
						int h = -1;
						int m = -1;
						int s = -1;
						String terminRealizacji = comboBoxGodzina.getSelectedItem() + ":"+comboMinuta.getSelectedItem()+":00";
						try{
							String [] czas = terminRealizacji.split(":");
							if(czas.length > 3){
								log.error("Coś nie tak przy parsowaniu terminu realizacji: "+terminRealizacji);
							}
							h = Integer.parseInt(czas[0]);
							m = Integer.parseInt(czas[1]);
							s = Integer.parseInt(czas[2]);
						}catch(NumberFormatException e){
							e.printStackTrace();
						}
						priorytet = h * 86400 + m * 3600 + s;
						zamowienie.setDaneKlienta(imieINazwisko);
						zamowienie.setTerminRealizacji(terminRealizacji);
						zamowienie.setPriorytet(priorytet); 
						zamowienie.setNumerZamowienia(index);
						new SelectProductBox(frmSystemyWbudowaneI, rootPaneCheckingEnabled, towaryNaMagazynie) {
							private static final long serialVersionUID = 7735927972721100415L;
							
							@Override
							public void dodajTowarAction() {
								String error = "";
								boolean OK = true;
								ListTowarTO towar = (ListTowarTO) comboBoxTowary.getSelectedItem();
								String kodTowaru = towar.getKodTowaru();
								String nazwaTowaru = towar.getNazwa();
								int ilePaczek = -1;
								try{
									ilePaczek = Integer.parseInt(textFieldIlePaczek.getText());
								}catch(NumberFormatException e){
									OK = false;
									error += "Nieprawidłowa liczba w polu \"Ile paczek\"";
								}
								ArrayList<TowarTO> towary = magazyn.getDostepneTowaryByKod(kodTowaru);
								if(ilePaczek > towary.size()){
									OK = false;
									error += "Brak wystarczającej ilości towaru: "+nazwaTowaru+" - jest: "+towary.size()+" zamowienie: "+ilePaczek;
								}
								if(!OK) {
									JOptionPane.showMessageDialog(frmSystemyWbudowaneI, error,
				                    "Błąd", JOptionPane.ERROR_MESSAGE);
								}
								else{
									for(int i = 0; i < ilePaczek; i++){
										TowarTO t = towary.get(i);
										ListTowarTO lt = towaryNaMagazynie.get(kodTowaru);
										zamowienie.getTowary().add(t);
										t.setZarezerwowany(true);
										lt.zmniejszIlosc();
										if(lt.getIlePaczek()< 1){
											towaryNaMagazynie.remove(kodTowaru);
										}
									}
									listModel.addElement(kodTowaru + " - "+nazwaTowaru + " - " +ilePaczek + " szt.");
									listElementy.setModel(listModel);
								}
			
							}
						};
						
					}

					@Override
					public void dodajZamowieniaOKAction() {
						if(zamowienie.getTowary().size() < 1 || textFieldImieINazwisko.getText().length() == 0){
							JOptionPane.showMessageDialog(frmSystemyWbudowaneI, "Musisz wypełnić: \"Imię i nazwisko\" oraz wybrać elementy zamówienia.",
				                    "Błąd", JOptionPane.ERROR_MESSAGE);
						}
						else {
							zamowienie.setDaneKlienta(textFieldImieINazwisko.getText());
							zamowienia.put(index, zamowienie);
							zamowieniaListModel.addElement(zamowienie.getNumerZamowienia() + ": "+zamowienie.getDaneKlienta() + " - "+zamowienie.getTowary().size() + " el." + " ("+zamowienie.getTerminRealizacji()+")");
//							for(ListTowarTO t: zamowienie.getTowaryDoListy()) {
//								zamowieniaListModel.addElement(zamowienie.getNumerZamowienia() + ": "+ zamowienie.getDaneKlienta() + " - "+t.getIlePaczek() + " x "+t.getNazwa() +" ("+zamowienie.getTerminRealizacji()+")");
//							}
							listZamowienia.setModel(zamowieniaListModel);
							aktualizujProdukty(towaryNaMagazynie);
							closeAddOrderBox();
						}
					}

					@Override
					public void anulujAction() {
						List<TowarTO> towary = zamowienie.getTowary();
						if(towary != null && towary.size() > 0){
							for(TowarTO t : towary){
								t.setZarezerwowany(false);
								if(towaryNaMagazynie.get(t.getKodTowaru()) == null){
									towaryNaMagazynie.put(t.getKodTowaru(), new ListTowarTO(t));
								}
								else
									towaryNaMagazynie.get(t.getKodTowaru()).zwiekszIlosc();
							}
						}
					}

					@Override
					public void usunTowarAction() {
						int [] indexy = listElementy.getSelectedIndices();
						ArrayList<String> listObjects = new ArrayList<>();
						for(int i = 0; i < indexy.length; i++){
							String el = listModel.getElementAt(indexy[i]);
							listObjects.add(el);
							String kodTowaru = el.substring(0, el.indexOf('-') -1);
							String ileEl = el.substring(el.lastIndexOf('-')+1, el.indexOf("szt.")-1);
							int ileElem = -1;
							try{
								ileElem = Integer.parseInt(ileEl.trim());
							}catch(NumberFormatException e){
								log.error("Nieudane parsowanie liczby: "+ileEl);
								continue;
							}
							boolean znaleziono = false;
							int licznik = 0;
							for(TowarTO t : zamowienie.getTowary()){
								if(licznik == ileElem)
									break;
								if(t.getKodTowaru().equals(kodTowaru) && t.isZarezerwowany()){
									t.setZarezerwowany(false);
									if(towaryNaMagazynie.get(t.getKodTowaru()) == null){
										towaryNaMagazynie.put(t.getKodTowaru(), new ListTowarTO(t));
									}
									else
										towaryNaMagazynie.get(t.getKodTowaru()).zwiekszIlosc();
									znaleziono = true;
									licznik++;
								}
							}
							if(!znaleziono){
								log.error("Nie można usunąć towaru o kodzie: "+kodTowaru);
								dodajWpisDoKonsoli("Nie można usunąć towaru o kodzie: "+kodTowaru);
								continue;
							}
						}
						
						for(String obj : listObjects){
							listModel.removeElement(obj);
						}
						listElementy.setModel(listModel);
					}

				};
			}
		});
		btnWczytajZamowienia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frmSystemyWbudowaneI);

				if (result == JFileChooser.APPROVE_OPTION) {
					IOLogic logic = new IOLogic();
					
					ArrayList<ZamowienieTO> noweZam = logic.readOrdersFromFile(fileChooser.getSelectedFile(), zamowienia, regaly, magazyn, towaryNaMagazynie);
					zamowieniaLista = noweZam;
					dodajZamowienia(noweZam);
					aktualizujProdukty(towaryNaMagazynie);
					saveFile.setEnabled(true);
					saveAsFile.setEnabled(true);
					log.info("Wczytano zamówienia");			
					dodajWpisDoKonsoli("Wczytano zamówienia z pliku :" + fileChooser.getSelectedFile());
				}
			}
		});
		btnDodajZamowienie.setEnabled(false);
		btnWczytajZamowienia.setEnabled(false);
		btnDodajZamowienie.setToolTipText("Brak produktów na magazynie");
		btnWczytajZamowienia.setToolTipText("Brak produktów na magazynie");
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Liczba wolnych box\u00F3w", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(812, 0, 212, 70);
		frmSystemyWbudowaneI.getContentPane().add(panel_8);
		
		radioButton1Box = new JRadioButton("1", true);
		radioButton1Box.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radioButton2Boxy = new JRadioButton("2", false);
		radioButton2Boxy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		radioButton1Box.setActionCommand("1Box");
		radioButton2Boxy.setActionCommand("2Boxy");
		
		RadioButtonHandler handler = new RadioButtonHandler();
		radioButton1Box.addActionListener(handler);
		radioButton2Boxy.addActionListener(handler);
		
		ButtonGroup zmianaIlosciBoxow = new ButtonGroup();
		zmianaIlosciBoxow.add(radioButton1Box);
		zmianaIlosciBoxow.add(radioButton2Boxy);
		
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(35)
					.addComponent(radioButton1Box, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(radioButton2Boxy)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton1Box, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(radioButton2Boxy))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_8.setLayout(gl_panel_8);

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
	
    private class RadioButtonHandler implements  ActionListener {
	    @Override
	    public void actionPerformed( ActionEvent e ) {
	        if (e.getActionCommand().equals("1Box")){
	        	//TODO
	        	dodajWpisDoKonsoli("Wybrano 1 Box");
	        	log.info("Wybrano 1 Box");
	        }
	        else {
	        	//TODO
	        	dodajWpisDoKonsoli("Wybrano 2 Boxy");
	        	log.info("Wybrano 2 Boxy");
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
		SwingUtilities.updateComponentTreeUI(frmSystemyWbudowaneI);
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
		konsolaListModel.add(0, wpis);
//		konsolaListModel.addElement(wpis);
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
	
	public static void ustalLiczbeZamowien(int lzam){
		liczbaZamowien = lzam;
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
	public static void ustawLiczbePrzedmiotow(int lprzedm) {
		lblLbliloscprzedmiotow.setText(String.valueOf(lprzedm));
	}
	
	public static void zmniejszLiczbePrzedmiotow(){
		liczbaPrzedmiotow --;
		lblLbliloscprzedmiotow.setText(String.valueOf(liczbaPrzedmiotow));
	}
	
	public static void zwiekszLiczbePrzedmiotow() {
		liczbaPrzedmiotow++;
		lblLbliloscprzedmiotow.setText(String.valueOf(liczbaPrzedmiotow));
	}
	

	/* liczba wszystkich miejsc */
	public static void ustawLiczbeWszystkichMiejsc(int liczbaWszystkichMiejsc) {
		lblWszystkieMiejsca.setText(String.valueOf(liczbaWszystkichMiejsc));
	}

	/* Dodaj zamowienia */
	public static void dodajZamowienia(ArrayList<ZamowienieTO> listaZamowien) {

		if (listaZamowien != null){
			for (ZamowienieTO z : listaZamowien) {
				zamowieniaListModel.addElement(z.getNumerZamowienia() + ": "+z.getDaneKlienta() + " - "+z.getTowary().size() + " el." + " ("+z.getTerminRealizacji()+")");
//				for(ListTowarTO t: z.getTowaryDoListy()) {
//					zamowieniaListModel.addElement("ID:"+ z.getNumerZamowienia() + " - "+ z.getDaneKlienta() + " - "+t.getIlePaczek() + " x "+t.getNazwa() +" ("+z.getTerminRealizacji()+")");
//				}
			}
			listZamowienia.setModel(zamowieniaListModel);
		}else{
			log.error("przekazana pusta lista zamowien");
		}
	}
	
	/* produkty */
	public static void dodajProdukty(HashMap<String, ListTowarTO> listaProduktow){
		if(listaProduktow != null){
			for(ListTowarTO t: listaProduktow.values())
				produktyListModel.addElement(t.getKodTowaru()+": "+t.getNazwa() + " - "+t.getIlePaczek() +" x " + t.getIlosc() +" szt.");
			listProdukty.setModel(produktyListModel);
		}else{
			log.error("pusta lista produktow");
		}
		
	}
	
	public static void aktualizujProdukty(HashMap<String, ListTowarTO> towaryNaMagazynie) {
		produktyListModel.clear();
		for(ListTowarTO t : towaryNaMagazynie.values())				
			produktyListModel.addElement(t.getKodTowaru()+": "+t.getNazwa() + " - "+t.getIlePaczek() +" x " + t.getIlosc() +" szt.");
		listProdukty.setModel(produktyListModel);
	}

	public HashMap<String, ListTowarTO> getTowaryNaMagazynie() {
		return towaryNaMagazynie;
	}

	public void setTowaryNaMagazynie(HashMap<String, ListTowarTO> towaryNaMagazynie) {
		this.towaryNaMagazynie = towaryNaMagazynie;
	}
	
	private Integer getMaxKey(HashMap<Integer, ZamowienieTO> zamowienia){
		Integer max = null;

		for (Integer i : zamowienia.keySet()) {
		    if (max == null || i  > max)
		    {
		    	max = i;
		    }
		}
		
		return max;
	}

	public void setPietro(int pietro) {   
		if (pietro > 0 && pietro < MagazynUtils.liczbaPieter - 1) {   
			this.pietro = pietro;      
			levelTextField.setText(Integer.toString(pietro));   
			mapa.pokazPietro(pietro);          
			if (pietro == 0)          
				btnMinus.setEnabled(false);       
			else            
				btnMinus.setEnabled(true);       
				if (pietro < MagazynUtils.liczbaPieter - 1)   
					btnPlus.setEnabled(true);       
				else            
					btnPlus.setEnabled(false);    
			}      
		}

	
	class MyList extends JList<String> {
		private static final long serialVersionUID = -7810572755518819881L;

		public MyList() {
	        super();

		// Attach a mouse motion adapter to let us know the mouse is over an item and to show the tip.
		addMouseMotionListener( new MouseMotionAdapter() {
			public void mouseMoved( MouseEvent e) {
				MyList theList = (MyList) e.getSource();
				ListModel<String> model = theList.getModel();
				int index = theList.locationToIndex(e.getPoint());
				if (index > -1) {
					theList.setToolTipText(null);
					String el = model.getElementAt(index);
					String idZam = el.substring(0, el.indexOf(':'));
					int idZamowienia = -1;
					try{
						idZamowienia = Integer.parseInt(idZam.trim());
					}catch(NumberFormatException ex){
						log.error("Nieudane parsowanie identyfikatora zamówienia: "+idZamowienia);
					}
					ZamowienieTO zamowienie = zamowienia.get(idZamowienia);
					String text = "<html>Elementy zamówienia:<br>";
					int idx = 1;
					for(ListTowarTO t : zamowienie.getTowaryDoListy()){
						text += idx+". "+t.getKodTowaru() + ": " + t.getNazwa() + " - " + t.getIlePaczek() + " x " + t.getIlosc() + " szt.<br>";
						idx++;
					}
					text += "</html>";
					theList.setToolTipText(text);
				}
			}
		});
	    }

	    // Expose the getToolTipText event of our JList
	    public String getToolTipText(MouseEvent e){
	        return super.getToolTipText();
	    }

	}
}
