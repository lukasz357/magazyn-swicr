package marek;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Plansza extends JFrame {
	private static final long serialVersionUID = -1082372636822205432L;

	// public static final int kolumny = 8;
	// public static final int wiersze = 8;

	private int wiersze = 0;
	private int kolumny = 0;
	public static JLabel label;
	public static MagazynJPanel[][] macierz;
	public static JPanel magazyn = new JPanel();

	public Plansza() {
		wiersze = MarekUtils.getWidth(); // pobieram wysokosc/szerokosc z utilsow
		kolumny = MarekUtils.getHeight();

		macierz = new MagazynJPanel[wiersze][kolumny];
		inicjalizuj();
	}

	private void inicjalizuj() {
		System.out.println("initialize has begun !");

		setTitle("Magazyn");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int boardSize = 0;
		int width = dim.width;
		int height = dim.height;
		if (width >= height) {
			boardSize = height / 2;
		} else {
			boardSize = width / 2;
		}
		createSquares();

		// setIconImage(new ImageIcon(�board.jpg�).getImage());
		getContentPane().add(magazyn, BorderLayout.CENTER);
		setVisible(true);
		Dimension preferredSize = new Dimension();
		preferredSize.width = boardSize;
		preferredSize.height = boardSize;
		setPreferredSize(preferredSize);
		setBounds(boardSize / 4, boardSize / 4, boardSize, boardSize);
		pack();
	}

	/* tworzy jPanele na calym magazynie */
	private void createSquares() {
		LayoutManager layout = new GridLayout(wiersze, kolumny);
		magazyn.setLayout(layout);
		for (int i = 0; i < wiersze; i++) {
			for (int j = 0; j < kolumny; j++) {
				macierz[i][j] = new MagazynJPanel();
				label = new JLabel("(" + i + ", " + j + ")");

				macierz[i][j].add(label);
				macierz[i][j].setBackground(Color.white);
				label.setForeground(new Color(0, 0, 250));

				// macierz[i][j].setToolTipText("Row " + i + " and Column " + j);

				magazyn.add(macierz[i][j]);
			}
		}
		zaznaczRegaly();
		zaznaczPunktOdbioru();

	}

	/* sprawdza rozklad magazynu i zaznacza odpowiednie jPanele jako kwadraty regalu */
	private void zaznaczRegaly() {

		List<Wspolrzedne> lista = MarekUtils.getWspolrzedneRegalow();

		// iteruje po liscie wspolrzednych regalow
		for (Wspolrzedne iterator : lista) {
			macierz[iterator.getX()][iterator.getY()].setIsShelf(true); // zaznaczam ,ze jest regalem
			macierz[iterator.getX()][iterator.getY()].setBackground(Color.BLACK); // koloruje go
		}
	}

	private void zaznaczPunktOdbioru() {
		List<Wspolrzedne> lista = MarekUtils.getPunktOdbioru();

		// iteruje po liscie wspolrzednych regalow
		for (Wspolrzedne iterator : lista) {
			macierz[iterator.getX()][iterator.getY()].setIsEntryPoint(true); // zaznaczam ,ze jest punktem odbioru
			macierz[iterator.getX()][iterator.getY()].setBackground(Color.yellow); // koloruje go
		}

	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Plansza();
			}
		});
	}

}
