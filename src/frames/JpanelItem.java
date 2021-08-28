package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entities.Category;
import entities.Item;
import entities.Manufacture;
import entities.Unit;
import models.CategoryModel;
import models.ItemModel;
import models.ManufactureModel;
import models.UnitModel;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JpanelItem extends JPanel {
	private JTable JtableListItem;
	private JButton JbuttonAdd;
	private JPanel PanelBodyItem;
	private JButton JbuttonDeleted;
	private JButton JbuttonEdit;
	private JTextField JtextfieldName;
	private JButton JbuttonSearch;

	/**
	 * Create the panel.
	 */
	public JpanelItem() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		PanelBodyItem = new JPanel();
		add(PanelBodyItem);
		PanelBodyItem.setLayout(new BoxLayout(PanelBodyItem, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		PanelBodyItem.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JpanelItem.class.getResource("/resources/Loading.png")));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("List Item");
		panel.add(btnNewButton);
		
		JtextfieldName = new JTextField();
		panel.add(JtextfieldName);
		JtextfieldName.setColumns(15);
		
		JbuttonSearch = new JButton("");
		JbuttonSearch.setIcon(new ImageIcon(JpanelItem.class.getResource("/resources/Search.png")));
		panel.add(JbuttonSearch);
		
		JPanel panel_1 = new JPanel();
		PanelBodyItem.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_1.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.YELLOW);
		panel_1.add(panel_4, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.YELLOW);
		panel_1.add(panel_5, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_1.add(panel_6, BorderLayout.EAST);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_7.add(scrollPane, BorderLayout.CENTER);
		
		JtableListItem = new JTable();
		scrollPane.setViewportView(JtableListItem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		PanelBodyItem.add(panel_2);
		
		JbuttonAdd = new JButton("    ADD ");
		JbuttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonAdd_actionPerformed(e);
			}
		});
		JbuttonAdd.setIcon(new ImageIcon(JpanelItem.class.getResource("/resources/Add.png")));
		panel_2.add(JbuttonAdd);
		
		JbuttonEdit = new JButton("  EDIT  ");
		JbuttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonEdit_actionPerformed(e);
			}
			
		});
		JbuttonEdit.setIcon(new ImageIcon(JpanelItem.class.getResource("/resources/Info.png")));
		panel_2.add(JbuttonEdit);
		
		JbuttonDeleted = new JButton("DELETED");
		JbuttonDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonDeleted_actionPerformed(e);
			}
		});
		JbuttonDeleted.setIcon(new ImageIcon(JpanelItem.class.getResource("/resources/Delete.png")));
		panel_2.add(JbuttonDeleted);
		loadData();

	}
	public void JbuttonDeleted_actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null,"Confrim","Are you sure ?", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				int selectedRow = JtableListItem.getSelectedRow();
				int id = Integer.parseInt(JtableListItem.getValueAt(selectedRow,0).toString());
				ItemModel itemmodel = new ItemModel();
				if(itemmodel.deleted(id)) {
					JOptionPane.showMessageDialog(null, "Done");
					FillDataTableItem(itemmodel.findAll());
				}else{
					JOptionPane.showMessageDialog(null, "faile");
				}
				
			}	
	}
	
	public void JbuttonEdit_actionPerformed(ActionEvent e) {
		int selectedRow = JtableListItem.getSelectedRow();
		int id = Integer.parseInt(JtableListItem.getValueAt(selectedRow,0).toString());
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id",id);
		JpanelItemEdit jpanelitemdit = new JpanelItemEdit(data);
		reset();
		PanelBodyItem.add(jpanelitemdit);
		PanelBodyItem.setVisible(true);
	}
	
	public void JbuttonAdd_actionPerformed(ActionEvent e) {
		JpanelItemAdd jpanelitemadd = new JpanelItemAdd();
		reset();
		PanelBodyItem.add(jpanelitemadd);
		jpanelitemadd.setVisible(true);
	}
	
	private void reset() {
		PanelBodyItem.removeAll();
		PanelBodyItem.revalidate();
	}	
	
	private void loadData() {
		ItemModel itemmodel = new ItemModel();
		FillDataTableItem(itemmodel.findAll());
		}
	
	
	private void FillDataTableItem(List<Item> item) {

		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.addColumn("id");
		defaultTableModel.addColumn("name");
		defaultTableModel.addColumn("unit");
		defaultTableModel.addColumn("category");
		
		for (Item i : item) {
			int unit_id = i.getUnitid();
			UnitModel unitModel = new UnitModel();
			Unit unit = unitModel.findId(unit_id);
			String unitName = unit.getName();
			
			int Category_id = i.getCategoryid();
			CategoryModel categorymodel = new CategoryModel();
			Category categoryName = categorymodel.findId(Category_id);
			String NameCategory = categoryName.getName();
			
			defaultTableModel.addRow(
					new Object[] {i.getId(), i.getName(), unitName, NameCategory});
		}
		JtableListItem.setModel(defaultTableModel);
		JtableListItem.getTableHeader().setReorderingAllowed(false);
	}

}
