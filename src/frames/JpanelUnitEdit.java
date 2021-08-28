package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import entities.Category;
import entities.Item;
import entities.Manufacture;
import entities.Unit;
import models.CategoryModel;
import models.ManufactureModel;
import models.UnitModel;

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
public class JpanelUnitEdit extends JPanel {
	
	private Map<String, Object> data;
	private JPanel JpanelEdit;
	private JTextField JtextfieldId;
	private JTextField JtextfieldName;

	/**
	 * Create the panel.
	 */
	public JpanelUnitEdit() {
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
		lblNewLabel_3.setIcon(new ImageIcon(JpanelUnitEdit.class.getResource("/resources/Business-Org-Unit-icon.png")));
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
		
		JPanel panel_15 = new JPanel();
		panel.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel.add(panel_17);
		
		JPanel panel_3 = new JPanel();
		JpanelEdit.add(panel_3);

	}
	
	
	
	public JpanelUnitEdit (Map<String,Object> data) {
		this();
		this.data = data;
		loadData();
		
	}
	
	public void JbuttonSave_actionPerformed(ActionEvent e) {
		String name = JtextfieldName.getText();
		int id = Integer.valueOf(JtextfieldId.getText());
		
		Unit unit = new Unit();
		unit.setId(id);
		unit.setName(name);
		UnitModel unitmodel = new UnitModel();
		if(unitmodel.update(unit)) {
			JOptionPane.showMessageDialog(null, "Done");
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", id);
			JpanelEdit.removeAll();
			JpanelEdit.revalidate();
			JpanelUnit jpanelunit = new JpanelUnit();
			JpanelEdit.add(jpanelunit);
			jpanelunit.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null,"Faield");
		}
		
	}
	
	private void loadData() {
		int id =Integer.parseInt(data.get("id").toString());
		UnitModel unitmodel = new UnitModel();
		Unit unit = unitmodel.findId(id);	
		JtextfieldName.setText(unit.getName());
		JtextfieldId.setText(String.valueOf(unit.getId()));
		
	}
}
