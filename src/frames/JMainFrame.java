package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import entities.Account;
import models.AccountModel;
import models.RoleModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class JMainFrame extends JFrame {
	
	private JPanel contentPane;
	private JButton jbtnAccount;
	private Map<String, Object> data = new HashMap<String, Object>();
	private JMenu jmenuWelcome;
	private JPanel jpanelBody;
	private JMenuItem jmenuLogin;
	private JButton jbtnInventory;
	private JButton btnNewButton_3;
	private JButton jbtnException;
	private JButton jbtnChangPassword;
	private JMenu jmenuCategory;
	private JMenu jmenuItem;
	private JMenu jmenuUnit;
	private JMenu jmenuManufacture;
	private JMenuItem jmnitemManufacture;
	private JMenuItem jmnitemCategory;
	private JMenuItem jmnitemItem;
	private JMenuItem jmnitemUnit;
	private JButton jbtnInvoice;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    } catch (Throwable e) {
			e.printStackTrace();
		    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMainFrame frame = new JMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 644);
		
		JMenuBar jmenuBarMenu = new JMenuBar();
		jmenuBarMenu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		jmenuBarMenu.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(jmenuBarMenu);
		
		jbtnAccount = new JButton("ACCOUNT");
		jbtnAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAccount_actionPerformed(e);
			}
		});
		jbtnAccount.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Profile.png")));
		jbtnAccount.setFocusPainted(false);
		jbtnAccount.setContentAreaFilled(false);
		jmenuBarMenu.add(jbtnAccount);
		
		jbtnChangPassword = new JButton("CHANGE PASSWORD");
		jbtnChangPassword.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/changepassword.png")));
		jbtnChangPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnChangPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnChangePassword_actionPerformed(e);
			}
		});
		jbtnChangPassword.setFocusPainted(false);
		jbtnChangPassword.setContentAreaFilled(false);
		jmenuBarMenu.add(jbtnChangPassword);
		
		jmenuManufacture = new JMenu("MANUFACTURE");
		jmenuManufacture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		jmenuManufacture.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/partner.png")));
		jmenuBarMenu.add(jmenuManufacture);
		
		jmnitemManufacture = new JMenuItem("MANUFACTORY");
		jmnitemManufacture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmnitemMenufacture_actionPerformed(e);
			}
		});
		jmnitemManufacture.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/partner.png")));
		jmenuManufacture.add(jmnitemManufacture);
		
		jmenuCategory = new JMenu("CATEGORY");
		jmenuCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		jmenuCategory.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/category-icon.png")));
		jmenuBarMenu.add(jmenuCategory);
		
		jmnitemCategory = new JMenuItem("CATEGORY");
		jmnitemCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmnitemCategory_actionPerformed(e);
			}
		});
		jmnitemCategory.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/category-icon.png")));
		jmenuCategory.add(jmnitemCategory);
		
		jmenuItem = new JMenu("ITEM");
		jmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jmenuItem.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Loading.png")));
		jmenuBarMenu.add(jmenuItem);
		
		jmnitemItem = new JMenuItem("ITEM");
		jmnitemItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmnitemItem_actionPerformed(e);
			}
		});
		jmnitemItem.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Loading.png")));
		jmenuItem.add(jmnitemItem);
		
		jmenuUnit = new JMenu("UNIT");
		jmenuUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		jmenuUnit.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Business-Org-Unit-icon.png")));
		jmenuBarMenu.add(jmenuUnit);
		
		jmnitemUnit = new JMenuItem("UNIT");
		jmnitemUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmnitemUnit_actionPerformed(e);
			}
		});
		jmnitemUnit.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Business-Org-Unit-icon.png")));
		jmenuUnit.add(jmnitemUnit);
		
		JPanel panel = new JPanel();
		jmenuBarMenu.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		jmenuWelcome = new JMenu("");
		jmenuWelcome.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/account.png")));
		jmenuBarMenu.add(jmenuWelcome);
		
		jmenuLogin = new JMenuItem("Login");
		jmenuLogin.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Login-square.png")));
		jmenuLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmenuLogin_actionPerformed(e);
			}
		});
		jmenuWelcome.add(jmenuLogin);
		
		JSeparator separator = new JSeparator();
		jmenuWelcome.add(separator);
		
		JMenuItem jmenuLogout = new JMenuItem("Logout");
		jmenuLogout.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Logout.png")));
		jmenuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jmenuLogout_actionPerformed(e);
			}
		});
		jmenuWelcome.add(jmenuLogout);
		
		JSeparator separator_1 = new JSeparator();
		jmenuWelcome.add(separator_1);
		
		JLabel jlabelAccount = new JLabel("");
		jmenuBarMenu.add(jlabelAccount);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menu_panel = new JPanel();
		menu_panel.setBackground(Color.CYAN);
		menu_panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(menu_panel, BorderLayout.WEST);
		
		jbtnInventory = new JButton("INVENTORY");
		jbtnInventory.setHorizontalAlignment(SwingConstants.LEFT);
		jbtnInventory.setForeground(Color.BLACK);
		jbtnInventory.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		jbtnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnInventory_actionPerformed(e);
			}
		});
		jbtnInventory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnInventory.setContentAreaFilled(false);
		jbtnInventory.setBorderPainted(false);
		jbtnInventory.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/inventory.png")));
			
			btnNewButton_3 = new JButton("INVOICE HISTORY");
			btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton_3.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/invoice_history.png")));
			btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewButton_3.setContentAreaFilled(false);
			btnNewButton_3.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jbtnHistory_actionPerformed(e);
				}
			});
			btnNewButton_3.setBorderPainted(false);
			
			jbtnInvoice = new JButton("INVOICE");
			jbtnInvoice.setHorizontalAlignment(SwingConstants.LEFT);
			jbtnInvoice.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/invoice.png")));
			jbtnInvoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtnInvoice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jbtnInvoice_actionPerformed(e);
				}
			});
			jbtnInvoice.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
			jbtnInvoice.setContentAreaFilled(false);
			jbtnInvoice.setBorderPainted(false);
			
			jbtnException = new JButton("EXCEPTION");
			jbtnException.setHorizontalAlignment(SwingConstants.LEFT);
			jbtnException.setIcon(new ImageIcon(JMainFrame.class.getResource("/resources/Warning.png")));
			jbtnException.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbtnException.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jbtnException_actionPerformed(e);
				}
			});
			jbtnException.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
			jbtnException.setContentAreaFilled(false);
			jbtnException.setBorderPainted(false);
			GroupLayout gl_menu_panel = new GroupLayout(menu_panel);
			gl_menu_panel.setHorizontalGroup(
				gl_menu_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_menu_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_menu_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
							.addComponent(jbtnInventory, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(jbtnInvoice, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(jbtnException, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			gl_menu_panel.setVerticalGroup(
				gl_menu_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_menu_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(jbtnInventory)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jbtnInvoice, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jbtnException, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(331))
			);
			menu_panel.setLayout(gl_menu_panel);
			
			JPanel footer_panel = new JPanel();
			footer_panel.setBackground(new Color(224, 255, 255));
			footer_panel.setForeground(Color.MAGENTA);
			footer_panel.setBorder(new EmptyBorder(0, 0, 0, 0));
			contentPane.add(footer_panel, BorderLayout.SOUTH);
			footer_panel.setLayout(new BoxLayout(footer_panel, BoxLayout.Y_AXIS));
			
			lblNewLabel = new JLabel("Project : Inventory Management");
			lblNewLabel.setFont(new Font("Bodoni MT Black", Font.BOLD, 14));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			footer_panel.add(lblNewLabel);
			
			lblNewLabel_1 = new JLabel("Teacher : Mr. Phan Huu Tri");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1.setFont(new Font("Bodoni MT Black", Font.BOLD, 14));
			footer_panel.add(lblNewLabel_1);
			
			lblNewLabel_2 = new JLabel("Team : Racing Boy");
			lblNewLabel_2.setFont(new Font("Bodoni MT Black", Font.BOLD, 14));
			footer_panel.add(lblNewLabel_2);
			
			JPanel main_panel = new JPanel();
			contentPane.add(main_panel, BorderLayout.NORTH);
			
			jpanelBody = new JPanel();
			contentPane.add(jpanelBody, BorderLayout.CENTER);
			jpanelBody.setLayout(new BorderLayout(0, 0));
	}
	
	public void jmnitemUnit_actionPerformed(ActionEvent e) {
		clearScreen();
		JpanelUnit jpanelunit = new JpanelUnit();
		jpanelBody.add(jpanelunit);
		jpanelunit.setVisible(true);
		
	}
	
	public void jmnitemItem_actionPerformed(ActionEvent e) {
		clearScreen();
		JpanelItem jpanelitem = new JpanelItem();
		jpanelBody.add(jpanelitem);
		jpanelitem.setVisible(true);
	}
	
	public void jmnitemCategory_actionPerformed(ActionEvent e) {
		clearScreen();
		JpanelCategory jpanelcategory = new JpanelCategory();
		jpanelBody.add(jpanelcategory);
		jpanelcategory.setVisible(true);
	}
	
	public void jmnitemMenufacture_actionPerformed(ActionEvent e) {
		clearScreen();
		JpanelManufactureList jpanelmanufaturelist = new JpanelManufactureList();
		jpanelBody.add(jpanelmanufaturelist);
		jpanelmanufaturelist.setVisible(true);
	}
	
	
	public void jbtnInventory_actionPerformed(ActionEvent e) {
		clearScreen();
		JpanelInventoryManage jpanelinventorymanage = new JpanelInventoryManage();
		jpanelBody.add(jpanelinventorymanage);
		jpanelinventorymanage.setVisible(true);
	}
	
	public void jbtnHistory_actionPerformed(ActionEvent e) {
		clearScreen();
		InvoiceHistoryPanel invoiceHistoryPanel = new InvoiceHistoryPanel();
		jpanelBody.add(invoiceHistoryPanel);
		invoiceHistoryPanel.setVisible(true);
	}
	
	public void jbtnInvoice_actionPerformed(ActionEvent e) {
		clearScreen();
		InvoicePanel invoicePanel = new InvoicePanel();
		jpanelBody.add(invoicePanel);
		invoicePanel.setVisible(true);
	}
	
	public void runText() {
		Thread thread = new Thread();
		
	}
	
	public void jbtnException_actionPerformed(ActionEvent e) {
		clearScreen();
		JpanelException panel = new JpanelException();
		jpanelBody.add(panel);
		panel.setVisible(true);
	}
	
	
	
	public void jbtnChangePassword_actionPerformed(ActionEvent e) {
		JFrameChangePassword jframeChangePassword = new JFrameChangePassword(data);
		jframeChangePassword.setVisible(true);
	}
	
	public void jmenuLogout_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure?", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			dispose();
			JFrameLogin jframeLogin = new JFrameLogin();
			jframeLogin.setVisible(true);
//			JMainFrame jmainFrame = new JMainFrame();
//			jmainFrame.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Logout Faile");
		}
	}
	
	public void jmenuLogin_actionPerformed(ActionEvent e) {
		JFrameLogin jframeLogin = new JFrameLogin();
		jframeLogin.setVisible(true);
		this.setVisible(false);
	}
	
	public JMainFrame(Map<String, Object> data) {
		this();
		this.data = data;
		loadData();	
	}
	
	private void loadData() {
		//String username = data.get("username").toString();
		//jmenuWelcome.setText("welcome " + username);
		Account account = (Account) data.get("account");
		jmenuWelcome.setText(account.getUsername());
		roleAccount();
	}
	
	public void roleAccount() {
		Account account = (Account) data.get("account");
		if(account.getRole_id() == 2) {
			jbtnAccount.setEnabled(false);
			jbtnInventory.setEnabled(false);
			jmenuManufacture.setEnabled(false);
			jmenuUnit.setEnabled(false);
			jmenuCategory.setEnabled(false);
			jmenuItem.setEnabled(false);
		}else if(account.getRole_id() == 3) {
			jbtnAccount.setEnabled(false);
			jbtnInventory.setEnabled(false);
			jbtnInvoice.setEnabled(false);
		}else if(account.getRole_id() == 4) {
			jbtnAccount.setEnabled(false);
			jbtnInvoice.setEnabled(false);		
		}
	}
	
	public void jbtnAccount_actionPerformed(ActionEvent e) {
		clearScreen();
		JPanelInfomationAccount jpanelInfomationAccount = new JPanelInfomationAccount(data);
		jpanelBody.add(jpanelInfomationAccount);
		jpanelInfomationAccount.setVisible(true);

	}
	
	public void clearScreen() {
		jpanelBody.removeAll();
		jpanelBody.revalidate();
	}
}
