package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entities.Category;
import entities.Item;
import entities.Unit;
import models.CategoryModel;
import models.ItemModel;
import models.UnitModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import java.awt.Font;

public class JpanelInventoryManage extends JPanel {
	private JTextField JtextfieldName;
	private JTable TableItem;
	private JComboBox JcomboBoxCategory;
	Map<String, Object> data = new HashMap<String, Object>();
	private JPanel MidPanel;
	private JButton jbtnDetail;

	/**
	 * Create the panel.
	 */
	public JpanelInventoryManage() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("INVENTORY");
		lblNewLabel.setFont(new Font("Bodoni MT", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon(JpanelInventoryManage.class.getResource("/resources/Home-icon.png")));
		panel.add(lblNewLabel);
		
		JcomboBoxCategory = new JComboBox();
		JcomboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JcomboBoxCategory_actionPerformed(e);
			}
		});
		panel.add(JcomboBoxCategory);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		panel.add(lblNewLabel_1);
		
		JtextfieldName = new JTextField();
		panel.add(JtextfieldName);
		JtextfieldName.setColumns(10);
		
		JButton Jbuttonsearch = new JButton("Search");
		Jbuttonsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jbuttonsearch_actionPerformed(e);
			}
		});
		panel.add(Jbuttonsearch);
		
		MidPanel = new JPanel();
		add(MidPanel);
		MidPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		MidPanel.add(scrollPane, BorderLayout.CENTER);
		
		TableItem = new JTable();
		scrollPane.setViewportView(TableItem);
		
		JPanel panel_3 = new JPanel();
		MidPanel.add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		MidPanel.add(panel_4, BorderLayout.SOUTH);
		
		jbtnDetail = new JButton("Detail");
		jbtnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDetail_actionPerformed(e);
			}
		});
		panel_4.add(jbtnDetail);
		
		JPanel panel_5 = new JPanel();
		MidPanel.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9);
		
		JPanel panel_6 = new JPanel();
		MidPanel.add(panel_6, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		loadData();
		loadCategoryData();

	}
	
	public void jbtnDetail_actionPerformed(ActionEvent e) {
		int row = TableItem.getSelectedRow();
		int itemid = Integer.parseInt(TableItem.getValueAt(row,0).toString());
		ItemModel itemModel = new ItemModel();
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("item", itemModel.findId(itemid));
		Clearscreen();
		JPanel MidPanel = (JPanel) this.getParent();
		JpanelItemInvoice jpanelItemInvoice = new JpanelItemInvoice(data);
		MidPanel.add(jpanelItemInvoice);
		jpanelItemInvoice.setVisible(true);
	}
	
	public void JcomboBoxCategory_actionPerformed(ActionEvent e) {
		Category category = new Category();
		category = (Category) JcomboBoxCategory.getSelectedItem();
		int category_id = category.getId();
		
		ItemModel itemModel = new ItemModel();
		List<Item> items = new ArrayList<Item>();
		items = itemModel.findCategoryId(category_id);
		FillDatatableItem(items);
	}
	
	public void Jbuttonsearch_actionPerformed(ActionEvent e) {
		String name = JtextfieldName.getText().trim();
		ItemModel itemModel = new ItemModel();
		List<Item> items = new ArrayList<Item>();
		items = itemModel.findName(name);
		FillDatatableItem(items);
	}

	
	private void loadData() {
		ItemModel itemModel = new ItemModel();
		FillDatatableItem(itemModel.findAll());
		
	}
	
	private void FillDatatableItem(List<Item> items) {

		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.addColumn("id");
		defaultTableModel.addColumn("name");
		defaultTableModel.addColumn("unit");
		defaultTableModel.addColumn("price");
		defaultTableModel.addColumn("quantity");
		//defaultTableModel.addColumn("categoryid");
		//defaultTableModel.addColumn("Manufactureid");
		defaultTableModel.addColumn("created");

		for (Item item : items) {
			int unit_id = item.getUnitid();
			UnitModel unitModel = new UnitModel();
			Unit unit = unitModel.findId(unit_id);
			String unitName = unit.getName();
			defaultTableModel.addRow(
					new Object[] {item.getId(),item.getName(), unitName,item.getPrice(),item.getQuantity() ,item.getCreated() });
		}
		TableItem.setModel(defaultTableModel);
		TableItem.getTableHeader().setReorderingAllowed(false);
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
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
	
	public void Clearscreen() {
		MidPanel.removeAll();
		MidPanel.revalidate();
	}

}
