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
import java.util.Random;

import javax.swing.ButtonGroup;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import siwc.magazyn.panels.MapaMagazynu;
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
	private MapaMagazynu mapaMagazynu;
	private JButton btnRegal1Random;
	private JButton btnRegal2Random;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnRight;
	private JButton btnLeft;
	private JButton btnPlus;
	private JButton btnMinus;
	private JTextField levelTextField;

	private int pietro = 0;
	private JButton btnStop;
	private JButton btnStart;
	private JLabel lblStatystyki;
	private JLabel lblZamwienia;
	private JButton btnDodaj;
	private JButton btnEdytuj;
	private JPanel panel;
	private JLabel lblPietro;
	private JPanel panel_1;
	private JButton btnRegal3Random;
	private JButton btnRWPrawo;
	private JButton btnRWLewo;
	private JButton btnRWPrawo_1;
	private JButton btnRWPrawo_2;
	private JButton btnRWLewo_1;
	private JButton btnRWLewo_2;
	private JScrollPane KonsolaScrollPane;
	private JList KonsolaList;

	
	/* Listy konsoli/zamowien */
	ArrayList<String> konsolaLista = new ArrayList<>();
	
	
	
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
	
	/*temp */
	public void tymczasowoWypelnijKonsole(){
		konsolaLista.add("Magazyn został uruchomiony");
		konsolaLista.add("Dodano zamowienie nr 5435345, czas odbioru 14:10");
		konsolaLista.add("Dodano zamowienie nr 423423, czas odbioru 14:11");
		konsolaLista.add("Dodano zamowienie nr 5434235423432345, czas odbioru 14:18");
		konsolaLista.add("Dodano zamowienie nr 54432435345, czas odbioru 15:50");
		konsolaLista.add("Dodano zamowienie nr 543423425345, czas odbioru 14:10");
		konsolaLista.add("Dodano zamowienie nr 523, czas odbioru 14:15");
		konsolaLista.add("Dodano zamowienie nr 123123, czas odbioru 18:00");
		konsolaLista.add("Magazyn został zatrzymany");
		
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
					frame.dispose();
				}
			}
		});
		closeWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuPlik.add(closeWindow);

		JMenu mnOkno = new JMenu("Okno");
		menuBar.add(mnOkno);

		mnZmienStyl = new JMenu("Zmień styl");
		mnOkno.add(mnZmienStyl);

		mapaMagazynu = new MapaMagazynu();

		mapaMagazynu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// TODO MOCK ZMAIAN W BOXACH - do wywalenia soon
		final Color[] colors = new Color[4];
		colors[0] = Color.WHITE;
		colors[1] = Color.BLACK;
		colors[2] = Color.RED;
		colors[3] = Color.BLUE;

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

			}
		});

		/* START MAGAZYNU */
		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				log.info("Magazyn został uruchomiony");
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
				 * dodaj zamowienie 
				 * zaloguj w konsoli
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
				 * edytuj zamowienie 
				 * zaloguj w konsoli
				 */
				log.info("Edytowano zamówienie zamówienie");
			}
		});
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Panel testowy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		KonsolaScrollPane = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, 0, 0, Short.MAX_VALUE))
							.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
							.addComponent(mapaMagazynu, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(KonsolaScrollPane, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnDodaj)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnEdytuj))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblZamwienia)
									.addGap(74))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStatystyki)
							.addGap(39))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(lblZamwienia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDodaj)
								.addComponent(btnEdytuj)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(mapaMagazynu, GroupLayout.PREFERRED_SIZE, 396, 396)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(KonsolaScrollPane, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(40))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStatystyki)
							.addGap(94)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGap(75)))
					.addGap(14))
		);
		
		KonsolaList = new JList(konsolaLista.toArray());
		KonsolaScrollPane.setViewportView(KonsolaList);
		
				btnLeft = new JButton("left");
				
						btnUp = new JButton("up");
						btnUp.setMnemonic(KeyEvent.VK_UP);
						btnUp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mapaMagazynu.moveUp();
							}
						});
				
						btnRight = new JButton("right");
						btnRight.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mapaMagazynu.moveRight();
							}
						});
				
						btnDown = new JButton("down");
						btnDown.setMnemonic(KeyEvent.VK_DOWN);
						btnDown.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								mapaMagazynu.moveDown();

							}
						});
				
						btnRegal2Random = new JButton("Regal2 random");
						btnRegal2Random.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Random rand = new Random();

								char prefix = (char) (65 + rand.nextInt(MagazynUtils.rzedowWRegale));
								int sufix = rand.nextInt(MagazynUtils.kolumnWRegale);
								int c = rand.nextInt(colors.length);

								String position = prefix + Integer.toString(sufix + 1);

								mapaMagazynu.zmienKolorBoksu("regal2", position, colors[c]);
								log.info("R2: [" + position + "] color=" + c + "\t linia 192 jak nie wiesz jaki kolor");
							}
						});
				btnRegal1Random = new JButton("Regal1 random");
				btnRegal1Random.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Random rand = new Random();

						char prefix = (char) (65 + rand.nextInt(MagazynUtils.rzedowWRegale));
						int sufix = rand.nextInt(MagazynUtils.kolumnWRegale);
						int c = rand.nextInt(colors.length);

						String position = prefix + Integer.toString(sufix + 1);

						mapaMagazynu.zmienKolorBoksu("regal1", position, colors[c]);
						log.info("R1: [" + position + "] color=" + c + "\t linia 192 jak nie wiesz jaki kolor");
					}
				});
				
				btnRegal3Random = new JButton("Regal3 random");
				btnRegal3Random.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Random rand = new Random();

						char prefix = (char) (65 + rand.nextInt(MagazynUtils.rzedowWRegale));
						int sufix = rand.nextInt(MagazynUtils.kolumnWRegale);
						int c = rand.nextInt(colors.length);

						String position = prefix + Integer.toString(sufix + 1);

						mapaMagazynu.zmienKolorBoksu("regal3", position, colors[c]);
						log.info("R3: [" + position + "] color=" + c + "\t linia 192 jak nie wiesz jaki kolor");
					}
				});
				
				btnRWPrawo = new JButton("R1 w prawo");
				btnRWPrawo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.obrocWPrawo("regal1");
					}
				});
				
				
				btnRWPrawo_1 = new JButton("R2 w prawo");
				btnRWPrawo_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.obrocWPrawo("regal2");
					}
				});
				
				btnRWPrawo_2 = new JButton("R3 w prawo");
				btnRWPrawo_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.obrocWPrawo("regal3");
					}
				});

				btnRWLewo = new JButton("R1 w lewo");
				btnRWLewo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.obrocWLewo("regal1");
					}
				});
				
				btnRWLewo_1 = new JButton("R2 w lewo");
				btnRWLewo_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.obrocWLewo("regal2");
					}
				});
				
				btnRWLewo_2 = new JButton("R3 w lewo");
				btnRWLewo_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.obrocWLewo("regal3");
					}
				});
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap(479, Short.MAX_VALUE)
							.addComponent(btnRWLewo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRWPrawo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRegal1Random)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnLeft)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDown)
										.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRight)
									.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
									.addComponent(btnRWLewo_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRWPrawo_2))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(471)
									.addComponent(btnRWLewo_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRWPrawo_1)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnRegal3Random)
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnRegal2Random)
									.addContainerGap())))
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRegal1Random)
								.addComponent(btnRWPrawo)
								.addComponent(btnRWLewo))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_1.createSequentialGroup()
													.addGap(44)
													.addComponent(btnLeft))
												.addGroup(gl_panel_1.createSequentialGroup()
													.addGap(30)
													.addComponent(btnUp)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnDown)))
											.addGap(14))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(btnRight)
											.addGap(30))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnRegal2Random)
										.addComponent(btnRWPrawo_1)
										.addComponent(btnRWLewo_1))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnRegal3Random)
										.addComponent(btnRWPrawo_2)
										.addComponent(btnRWLewo_2))
									.addGap(45))))
				);
				panel_1.setLayout(gl_panel_1);
				btnLeft.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mapaMagazynu.moveLeft();

					}
				});
		
		btnMinus = new JButton("-");
		btnMinus.setEnabled(false);
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pietro > 0) {
					pietro--;
					levelTextField.setText(Integer.toString(pietro));
					mapaMagazynu.ustawPietro(pietro);
					
					if(pietro == 0)
						btnMinus.setEnabled(false);
					if(pietro < MagazynUtils.liczbaPieter - 1)
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
					mapaMagazynu.ustawPietro(pietro);
					
					if(pietro == MagazynUtils.liczbaPieter - 1)
						btnPlus.setEnabled(false);
					if(pietro > 0)
						btnMinus.setEnabled(true);
				}
			}
		});
	
		levelTextField = new JTextField();
		levelTextField.setDisabledTextColor(Color.WHITE);
		levelTextField.setColumns(10);
		levelTextField.setText(Integer.toString(pietro));
		
		
		lblPietro = new JLabel("Piętro:");
		lblPietro.setFont(new Font("Tahoma", Font.BOLD, 13));
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPietro)
							.addGap(18)
							.addComponent(btnMinus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPlus)
							.addGap(16))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnMinus)
								.addComponent(lblPietro)
								.addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPlus))
							.addContainerGap())
				);
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
}
