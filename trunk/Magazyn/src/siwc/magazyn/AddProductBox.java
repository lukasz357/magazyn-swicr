package siwc.magazyn;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public abstract class AddProductBox extends javax.swing.JDialog {
	private static final long serialVersionUID = -8653952429272195208L;
	
	public abstract void addProductActionPerformed();

    public AddProductBox(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setMinimumSize(new Dimension(300, 270));
        setBounds(new Rectangle(62, 0, 300, 270));
        setResizable(false);
        initComponents();
        this.setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(okButton);
        showAddQBox();
    }

    public void closeAddQBox() {
        setVisible(false);
        dispose();
    }

    public void showAddQBox() {
        setVisible(true);
    }


    private void initComponents() {

        textFieldNazwa = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dodaj nowy przedmiot do magazynu"); 
        setName("addProductDialog");
        textFieldNazwa.setName("nazwaField");

        okButton.setText("OK");
        okButton.setName("okButton");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Anuluj");
        cancelButton.setName("cancelButton");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        
        SpinnerNumberModel regalyModel = new SpinnerNumberModel(1, 1, 3, 1);
        spinnerNumerRegalu = new JSpinner(regalyModel);
        
        lblNumerRegalu = new JLabel("Numer regalu:");
        
        SpinnerNumberModel pietraModel = new SpinnerNumberModel(0, 0, 4, 1);
        spinnerNumerPietra = new JSpinner(pietraModel);
        
        lblNumerPietra = new JLabel("Numer pietra:");
        
        lblNazwa = new JLabel("Nazwa:");
        
        lblPozycja = new JLabel("Pozycja:");
        
        textFieldProducent = new JTextField();
        textFieldProducent.setColumns(10);
        
        lblProducent = new JLabel("Producent:");
        
        textFieldKod = new JTextField();
        textFieldKod.setColumns(10);
        
        lblKod = new JLabel("Kod:");
        
        textFieldIloscWPaczce = new JTextField();
        textFieldIloscWPaczce.setColumns(10);
        
        lblIloscWPaczce = new JLabel("Ilosc w jednej paczce:");
        
        comboBoxPositionA_C = new JComboBox<String>();
        comboBoxPositionA_C.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C"}));
        
        comboBoxPosition1_38 = new JComboBox<String>();
        comboBoxPosition1_38.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(39)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblNumerRegalu)
        				.addComponent(lblNumerPietra)
        				.addComponent(lblPozycja)
        				.addComponent(lblNazwa)
        				.addComponent(lblProducent)
        				.addComponent(lblKod)
        				.addComponent(lblIloscWPaczce))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(spinnerNumerPietra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(layout.createParallelGroup(Alignment.LEADING)
        					.addGroup(layout.createSequentialGroup()
        						.addComponent(spinnerNumerRegalu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addContainerGap())
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(comboBoxPositionA_C, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(comboBoxPosition1_38, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(196))
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addGroup(layout.createSequentialGroup()
        								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        									.addComponent(textFieldNazwa, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        									.addComponent(textFieldProducent, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        									.addComponent(textFieldKod, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        									.addGroup(layout.createSequentialGroup()
        										.addGap(98)
        										.addComponent(okButton)
        										.addPreferredGap(ComponentPlacement.RELATED)
        										.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
        								.addGap(32))
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(textFieldIloscWPaczce, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        								.addContainerGap()))))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNumerRegalu)
        				.addComponent(spinnerNumerRegalu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNumerPietra)
        				.addComponent(spinnerNumerPietra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPozycja)
        				.addComponent(comboBoxPositionA_C, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(comboBoxPosition1_38, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldNazwa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNazwa))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldProducent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblProducent))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldKod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblKod))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(48)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(cancelButton)
        						.addComponent(okButton)))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblIloscWPaczce)
        						.addComponent(textFieldIloscWPaczce, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap())
        );
        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {okButton, cancelButton});
        getContentPane().setLayout(layout);

        pack();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        addProductActionPerformed();
//    	closeAddQBox();       
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {

        closeAddQBox();
    }

    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    protected javax.swing.JTextField textFieldNazwa;
    protected JTextField textFieldProducent;
    protected JTextField textFieldKod;
    protected JTextField textFieldIloscWPaczce;
    protected JLabel lblNumerRegalu;
    protected JLabel lblNumerPietra;
    protected JLabel lblNazwa;
    protected JLabel lblPozycja;
    protected JLabel lblProducent;
    protected JLabel lblKod;
    protected JLabel lblIloscWPaczce;
    protected JSpinner spinnerNumerRegalu;
    protected JSpinner spinnerNumerPietra;
    protected JComboBox<String> comboBoxPositionA_C;
    protected JComboBox<String> comboBoxPosition1_38;
}