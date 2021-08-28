package frames;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entities.ExportInvoice;
import entities.ExportInvoiceDetail;
import entities.ImportInvoice;
import entities.ImportInvoiceDetail;
import entities.Item;
import models.InvoiceModel;
import models.ItemModel;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ImportInvoiceDetailPanel extends JPanel {

	Map<String, Object> data = new HashMap<String,Object>();
	private JTable jtableData;
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	public ImportInvoiceDetailPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(175, 238, 238));
		panel.add(TopPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("INVOICE DETAIL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		TopPanel.add(lblNewLabel);
		
		JPanel MidPanel = new JPanel();
		panel.add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BoxLayout(MidPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		MidPanel.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);

	}
	public ImportInvoiceDetailPanel(Map<String, Object> data) {
		 this();
		 this.data= data;
		 loadData();

	}
	public void loadData() {
		ImportInvoice importInvoice = (ImportInvoice) data.get("invoice");
		InvoiceModel invoiceModel = new InvoiceModel();
		fillDataToJTable(invoiceModel.findImportInvoiceDetail(importInvoice));
	}
	private void fillDataToJTable(List<ImportInvoiceDetail> importInvoiceDetails) {
		ItemModel itemModel = new ItemModel();
		
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("NAME");
		defaultTableModel.addColumn("PRICE");
		defaultTableModel.addColumn("QUANTITY");
		defaultTableModel.addColumn("AMOUNT");
		for (ImportInvoiceDetail importInvoiceDetail : importInvoiceDetails) {
			Item item = itemModel.FindItemById(importInvoiceDetail.getItemid());
			defaultTableModel.addRow(new Object[] {
					importInvoiceDetail.getItemid(),
					item.getName(),
					item.getPrice(),
					importInvoiceDetail.getQuantity(),
					item.getPrice()*importInvoiceDetail.getQuantity()
					
			});
		}
		jtableData.setModel(defaultTableModel);
		jtableData.getTableHeader().setReorderingAllowed(false);
	}

}

