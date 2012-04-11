package siwc.magazyn;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;


import siwc.magazyn.dto.ListTowarTO;
import siwc.magazyn.dto.MagazynTO;

public abstract class SelectProductBox extends javax.swing.JDialog{
	public abstract void dodajTowarAction();
	
	
	public SelectProductBox(java.awt.Frame parent, boolean modal, MagazynTO magazyn, HashMap<String, ListTowarTO> towaryNaMagazynie){
        super(parent, modal);
        setMinimumSize(new Dimension(350, 145));
        setBounds(new Rectangle(62, 0, 350, 145));
        setResizable(false);
        initComponents(towaryNaMagazynie);
        this.setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(btnDodaj);
        showSelectPBox();
	}
	
    public void closeSelectPBox() {
        setVisible(false);
        dispose();
    }

    public void showSelectPBox() {
        setVisible(true);
    }
	private void initComponents(HashMap<String, ListTowarTO> towaryNaMagazynie){
		setTitle("Wybierz towar");

		comboBoxTowary = new JComboBox<ListTowarTO>();
		for(ListTowarTO lt : towaryNaMagazynie.values()){
			comboBoxTowary.addItem(lt);
		}
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajTowarAction();
			}
		});
		
		textFieldIlePaczek = new JTextField();
		textFieldIlePaczek.setColumns(10);
		
		JLabel lblTowar = new JLabel("Towar:");
		
		JLabel lblIlePaczek = new JLabel("Ile paczek:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(comboBoxTowary, 0, 190, Short.MAX_VALUE)
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTowar)
									.addGap(194))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(139)
							.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIlePaczek)
						.addComponent(textFieldIlePaczek, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTowar)
						.addComponent(lblIlePaczek))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTowary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldIlePaczek, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private static final long serialVersionUID = 4998454943738569292L;
	protected JTextField textFieldIlePaczek;
	protected JButton btnDodaj;
	protected JComboBox<ListTowarTO> comboBoxTowary;
}
