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
import siwc.magazyn.dto.ListTowarTO;
import siwc.magazyn.dto.MagazynTO;
import siwc.magazyn.dto.ZamowienieTO;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;

public abstract class AddOrderBox extends javax.swing.JDialog{
	public abstract void dodajZamowienieAction();
	public abstract void dodajZamowieniaOKAction();
	
	public AddOrderBox(java.awt.Frame parent, boolean modal, HashMap<Integer, ZamowienieTO> zamowienia, MagazynTO magazyn, HashMap<String, ListTowarTO> towaryNaMagazynie){
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
		
		textFieldPriorytet = new JTextField();
		textFieldPriorytet.setColumns(10);
		
		lblImieINazwisko = new JLabel("Imię i nazwisko:");
		
		lblPriorytet = new JLabel("Priorytet zamówienia:");
		
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
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajZamowieniaOKAction();
				closeAddOrderBox();
			}
		});
		btnOk.setMinimumSize(new Dimension(63, 23));
		btnOk.setMaximumSize(new Dimension(63, 23));
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeAddOrderBox();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldImieINazwisko, 0, 262, Short.MAX_VALUE)
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblImieINazwisko)
									.addGap(194)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPriorytet)
								.addComponent(textFieldPriorytet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblElementyZamwienia)
						.addComponent(scrollPaneElementy, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDodaj)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUsun))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnOk)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnuluj)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImieINazwisko)
						.addComponent(lblPriorytet))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldImieINazwisko, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPriorytet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblElementyZamwienia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneElementy, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDodaj)
						.addComponent(btnUsun))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnAnuluj))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		listElementy = new JList<>();
		scrollPaneElementy.setViewportView(listElementy);
		getContentPane().setLayout(groupLayout);
	}
	protected static final long serialVersionUID = 4998454943738569292L;
	protected JTextField textFieldPriorytet;
	protected JTextField textFieldImieINazwisko;
	protected JLabel lblImieINazwisko;
	protected JLabel lblPriorytet;
	protected JLabel lblElementyZamwienia;
	protected JScrollPane scrollPaneElementy;
	protected JButton btnDodaj;
	protected JList<String> listElementy;
	protected DefaultListModel<String> listModel;
	protected ZamowienieTO zamowienie;
	protected int index;
	
}
