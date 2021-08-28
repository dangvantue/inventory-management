package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.ExceptionData;
import entities.ExceptionItem;
import entities.ExportInvoice;
import entities.Item;
import models.CustomerModel;
import models.ExceptionItemModel;
import models.ItemModel;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JpanelException extends JPanel {
	private JPanel panel;
	private JTable jtableData;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton jbtnRefesh;
	private JLabel lblNewLabel_1;
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JTextField jtextFieldException;
	private JButton jbtnSet;
	/**
	 * Create the panel.
	 */
	public JpanelException() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 204));
		panel.add(panel_1, BorderLayout.NORTH);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JpanelException.class.getResource("/resources/exception.png")));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("EXCEPTION");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel_1.add(lblNewLabel);

		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		jbtnRefesh = new JButton("");
		jbtnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnRefesh_actionPerformed(e);
				
			}
		});
		
		jtextFieldException = new JTextField();
		panel_3.add(jtextFieldException);
		jtextFieldException.setColumns(10);
		
		jbtnSet = new JButton("");
		jbtnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSet_actionPerformed(e);
			}
		});
		jbtnSet.setBorderPainted(false);
		jbtnSet.setIcon(new ImageIcon(JpanelException.class.getResource("/resources/set.png")));
		panel_3.add(jbtnSet);
		jbtnRefesh.setBorderPainted(false);
		jbtnRefesh.setIcon(new ImageIcon(JpanelException.class.getResource("/resources/refresh.png")));
		panel_3.add(jbtnRefesh);
		loadData();
	}
	
	public void jbtnSet_actionPerformed(ActionEvent e) {
		ExceptionData.currentException = Double.parseDouble(jtextFieldException.getText());
		jtextFieldException.setText("");
		JOptionPane.showMessageDialog(null, "Change Item Exception Successfully");
	}
	
	public void jbtnRefesh_actionPerformed(ActionEvent e) {
		ExceptionItemModel exceptionModel = new ExceptionItemModel();
		ItemModel itemModel = new ItemModel();
		ExceptionItemModel exceptionItemModel = new ExceptionItemModel();
		for (Item item: itemModel.findAll()) {
			if((item.getQuantity())>ExceptionData.currentException){
				exceptionItemModel.Delete(item.getId());
			}
		}
		Clear();
		for (ExceptionItem exception : exceptionModel.findAll()) {
			defaultTableModel.addRow(new Object[] { exception.getId(),exception.getItem_id() ,itemModel.findId(exception.getItem_id()).getName(), exception.getContent(),
					exception.isStatus()?false:"Unavailable","Available" });
		}
		
	}
	public void Clear() {
		while (defaultTableModel.getRowCount() > 0) {
			defaultTableModel.removeRow(0);
		}
	}

	
	private void loadData() {
		ExceptionItemModel exceptionModel = new ExceptionItemModel();
		ItemModel itemModel = new ItemModel();
		fillDataToJTable();
		for (ExceptionItem exception : exceptionModel.findAll()) {
			defaultTableModel.addRow(new Object[] { exception.getId(),exception.getItem_id() ,itemModel.findId(exception.getItem_id()).getName(), exception.getContent(),
					exception.isStatus()?false:"Unavailable","Available" });
		}
	}

	private void fillDataToJTable() {
		ItemModel itemModel = new ItemModel();
		
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("ITEM ID");
		defaultTableModel.addColumn("ITEM NAME");
		defaultTableModel.addColumn("CONTENT");
		defaultTableModel.addColumn("STATUS");
		
		jtableData.setModel(defaultTableModel);
		jtableData.getTableHeader().setReorderingAllowed(false);
	}

}
