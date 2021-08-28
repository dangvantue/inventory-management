package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import entities.Category;
import entities.Item;
import entities.Manufacture;
import entities.Unit;
import models.CategoryModel;
import models.ItemModel;
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
import javax.swing.JComboBox;
public class JpanelItemEdit extends JPanel {
	
	private Map<String, Object> data;
	private JPanel JpanelEdit;
	private JTextField JtextfieldId;
	private JTextField JtextfieldName;
	private JComboBox JcomboBoxUnit;
	private JComboBox JcomboBoxCategory;
	private JButton JbuttonSave;

	/**
	 * Create the panel.
	 */
	public JpanelItemEdit() {
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
		lblNewLabel_3.setIcon(new ImageIcon(JpanelItemEdit.class.getResource("/resources/Profile.png")));
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
		JtextfieldId.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		
		JLabel lblName = new JLabel("  Name   ");
		panel_10.add(lblName);
		
		JtextfieldName = new JTextField();
		JtextfieldName.setColumns(10);
		panel_10.add(JtextfieldName);
		
		JPanel panel_13 = new JPanel();
		panel.add(panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Unit");
		panel_13.add(lblNewLabel_1);
		
		JcomboBoxUnit = new JComboBox();
		panel_13.add(JcomboBoxUnit);
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14);
		
		JLabel lblNewLabel_2 = new JLabel("Category");
		panel_14.add(lblNewLabel_2);
		
		JcomboBoxCategory = new JComboBox();
		panel_14.add(JcomboBoxCategory);
		
		JPanel panel_15 = new JPanel();
		panel.add(panel_15);
		
		JbuttonSave = new JButton("Save");
		panel_15.add(JbuttonSave);
		JbuttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonSave_actionPerformed(e);
			}
		});
		
		JPanel panel_16 = new JPanel();
		panel.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel.add(panel_17);
		
		JPanel panel_3 = new JPanel();
		JpanelEdit.add(panel_3);

	}
	
	
	
	public JpanelItemEdit (Map<String,Object> data) {
		this();
		this.data = data;
		loadUnitData();
		loadCategoryData();
		loadData();
		
		
	}
	
	public void JbuttonSave_actionPerformed(ActionEvent e) {
		String name = JtextfieldName.getText();
		int id = Integer.valueOf(JtextfieldId.getText());
	
		Unit unit = (Unit) JcomboBoxUnit.getSelectedItem();
		int unit_id = unit.getId();
		
		Category category = (Category) JcomboBoxCategory.getSelectedItem();
		int category_id = category.getId();
		
		
		Item item = new Item();
		item.setId(id);
		item.setName(name);
		item.setCategoryid(category_id);
		item.setUnitid(unit_id);
		
		ItemModel itemmodel = new ItemModel();
		if(itemmodel.update(item)) {
			JOptionPane.showMessageDialog(null,"Done");
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", id);
			
			JpanelEdit.removeAll();
			JpanelEdit.revalidate();
			JpanelItem jpanelitem = new JpanelItem();
			JpanelEdit.add(jpanelitem);
			jpanelitem.setVisible(true);
	
		}else {
			JOptionPane.showMessageDialog(null,"Faield");
		}
		
	}
	
	private void loadData() {
		int id =Integer.parseInt(data.get("id").toString());
		ItemModel itemmodel = new ItemModel();
		Item item = itemmodel.findId(id);
			
		JtextfieldName.setText(item.getName());
		JtextfieldId.setText(String.valueOf(item.getId()));
		
		CategoryModel categorymodel = new CategoryModel();
		Category category = categorymodel.findId(item.getCategoryid());
		JcomboBoxCategory.getModel().setSelectedItem(category);
		
		UnitModel unitmodel = new UnitModel();
		Unit unit = unitmodel.findId(item.getUnitid());
		JcomboBoxUnit.getModel().setSelectedItem(unit);
		
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
