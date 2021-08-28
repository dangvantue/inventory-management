package frames;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import data.AccountData;
import entities.Account;
import entities.ExportInvoice;
import entities.ImportInvoice;
import entities.RoleAccount;
import models.AccountModel;
import models.InvoiceModel;
import models.ManufactureModel;
import models.RoleModel;

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

public class ImportHistoryPanel extends JPanel {
	private JTable jtableData;
	private DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JButton jbtnDetail;
	private JPanel MidPanel;
	private JScrollPane scrollPane;
	private Map<String, Object> data = new HashMap<String, Object>();
	private JLabel lblNewLabel;
	private JTextField jtextFieldSearch;
	/**
	 * Create the panel.
	 */
	public ImportHistoryPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		add(TopPanel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ImportHistoryPanel.class.getResource("/resources/Search.png")));
		TopPanel.add(lblNewLabel);
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jtextFieldSearch_keyReleased(e);
			}
		});
		TopPanel.add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		MidPanel = new JPanel();
		add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BoxLayout(MidPanel, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
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
		defaultTableModel = (DefaultTableModel)jtableData.getModel();
		TableRowSorter<DefaultTableModel> tr =  new TableRowSorter<DefaultTableModel>(defaultTableModel);
		jtableData.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
	
	public ImportHistoryPanel(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();
	}
	
	private void loadData() {
		Account account = (Account) data.get("account");
		InvoiceModel invoiceModel = new InvoiceModel();
		ImportInvoice importInvoice = new ImportInvoice();
		
		fillDataToJTable(invoiceModel.findAllImportInvoice());
		
	}
	public void jbtnDetail_actionPerformed(ActionEvent e) {
		InvoiceModel invoiceModel = new InvoiceModel();
		int row = jtableData.getSelectedRow();
		int id = Integer.parseInt(jtableData.getValueAt(row, 0).toString());
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("invoice", invoiceModel.findImportInvoiceById(id));
		Clearscreen();
		JPanel MidPanel = (JPanel) this.getParent();
		ImportInvoiceDetailPanel importInvoiceDetailPanel = new ImportInvoiceDetailPanel(data);
		MidPanel.add(importInvoiceDetailPanel);
		importInvoiceDetailPanel.setVisible(true);
	}
	
	private void fillDataToJTable(List<ImportInvoice> importInvoices) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
			}		
		};
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("CREATED");
		defaultTableModel.addColumn("ACCOUNT");
		defaultTableModel.addColumn("MACNUFACTURE");
		for(ImportInvoice importInvoice : importInvoices) {
			ManufactureModel manufactureModel = new ManufactureModel();
			AccountModel accountModel = new AccountModel();
			Account account = new Account();
			RoleAccount roleAccount = new RoleModel().findId(account.getRole_id());
			defaultTableModel.addRow(new Object[] {
					importInvoice.getId(),
					importInvoice.getCreated(),
					AccountData.currentAccount.getUsername(),
					manufactureModel.FindID(importInvoice.getManufactureid()).getName()

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
