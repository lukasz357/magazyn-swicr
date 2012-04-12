package siwc.magazyn;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import siwc.magazyn.dto.ZamowienieTO;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public abstract class AddOrderBox extends javax.swing.JDialog{
	public abstract void dodajZamowienieAction();
	public abstract void dodajZamowieniaOKAction();
	public abstract void anulujAction();
	
	public AddOrderBox(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        setMinimumSize(new Dimension(440, 330));
        setBounds(new Rectangle(62, 0, 440, 330));
        setResizable(false);
        initComponents(parent);
        this.setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(btnDodaj);
        showAddOrderBox();
	}
	
    public void closeAddOrderBox() {
        setVisible(false);
        dispose();
    }

    public void showAddOrderBox() {
        setVisible(true);
    }
	private void  initComponents(final java.awt.Frame frame) {
		setTitle("Dodaj nowe zamówienie");
		
		textFieldImieINazwisko = new JTextField();
		
		lblImieINazwisko = new JLabel("Imię i nazwisko:");
		
		lblTerminRealizacji = new JLabel("Termin realizacji:");
		
		lblElementyZamwienia = new JLabel("Elementy zamówienia:");
		
		scrollPaneElementy = new JScrollPane();
		
		listModel = new DefaultListModel<>();
		
		btnDodaj = new JButton("Dodaj");
		
		zamowienie = new ZamowienieTO();
		index = -1;
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajZamowienieAction();

			}
		});
		btnDodaj.setPreferredSize(new Dimension(63, 23));
		
		JButton btnUsun = new JButton("Usuń");
		btnUsun.setEnabled(false);
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		btnUsun.setPreferredSize(new Dimension(63, 23));
		
		JButton btnOk = new JButton("OK");
		btnOk.setPreferredSize(new Dimension(67, 25));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajZamowieniaOKAction();
			}
		});
		btnOk.setMinimumSize(new Dimension(67, 25));
		btnOk.setMaximumSize(new Dimension(63, 23));
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anulujAction();
				closeAddOrderBox();
			}
		});
		
		comboBoxGodzina = new JComboBox<>();
		comboBoxGodzina.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		comboMinuta = new JComboBox<>();
		comboMinuta.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JLabel labelDwukropek = new JLabel(":");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnUsun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnuluj))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldImieINazwisko, 0, 255, Short.MAX_VALUE)
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblImieINazwisko)
									.addGap(194)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTerminRealizacji)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(comboBoxGodzina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelDwukropek)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboMinuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(22))
						.addComponent(lblElementyZamwienia)
						.addComponent(scrollPaneElementy, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImieINazwisko)
						.addComponent(lblTerminRealizacji))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldImieINazwisko, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxGodzina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboMinuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelDwukropek))
					.addGap(13)
					.addComponent(lblElementyZamwienia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneElementy, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUsun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAnuluj))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		listElementy = new JList<>();
		scrollPaneElementy.setViewportView(listElementy);
		getContentPane().setLayout(groupLayout);
	}
	protected static final long serialVersionUID = 4998454943738569292L;
	protected JTextField textFieldImieINazwisko;
	protected JLabel lblImieINazwisko;
	protected JLabel lblTerminRealizacji;
	protected JLabel lblElementyZamwienia;
	protected JScrollPane scrollPaneElementy;
	protected JButton btnDodaj;
	protected JList<String> listElementy;
	protected JComboBox<String> comboBoxGodzina;
	protected JComboBox<String> comboMinuta;
	protected DefaultListModel<String> listModel;
	protected ZamowienieTO zamowienie;
	protected int index;
}
