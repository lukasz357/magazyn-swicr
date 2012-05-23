package siwc.magazyn;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
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
import siwc.magazyn.dto.PoleTO;
import siwc.magazyn.dto.TowarTO;
import siwc.magazyn.dto.ZamowienieTO;
import siwc.magazyn.logic.Algorithm;
import siwc.magazyn.logic.IOLogic;
import siwc.magazyn.panels.MapaMagazynu;
import siwc.magazyn.panels.RegalPanel;
import siwc.magazyn.thirdparty.Clock;
import siwc.magazyn.utils.MagazynUtils;

public class Magazyn {
	private static Logger log = Logger.getLogger(Magazyn.class);
	private JFrame frmSystemyWbudowaneI;
	private JMenuItem saveFile;
	private JMenuItem readProductsMenuItem;
	private JMenuItem readOrdersMenuItem;
	private JMenuItem saveAsFile;
	private JMenuItem closeWindow;
	private JMenuItem aboutBox;
	private JMenu mnZmienStyl;
	private JMenu mnPomoc;
	private JFileChooser fileChooser;
	private JLabel lblNewLabel = new JLabel("Systemy wbudowane i czasu rzeczywistego - Magazyn");
	private MapaMagazynu mapa;
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

	private int pietro = 0;
	private JPanel panelStatystyki;
	private JPanel panelLegenda;
	private JLabel lblLiczbaZamowienTekst;
	private static JLabel lblLiczbaZamowien;

	/* statystyki */
	private static int liczbaZamowien = 0;
	private static int liczbaZamowienZrealizowany = 0;
	private static int liczbaWszystkichMiejsc = MagazynUtils.liczbaRegalow * MagazynUtils.liczbaBoxowWRegale;
	private static int liczbaPrzedmiotow = 0;
	private static float calkowityCzasRealizacji = 0;
	private JLabel lblLiczbaZamwienZrealizowanychTekst;
	private static JLabel lblLiczbaZamowienZrealizowanych;
	private JLabel lblIleProduktowTekst;
	private static JLabel lblIloscprzedmiotow;
	private JLabel lblLiczbaDostepnychProduktowText;
	private static JLabel lblLiczbaWszystkichMiejscWMagazynieText;
	private JLabel lblSredniCzasRealizacjiTekst;
	private static JLabel lblDostepnychProduktow;
	private static JLabel lblWszystkichMiejscWMag;
	private static JLabel lblSredniCzasRealizacji;
	private JLabel lblPustaPka;
	private JLabel lblPkaTransferowa;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_5;
	private JLabel lblWzek;
	private JPanel panel_6;
	private JScrollPane scrollPaneListaZamowien;
	private static ZamowieniaJList listZamowienia;
	private JPanel panel_4;
	private JLabel lblBoksZajety;
	private JPanel panel_7_produkty;
	private JButton btnWczytajProdukty;
	private JScrollPane scrollPaneProdukty;
	private ArrayList<RegalPanel> regaly;
	private static ProduktyJList listProdukty;
	private HashMap<String, ListTowarTO> towaryNaMagazynie; // tylko do listy po prawej stronie
	private HashMap<Integer, ZamowienieTO> zamowienia;
	private int idPolaCounter;
	private ArrayList<String> tempDoklPoz;

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
		
		mapa = new MapaMagazynu(this, regaly);
		mapa.setBounds(10, 72, 792, 396);

