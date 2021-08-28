package frames;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class InvoiceHistoryPanel extends JPanel {
	private JPanel MidPanel;

	/**
	 * Create the panel.
	 */
	public InvoiceHistoryPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(245, 245, 220));
		panel.add(TopPanel, BorderLayout.NORTH);
		
		JButton jbtnExport = new JButton("EXPORT");
		jbtnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnExport_actionPerformed(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("INVOICE HISTORY");
		lblNewLabel.setFont(new Font("Bodoni MT", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon(InvoiceHistoryPanel.class.getResource("/resources/invoice-history.png")));
		TopPanel.add(lblNewLabel);
		TopPanel.add(jbtnExport);
		
		JButton jbtnImport = new JButton("IMPORT");
		jbtnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnImport_actionPerformed(e);
			}
		});
		TopPanel.add(jbtnImport);
		
		JButton jbtnReturn = new JButton("RETURN");
		jbtnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnReturn_actionPerformed(e);
			}
		});
		TopPanel.add(jbtnReturn);
		
		MidPanel = new JPanel();
		panel.add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BorderLayout(0, 0));

	}
	public void jbtnImport_actionPerformed(ActionEvent e) {
		Clearscreen();
		ImportHistoryPanel importHistoryPanel = new ImportHistoryPanel();
		MidPanel.add(importHistoryPanel);
		importHistoryPanel.setVisible(true);
	}
	public void jbtnExport_actionPerformed(ActionEvent e) {
		Clearscreen();
		ExportHistoryPanel exportHistoryPanel = new ExportHistoryPanel();
		MidPanel.add(exportHistoryPanel);
		exportHistoryPanel.setVisible(true);
	}
	public void jbtnReturn_actionPerformed(ActionEvent e) {
		Clearscreen();
		ReturnHistoryPanel returnHistoryPanel = new ReturnHistoryPanel();
		MidPanel.add(returnHistoryPanel);
		returnHistoryPanel.setVisible(true);
	}
	
	public void Clearscreen() {
		MidPanel.removeAll();
		MidPanel.revalidate();
	}
}
