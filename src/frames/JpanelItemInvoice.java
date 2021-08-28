package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entities.ExportInvoice;
import entities.ExportInvoiceDetail;
import entities.ImportInvoiceDetail;
import entities.Item;
import entities.ReturnInvoiceDetail;
import models.CustomerModel;
import models.InvoiceModel;
import models.ItemModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class JpanelItemInvoice extends JPanel {
	Map<String, Object> data = new HashMap<String, Object>();
	private JPanel MidPanel;
	private JTabbedPane tabbedPane;
	private JPanel EXPORT;
	private JTable jtableExport;
	private JPanel RETURN;
	private JPanel IMPORT;
	private JLabel jlblItem;
	private JScrollPane scrollPane_1;
	private JTable jtableImport;
	private JScrollPane scrollPane_2;
	private JTable jtableReturn;
	private JPanel BotPanel;

	/**
	 * Create the panel.
	 */
	public JpanelItemInvoice() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		jlblItem = new JLabel("");
		jlblItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		jlblItem.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(jlblItem, BorderLayout.NORTH);

		MidPanel = new JPanel();
		panel.add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MidPanel.add(tabbedPane, BorderLayout.CENTER);
		
		EXPORT = new JPanel();
		tabbedPane.addTab("EXPORT", null, EXPORT, null);
		EXPORT.setLayout(new BoxLayout(EXPORT, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		EXPORT.add(scrollPane);
		
		jtableExport = new JTable();
		scrollPane.setViewportView(jtableExport);
		
		IMPORT = new JPanel();
		tabbedPane.addTab("IMPORT", null, IMPORT, null);
		IMPORT.setLayout(new BoxLayout(IMPORT, BoxLayout.X_AXIS));
		
		scrollPane_1 = new JScrollPane();
		IMPORT.add(scrollPane_1);
		
		jtableImport = new JTable();
		scrollPane_1.setViewportView(jtableImport);
		
		RETURN = new JPanel();
		tabbedPane.addTab("RETURN", null, RETURN, null);
		RETURN.setLayout(new BoxLayout(RETURN, BoxLayout.X_AXIS));
		
		scrollPane_2 = new JScrollPane();
		RETURN.add(scrollPane_2);
		
		jtableReturn = new JTable();
		scrollPane_2.setViewportView(jtableReturn);
		
		BotPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) BotPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		BotPanel.setBackground(new Color(102, 102, 255));
		panel.add(BotPanel, BorderLayout.SOUTH);

	}
	public JpanelItemInvoice(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();
	}

	private void loadData() {
		Item item = (Item) data.get("item");
		jlblItem.setText(item.getName());
		System.out.println("itemID"+item.getId());
		InvoiceModel invoiceModel = new InvoiceModel();
		ItemModel itemModel = new ItemModel();
		fillDataToJTableExport(invoiceModel.findExportInvoiceByItem(item));
		fillDataToJTableImport(invoiceModel.findImportInvoiceDetailByItem(item));
		fillDataToJTableReturn(invoiceModel.findReturnInvoiceDetailByItem(item));

	}

	private void fillDataToJTableExport(List<ExportInvoiceDetail> exportInvoiceDetails) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
		}
		};
		defaultTableModel.addColumn("INVOICE ID");
		defaultTableModel.addColumn("QUANTITY");
		defaultTableModel.addColumn("CREATED");
		InvoiceModel invoiceModel = new InvoiceModel();
		for(ExportInvoiceDetail exportInvoicedetail : exportInvoiceDetails) {
		
			defaultTableModel.addRow(new Object[] {				
					
					exportInvoicedetail.getExportinvoiceid(),
					exportInvoicedetail.getQuantity(),
					invoiceModel.findExportInvoiceById(exportInvoicedetail.getExportinvoiceid()).getCreated()
				
			});
			jtableExport.setModel(defaultTableModel);
			jtableExport.getTableHeader().setReorderingAllowed(false);
		}
		
	}
	
	private void fillDataToJTableImport(List<ImportInvoiceDetail> importInvoiceDetails) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
		}
		};
		defaultTableModel.addColumn("INVOICE ID");
		defaultTableModel.addColumn("QUANTITY");
		defaultTableModel.addColumn("CREATED");
		InvoiceModel invoiceModel = new InvoiceModel();
		for(ImportInvoiceDetail importInvoiceDetail : importInvoiceDetails) {
		
			defaultTableModel.addRow(new Object[] {				
					
					importInvoiceDetail.getImportinvoiceid(),
					importInvoiceDetail.getQuantity(),
					invoiceModel.findImportInvoiceById(importInvoiceDetail.getImportinvoiceid()).getCreated()
				
			});
			jtableImport.setModel(defaultTableModel);
			jtableImport.getTableHeader().setReorderingAllowed(false);
		}
		
	}
	private void fillDataToJTableReturn(List<ReturnInvoiceDetail> returnInvoiceDetails) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
		}
		};
		defaultTableModel.addColumn("INVOICE ID");
		defaultTableModel.addColumn("QUANTITY");
		defaultTableModel.addColumn("CREATED");
		InvoiceModel invoiceModel = new InvoiceModel();
		for(ReturnInvoiceDetail returnInvoiceDetail : returnInvoiceDetails) {
		
			defaultTableModel.addRow(new Object[] {				
					
					returnInvoiceDetail.getReturninvoiceid(),
					returnInvoiceDetail.getQuantity(),
					invoiceModel.findReturnInvoiceById(returnInvoiceDetail.getReturninvoiceid()).getCreated()
				
			});
			jtableReturn.setModel(defaultTableModel);
			jtableReturn.getTableHeader().setReorderingAllowed(false);
		}
		
	}

	
	public void Clearscreen() {
		MidPanel.removeAll();
		MidPanel.revalidate();
	}
}
