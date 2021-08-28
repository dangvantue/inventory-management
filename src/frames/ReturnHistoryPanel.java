package frames;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entities.Account;
import entities.ExportInvoice;
import entities.ReturnInvoice;
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

public class ReturnHistoryPanel extends JPanel {
	private JTable jtableData;
	private DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JButton jbtnDetail;
	private JPanel MidPanel;
	private JTextField jtextFieldSearch;
	private JScrollPane scrollPane;
	private Map<String, Object> data = new HashMap<String, Object>();
	private JLabel jlblSearch;
	/**
	 * Create the panel.
	 */
	public ReturnHistoryPanel() {
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
		
		jlblSearch = new JLabel("");
		jlblSearch.setIcon(new ImageIcon(ReturnHistoryPanel.class.getResource("/resources/Search.png")));
		TopPanel.add(jlblSearch);
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
	
	public ReturnHistoryPanel(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();
	}
	
	private void loadData() {
		Account account = (Account) data.get("account");
		InvoiceModel invoiceModel = new InvoiceModel();
		ReturnInvoice returnInvoice = new ReturnInvoice();
		
		fillDataToJTable(invoiceModel.findAllReturnInvoice());
		
	}
	public void jbtnDetail_actionPerformed(ActionEvent e) {
		InvoiceModel invoiceModel = new InvoiceModel();
		int row = jtableData.getSelectedRow();
		int id = Integer.parseInt(jtableData.getValueAt(row, 0).toString());
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("invoice", invoiceModel.findReturnInvoiceById(id));
		Clearscreen();
		JPanel MidPanel = (JPanel) this.getParent();
		ReturnInvoiceDetailPanel returnInvoiceDetailPanel = new ReturnInvoiceDetailPanel(data);
		MidPanel.add(returnInvoiceDetailPanel);
		returnInvoiceDetailPanel.setVisible(true);
	}
	
	private void fillDataToJTable(List<ReturnInvoice> returnInvoices) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
			}		
		};
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("CREATED");
		defaultTableModel.addColumn("ACCOUNT");
		defaultTableModel.addColumn("CUSTOMER");
		for(ReturnInvoice returnInvoice : returnInvoices) {
			CustomerModel customerModel = new CustomerModel();
			AccountModel accountModel = new AccountModel();
			defaultTableModel.addRow(new Object[] {
					returnInvoice.getId(),
					returnInvoice.getCreated(),
					accountModel.findId(returnInvoice.getAccountid()).getUsername(),
					customerModel.FindCustomerById(returnInvoice.getCustomerid()).getName()
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
