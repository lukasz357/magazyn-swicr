package siwc.magazyn;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import siwc.magazyn.dto.ListTowarTO;

public abstract class SelectProductBox extends javax.swing.JDialog{
	public abstract void dodajTowarAction();
	public abstract void comboBoxChangeAction();
	public abstract void comboBoxPozycjeInitAction();
	
	public SelectProductBox(java.awt.Frame parent, boolean modal, HashMap<String, ListTowarTO> towaryNaMagazynie){
        super(parent, modal);
        setMinimumSize(new Dimension(500, 160));
        setBounds(new Rectangle(62, 0, 500, 160));
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

		comboBoxPozycje = new JComboBox<>();
		
		comboBoxTowary = new JComboBox<ListTowarTO>();
		for(ListTowarTO lt : towaryNaMagazynie.values()){
			comboBoxTowary.addItem(lt);
		}
		comboBoxTowary.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        comboBoxChangeAction();
		    }
		});
		comboBoxPozycjeInitAction();
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajTowarAction();
			}
		});
		
		JLabel lblTowar = new JLabel("Towar:");
		
		JLabel lblDokladnaPozycja = new JLabel("Dokładna pozycja:");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxTowary, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTowar))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxPozycje, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDokladnaPozycja)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(190)
							.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTowar)
						.addComponent(lblDokladnaPozycja))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTowary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxPozycje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private static final long serialVersionUID = 4998454943738569292L;
	protected JButton btnDodaj;
	protected JComboBox<ListTowarTO> comboBoxTowary;
	protected JComboBox<String> comboBoxPozycje;
}
