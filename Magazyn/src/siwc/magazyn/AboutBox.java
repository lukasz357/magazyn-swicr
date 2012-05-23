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


public class AboutBox extends javax.swing.JDialog{
	private static final long serialVersionUID = -3991402985653887754L;
	
	public AboutBox(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        setMinimumSize(new Dimension(430, 250));
        setPreferredSize(new Dimension(430, 250));
        setSize(new Dimension(430, 250));
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
		setTitle("O programie");
			
		JButton btnZamknij = new JButton("Zamknij");
		btnZamknij.setPreferredSize(new Dimension(67, 25));
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAboutBox();
			}
		});
		btnZamknij.setMinimumSize(new Dimension(67, 25));
		btnZamknij.setMaximumSize(new Dimension(63, 23));
		
		JLabel lblNazwa = new JLabel("Nazwa:");
		lblNazwa.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblSystemyWbudowaneI = new JLabel("Systemy Wbudowane i Czasu Rzeczywistego - Magazyn");
		
		JLabel labelWersja = new JLabel("Wersja:");
		labelWersja.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel labelAutorzy = new JLabel("Autorzy:");
		labelAutorzy.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label = new JLabel("1.0");
		
		JLabel lblMarcinJasion = new JLabel("Marcin Jasion");
		
		JLabel lblukaszKrok = new JLabel("Łukasz Krok");
		
		JLabel lblMarekSarnecki = new JLabel("Marek Sarnecki");
		
		JLabel lblTobiaszSiemiski = new JLabel("Tobiasz Siemiński");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNazwa)
						.addComponent(labelWersja, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelAutorzy, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTobiaszSiemiski)
						.addComponent(lblMarekSarnecki)
						.addComponent(lblukaszKrok)
						.addComponent(label)
						.addComponent(lblSystemyWbudowaneI)
						.addComponent(lblMarcinJasion))
					.addGap(22))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(154)
					.addComponent(btnZamknij, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(166, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSystemyWbudowaneI)
						.addComponent(lblNazwa))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelWersja, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelAutorzy, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMarcinJasion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblukaszKrok)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMarekSarnecki)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTobiaszSiemiski)
					.addGap(18)
					.addComponent(btnZamknij, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
