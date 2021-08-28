package frames;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entities.Account;
import entities.ExportInvoice;
import models.AccountModel;
import models.CustomerModel;
import models.InvoiceModel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class ExportHistoryPanel extends JPanel {
	private JTable jtableData;
	private DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JButton jbtnDetail;
	private JPanel MidPanel;
	private JTextField jtextFieldSearch;
	private Map<String, Object> data = new HashMap<String, Object>();
	/**
	 * Create the panel.
	 */
	public ExportHistoryPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		add(TopPanel, BorderLayout.NORTH);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jtextFieldSearch_keyReleased(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ExportHistoryPanel.class.getResource("/resources/Search.png")));
		TopPanel.add(lblNewLabel);
		TopPanel.add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		MidPanel = new JPanel();
		add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BoxLayout(MidPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		MidPanel.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);
		
		JPanel BotPanel = new JPanel();
		add(BotPanel, BorderLayout.SOUTH);
		
		jbtnDetail = new JButton("Detail");
		jbtnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDetail_actionPerformed(e);
			}
		});
		BotPanel.add(jbtnDetail);
		loadData();
	}
	
	public void jtextFieldSearch_keyReleased(KeyEvent e) {
		String search = jtextFieldSearch.getText().toLowerCase();
		DefaultTableModel defaultTableModel = (DefaultTableModel)jtableData.getModel();
		TableRowSorter<DefaultTableModel> tr =  new TableRowSorter<DefaultTableModel>(defaultTableModel);
		jtableData.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
	
	public ExportHistoryPanel(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();
	}
	
	private void loadData() {
		Account account = (Account) data.get("account");
		InvoiceModel invoiceModel = new InvoiceModel();
		ExportInvoice exportInvoice = new ExportInvoice();	
		fillDataToJTable(invoiceModel.findAllExportInvoice());
		
	}
	public void jbtnDetail_actionPerformed(ActionEvent e) {
		InvoiceModel invoiceModel = new InvoiceModel();
		int row = jtableData.getSelectedRow();
		int id = Integer.parseInt(jtableData.getValueAt(row, 0).toString());
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("invoice", invoiceModel.findExportInvoiceById(id));
		Clearscreen();
		JPanel MidPanel = (JPanel) this.getParent();
		ExportInvoiceDetailPanel exportInvoiceDetailPanel = new ExportInvoiceDetailPanel(data);
		MidPanel.add(exportInvoiceDetailPanel);
		exportInvoiceDetailPanel.setVisible(true);
	}
	
	private void fillDataToJTable(List<ExportInvoice> exportInvoices) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
			}		
		};
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("CREATED");
		defaultTableModel.addColumn("ACCOUNT");
		defaultTableModel.addColumn("CUSTOMER");
		for(ExportInvoice exportInvoice : exportInvoices) {
			CustomerModel customerModel = new CustomerModel();
			AccountModel accountModel = new AccountModel();
			defaultTableModel.addRow(new Object[] {				
					exportInvoice.getId(),
					exportInvoice.getCreated(),
					accountModel.findId(exportInvoice.getAccountid()).getUsername(),
					customerModel.FindCustomerById(exportInvoice.getCustomerid()).getName()
			});
			jtableData.setModel(defaultTableModel);
			jtableData.getTableHeader().setReorderingAllowed(false);
		}
		
	}
	public void Clearscreen() {
		MidPanel.removeAll();
		MidPanel.revalidate();
	}
}
