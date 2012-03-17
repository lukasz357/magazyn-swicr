package siwc.magazyn.panels;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import siwc.magazyn.dto.BoxTO;
import siwc.magazyn.utils.MagazynUtils;

import java.awt.Color;
import java.awt.GridLayout;

public class RegalPanel extends JPanel {
	private static final long serialVersionUID = 3160205159810161295L;

	private int rows = MagazynUtils.rzedowWRegale;
	private int cols = MagazynUtils.kolumnWRegale;

	private BoxPanel[][] boxes = new BoxPanel[rows][cols];

	public RegalPanel() {
		setLayout(new GridLayout(MagazynUtils.rzedowWRegale, MagazynUtils.kolumnWRegale, 0, 0));
		BoxPanel bp;
		for (int i = 0; i < rows; i++) {
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
	}
}
