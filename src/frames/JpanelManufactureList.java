package frames;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import entities.Manufacture;
import models.AccountModel;
import models.InvoiceModel;
import models.ManufactureModel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JpanelManufactureList extends JPanel {
	private JTable JtableListManufacture;
	private JScrollPane scrollPane;
	private JPanel JpanelBody;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField JtextfieldName;
	private JLabel lblNewLabel;
	private JButton JbuttonSearch;
	private JButton JbuttonEdit;
	private JButton JbuttonAdd;
	private JButton JbuttonDeleted;

	/**
	 * Create the panel.
	 */
	public JpanelManufactureList() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JpanelBody = new JPanel();
		add(JpanelBody);
		JpanelBody.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		JpanelBody.add(scrollPane, BorderLayout.CENTER);
		
		JtableListManufacture = new JTable();
		scrollPane.setViewportView(JtableListManufacture);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		JpanelBody.add(panel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JpanelManufactureList.class.getResource("/resources/Profile.png")));
		panel.add(lblNewLabel);
		
		JtextfieldName = new JTextField();
		panel.add(JtextfieldName);
		JtextfieldName.setColumns(15);
		
		JbuttonSearch = new JButton("Search");
		JbuttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonSearch_actionPerformed(e);
			}
		});
		panel.add(JbuttonSearch);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		JpanelBody.add(panel_1, BorderLayout.SOUTH);
		
		JbuttonEdit = new JButton("Edit");
		JbuttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonEdit_actionPerFormed(e);
			}
		});
		
		JbuttonAdd = new JButton("Add");
		JbuttonAdd.setIcon(new ImageIcon(JpanelManufactureList.class.getResource("/resources/Add.png")));
		JbuttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonAdd_actionPerformed(e);
			}
		});
		panel_1.add(JbuttonAdd);
		JbuttonEdit.setIcon(new ImageIcon(JpanelManufactureList.class.getResource("/resources/Info.png")));
		panel_1.add(JbuttonEdit);
		
		JbuttonDeleted = new JButton("Deleted");
		JbuttonDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonDeleted_actionPerformed(e);
			}
			
		});
		JbuttonDeleted.setIcon(new ImageIcon(JpanelManufactureList.class.getResource("/resources/Delete.png")));
		panel_1.add(JbuttonDeleted);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		JpanelBody.add(panel_2, BorderLayout.WEST);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.YELLOW);
		JpanelBody.add(panel_3, BorderLayout.EAST);
		
		loadData();

	} 
	
	public void JbuttonDeleted_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null,"Confrim","Are you sure ?", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			int selectedRow = JtableListManufacture.getSelectedRow();
			int id = Integer.parseInt(JtableListManufacture.getValueAt(selectedRow,0).toString());
			ManufactureModel manufacturemodel = new ManufactureModel();
			InvoiceModel invoiceModel = new InvoiceModel();
			if (invoiceModel.findImportInvoiceByMacnufacture(manufacturemodel.FindIManufactureById(id)).size()>0) {
				JOptionPane.showMessageDialog(null, "Cannot Detele Manufacture that Contained in ImportInvoice");
			
			}else {
				manufacturemodel.deleted(id);
				JOptionPane.showMessageDialog(null, "Done");
				FillDatatableItem(manufacturemodel.findAll());
			}
		}	
	}
	
	public void JbuttonAdd_actionPerformed(ActionEvent e) {
		JpanelManufactureAdd jamnufactureadd = new JpanelManufactureAdd();
		reset();
		JpanelBody.add(jamnufactureadd);
		jamnufactureadd.setVisible(true);
		
	}
	
	
	public void JbuttonSearch_actionPerformed(ActionEvent e) {
		String name = JtextfieldName.getText();
		ManufactureModel manufacturemodel = new ManufactureModel();
		FillDatatableItem(manufacturemodel.findName(name));
	}
	
	public void JbuttonEdit_actionPerFormed(ActionEvent e) {
		int selectedRow = JtableListManufacture.getSelectedRow();
		int id = Integer.parseInt(JtableListManufacture.getValueAt(selectedRow,0).toString());
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id",id);
		JpanelManufactureEdit jpanelfatureedit = new JpanelManufactureEdit(data);
		reset();
		JpanelBody.add(jpanelfatureedit);
		jpanelfatureedit.setVisible(true);
	}
	
	private void reset() {
		JpanelBody.removeAll();
		JpanelBody.revalidate();
	}
	private void loadData() {
		ManufactureModel manufactureModel = new ManufactureModel();
			FillDatatableItem(manufactureModel.findAll());
		}
		
		private void FillDatatableItem(List<Manufacture> manufacture) {

			DefaultTableModel defaultTableModel = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			defaultTableModel.addColumn("id");
			defaultTableModel.addColumn("name");
			defaultTableModel.addColumn("address");
			defaultTableModel.addColumn("taxcode");
			

			for (Manufacture i : manufacture) {
				defaultTableModel.addRow(
						new Object[] {i.getId(), i.getName(), i.getAddress(), i.getTaxcode()});
			}
			JtableListManufacture.setModel(defaultTableModel);
			JtableListManufacture.getTableHeader().setReorderingAllowed(false);
		}


}
