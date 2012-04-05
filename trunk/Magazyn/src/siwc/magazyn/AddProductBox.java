package siwc.magazyn;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JTextField;


public class AddProductBox extends javax.swing.JDialog {
	private static final long serialVersionUID = -8653952429272195208L;

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
        
        Integer [] numeryRegalow = {1, 2, 3};
        SpinnerListModel regalyModel = new SpinnerListModel(numeryRegalow);
        JSpinner spinnerNumerRegalu = new JSpinner(regalyModel);
        
        JLabel lblNumerRegalu = new JLabel("Numer regalu:");
        
        Integer [] numeryPieter = {0, 1, 2, 3};
        SpinnerListModel pietraModel = new SpinnerListModel(numeryPieter);
        JSpinner spinnerNumerPietra = new JSpinner(pietraModel);
        
        JLabel lblNumerPietra = new JLabel("Numer pietra:");
        
        JLabel lblNazwa = new JLabel("Nazwa:");
        
        textFieldPozycja = new JTextField();
        textFieldPozycja.setColumns(10);
        
        JLabel lblPozycja = new JLabel("Pozycja:");
        
        textFieldProducent = new JTextField();
        textFieldProducent.setColumns(10);
        
        JLabel lblProducent = new JLabel("Producent:");
        
        textFieldKod = new JTextField();
        textFieldKod.setColumns(10);
        
        JLabel lblKod = new JLabel("Kod:");
        
        textFieldIloscWPaczce = new JTextField();
        textFieldIloscWPaczce.setColumns(10);
        
        JLabel lblIloscWPaczce = new JLabel("Ilosc w jednej paczce:");

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
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(textFieldNazwa, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        						.addComponent(spinnerNumerPietra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(spinnerNumerRegalu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textFieldPozycja, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        						.addComponent(textFieldProducent, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        						.addComponent(textFieldKod, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        							.addGap(98)
        							.addComponent(okButton)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
        					.addGap(32))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(textFieldIloscWPaczce, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
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
        				.addComponent(spinnerNumerPietra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNumerPietra))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldPozycja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblPozycja))
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

        closeAddQBox();
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {

        closeAddQBox();
    }

    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField textFieldNazwa;
    private JTextField textFieldPozycja;
    private JTextField textFieldProducent;
    private JTextField textFieldKod;
    private JTextField textFieldIloscWPaczce;
}