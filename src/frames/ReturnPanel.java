package frames;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import data.AccountData;
import entities.Category;
import entities.Customer;
import entities.Item;
import entities.ReturnInvoice;
import entities.ReturnInvoiceDetail;
import frames.ExportPanel.CategoryListCellRenderer;
import frames.ExportPanel.CustomerListCellRenderer;
import frames.ExportPanel.ItemListCellRenderer;
import models.CategoryModel;
import models.CustomerModel;
import models.InvoiceModel;
import models.ItemModel;
import models.UnitModel;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ReturnPanel extends JPanel {
	private JTextField textField;
	private JTextField jtextFieldTotal;
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JTable jtableData;
	private JComboBox jcomboBoxItem;
	private JComboBox jcomboBoxCategory;
	private JButton jbtnClear;
	private JTextField jtextFieldQuantity;
	private int itemid;
	private double total;
	private JLabel jlblUnit;
	private JComboBox jcomboBoxCustomer;
	private int customerid;
	private JLabel jlblAccount;

	/**
	 * Create the panel.
	 */
	public ReturnPanel() {

		setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(255, 255, 51));
		add(TopPanel, BorderLayout.NORTH);
		GridBagLayout gbl_TopPanel = new GridBagLayout();
		gbl_TopPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_TopPanel.rowHeights = new int[]{59, 0, 0, 0, 0};
		gbl_TopPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_TopPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		TopPanel.setLayout(gbl_TopPanel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ReturnPanel.class.getResource("/resources/history-icon (1).png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		TopPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("RETURN");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 0;
		TopPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(ReturnPanel.class.getResource("/resources/Email.png")));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_8.gridx = 10;
		gbc_lblNewLabel_8.gridy = 0;
		TopPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblNewLabel_1 = new JLabel("INVOICE");
		lblNewLabel_1.setFont(new Font("Stencil Std", Font.PLAIN, 27));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		TopPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CTY TNHH NO NAME");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridwidth = 8;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		TopPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("5 Le Tan Be, An Lac Ward");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridwidth = 4;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 7;
		gbc_lblNewLabel_3.gridy = 2;
		TopPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 8;
		gbc_lblNewLabel_4.gridy = 3;
		TopPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JPanel BotPanel = new JPanel();
		BotPanel.setBorder(new LineBorder(new Color(255, 51, 51)));
		add(BotPanel, BorderLayout.SOUTH);
		BotPanel.setLayout(new BoxLayout(BotPanel, BoxLayout.X_AXIS));
		
		JPanel Left = new JPanel();
		FlowLayout flowLayout = (FlowLayout) Left.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		Left.setBackground(new Color(255, 255, 204));
		BotPanel.add(Left);
		
		JLabel lblNewLabel_5 = new JLabel("NOTE :");
		lblNewLabel_5.setFont(new Font("Segoe UI Symbol", Font.ITALIC, 14));
		Left.add(lblNewLabel_5);
		
		textField = new JTextField();
		Left.add(textField);
		textField.setColumns(20);
		
		JPanel Right = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) Right.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		Right.setBackground(new Color(204, 255, 255));
		BotPanel.add(Right);
		
		JLabel lblNewLabel_6 = new JLabel("TOTAL :");
		lblNewLabel_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 14));
		Right.add(lblNewLabel_6);
		
		jtextFieldTotal = new JTextField();
		jtextFieldTotal.setBackground(new Color(51, 204, 255));
	
		jtextFieldTotal.setBorder(new EmptyBorder(0, 0, 0, 0));
		jtextFieldTotal.setEditable(false);
		Right.add(jtextFieldTotal);
		jtextFieldTotal.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		BotPanel.add(panel);
		
		JButton jbtnReturn = new JButton("");
		jbtnReturn.setFocusPainted(false);
		jbtnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnReturn.setContentAreaFilled(false);
		jbtnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnReturn_actionPerformed(e);
			}
		});
		jbtnReturn.setIcon(new ImageIcon(ReturnPanel.class.getResource("/resources/Add.png")));
		panel.add(jbtnReturn);
		
		JPanel MidPanel = new JPanel();
		add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel Mid_TopPanel = new JPanel();
		MidPanel.add(Mid_TopPanel, BorderLayout.NORTH);
		
		jbtnClear = new JButton("CLEAR");
		jbtnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jlblAccount = new JLabel("");
		jlblAccount.setIcon(new ImageIcon(ReturnPanel.class.getResource("/resources/account.png")));
		Mid_TopPanel.add(jlblAccount);
		Mid_TopPanel.add(jbtnClear);
		
		jcomboBoxCustomer = new JComboBox();
		jcomboBoxCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcomboBoxCustomer_actionPerformed(e);
			}
		});
		Mid_TopPanel.add(jcomboBoxCustomer);
		
		jcomboBoxCategory = new JComboBox();
		jcomboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcomboBoxCategory_actionPerformed(e);
			}
		});
		Mid_TopPanel.add(jcomboBoxCategory);
		
		jcomboBoxItem = new JComboBox();
		jcomboBoxItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcomboBoxItem_actionPerformed(e);
			}
		});
		Mid_TopPanel.add(jcomboBoxItem);
		
		jtextFieldQuantity = new JTextField();
		jtextFieldQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jlblItemQuantity_keyReleased(e);
			}
		});
		Mid_TopPanel.add(jtextFieldQuantity);
		jtextFieldQuantity.setColumns(10);
		
		jlblUnit = new JLabel("");
		Mid_TopPanel.add(jlblUnit);
		
		JButton jbtnAdd = new JButton("ADD");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAdd_actionPerformed(e);
			}
		});
		Mid_TopPanel.add(jbtnAdd);
		
		JPanel Mid_MidPanel = new JPanel();
		MidPanel.add(Mid_MidPanel, BorderLayout.CENTER);
		Mid_MidPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		Mid_MidPanel.add(scrollPane, BorderLayout.CENTER);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);
		loadData();
	}
	
	public void jlblItemQuantity_keyReleased(KeyEvent e) {
		try {
			double quantity = Double.parseDouble(jtextFieldQuantity.getText());			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Quantity can not be String ");
			jtextFieldQuantity.setText("");
		}
	}
	
	private void loadData() {
		jlblAccount.setText(AccountData.currentAccount.getUsername());
		ItemModel itemModel = new ItemModel();
		
		CategoryModel categoryModel = new CategoryModel();
		CustomerModel customerModel = new CustomerModel();
		fillDatatoComboBoxCustomer(customerModel.findAll());
		fillDatatoComboBox(categoryModel.findAll());
		fillDataToJTable();

	}
	public void jbtnReturn_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Confirm", "Are you Sure?", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			CustomerModel customerModel = new CustomerModel();
			java.util.Date date = new java.util.Date();
			ReturnInvoice returnInvoice = new ReturnInvoice();
			returnInvoice.setAccountid(AccountData.currentAccount.getId());
			returnInvoice.setCreated(date);
			returnInvoice.setCustomerid(customerModel.FindCustomerById(customerid).getId());
			InvoiceModel invoiceModel = new InvoiceModel();
			int id = invoiceModel.CreateReturnInvoice(returnInvoice);
			ReturnInvoiceDetail returnInvoiceDetail = new ReturnInvoiceDetail();
			for (int i = defaultTableModel.getRowCount()-1;i>=0;i--) {
				int itemid = Integer.parseInt(defaultTableModel.getValueAt(i, 1).toString());
				System.out.println(itemid);
				double quantity = Double.parseDouble(defaultTableModel.getValueAt(i, 4).toString());
				System.out.println(quantity);
				returnInvoiceDetail.setItemid(itemid);
				returnInvoiceDetail.setQuantity(quantity);
				returnInvoiceDetail.setReturninvoiceid(id);
				invoiceModel.ReturnInvoiceDetail(returnInvoiceDetail);
				invoiceModel.update(itemid, quantity);
			}
		}
	}
	public void jcomboBoxCustomer_actionPerformed(ActionEvent e) {
		Customer customer = (Customer) jcomboBoxCustomer.getSelectedItem();
		customerid = customer.getId();
	}
	public void jbtnClear_actionPerformed(ActionEvent e) {
		while(defaultTableModel.getRowCount()>0) {
			defaultTableModel.removeRow(0);
		}
	}
	public void jbtnAdd_actionPerformed(ActionEvent e) {

		ItemModel itemModel = new ItemModel();
		Item item = itemModel.FindItemById(itemid);
		Customer customer = new CustomerModel().FindCustomerById(customerid);
		double amount = item.getPrice() * Double.parseDouble(jtextFieldQuantity.getText());
		defaultTableModel.addRow(new Object[] { customer.getName(),item.getId(), item.getName(), item.getPrice(),
				jtextFieldQuantity.getText(), item.getUnitid(), item.getDescription(), amount });
		jtableData.setModel(defaultTableModel);
		total += amount;
		jtextFieldTotal.setText(String.valueOf(total));
	}

	public void jcomboBoxItem_actionPerformed(ActionEvent e) {
		Item item = (Item) jcomboBoxItem.getSelectedItem();
		UnitModel unitModel = new UnitModel();
		itemid = item.getId();
		int unitid = item.getUnitid();
		jlblUnit.setText(unitModel.FindUnitById(unitid).getName());
	}

	public void jcomboBoxCategory_actionPerformed(ActionEvent e) {
		CategoryModel categoryModel = new CategoryModel();
		ItemModel itemModel = new ItemModel();
		Category category = (Category) jcomboBoxCategory.getSelectedItem();
		fillDataToComboBox(itemModel.findAllInCategory(category.getId()));
	}
	private void fillDataToJTable() {
		defaultTableModel.addColumn("CUSTOMER");
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("NAME");
		defaultTableModel.addColumn("PRICE");
		defaultTableModel.addColumn("QUANTITY");
		defaultTableModel.addColumn("UNIT");
		defaultTableModel.addColumn("DESCRIPTION");
		defaultTableModel.addColumn("AMOUNT");
		ItemModel itemModel = new ItemModel();
		jtableData.setModel(defaultTableModel);
		jtableData.getTableHeader().setReorderingAllowed(false);
	}
	public void fillDatatoComboBox(List<Category> categories) {
		DefaultComboBoxModel<Category> defaultComboBoxModel = new DefaultComboBoxModel<Category>();
		for (Category category : categories) {
			defaultComboBoxModel.addElement(category);
		}
		jcomboBoxCategory.setModel(defaultComboBoxModel);
		jcomboBoxCategory.setRenderer(new CategoryListCellRenderer());

	}

	public class CategoryListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Category category = (Category) value;
			return super.getListCellRendererComponent(list, category.getName(), index, isSelected, cellHasFocus);
		}

	}

	public void fillDataToComboBox(List<Item> items) {

		DefaultComboBoxModel<Item> defaultComboBoxModel = new DefaultComboBoxModel<Item>();
		for (Item item : items) {
			defaultComboBoxModel.addElement(item);
		}
		jcomboBoxItem.setModel(defaultComboBoxModel);
		jcomboBoxItem.setRenderer(new ItemListCellRenderer());

	}

	public class ItemListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Item item = (Item) value;
			return super.getListCellRendererComponent(list, item.getName(), index, isSelected, cellHasFocus);
		}

	}
	public void fillDatatoComboBoxCustomer(List<Customer> customers) {
		DefaultComboBoxModel<Customer> defaultComboBoxModel = new DefaultComboBoxModel<Customer>();
		for (Customer customer : customers) {
			defaultComboBoxModel.addElement(customer);
		}
		jcomboBoxCustomer.setModel(defaultComboBoxModel);
		jcomboBoxCustomer.setRenderer(new CustomerListCellRenderer());
	}

	public class CustomerListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Customer customer = (Customer) value;
			return super.getListCellRendererComponent(list, customer.getName(), index, isSelected, cellHasFocus);
		}

	}
	

}
