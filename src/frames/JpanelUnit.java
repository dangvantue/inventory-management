package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Category;
import entities.Item;
import entities.Unit;
import models.CategoryModel;
import models.ItemModel;
import models.UnitModel;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class JpanelUnit extends JPanel {
	private JButton JbuttonDeleted;
	private JButton JbuttonEdit;
	private JButton JbuttonAdd;
	private JTable JtableListUnit;
	private JPanel PanelBodyUnit;

	/**
	 * Create the panel.
	 */
	public JpanelUnit() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		PanelBodyUnit = new JPanel();
		add(PanelBodyUnit);
		PanelBodyUnit.setLayout(new BoxLayout(PanelBodyUnit, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		PanelBodyUnit.add(panel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JpanelUnit.class.getResource("/resources/Business-Org-Unit-icon.png")));
		panel_1.add(lblNewLabel);

		JButton btnNewButton = new JButton("List Unit");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setFont(new Font("NSimSun", Font.BOLD, 16));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		PanelBodyUnit.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_2.add(panel, BorderLayout.WEST);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.YELLOW);
		panel_2.add(panel_4, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_2.add(panel_5, BorderLayout.EAST);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.YELLOW);
		panel_2.add(panel_6, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_7.add(scrollPane, BorderLayout.CENTER);

		JtableListUnit = new JTable();
		scrollPane.setViewportView(JtableListUnit);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		panel_3.setBackground(Color.BLUE);
		PanelBodyUnit.add(panel_3);

		JbuttonAdd = new JButton("Add");
		JbuttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonAdd_actionPerformed(e);

			}
		});
		JbuttonAdd.setIcon(new ImageIcon(JpanelUnit.class.getResource("/resources/Add.png")));
		panel_3.add(JbuttonAdd);

		JbuttonEdit = new JButton("Edit");
		JbuttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonEdit_actionPerformed(e);
			}
		});
		JbuttonEdit.setIcon(new ImageIcon(JpanelUnit.class.getResource("/resources/Info.png")));
		panel_3.add(JbuttonEdit);

		JbuttonDeleted = new JButton("Deleted");
		JbuttonDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JbuttonDeleted_actionPerformed(e);
			}
		});
		JbuttonDeleted.setIcon(new ImageIcon(JpanelUnit.class.getResource("/resources/Delete.png")));
		panel_3.add(JbuttonDeleted);

		loadData();

	}

	private void loadData() {
		UnitModel unitmodel = new UnitModel();
		FillDataTableUnit(unitmodel.findAll());
	}

	private void FillDataTableUnit(List<Unit> unit) {

		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.addColumn("id");
		defaultTableModel.addColumn("name");

		for (Unit i : unit) {

			defaultTableModel.addRow(new Object[] { i.getId(), i.getName() });
		}
		JtableListUnit.setModel(defaultTableModel);
		JtableListUnit.getTableHeader().setReorderingAllowed(false);
	}

	public void JbuttonAdd_actionPerformed(ActionEvent e) {
		JpanelUnitAdd jpanelunitadd = new JpanelUnitAdd();
		reset();
		PanelBodyUnit.add(jpanelunitadd);
		jpanelunitadd.setVisible(true);

	}

	public void JbuttonEdit_actionPerformed(ActionEvent e) {
		int SelectedRow = JtableListUnit.getSelectedRow();
		int id = Integer.valueOf(JtableListUnit.getValueAt(SelectedRow, 0).toString());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		JpanelUnitEdit jpanelunitedit = new JpanelUnitEdit(data);
		reset();
		PanelBodyUnit.add(jpanelunitedit);
		jpanelunitedit.setVisible(true);
	}

	public void JbuttonDeleted_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Confrim", "are you sure ?", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			int SelectedRow = JtableListUnit.getSelectedRow();
			int id = Integer.valueOf(JtableListUnit.getValueAt(SelectedRow, 0).toString());
			UnitModel unitmodel = new UnitModel();
			ItemModel itemModel = new ItemModel();
			if (itemModel.findItemByUnitId(id).size() > 0) {
				JOptionPane.showMessageDialog(null, "Cannot Delete Unit that Contained in Items");
			} else {
				unitmodel.deleted(id);
				JOptionPane.showMessageDialog(null, "Done");
				FillDataTableUnit(unitmodel.findAll());
			}
		}

	}

	private void reset() {
		PanelBodyUnit.removeAll();
		PanelBodyUnit.revalidate();
	}

}
