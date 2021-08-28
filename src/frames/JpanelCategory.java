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
import entities.Manufacture;
import models.CategoryModel;
import models.ItemModel;
import models.ManufactureModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JpanelCategory extends JPanel {
	private JTable JtableListCategory;
	private JButton JbuttonAdd;
	private JPanel PanelBodyCategory;
	private JButton JbuttonDeleted;
	private JButton JbuttonEdit;

	/**
	 * Create the panel.
	 */
	public JpanelCategory() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		PanelBodyCategory = new JPanel();
		add(PanelBodyCategory);
		PanelBodyCategory.setLayout(new BoxLayout(PanelBodyCategory, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		PanelBodyCategory.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JpanelCategory.class.getResource("/resources/category-icon.png")));
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("List Category");
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		PanelBodyCategory.add(panel_1);
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
		
		JtableListCategory = new JTable();
		scrollPane.setViewportView(JtableListCategory);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		PanelBodyCategory.add(panel_2);
		
		JbuttonAdd = new JButton("    ADD ");
		JbuttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonAdd_actionPerformed(e);
			}
		});
		JbuttonAdd.setIcon(new ImageIcon(JpanelCategory.class.getResource("/resources/Add.png")));
		panel_2.add(JbuttonAdd);
		
		JbuttonEdit = new JButton("  EDIT  ");
		JbuttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonEdit_actionPerformed(e);
			}
			
		});
		JbuttonEdit.setIcon(new ImageIcon(JpanelCategory.class.getResource("/resources/Info.png")));
		panel_2.add(JbuttonEdit);
		
		JbuttonDeleted = new JButton("DELETED");
		JbuttonDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonDeleted_actionPerformed(e);
			}
		});
		JbuttonDeleted.setIcon(new ImageIcon(JpanelCategory.class.getResource("/resources/Delete.png")));
		panel_2.add(JbuttonDeleted);
		loadData();

	}
	public void JbuttonDeleted_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null,"Confrim","Are you sure ?", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			ItemModel itemModel = new ItemModel();
			int selectedRow = JtableListCategory.getSelectedRow();
			int id = Integer.parseInt(JtableListCategory.getValueAt(selectedRow,0).toString());
			CategoryModel categorymodel = new CategoryModel();
			if(itemModel.findCategoryId(categorymodel.findId(id).getId()).size()>0)
			{
				JOptionPane.showMessageDialog(null, "Cannot Delete Category that contains Items");
			}
			else {
				categorymodel.deleted(id);
				JOptionPane.showMessageDialog(null, "Done");
				FillDataTableCategory(categorymodel.findAll());
			}
			
		}	
	}
	
	public void JbuttonEdit_actionPerformed(ActionEvent e) {
		int selectedRow = JtableListCategory.getSelectedRow();
		int id = Integer.parseInt(JtableListCategory.getValueAt(selectedRow,0).toString());
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id",id);
		JpanelCategoryEdit jpanelcategoryedit = new JpanelCategoryEdit(data);
		reset();
		PanelBodyCategory.add(jpanelcategoryedit);
		PanelBodyCategory.setVisible(true);
	}
	
	public void JbuttonAdd_actionPerformed(ActionEvent e) {
		JpanelCategoryAdd jpanelcategoryadd = new JpanelCategoryAdd();
		reset();
		PanelBodyCategory.add(jpanelcategoryadd);
		jpanelcategoryadd.setVisible(true);
	}
	
	private void reset() {
		PanelBodyCategory.removeAll();
		PanelBodyCategory.revalidate();
	}	
	
	private void loadData() {
		CategoryModel categorymodel = new CategoryModel();
		FillDataTableCategory(categorymodel.findAll());
		}
	
	
	private void FillDataTableCategory(List<Category> category) {

		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.addColumn("id");
		defaultTableModel.addColumn("name");
		

		for (Category i : category) {
			defaultTableModel.addRow(
					new Object[] {i.getId(), i.getName()});
		}
		JtableListCategory.setModel(defaultTableModel);
		JtableListCategory.getTableHeader().setReorderingAllowed(false);
	}

}
