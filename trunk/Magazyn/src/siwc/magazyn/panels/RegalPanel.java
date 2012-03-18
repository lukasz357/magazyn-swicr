package siwc.magazyn.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import siwc.magazyn.dto.BoxTO;
import siwc.magazyn.utils.MagazynUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class RegalPanel extends JPanel {
	private static final long serialVersionUID = 3160205159810161295L;

	private int rows = MagazynUtils.rzedowWRegale;
	private int cols = MagazynUtils.kolumnWRegale;

	private BoxPanel[][] boxes = new BoxPanel[rows][cols];

	public RegalPanel(boolean withColNumbers) {
		if (withColNumbers) { // czy sa wyswietlane numery kolumn - sadze by to wpisywac w pierwszym tylko rzedzie ;P
			setLayout(new GridLayout(MagazynUtils.rzedowWRegale + 1, MagazynUtils.kolumnWRegale + 1, 0, 0));
			add(new JLabel());
			for(int i=0; i<cols; i++) {
				add(new JLabel(Integer.toString(i+1)));
			}
		} else {
			setLayout(new GridLayout(MagazynUtils.rzedowWRegale, MagazynUtils.kolumnWRegale + 1, 0, 0));
		}
		
		setBackground(Color.WHITE);
		
		BoxPanel bp;

		for (int i = 0; i < rows; i++) {
			char c = (char) (65 + i);
			add(new JLabel(c + ""));
			for (int j = 0; j < cols; j++) {
				bp = new BoxPanel(j, i, new BoxTO());
				bp.setBackground(Color.black);
				bp.setBorder(new LineBorder(new Color(192, 192, 192), 1, false));
				boxes[i][j] = bp;
				add(bp);
			}
		}
	}

	public void zmienKolorBoksu(int x, int y, Color c) {
		boxes[y][x].setBackground(c);
		JLabel nr = new JLabel(x * y + "");
		nr.setForeground(new Color(255, 200, 0));
		boxes[y][x].add(nr, BorderLayout.CENTER);
		boxes[y][x].revalidate();
	}
}
