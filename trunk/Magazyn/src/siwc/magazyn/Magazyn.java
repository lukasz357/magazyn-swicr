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
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
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
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

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
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		frame = new JFrame();
		frame.setTitle(MagazynUtils.frameTitle);
		frame.setSize(MagazynUtils.frameWidth, MagazynUtils.frameHeight);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
		FileNameExtensionFilter marekFilter = new FileNameExtensionFilter("Marek (*.ms; *.pała, *.pala, *.marek)", "ms", "marek", "pała", "pala");
		fileChooser.setFileFilter(marekFilter);
		fileChooser.addChoosableFileFilter(marekFilter);
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

		btnUp = new JButton("up");
		btnUp.setMnemonic(KeyEvent.VK_UP);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaMagazynu.moveUp();
			}
		});

		btnDown = new JButton("down");
		btnDown.setMnemonic(KeyEvent.VK_DOWN);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaMagazynu.moveDown();

			}
		});

		btnRight = new JButton("right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaMagazynu.moveRight();
			}
		});

		btnLeft = new JButton("left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapaMagazynu.moveLeft();

			}
		});

		btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pietro < MagazynUtils.liczbaPieter) {
					pietro++;
					levelTextField.setText(Integer.toString(pietro));
					mapaMagazynu.ustawPietro(pietro);
				}
			}
		});

		btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pietro > 0) {
					pietro--;
					levelTextField.setText(Integer.toString(pietro));
					mapaMagazynu.ustawPietro(pietro);
				}
			}
		});

		levelTextField = new JTextField();
		levelTextField.setDisabledTextColor(Color.WHITE);
		levelTextField.setColumns(10);
		levelTextField.setText(Integer.toString(pietro));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(437)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
					.addGap(313)
					.addComponent(btnMinus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPlus)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(206)
					.addComponent(btnLeft)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDown))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRight)
					.addPreferredGap(ComponentPlacement.RELATED, 810, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegal2Random)
						.addComponent(btnRegal1Random))
					.addGap(54))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(mapaMagazynu, GroupLayout.DEFAULT_SIZE, 792, 792)
					.addContainerGap(548, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnPlus)
						.addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMinus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mapaMagazynu, GroupLayout.PREFERRED_SIZE, 396, 396)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnRegal1Random)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRegal2Random))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnUp)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDown))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(btnRight))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(btnLeft)))
					.addContainerGap(163, Short.MAX_VALUE))
		);

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
