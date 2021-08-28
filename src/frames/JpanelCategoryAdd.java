package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import entities.Category;
import entities.Manufacture;
import models.CategoryModel;
import models.ManufactureModel;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;

public class JpanelCategoryAdd extends JPanel {
	private JTextField JtextfieldName;
	private JPanel PanelBodyAdd;

	/**
	 * Create the panel.
	 */
	public JpanelCategoryAdd() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		PanelBodyAdd = new JPanel();
		PanelBodyAdd.setToolTipText("");
		add(PanelBodyAdd);
		PanelBodyAdd.setLayout(new BoxLayout(PanelBodyAdd, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		PanelBodyAdd.add(panel);
		
		JLabel lblNewLabel = new JLabel("Add Category");
		lblNewLabel.setIcon(new ImageIcon(JpanelCategoryAdd.class.getResource("/resources/category-icon.png")));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		PanelBodyAdd.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_1.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.YELLOW);
		panel_6.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9);
		
		JLabel Name = new JLabel("Name");
		panel_9.add(Name);
		
		JtextfieldName = new JTextField();
		panel_9.add(JtextfieldName);
		JtextfieldName.setColumns(30);
		
		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12);
		
		JButton JbuttonSave = new JButton("Save");
		JbuttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonSave_actionPerformed(e);
			}
		});
		panel_12.add(JbuttonSave);
		
		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_6.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_6.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_6.add(panel_16);
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.YELLOW);
		panel_5.add(panel_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		PanelBodyAdd.add(panel_2);

	}
	public void JbuttonSave_actionPerformed(ActionEvent e) {
		String name = JtextfieldName.getText();
		 Category category= new Category();
		 category.setName(name);
		CategoryModel categorymodel = new CategoryModel();
		if(categorymodel.Create(category)) {
			JOptionPane.showMessageDialog(null,"Done");
			JpanelCategory jpanecategory = new JpanelCategory();
			PanelBodyAdd.removeAll();
			PanelBodyAdd.revalidate();
			PanelBodyAdd.add(jpanecategory);
			jpanecategory.setVisible(true);			
		}
		else {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}
}
