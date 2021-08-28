package frames;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import data.AccountData;
import entities.Account;
import entities.RoleAccount;
import models.AccountModel;
import models.RoleModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelInfomationAccount extends JPanel {
	private Map<String, Object> data = new HashMap<String, Object>();
	private JPanel jpanelInfoAccount;
	private JPanel panel_1;
	private JTable jtableAccount;
	private JScrollPane scrollPane;
	private JButton jbtnAddAccount;
	private JButton jbtnUpdateInfoAccount;
	private JButton jbtnDelAccount;
	private JButton jbtnSearch;
	private JTextField jtextFieldSearch;
	List<Account> list = new ArrayList<>();
	

	/**
	 * Create the panel.
	 */
	public JPanelInfomationAccount() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		jpanelInfoAccount = new JPanel();
		add(jpanelInfoAccount);
		jpanelInfoAccount.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		jpanelInfoAccount.add(scrollPane, BorderLayout.NORTH);
		
		jtableAccount = new JTable();
		scrollPane.setViewportView(jtableAccount);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		jbtnAddAccount = new JButton("");
		jbtnAddAccount.setContentAreaFilled(false);
		jbtnAddAccount.setBorderPainted(false);
		jbtnAddAccount.setIcon(new ImageIcon(JPanelInfomationAccount.class.getResource("/resources/Add.png")));
		jbtnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAddAccount_actionPerformed(e);
				
			}
		});
		
		jtextFieldSearch = new JTextField();
		jtextFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jtextFieldSearch_keyReleased(e);
			}
		});
		panel_1.add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		jbtnSearch = new JButton("");
		jbtnSearch.setContentAreaFilled(false);
		jbtnSearch.setBorderPainted(false);
		jbtnSearch.setIcon(new ImageIcon(JPanelInfomationAccount.class.getResource("/resources/Search.png")));
		jbtnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String keyword = jtextFieldSearch.getText().trim();
				AccountModel accountModel = new AccountModel();
				if(keyword.isEmpty()) {
					fillDataToJTable(accountModel.findAll());
				}else {
				fillDataToJTable(accountModel.search(keyword));
			}}
		});
	
		panel_1.add(jbtnSearch);
		panel_1.add(jbtnAddAccount);
		
		jbtnUpdateInfoAccount = new JButton("");
		jbtnUpdateInfoAccount.setContentAreaFilled(false);
		jbtnUpdateInfoAccount.setIcon(new ImageIcon(JPanelInfomationAccount.class.getResource("/resources/update.png")));
		jbtnUpdateInfoAccount.setBorderPainted(false);
		jbtnUpdateInfoAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jupdateInfoAccount_actionPerformed(e);
			}
		});
		panel_1.add(jbtnUpdateInfoAccount);
		
		jbtnDelAccount = new JButton("");
		jbtnDelAccount.setContentAreaFilled(false);
		jbtnDelAccount.setBorderPainted(false);
		jbtnDelAccount.setIcon(new ImageIcon(JPanelInfomationAccount.class.getResource("/resources/Delete.png")));
		jbtnDelAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdelAccount_actionPerformed(e);
			}
		});
		panel_1.add(jbtnDelAccount);
		
		loadData();
	}
	
	public void jtextFieldSearch_keyReleased(KeyEvent e) {
		String search = jtextFieldSearch.getText().toLowerCase();
		DefaultTableModel defaultTableModel = (DefaultTableModel)jtableAccount.getModel();
		TableRowSorter<DefaultTableModel> tr =  new TableRowSorter<DefaultTableModel>(defaultTableModel);
		jtableAccount.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
	
	
	public void jdelAccount_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure?", JOptionPane.YES_NO_OPTION);	
		if(result == JOptionPane.YES_OPTION) {
			int selectedRow = jtableAccount.getSelectedRow();
			int id = Integer.parseInt(jtableAccount.getValueAt(selectedRow, 0).toString());
			AccountModel accountModel = new AccountModel();
			if(AccountData.currentAccount.getId() == 1 ) {
				JOptionPane.showMessageDialog(null, "Do not deltele admin !");
			}else {
				accountModel.delete(id);
				JOptionPane.showMessageDialog(null, "Done");
				fillDataToJTable(accountModel.findAll());
			}
		}
	}
	
	public void jupdateInfoAccount_actionPerformed(ActionEvent e) {
		int selectedRow = jtableAccount.getSelectedRow();
		int id  = Integer.parseInt(jtableAccount.getValueAt(selectedRow,0).toString());
		Map<String, Object> data = new HashMap<String,Object>();
		AccountModel accountModel = new AccountModel();
		data.put("account", accountModel.findId(id));
		JFrameUpdateAccount jframeUpdateAccount = new JFrameUpdateAccount(data);
		jframeUpdateAccount.setVisible(true);	
	}
	
	public void jbtnAddAccount_actionPerformed(ActionEvent e) {
		JFrameRegister frameRegister = new JFrameRegister();
		frameRegister.setVisible(true);	
	}	
	
	public JPanelInfomationAccount(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();
	}
	
	private void loadData() {
		AccountModel accountModel = new AccountModel();
		fillDataToJTable(accountModel.findAll());
	}
	
	private void fillDataToJTable(List<Account> accounts) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {	
				return false;
			}		
		};
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("UserName");
		defaultTableModel.addColumn("FullName");
		defaultTableModel.addColumn("Email");
		defaultTableModel.addColumn("Phone");
		defaultTableModel.addColumn("Address");
		defaultTableModel.addColumn("Birthday");
		defaultTableModel.addColumn("Role");
		for (Account account : accounts) {
		RoleAccount roleAccount = new RoleModel().findId(account.getRole_id());
			defaultTableModel.addRow(new Object[] {
				account.getId(),	
				account.getUsername(),
				account.getFullname(),
				account.getEmail(),
				account.getPhone(),
				account.getAddress(),
				simpleDateFormat.format(account.getBirthday()),
				roleAccount.getName()
			});
		}	
		jtableAccount.setModel(defaultTableModel);
		jtableAccount.getTableHeader().setReorderingAllowed(false);
	}
}