		mapa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		
		readProductsMenuItem = new JMenuItem("Wczytaj produkty");
		readProductsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frmSystemyWbudowaneI);

				if (result == JFileChooser.APPROVE_OPTION) {
					IOLogic logic = new IOLogic();
					//Wyczyszczenie poprzednich produktów
					towaryNaMagazynie.clear();
					produktyListModel.clear();
					
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
					ustalLiczbePrzedmiotow(magazyn.getLiczbaWszystkichProduktow());
					ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
					saveFile.setEnabled(true);
					saveAsFile.setEnabled(true);
				}
			}
		});
		readProductsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		menuPlik.add(readProductsMenuItem);
		menuPlik.addSeparator();
		
		readOrdersMenuItem = new JMenuItem("Wczytaj zamówienia");
		readOrdersMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frmSystemyWbudowaneI);

				if (result == JFileChooser.APPROVE_OPTION) {
					IOLogic logic = new IOLogic();			
					logic.readOrdersFromFile(fileChooser.getSelectedFile(), zamowienia, regaly, magazyn, towaryNaMagazynie);
					dodajZamowienia(zamowienia);
					aktualizujProdukty(towaryNaMagazynie);
					saveFile.setEnabled(true);
					saveAsFile.setEnabled(true);
					log.info("Wczytano zamówienia");			
					dodajWpisDoKonsoli("Wczytano zamówienia z pliku :" + fileChooser.getSelectedFile());
					ustalLiczbeZamowien(zamowienia.size());
					ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
				}
			}
		});
		readOrdersMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		menuPlik.add(readOrdersMenuItem);
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
		mnPomoc = new JMenu("Pomoc");
		menuBar.add(mnPomoc);
		aboutBox = new JMenuItem("O programie");
		aboutBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutBox(frmSystemyWbudowaneI, true);
			}
		});
		mnPomoc.add(aboutBox);
		

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
							public void odswiezZamowienia(ZamowienieTO z) {
								zamowienia.remove(z.getNumerZamowienia());
								zamowieniaListModel.removeElement(z.getNumerZamowienia() + ": "+z.getDaneKlienta() + " - "+z.getTowary().size() + " el." + " ("+z.getTerminRealizacji()+")");
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

							@Override
							public void zwiekszLiczbeZamowienZrealizowanych(float czasRealizacjiZamowienia) {
								Magazyn.zwiekszLiczbeZamowienZrealizowanych(czasRealizacjiZamowienia);
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
		/************************************************************************************/
		KonsolaScrollPane = new JScrollPane();
		KonsolaScrollPane.setBounds(20, 590, 782, 150);

		lblKonsola = new JLabel("Konsola");
		lblKonsola.setBounds(20, 565, 37, 14);

		panelStatystyki = new JPanel();
		panelStatystyki.setBounds(802, 462, 280, 183);
		panelStatystyki.setBorder(new TitledBorder(null, "Statystyki", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelLegenda = new JPanel();
		panelLegenda.setBounds(20, 479, 772, 75);
		panelLegenda.setBorder(new TitledBorder(null, "Legenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelStatystyki.setLayout(null);

		lblLiczbaZamowienTekst = new JLabel("Liczba zamówień :");
		lblLiczbaZamowienTekst.setBounds(10, 34, 94, 14);
		panelStatystyki.add(lblLiczbaZamowienTekst);

		lblLiczbaZamowien = new JLabel("0");
		lblLiczbaZamowien.setFont(new Font("Tahoma", Font.BOLD, 11));
		// lblLiczbaZamowien.set
		lblLiczbaZamowien.setBounds(185, 34, 35, 14);
		panelStatystyki.add(lblLiczbaZamowien);

		lblLiczbaZamwienZrealizowanychTekst = new JLabel("Liczba zamówien zrealizowanych:");
		lblLiczbaZamwienZrealizowanychTekst.setBounds(10, 59, 165, 14);
		panelStatystyki.add(lblLiczbaZamwienZrealizowanychTekst);

		lblLiczbaZamowienZrealizowanych = new JLabel("0");
		lblLiczbaZamowienZrealizowanych.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLiczbaZamowienZrealizowanych.setBounds(185, 59, 35, 14);
		panelStatystyki.add(lblLiczbaZamowienZrealizowanych);

		lblIleProduktowTekst = new JLabel("Liczba wszystkich produktów:");
		lblIleProduktowTekst.setBounds(10, 84, 165, 14);
		panelStatystyki.add(lblIleProduktowTekst);

		lblIloscprzedmiotow = new JLabel("0");
		lblIloscprzedmiotow.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIloscprzedmiotow.setBounds(185, 84, 35, 14);
		panelStatystyki.add(lblIloscprzedmiotow);

		lblLiczbaDostepnychProduktowText = new JLabel("Liczba dostępnych produktów:");
		lblLiczbaDostepnychProduktowText.setBounds(10, 109, 165, 14);
		panelStatystyki.add(lblLiczbaDostepnychProduktowText);

		lblLiczbaWszystkichMiejscWMagazynieText = new JLabel("Wszystkich miejsc w magazynie:");
		lblLiczbaWszystkichMiejscWMagazynieText.setBounds(10, 134, 165, 14);
		panelStatystyki.add(lblLiczbaWszystkichMiejscWMagazynieText);

		lblSredniCzasRealizacjiTekst = new JLabel("Średni czas realizacji:");
		lblSredniCzasRealizacjiTekst.setBounds(10, 159, 165, 14);
		panelStatystyki.add(lblSredniCzasRealizacjiTekst);

		lblDostepnychProduktow = new JLabel("0");
		lblDostepnychProduktow.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDostepnychProduktow.setBounds(185, 109, 35, 14);
		panelStatystyki.add(lblDostepnychProduktow);

		lblWszystkichMiejscWMag = new JLabel("0");
		lblWszystkichMiejscWMag.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWszystkichMiejscWMag.setBounds(185, 134, 35, 14);
		panelStatystyki.add(lblWszystkichMiejscWMag);
		// KonsolaList = new JList(konsolaLista.toArray());

		KonsolaList = new JList<>();
		KonsolaList.setModel(konsolaListModel);

		KonsolaScrollPane.setViewportView(KonsolaList);

		// TODO MOCK ZMAIAN W BOXACH - do wywalenia soon
		final Color[] colors = new Color[4];
		colors[0] = Color.WHITE;
		colors[1] = Color.BLACK;
		colors[2] = Color.RED;
		colors[3] = Color.YELLOW;

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
		frmSystemyWbudowaneI.getContentPane().add(lblNewLabel);
		frmSystemyWbudowaneI.getContentPane().add(panel);
		frmSystemyWbudowaneI.getContentPane().add(mapa);
		frmSystemyWbudowaneI.getContentPane().add(KonsolaScrollPane);
		frmSystemyWbudowaneI.getContentPane().add(lblKonsola);
		frmSystemyWbudowaneI.getContentPane().add(panelStatystyki);

		lblSredniCzasRealizacji = new JLabel("0");
		lblSredniCzasRealizacji.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSredniCzasRealizacji.setBounds(185, 159, 35, 14);
		panelStatystyki.add(lblSredniCzasRealizacji);
		frmSystemyWbudowaneI.getContentPane().add(panelLegenda);
		panelLegenda.setLayout(null);

		lblPustaPka = new JLabel("boks wolny");
		lblPustaPka.setBounds(53, 50, 70, 14);
		panelLegenda.add(lblPustaPka);

		lblPkaTransferowa = new JLabel("boks do przesuwania");
		lblPkaTransferowa.setBounds(161, 50, 102, 14);
		panelLegenda.add(lblPkaTransferowa);

		panel_2 = new JPanel();
		panel_2.setBackground(MagazynUtils.defaultBoxBackground);
		panel_2.setBounds(25, 46, MagazynUtils.boxSize, MagazynUtils.boxSize);
		panelLegenda.add(panel_2);

		panel_3 = new JPanel();
		panel_3.setBackground(MagazynUtils.freeBoxBackround);
		panel_3.setBounds(133, 46, MagazynUtils.boxSize, MagazynUtils.boxSize);
		panelLegenda.add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBackground(MagazynUtils.busyBoxBackground);
		panel_4.setBounds(278, 46, MagazynUtils.boxSize, MagazynUtils.boxSize);
		panelLegenda.add(panel_4);
		
		lblBoksZajety = new JLabel("boks zajęty");
		lblBoksZajety.setBounds(306, 50, 89, 14);
		panelLegenda.add(lblBoksZajety);
		
		panel_5 = new JPanel();
		panel_5.setBackground(MagazynUtils.liftBackground);
		panel_5.setBounds(626, 28, MagazynUtils.liftSizeX, MagazynUtils.liftSizeY);
		panelLegenda.add(panel_5);

		lblWzek = new JLabel("wózek");
		lblWzek.setBounds(654, 50, 46, 14);
		panelLegenda.add(lblWzek);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(MagazynUtils.reservedBoxBackground);
		panel_1.setBounds(377, 46, 18, 18);
		panelLegenda.add(panel_1);
		
		JLabel lblBoksZarezerwowany = new JLabel("boks zarezerwowany");
		lblBoksZarezerwowany.setBounds(403, 50, 131, 14);
		panelLegenda.add(lblBoksZarezerwowany);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(MagazynUtils.targetBoxBacground);
		panel_9.setBounds(516, 46, 18, 18);
		panelLegenda.add(panel_9);
		
		JLabel lblBoxDocelowy = new JLabel("box docelowy");
		lblBoxDocelowy.setBounds(545, 50, 89, 14);
		panelLegenda.add(lblBoxDocelowy);
		
		
		
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

		listZamowienia = new ZamowieniaJList();
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
							PoleTO p = znajdzPolePoId(t.getIdBoxu());
							int nrRg = p.getNrRegalu();
							int nrPtr = p.getPietro();
							String position = p.getPosition();
							regaly.get(nrRg).zmienKolorBoksu(nrPtr, position, MagazynUtils.busyBoxBackground);
							regaly.get(nrRg).zmienToolTipTextBoxu(nrPtr, position, "Pusty");
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
				ustalLiczbeZamowien(zamowienia.size());
				ustalLiczbePrzedmiotow(magazyn.getLiczbaWszystkichProduktow());
				ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
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
		panel_7_produkty.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dost\u0119pne produkty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					//Wyczyszczenie poprzednich produktów
					towaryNaMagazynie.clear();
					produktyListModel.clear();
					
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
					ustalLiczbePrzedmiotow(magazyn.getLiczbaWszystkichProduktow());
					ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
				}
			}
		});
		
		scrollPaneProdukty = new JScrollPane();
		
		listProdukty = new ProduktyJList();
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
							magazyn = logic.convertToMagazynTO(regaly);
							produktyListModel.clear();
							dodajProdukty(towaryNaMagazynie);
							zwiekszLiczbePrzedmiotow();
							ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
							dodajWpisDoKonsoli("Dodano produkt: "+kodTowaru + " - "+nazwa + " 1 szt.");
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
		btnUsunProdukty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] indexy = listProdukty.getSelectedIndices();
				ArrayList<String> listObjects = new ArrayList<>();
				for(int i = 0; i < indexy.length; i++){
					String el = produktyListModel.getElementAt(indexy[i]);
					listObjects.add(el);
					String kodProduktu= el.substring(0, el.indexOf(':'));

					List<PoleTO> pola = magazyn.getPolaZDostepnymiTowaramiByKod(kodProduktu);
					if(pola != null && pola.size() > 0){
						for(PoleTO p : pola){
							regaly.get(p.getNrRegalu()).zmienKolorBoksu(p.getPietro(), p.getPosition(), MagazynUtils.defaultBoxBackground);
							regaly.get(p.getNrRegalu()).zmienToolTipTextBoxu(p.getPietro(), p.getPosition(), "Pusty");
							int id = p.getTowar().getIdBoxu();
							p.setTowar(new TowarTO(id));
						}
					}
					towaryNaMagazynie.remove(kodProduktu);
				}
				
				for(String obj : listObjects){
					produktyListModel.removeElement(obj);
				}
				listProdukty.setModel(produktyListModel);
				aktualizujProdukty(towaryNaMagazynie);
				ustalLiczbePrzedmiotow(magazyn.getLiczbaWszystkichProduktow());
				ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
			}
		});
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
		panel_7.setBounds(812, 0, 434, 70);
		frmSystemyWbudowaneI.getContentPane().add(panel_7);
		zegar = new Clock();
		zegar.setFont(new Font("SansSerif", Font.PLAIN, 30));
		
		spinnerScaleTime = new JSpinner();
		spinnerScaleTime.setModel(new SpinnerListModel(new String[] {"Real", "5x", "10x", "20x", "30x", "50x", "100x", "1000x"}));
		spinnerScaleTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		spinnerScaleTime.getEditor().setEnabled(false);
		spinnerScaleTime.addChangeListener(new ChangeListener() {

	        @Override
	        public void stateChanged(ChangeEvent e) {
	        	String value = (String)spinnerScaleTime.getValue();
	            if(value.equals("Real")){
	            	zegar.rescaleTime(1000);
	            	MagazynUtils.boxMovingSleepTime = 1200;
	            }
	            else if(value.equals("5x")){
	            	zegar.rescaleTime(200);
	            	MagazynUtils.boxMovingSleepTime = 240;
	            }
	            else if(value.equals("10x")){
	            	zegar.rescaleTime(100);
	            	MagazynUtils.boxMovingSleepTime = 120;
	            }
	            else if(value.equals("20x")){
	            	zegar.rescaleTime(50);
	            	MagazynUtils.boxMovingSleepTime = 60;
	            }
	            else if(value.equals("30x")){
	            	zegar.rescaleTime(33);
	            	MagazynUtils.boxMovingSleepTime = 40;
	            }
	            else if(value.equals("50x")){
	            	zegar.rescaleTime(20);
	            	MagazynUtils.boxMovingSleepTime = 24;
	            }
	            else if(value.equals("100x")){
	            	zegar.rescaleTime(10);
	            	MagazynUtils.boxMovingSleepTime = 12;
	            }
	            else if(value.equals("1000x")){
	            	zegar.rescaleTime(1);
	            	MagazynUtils.boxMovingSleepTime = 1;
	            }
	            else {
	            	log.error("Błąd przy reskalowaniu zegara");
	            }
	        }
	    });
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(zegar, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerScaleTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(103))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
						.addComponent(spinnerScaleTime, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addComponent(zegar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(2))
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
						tempDoklPoz = new ArrayList<String>();
						new SelectProductBox(frmSystemyWbudowaneI, rootPaneCheckingEnabled, towaryNaMagazynie) {
							private static final long serialVersionUID = 7735927972721100415L;
							
							@Override
							public void dodajTowarAction() {
								ListTowarTO towar = (ListTowarTO) comboBoxTowary.getSelectedItem();
								int idx = comboBoxPozycje.getSelectedIndex();
								String kodTowaru = towar.getKodTowaru();
								String nazwaTowaru = towar.getNazwa();
								String doklPoz = (String) comboBoxPozycje.getItemAt(idx);
								String [] pozycjaSplit = doklPoz.split(":");
								Integer nrRg = -1;
								Integer nrPtr = -1;
								String pzc = pozycjaSplit[2].substring("Pozycja ".length());
								
								try{
									nrRg = Integer.parseInt(pozycjaSplit[0].trim().substring("Regał ".length()));
									nrPtr = Integer.parseInt(pozycjaSplit[1].trim().substring("Piętro ".length()));
								}catch(NumberFormatException e){
									log.error("Nieudane parsowanie nr Regalu lub nr Pietra z combo boxa");
									e.printStackTrace();
								}
								
								TowarTO t = regaly.get(nrRg).getTowar(nrPtr, pzc);
								ListTowarTO lt = towaryNaMagazynie.get(kodTowaru);
								t.setZarezerwowany(true);
								lt.zmniejszIlosc();
								zamowienie.getTowary().add(t);
								tempDoklPoz.add(doklPoz);
                                if(lt.getIlePaczek()< 1)
                                         towaryNaMagazynie.remove(kodTowaru);

								listModel.addElement(kodTowaru + " - "+nazwaTowaru + " - " +doklPoz);
								listElementy.setModel(listModel);
								comboBoxPozycje.removeItemAt(idx);
								comboBoxPozycje.revalidate();
			
							}

							@Override
							public void comboBoxChangeAction() {
								ListTowarTO lt = (ListTowarTO)comboBoxTowary.getSelectedItem();
								ArrayList<String> pozycje = magazyn.getDokladnePozycjeByKod(lt.getKodTowaru());
								comboBoxPozycje.removeAllItems();
								for(String s : pozycje) {
									comboBoxPozycje.addItem(s);
								}
							}

							@Override
							public void comboBoxPozycjeInitAction() {
								ListTowarTO lt = (ListTowarTO)comboBoxTowary.getSelectedItem();
								ArrayList<String> pozycje = magazyn.getDokladnePozycjeByKod(lt.getKodTowaru());
								comboBoxPozycje.removeAllItems();
								for(String s : pozycje) {
									comboBoxPozycje.addItem(s);
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
							for(String doklPoz : tempDoklPoz) {
								String [] pozycjaSplit = doklPoz.split(":");
								Integer nrRg = -1;
								Integer nrPtr = -1;
								String pzc = pozycjaSplit[2].substring("Pozycja ".length());
								try{
									nrRg = Integer.parseInt(pozycjaSplit[0].trim().substring("Regał ".length()));
									nrPtr = Integer.parseInt(pozycjaSplit[1].trim().substring("Piętro ".length()));
								}catch(NumberFormatException e){
									log.error("Nieudane parsowanie nr Regalu lub nr Pietra z combo boxa");
									e.printStackTrace();
								}
								regaly.get(nrRg).zmienKolorBoksu(nrPtr, pzc, MagazynUtils.reservedBoxBackground);// !!!! Dopiero tutaj trzeba
							}
							zamowienie.setDaneKlienta(textFieldImieINazwisko.getText());
							zamowienia.put(index, zamowienie);
							zamowieniaListModel.addElement(zamowienie.getNumerZamowienia() + ": "+zamowienie.getDaneKlienta() + " - "+zamowienie.getTowary().size() + " el." + " ("+zamowienie.getTerminRealizacji()+")");
//							for(ListTowarTO t: zamowienie.getTowaryDoListy()) {
//								zamowieniaListModel.addElement(zamowienie.getNumerZamowienia() + ": "+ zamowienie.getDaneKlienta() + " - "+t.getIlePaczek() + " x "+t.getNazwa() +" ("+zamowienie.getTerminRealizacji()+")");
//							}
							listZamowienia.setModel(zamowieniaListModel);
							aktualizujProdukty(towaryNaMagazynie);
							ustalLiczbeZamowien(zamowienia.size());
							ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
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
					logic.readOrdersFromFile(fileChooser.getSelectedFile(), zamowienia, regaly, magazyn, towaryNaMagazynie);
					dodajZamowienia(zamowienia);
					aktualizujProdukty(towaryNaMagazynie);
					saveFile.setEnabled(true);
					saveAsFile.setEnabled(true);
					log.info("Wczytano zamówienia");			
					dodajWpisDoKonsoli("Wczytano zamówienia z pliku :" + fileChooser.getSelectedFile());
					ustalLiczbeZamowien(zamowienia.size());
					ustalLiczbeDostepnychProduktow(getLiczbaDostepnychProduktow());
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
		panel_8.setVisible(false);
		frmSystemyWbudowaneI.getContentPane().add(panel_8);
		
		radioButton1Box = new JRadioButton("1", true);
		radioButton1Box.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radioButton2Boxy = new JRadioButton("2", false);
		radioButton2Boxy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		radioButton1Box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<MagazynUtils.liczbaRegalow; i++)
					mapa.setRegalFreeBoxes(i, 1);
			}
		});
		radioButton2Boxy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<MagazynUtils.liczbaRegalow; i++)
					mapa.setRegalFreeBoxes(i, 2);
			}
		});
		
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
		//Zwiekszenie czasu wyswietlania sie ToolTipTextu
		ToolTipManager.sharedInstance().setDismissDelay(20000);
		ToolTipManager.sharedInstance().registerComponent(listProdukty);
		ToolTipManager.sharedInstance().registerComponent(listZamowienia);
		ustalLiczbeWszystkichMiejsc(liczbaWszystkichMiejsc);
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
	public static void zwiekszLiczbeZamowienZrealizowanych(float czasRealizacjiZamowienia) {
		calkowityCzasRealizacji += czasRealizacjiZamowienia;
		liczbaZamowienZrealizowany++;
		float sredniCzas = calkowityCzasRealizacji/liczbaZamowienZrealizowany;
		lblSredniCzasRealizacji.setText(String.valueOf(sredniCzas) + " s");
		lblLiczbaZamowienZrealizowanych.setText(String.valueOf(liczbaZamowienZrealizowany));
	}

	public static void zmniejszLiczbeZamowienZrealizowanych() {
		liczbaZamowienZrealizowany--;
		lblLiczbaZamowienZrealizowanych.setText(String.valueOf(liczbaZamowienZrealizowany));
	}

	/* ilosc wszystkich produktow */
	public static void ustalLiczbePrzedmiotow(int lPrzedm) {
		liczbaPrzedmiotow = lPrzedm;
		lblIloscprzedmiotow.setText(String.valueOf(lPrzedm));
	}
	
	public static void zmniejszLiczbePrzedmiotow(){
		liczbaPrzedmiotow --;
		lblIloscprzedmiotow.setText(String.valueOf(liczbaPrzedmiotow));
	}
	
	public static void zwiekszLiczbePrzedmiotow() {
		liczbaPrzedmiotow++;
		lblIloscprzedmiotow.setText(String.valueOf(liczbaPrzedmiotow));
	}
	

	/* liczba dostepnych produktow */
	public static void ustalLiczbeDostepnychProduktow(int liczbaDostepnychProduktow) {
		lblDostepnychProduktow.setText(String.valueOf(liczbaDostepnychProduktow));
	}
	
	/* liczba wszystkich miejsc w magazynie */
	public static void ustalLiczbeWszystkichMiejsc(int liczbaWszystkichMiejsc){
		lblWszystkichMiejscWMag.setText(String.valueOf(liczbaWszystkichMiejsc));
	}
	
	/* średni czas realizacji */
	
	public static void ustalSredniCzasRealizacji(int sredniCzasRealizacji){
		lblSredniCzasRealizacji.setText(String.valueOf(sredniCzasRealizacji));
	}
	/* Dodaj zamowienia */
	public static void dodajZamowienia(HashMap<Integer, ZamowienieTO> listaZamowien) {
		zamowieniaListModel.clear();
		if (listaZamowien != null){
			for (ZamowienieTO z : listaZamowien.values()) {
				zamowieniaListModel.addElement(z.getNumerZamowienia() + ": "+z.getDaneKlienta() + " - "+z.getTowary().size() + " el." + " ("+z.getTerminRealizacji()+")");
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
				produktyListModel.addElement(t.getKodTowaru()+": "+t.getNazwa() + " - "+t.getIlePaczek() +" szt.");
			listProdukty.setModel(produktyListModel);
		}else{
			log.error("pusta lista produktow");
		}
		
	}
	
	public static void aktualizujProdukty(HashMap<String, ListTowarTO> towaryNaMagazynie) {
		produktyListModel.clear();
		for(ListTowarTO t : towaryNaMagazynie.values())				
			produktyListModel.addElement(t.getKodTowaru()+": "+t.getNazwa() + " - "+t.getIlePaczek() +" szt.");
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

	
	class ZamowieniaJList extends JList<String> {
		private static final long serialVersionUID = -7810572755518819881L;

		public ZamowieniaJList() {
	        super();

		// Attach a mouse motion adapter to let us know the mouse is over an item and to show the tip.
		addMouseMotionListener( new MouseMotionAdapter() {
			public void mouseMoved( MouseEvent e) {
				ZamowieniaJList theList = (ZamowieniaJList) e.getSource();
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
						text += idx+". "+t.getKodTowaru() + ": " + t.getNazwa() + " - " + t.getIlePaczek() + " szt.<br>";
						idx++;
					}
					text += "</html>";
					theList.setToolTipText(text);
				}
			}
		});
	    }

	    public String getToolTipText(MouseEvent e){
	        return super.getToolTipText();
	    }
	}
	
	class ProduktyJList extends JList<String> {
		private static final long serialVersionUID = -7810572755518819881L;

		public ProduktyJList() {
	        super();

		// Attach a mouse motion adapter to let us know the mouse is over an item and to show the tip.
		addMouseMotionListener( new MouseMotionAdapter() {
			public void mouseMoved( MouseEvent e) {
				ProduktyJList theList = (ProduktyJList) e.getSource();
				ListModel<String> model = theList.getModel();
				int index = theList.locationToIndex(e.getPoint());
				if (index > -1) {
					theList.setToolTipText(null);
					String el = model.getElementAt(index);
					String kodTowaru = el.substring(0, el.indexOf(':'));
					ArrayList<String> towary = magazyn.getInfoODostepnychTowarachByKod(kodTowaru);
					
					String text = "<html>Pozycje towarów o kodzie "+kodTowaru+":<br>";
					int idx = 1;
					for(String t : towary){
						String [] info = t.split(":");
						if(info.length != 3){
							log.error("Blad w parsowaniu info o towarze do listy");
							continue;
						}
						text += idx+". "+" Pietro: "+info[0] + " Regał: " + info[1] + " Pozycja: " + info[2] + "<br>";
						idx++;
					}
					text += "</html>";
					theList.setToolTipText(text);
				}
			}
		});
	    }

	    public String getToolTipText(MouseEvent e){
	        return super.getToolTipText();
	    }
	}
	
	private int getLiczbaDostepnychProduktow(){
		int liczba = 0;
		for(ListTowarTO lt: towaryNaMagazynie.values()){
			liczba += lt.getIlePaczek();
		}
		return liczba;
	}
	
	private PoleTO znajdzPolePoId(Integer id) {
		if (id != null) {
			
			for(int i=0; i < magazyn.getPietra().keySet().size(); i++) {
				for (int j=0; j < magazyn.getWielkoscXMagazynu(); j++) {
					for (int k=0; k < magazyn.getWielkoscYMagazynu(); k++) {
						if (magazyn.getPietra().get(i)[j][k].getId() != null &&magazyn.getPietra().get(i)[j][k].getId().equals(id)) {
							magazyn.getPietra().get(i)[j][k].setZ(i);
							return magazyn.getPietra().get(i)[j][k];
						}
					}
				}
			}
			return null;
		}
		else
			return null;
	}
}