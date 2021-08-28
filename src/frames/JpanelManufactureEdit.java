package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import entities.Item;
import entities.Manufacture;
import models.ManufactureModel;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Font;
public class JpanelManufactureEdit extends JPanel {
	
	private Map<String, Object> data;
	private JPanel JpanelEdit;
	private JTextField JtextfieldId;
	private JTextField JtextfieldName;
	private JTextField JtextfieldTaxcode;
	private JTextField JtextfieldAddress;

	/**
	 * Create the panel.
	 */
	public JpanelManufactureEdit() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JpanelEdit = new JPanel();
		add(JpanelEdit);
		JpanelEdit.setLayout(new BoxLayout(JpanelEdit, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		JpanelEdit.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(JpanelManufactureEdit.class.getResource("/resources/Profile.png")));
		panel_1.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		JpanelEdit.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.YELLOW);
		panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.ORANGE);
		panel_5.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.add(panel_5, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_2.add(panel_6, BorderLayout.WEST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_2.add(panel_7, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		
		JLabel lblNewLabel = new JLabel("     Id    ");
		panel_9.add(lblNewLabel);
		
		JtextfieldId = new JTextField();
		JtextfieldId.setEditable(false);
		JtextfieldId.setEnabled(false);
		panel_9.add(JtextfieldId);
		JtextfieldId.setColumns(20);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		
		JLabel lblName = new JLabel("  Name   ");
		panel_10.add(lblName);
		
		JtextfieldName = new JTextField();
		JtextfieldName.setColumns(20);
		panel_10.add(JtextfieldName);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		
		JLabel lblTaxcode = new JLabel("Taxcode");
		panel_11.add(lblTaxcode);
		
		JtextfieldTaxcode = new JTextField();
		JtextfieldTaxcode.setColumns(20);
		panel_11.add(JtextfieldTaxcode);
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		
		JLabel lblAdrress = new JLabel("Address  ");
		panel_12.add(lblAdrress);
		
		JtextfieldAddress = new JTextField();
		JtextfieldAddress.setColumns(20);
		panel_12.add(JtextfieldAddress);
		
		JPanel panel_13 = new JPanel();
		panel.add(panel_13);
		
		JButton JbuttonSave = new JButton("Save");
		JbuttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonSave_actionPerformed(e);
			}
		});
		panel_13.add(JbuttonSave);
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14);
		
		JPanel panel_3 = new JPanel();
		JpanelEdit.add(panel_3);

	}
	
	
	
	public JpanelManufactureEdit (Map<String,Object> data) {
		this();
		this.data = data;
		loadData();
		
	}
	
	public void JbuttonSave_actionPerformed(ActionEvent e) {
		String name = JtextfieldName.getText();
		String taxcode = JtextfieldTaxcode.getText();
		String address = JtextfieldAddress.getText();
		int id = Integer.valueOf(JtextfieldId.getText());
		
		Manufacture manufacture = new Manufacture();
		manufacture.setName(name);
		manufacture.setTaxcode(taxcode);
		manufacture.setAddress(address);
		manufacture.setId(id);
	
		
		ManufactureModel manufacturemodel = new ManufactureModel();
		if(manufacturemodel.update(manufacture)) {
			JOptionPane.showMessageDialog(null,"Done");
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", id);
			
			JpanelEdit.removeAll();
			JpanelEdit.revalidate();
			JpanelManufactureList jpanelmanufacturelist = new JpanelManufactureList();
			JpanelEdit.add(jpanelmanufacturelist);
			jpanelmanufacturelist.setVisible(true);
	
		}else {
			JOptionPane.showMessageDialog(null,"Faield");
		}
		
	}
	
	
	
	private void loadData() {
		int id =Integer.parseInt(data.get("id").toString());
	ManufactureModel manufacturemodel = new ManufactureModel();
	Manufacture manufacture = manufacturemodel.FindID(id);	
		JtextfieldName.setText(manufacture.getName());
		JtextfieldAddress.setText(manufacture.getAddress());
		JtextfieldTaxcode.setText(manufacture.getTaxcode());
		JtextfieldId.setText(String.valueOf(manufacture.getId()));
		
	}
}
