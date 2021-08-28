package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTextField;
import entities.Category;
import entities.Item;
import entities.Unit;
import models.CategoryModel;
import models.ItemModel;
import models.UnitModel;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class JpanelItemAdd extends JPanel {
	private JTextField JtextfieldName;
	private JButton JbuttonSave;
	private JComboBox JcomboBoxUnit;
	private JComboBox JcomboBoxCategory;
	private JPanel PanelBodyAddItem;

	/**
	 * Create the panel.
	 */
	public JpanelItemAdd() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		PanelBodyAddItem = new JPanel();
		add(PanelBodyAddItem);
		PanelBodyAddItem.setLayout(new BoxLayout(PanelBodyAddItem, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		PanelBodyAddItem.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("ADD ITEM");
		lblNewLabel.setIcon(new ImageIcon(JpanelItemAdd.class.getResource("/resources/Add.png")));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		PanelBodyAddItem.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.WEST);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.SOUTH);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.EAST);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		JLabel lblNewLabel_1 = new JLabel("    Name");
		lblNewLabel_1.setIcon(new ImageIcon(JpanelItemAdd.class.getResource("/resources/Loading.png")));
		panel_10.add(lblNewLabel_1);
		
		JtextfieldName = new JTextField();
		panel_10.add(JtextfieldName);
		JtextfieldName.setColumns(15);
		
		JPanel panel = new JPanel();
		panel_8.add(panel);
		
		JLabel lblNewLabel_1_1 = new JLabel("      Unit  ");
		lblNewLabel_1_1.setIcon(new ImageIcon(JpanelItemAdd.class.getResource("/resources/Loading.png")));
		panel.add(lblNewLabel_1_1);
		
		JcomboBoxUnit = new JComboBox();
		JcomboBoxUnit.setPreferredSize(new Dimension(180, 22));
		panel.add(JcomboBoxUnit);
		
		JPanel panel_12 = new JPanel();
		panel_8.add(panel_12);
		
		JLabel lblNewLabel_1_2 = new JLabel("Category");
		lblNewLabel_1_2.setIcon(new ImageIcon(JpanelItemAdd.class.getResource("/resources/Loading.png")));
		panel_12.add(lblNewLabel_1_2);
		
		JcomboBoxCategory = new JComboBox();
		JcomboBoxCategory.setPreferredSize(new Dimension(180, 22));
		panel_12.add(JcomboBoxCategory);
		
		JPanel panel_13 = new JPanel();
		panel_8.add(panel_13);
		
		JbuttonSave = new JButton("Save");
		JbuttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonSave_actionPerformed(e);
			}
		});
		panel_13.add(JbuttonSave);
		
		JPanel panel_14 = new JPanel();
		panel_8.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_8.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_8.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_8.add(panel_17);
		
		JPanel panel_18 = new JPanel();
		panel_8.add(panel_18);
		
		JPanel panel_3 = new JPanel();
		PanelBodyAddItem.add(panel_3);
		
		loadUnitData();
		loadCategoryData();

	}
	
	public void JbuttonSave_actionPerformed(ActionEvent e) {
			String Name = JtextfieldName.getText();
			Unit unit = new Unit();
			unit = (Unit) JcomboBoxUnit.getSelectedItem();
			int unit_id = unit.getId();
			
			Category category = new Category();
			category = (Category) JcomboBoxCategory.getSelectedItem();
			int category_id = category.getId();
			
			Item item = new Item();
			item.setName(JtextfieldName.getText());
			item.setUnitid(unit_id);
			item.setCategoryid(category_id);
			
			ItemModel itemmodel = new ItemModel();
			if (itemmodel.Create(item)) {
				JOptionPane.showMessageDialog(null, "Done");
				JpanelItem jpanelitem = new JpanelItem();
				PanelBodyAddItem.removeAll();
				PanelBodyAddItem.revalidate();
				PanelBodyAddItem.add(jpanelitem);
				jpanelitem.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}
	}
	
	private void loadUnitData() {
		DefaultComboBoxModel<Unit> units = new DefaultComboBoxModel<Unit>();
		UnitModel unitmodel = new UnitModel();
		for(Unit unit : unitmodel.findAll()) {
			units.addElement(unit);
		}
		JcomboBoxUnit.setModel(units);
		JcomboBoxUnit.setRenderer(new UnitListcellrender());	
	}
	
	private class UnitListcellrender extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Unit uni = (Unit) value;
			return super.getListCellRendererComponent(list, uni.getName(), index, isSelected, cellHasFocus);
		}
		
		}
	
	
	private void loadCategoryData() {
		DefaultComboBoxModel<Category> category = new DefaultComboBoxModel<Category>();
		CategoryModel categorymodel = new CategoryModel();
		for(Category cate : categorymodel.findAll()) {
			category.addElement(cate);
		}
		JcomboBoxCategory.setModel(category);
		JcomboBoxCategory.setRenderer(new CategoryListcellrender());	
	}
	
	private class CategoryListcellrender extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Category cate = (Category) value;
			return super.getListCellRendererComponent(list, cate.getName(), index, isSelected, cellHasFocus);
		}
		
		}

}
