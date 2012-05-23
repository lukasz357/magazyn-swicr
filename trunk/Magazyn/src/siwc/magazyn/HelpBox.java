package siwc.magazyn;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class HelpBox extends javax.swing.JDialog{
	private static final long serialVersionUID = -3991402985653887754L;
	
	public HelpBox(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        setMinimumSize(new Dimension(430, 250));
        setPreferredSize(new Dimension(430, 250));
        setSize(new Dimension(490, 442));
        setModal(true);
        setResizable(false);
        initComponents(parent);
        this.setLocationRelativeTo(parent);
        showAboutBox();
	}
	
    public void closeAboutBox() {
        setVisible(false);
        dispose();
    }

    public void showAboutBox() {
        setVisible(true);
    }
	private void  initComponents(final java.awt.Frame frame) {
		setTitle("Pomoc");
			
		JButton btnZamknij = new JButton("Zamknij");
		btnZamknij.setPreferredSize(new Dimension(67, 25));
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAboutBox();
			}
		});
		btnZamknij.setMinimumSize(new Dimension(67, 25));
		btnZamknij.setMaximumSize(new Dimension(63, 23));
		
		JLabel lblNazwa = new JLabel("Pomoc");
		lblNazwa.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblSystemyWbudowaneI = new JLabel((String) null);
		
		JTextArea txtrScenariuszDziaaniaProgramu = new JTextArea();
		txtrScenariuszDziaaniaProgramu.setEditable(false);
		txtrScenariuszDziaaniaProgramu.setWrapStyleWord(true);
		txtrScenariuszDziaaniaProgramu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtrScenariuszDziaaniaProgramu.setLineWrap(true);
		txtrScenariuszDziaaniaProgramu.setText("SCENARIUSZ DZIAŁANIA PROGRAMU\r\n\r\n1. Użytkownik naciska przycisk Wczytaj w panelu produktów i wybiera przygotowany plik z produktami, którymi zostanie zapełniony magazyn.\r\n\r\n2. Program wczytuje produkty zapełniając magazyn.\r\n\r\n3. Użytkownik naciska przycisk Wczytaj w panelu zamówień i wybiera przygotowany plik z zamówieniami (analogicznie jak wczytywanie produktów).\r\n\r\n4. Program wczytuje zamówienia.\r\n\r\n5. Użytkownik naciska przycisk Start.\r\n\r\n6. Program przystępuje do realizacji zamówień.\r\n\r\n7. Po zrealizowaniu zamówień, program oczekuje na nowe zamówienia, które można analogicznie jak poprzednio wczytać z pliku.\r\nNaciśnięcie przycisku Stop kończy oczekiwanie programu na nowe zamówienia.");
		JScrollPane scrolltxt = new JScrollPane(txtrScenariuszDziaaniaProgramu);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSystemyWbudowaneI)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrolltxt, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNazwa))
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(205, Short.MAX_VALUE)
					.addComponent(btnZamknij, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(178))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNazwa)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(lblSystemyWbudowaneI))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrolltxt, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnZamknij, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
