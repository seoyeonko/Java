package FinalProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SiteInfoManager extends JFrame {

	// Loginâ
	LoginDialog ld;
	// �Ӽ�
	// 0. �޴����� �Ӽ�
	JMenuBar mb;

	// ȭ�鱸���Լ��� �Ӽ�
	// 1. �Է�/���� �׸�
	// 1-1. [�⺻ ����] �׸�
	JTextField tf_siteName; // ����Ʈ��
	JTextField tf_url; // �ּ�
	JTextField tf_id; // ���̵�
	JTextField tf_passwd; // ���

	// 1-2. [�߰� ����] �׸�
	JComboBox<SiteCategory> list_categ; // �з�
	JComboBox<String> list_prefer; // ��ȣ��
	JTextArea ta_memo;

	// 1-3. ��ư �׸�
	JButton reWrite;
	JButton Input;

	// 2.����Ʈ ��� ��
	// 2-1. �˻�/���� �׸� (p1����)
	JComboBox<SiteCategory> list_search; // �˻� - �޺��ڽ�
	JTextField searchField; // �˻� - ����

	JComboBox<SiteCategory> sort_categ; // ���� - �з�
	JComboBox<String> sort_name; // ���� - ����Ʈ�̸�
	JButton sortButton; // ���� - [����]��ư
	JButton defaultButton; // ���� - [�⺻]��ư

	// 2-2. ǥ �׸� (p2����)
	JTable mTable; // ǥ

	// 2-3. ���� �������� �׸� (p3����)
	JCheckBox cb; // ������������ üũ�ڽ�
	JTextField id; // ���̵�
	JTextField pass; // ���
	JButton deleteButton; // [����] ��ư

	// 3 �Է� ��ư Ŭ���� �ʿ�
	static SiteInfoList siteInfoList;

	// +����Ʈ����
	int siteSize;
	JLabel sizeLabel;
	JPanel regiPanel;

	// ǥ���� ������ �࿡ ���� SiteInfo �׸�
	SiteInfo siteInfo;

	// CategoryManageDialog�� ī�װ� ����Ʈ
	static SiteCategoryList list = new SiteCategoryList();
	static Vector<SiteCategory> v = new Vector<SiteCategory>();
	static Vector<SiteCategory> v2 = new Vector<SiteCategory>();
	SiteCategory normal_category = new SiteCategory("�Ϲ�");
	SiteCategory total_category = new SiteCategory("��ü");

	// �����Ȳ �� �׷���
	GraphCanvas can;

	// �ڵ��α���
	JCheckBoxMenuItem sItem1;

	public SiteInfoManager() {
		super("������ �����̰� ���� ���ͳ� ���� ���� ���α׷�������");

		siteInfoList = new SiteInfoList();

		boolean boolCate = readCategory();
		if (boolCate == false) {
			list.addList(normal_category); // �Ϲ�
			list.addList(new SiteCategory("�б�"));
			list.addList(new SiteCategory("��Ż"));
			list.addList(new SiteCategory("����"));
		}

		Iterator it = list.getList().iterator();
		while (it.hasNext()) {
			SiteCategory sc = (SiteCategory) it.next();
			v.add(sc);
			v2.add(sc);
		}
		v2.add(total_category);

		createMenu(); // �޴��� ���� �Լ�
		buildGUI(); // ȭ�� ���� �Լ�

		setSize(850, 650);
		setLocation(50, 100);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);

		boolean au = autoLoginCheck();
		ld = new LoginDialog(this);
		if (au == false) {
			ld.setVisible(true);
		} else if (au == true) {
			sItem1.setSelected(true);
		}

		// �α��� ���� �� ���� �о���� �Լ�
		readData();
		sizeLabel.setText(siteSize + "���� ����Ʈ�� ��ϵǾ����ϴ�.");

	}

	private boolean readCategory() {

		File file = new File("C:/JavaFinal/Category.txt");
		if (file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				try {
					while ((sLine = inFile.readLine()) != null) {
						String[] array = sLine.split("/");
						for (int i = 0; i < array.length; i++) {
							list.addList(new SiteCategory(array[i]));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean autoLoginCheck() {
		File file = new File("C:/JavaFinal/autoLogin.txt");
		if (file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				try {
					sLine = inFile.readLine();
					if (sLine.equals("true"))
						return true;
					else if (sLine.equals("false"))
						return false;
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	private void readData() {
		File file = new File("C:/JavaFinal/TableInfo.txt");
		if (file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				try {
					while ((sLine = inFile.readLine()) != null) {
						String[] array = sLine.split("#");
						SiteInfo s = new SiteInfo(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
						siteInfoList.addList(s);
						++siteSize;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		mTable.setModel(new InfoTableModel(siteInfoList));
	}

	// �޴��� ���� �Լ�
	private void createMenu() {
		mb = new JMenuBar();
		setJMenuBar(mb); // �޴��ٸ� �����ӿ� ����

		// JMenu
		JMenu fileMenu = new JMenu("����(F)");
		JMenu manaMenu = new JMenu("����(M)");
		JMenu setMenu = new JMenu("����(S)");
		fileMenu.setMnemonic('F');
		manaMenu.setMnemonic('M');
		setMenu.setMnemonic('S');

		// JMenuItem
		JMenuItem fItem1 = new JMenuItem("���� ���Ͽ��� ��������(I)");
		JMenuItem fItem2 = new JMenuItem("���� ���Ϸ� ��������(E)");
		JMenuItem fItem3 = new JMenuItem("����(S)");
		JMenuItem fItem4 = new JMenuItem("�α׾ƿ�(O)");
		JMenuItem fItem5 = new JMenuItem("����(X)");
		fItem1.setMnemonic('I');
		fItem2.setMnemonic('E');
		fItem3.setMnemonic('S');
		fItem4.setMnemonic('O');
		fItem5.setMnemonic('X');
		// ��Ȱ��ȭ
		fItem1.setEnabled(false);
		fItem2.setEnabled(false);

		JMenuItem mItem1 = new JMenuItem("�����(U)");
		JMenuItem mItem2 = new JMenuItem("����Ʈ �з�(C)");
		mItem1.setMnemonic('U');
		mItem2.setMnemonic('C');
		// ��Ȱ��ȭ
		mItem1.setEnabled(false);

		sItem1 = new JCheckBoxMenuItem("�ڵ� �α���(L)");
		JCheckBoxMenuItem sItem2 = new JCheckBoxMenuItem("�������� ���� ���� ����ϱ�(V)");

		// JMenuItem�� JMenu�� ����
		fileMenu.add(fItem1);
		fileMenu.add(fItem2);
		fileMenu.add(fItem3);
		fileMenu.addSeparator();
		fileMenu.add(fItem4);
		fileMenu.add(fItem5);
		manaMenu.add(mItem1);
		manaMenu.add(mItem2);
		setMenu.add(sItem1);
		setMenu.add(sItem2);

		// ���� - c �� CategoryManageDialog�� ���� ������ �߰�
		mItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryManageDialog dlg = new CategoryManageDialog(SiteInfoManager.this);
				dlg.setVisible(true);

			}

		});

		// ����
		fItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					OutputStream output = new FileOutputStream("C:/JavaFinal/TableInfo.txt");
					Iterator it = siteInfoList.getList().iterator();
					String infoString = "";
					while (it.hasNext()) {
						SiteInfo info = (SiteInfo) it.next();
						infoString += info.getSiteName() + "#";
						infoString += info.getUrl() + "#";
						infoString += info.getId() + "#";
						infoString += info.getPasswd() + "#";
						infoString += info.getCateg() + "#";
						infoString += info.getPrefer() + "#";
						infoString += info.getMemo() + "#";
						infoString += " ";
						infoString += "\n";
					}
					byte[] by = infoString.getBytes();
					try {
						output.write(by);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					OutputStream output2 = new FileOutputStream("C:/JavaFinal/Category.txt");
					Iterator it = v.iterator();
					String outCategory = "";
					while (it.hasNext()) {
						outCategory += it.next() + "/";
					}
					byte[] by = outCategory.getBytes();
					try {
						output2.write(by);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}

		});
		// ���� - �α׾ƿ�
		fItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(SiteInfoManager.this, "�α׾ƿ��Ͻðڽ��ϱ�?", "�α׾ƿ�",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					if (sItem1.isSelected()) { // �ڵ��α��� ����
						return;
					} else { // �ڵ��α��� ���� x
						LoginDialog.f.setText("");
						ld.setVisible(true);
					}

				} else
					return;

			}

		});

		// ���� - ����
		fItem5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(SiteInfoManager.this, "�����Ͻðڽ��ϱ�?", "����",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {

					System.exit(0);
				} else
					return;
			}

		});

		// �ڵ��α��� üũ ����
		sItem1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String autoString;
				OutputStream output = null;
				try {
					output = new FileOutputStream("C:/JavaFinal/AutoLogin.txt");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				if (e.getStateChange() == ItemEvent.SELECTED)
					autoString = "true";
				else
					autoString = "false";

				byte[] by = autoString.getBytes();
				try {
					output.write(by);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		// ������������ �޴� ��ư
		sItem2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cb.setSelected(true);
				} else {
					cb.setSelected(false);
				}

			}

		});
		// JMenu�� JMenuBar�� ����
		mb.add(fileMenu);
		mb.add(manaMenu);
		mb.add(setMenu);
	}

	// ȭ�� ���� �Լ�
	private void buildGUI() {
		JPanel p = new JPanel(new BorderLayout());
		p.add(InputAndModi(), BorderLayout.WEST);
		p.add(Tab(), BorderLayout.CENTER);
		p.add(RegiStatus(), BorderLayout.SOUTH);
		this.add(p);
	}

	// [�Է�/����] �Է� �г� ���� �Լ� : BorderLayout.WEST
	private JPanel InputAndModi() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		p.setBorder(BorderFactory.createTitledBorder("�Է�/����"));
//      p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); // ���� ���� **

		// [�⺻ ����] �׸�
		JPanel normalInfo = new JPanel(new GridLayout(4, 1));
		normalInfo.setBorder(BorderFactory.createTitledBorder("�⺻ ����"));

		JPanel np1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel np2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel np3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel np4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		normalInfo.add(np1);
		normalInfo.add(np2);
		normalInfo.add(np3);
		normalInfo.add(np4);

		JLabel siteName = new JLabel("����Ʈ��");
		tf_siteName = new JTextField(18);
		JLabel url = new JLabel("�ּ�(URL)  https://");
		tf_url = new JTextField(12);
		JLabel id = new JLabel("�� �� ��");
		tf_id = new JTextField(10);
		JLabel passwd = new JLabel("��й�ȣ");
		tf_passwd = new JTextField(10);
		np1.add(siteName);
		np1.add(tf_siteName);
		np2.add(url);
		np2.add(tf_url);
		np3.add(id);
		np3.add(tf_id);
		np4.add(passwd);
		np4.add(tf_passwd);

		// �̺�Ʈ������
		tf_siteName.addKeyListener(handler);
		tf_url.addKeyListener(handler);
		tf_id.addKeyListener(handler);
		tf_passwd.addKeyListener(handler);

		// [�߰� ����] �׸�
		JPanel addInfo = new JPanel();
		addInfo.setBorder(BorderFactory.createTitledBorder("�߰� ����"));
		addInfo.setLayout(new BoxLayout(addInfo, BoxLayout.Y_AXIS)); // ���� ���� *

		JPanel ap1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel ap2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel ap3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addInfo.add(ap1);
		addInfo.add(ap2);
		addInfo.add(ap3);

		JLabel categ = new JLabel("��    ��");
		list_categ = new JComboBox<SiteCategory>(v);
		// list_categ.setSelectedIndex(0); // �⺻ ���õ� ����Ʈ : "�Ϲ�"
		// list_categ.setSelectedItem(normal_category);
		JLabel prefer = new JLabel("��ȣ��");
		String[] listData2 = { "������", "�ڡڡڡڡ�", "�١ڡڡڡ�", "�١١ڡڡ�", "�١١١ڡ�", "�١١١١�", "�١١١١�" };
		list_prefer = new JComboBox<String>(listData2);
		list_prefer.setSelectedIndex(0); // �⺻ ���õ� ����Ʈ : "������"
		JLabel memo = new JLabel("��   �� ");
		ta_memo = new JTextArea("", 4, 20);
		ap1.add(categ);
		ap1.add(list_categ);
		ap2.add(prefer);
		ap2.add(list_prefer);
		ap3.add(memo);
		ap3.add(new JScrollPane(ta_memo));

		// [�����ۼ�], [�Է�] ��ư // FlowLayout.RIGHT�ϸ� ������..
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		reWrite = new JButton("�����ۼ�(N)");
		Input = new JButton("�Է�(I)");
		reWrite.setMnemonic('N');
		Input.setMnemonic('I');
		buttonPanel.add(reWrite);
		buttonPanel.add(Input);
		// �ʱ⼳�� - ��Ȱ��ȭ
		reWrite.setEnabled(false);
		Input.setEnabled(false);

		p.add(normalInfo);
		p.add(addInfo);
		p.add(buttonPanel);

		registerButtonListener();

		return p;

	}

	// keyAdapter
	private KeyAdapter handler = new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent e) {
			if (tf_siteName.getText().length() > 0) {
				reWrite.setEnabled(true); // �ϳ��� �ԷµǸ� Ȱ��ȭ
				if (tf_url.getText().length() > 0) {
					// **
					// if, tf_id�� �ߺ��̶��
					for (int i = 0; i < mTable.getRowCount(); i++) {
						if (tf_url.getText().equals(mTable.getValueAt(i, 3))) {
							// ���â ����
							JOptionPane.showMessageDialog(SiteInfoManager.this, "�̹� �����ϴ� url�Դϴ�! �ٸ� url�� �Է����ּ��� :)",
									"���", JOptionPane.WARNING_MESSAGE);
						}
					}
					if (tf_id.getText().length() > 0) {
						if (tf_passwd.getText().length() > 0) {

							// Input ��ư Ȱ��ȭ
							Input.setEnabled(true); // �� �׸� ��� �ԷµǸ� Ȱ��ȭ
						} else if (Input.getText().equals("����(E)")) {
							Input.setEnabled(true);
						}
					}
				}
			}
		}

	};

	private void registerButtonListener() {
		Input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) (e.getSource());

				SiteInfo s = new SiteInfo(tf_siteName.getText(), tf_url.getText(), tf_id.getText(), tf_passwd.getText(),
						((SiteCategory) list_categ.getSelectedItem()).getSiteCategory(),
						(String) list_prefer.getSelectedItem(), ta_memo.getText());

				if (btn.getText().equals("�Է�(I)")) { // �Է¹�ư Ŭ����
					// �߰� �Է� : �ߺ��� url�� ���������� ���
					Iterator it = siteInfoList.getList().iterator();
					while (it.hasNext()) {
						SiteInfo si = (SiteInfo) (it.next());
						if (si.getUrl().equals(tf_url.getText())) {

							si.setSiteName(tf_siteName.getText());
							si.setUrl(tf_url.getText());
							si.setId(tf_id.getText());
							si.setPasswd(tf_passwd.getText());
							si.setCateg(((SiteCategory) (list_categ.getSelectedItem())).getSiteCategory());
							si.setPrefer((String) list_prefer.getSelectedItem());
							si.setMemo(ta_memo.getText());

							mTable.setModel(new InfoTableModel(siteInfoList));

							tf_siteName.setText("");
							tf_url.setText("");
							tf_id.setText("");
							tf_passwd.setText("");
							list_categ.setSelectedIndex(0);
							list_prefer.setSelectedIndex(0);
							ta_memo.setText("");

							return;
						}
					}

					siteInfoList.addList(s);

					tf_siteName.setText("");
					tf_url.setText("");
					tf_id.setText("");
					tf_passwd.setText("");
					list_categ.setSelectedIndex(0);
					list_prefer.setSelectedIndex(0);
					ta_memo.setText("");

					reWrite.setEnabled(false);
					Input.setEnabled(false);

					sizeLabel.setText((++siteSize) + "���� ����Ʈ�� ��ϵǾ����ϴ�.");

				} else { // ������ư Ŭ����
							// InfoTableModel model = (InfoTableModel)(mTable.getModel());
					siteInfo.setSiteName(tf_siteName.getText());
					siteInfo.setUrl(tf_url.getText());
					siteInfo.setId(tf_id.getText());
					if (!tf_passwd.getText().equals("")) {
						siteInfo.setPasswd(tf_passwd.getText());
					}
					siteInfo.setCateg(((SiteCategory) (list_categ.getSelectedItem())).getSiteCategory());
					// siteInfo.setCateg((String) list_categ.getSelectedItem());
					siteInfo.setPrefer((String) list_prefer.getSelectedItem());
					siteInfo.setMemo(ta_memo.getText());

					tf_siteName.setText("");
					tf_url.setText("");
					tf_id.setText("");
					tf_passwd.setText("");
					list_categ.setSelectedIndex(0);
					list_prefer.setSelectedIndex(0);
					ta_memo.setText("");

					tf_url.setEditable(true); // �ּ� �Է¶� ��Ȱ��ȭ
					reWrite.setEnabled(true); // [�����ۼ�]��ư ��Ȱ��ȭ

					Input.setText("�Է�(I)");
					Input.setMnemonic('I');
				}
				mTable.setModel(new InfoTableModel(siteInfoList));

			}

		});

		reWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf_siteName.setText("");
				tf_url.setText("");
				tf_id.setText("");
				tf_passwd.setText("");
				list_categ.setSelectedIndex(0);
				list_prefer.setSelectedIndex(0);
				ta_memo.setText("");

			}

		});
	}

	// ����Ʈ���&�����Ȳ �� ���� �Լ� : BorderLayout.CENTER
	private JTabbedPane Tab() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP); // �� ���� ����

		pane.addTab("����Ʈ ���", SiteListTab()); // '����Ʈ ���' ��
		pane.addTab("�����Ȳ", RigiStatusTab()); // '�����Ȳ' ��

		return pane;
	}

	// '����Ʈ ���' �� ���� ���� �Լ�
	private JPanel SiteListTab() {
		JPanel totalPanel = new JPanel(new BorderLayout());
		JPanel listPanel = new JPanel(new BorderLayout());
		listPanel.setBorder(BorderFactory.createTitledBorder("�˻�/����"));

		// �˻�, ���� �г��� p1�� ����.
		JPanel p1 = new JPanel(new BorderLayout());

		JPanel searchPanel = new JPanel(new FlowLayout());
		searchPanel.setBorder(BorderFactory.createTitledBorder("�˻�"));
		// String[] listData1 = { "��ü", "�Ϲ�", "����", "����", "�б�" };
		list_search = new JComboBox<SiteCategory>(v2);

		list_search.setSelectedItem(total_category);
		searchPanel.add(list_search);
		searchPanel.add(new JLabel("����: "));
		searchField = new JTextField(7);
		searchPanel.add(searchField);

		// ���͸��� ���� ������ ������ ���
		list_search.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SiteCategory cate = (SiteCategory) e.getItem();
				SiteInfoList list = new SiteInfoList();
				SiteInfo site;

				if (cate.getSiteCategory().equals("��ü")) {
					mTable.setModel(new InfoTableModel(siteInfoList));
					return;
				}

				Iterator it = siteInfoList.getList().iterator();
				while (it.hasNext()) {
					site = (SiteInfo) it.next();
					String siteCate = site.getCateg();
					if (cate.getSiteCategory().equals(siteCate)) {
						list.addList(site);
					}
				}

				mTable.setModel(new InfoTableModel(list));

			}

		});

		searchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = searchField.getText();
				SiteInfoList list = new SiteInfoList();
				SiteInfo site;

				if (s.equals("")) {
					mTable.setModel(new InfoTableModel(siteInfoList));
					return;
				}

				Iterator it = siteInfoList.getList().iterator();
				while (it.hasNext()) {
					site = (SiteInfo) it.next();
					String cate = site.getCateg();
					String name = site.getSiteName();
					String url = site.getUrl();

					if (cate.contains(s)) {
						list.addList(site);
						continue;
					}
					if (name.contains(s)) {
						list.addList(site);
						continue;
					}
					if (url.contains(s)) {
						list.addList(site);
						continue;
					}
				}

				mTable.setModel(new InfoTableModel(list));
			}

		});

		JPanel sortPanel = new JPanel(new FlowLayout());
		sortPanel.setBorder(BorderFactory.createTitledBorder("����"));

		// String[] listData2 = { "��ü", "�Ϲ�", "����", "����", "�б�" };
		sort_categ = new JComboBox<SiteCategory>(v2);
		sort_categ.setSelectedItem(total_category);

		String[] listData3 = { "����Ʈ �̸�", "����Ʈ �ּ�", "��ȣ��" };
		sort_name = new JComboBox<String>(listData3);
		sort_name.setSelectedIndex(0);

		sortButton = new JButton("����");
		defaultButton = new JButton("�⺻");

		sortPanel.add(sort_categ);
		sortPanel.add(sort_name);
		sortPanel.add(sortButton);
		sortPanel.add(defaultButton);

		p1.add(searchPanel, BorderLayout.WEST);
		p1.add(sortPanel, BorderLayout.CENTER);

		// ǥ�� p2�� ����.
		JPanel p2 = new JPanel(new BorderLayout());
		mTable = new JTable();
		registerTableListener();
		p2.add(new JScrollPane(mTable), BorderLayout.CENTER);

		listPanel.add(p1, BorderLayout.NORTH);
		listPanel.add(p2, BorderLayout.CENTER);

		// ������������
		JPanel listPanel2 = new JPanel(new BorderLayout());
		JPanel p3 = new JPanel();
		cb = new JCheckBox("�������� ����");
		p3.add(cb);
		p3.add(new JLabel(" ���̵�"));
		id = new JTextField(10);
		p3.add(id);
		p3.add(new JLabel(" ��й�ȣ"));
		pass = new JTextField(10);
		p3.add(pass);

		id.setEditable(false);
		pass.setEditable(false);

		JPanel p4 = new JPanel();
		deleteButton = new JButton("����");
		deleteButton.setEnabled(false);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// [����] ��ư ������ ǥ���� ����
				siteInfoList.removeList(siteInfo);
				mTable.setModel(new InfoTableModel(siteInfoList));

				// [�Է�/����] �׸� �ʱ�ȭ
				tf_siteName.setText("");
				tf_url.setText("");
				tf_id.setText("");
				tf_passwd.setText("");
				list_categ.setSelectedIndex(0);
				list_prefer.setSelectedIndex(0);
				ta_memo.setText("");

				siteSize--;
				sizeLabel.setText(siteSize + "���� ����Ʈ�� ��ϵǾ����ϴ�.");
			}

		});
		p4.add(deleteButton);

		listPanel2.add(p3, BorderLayout.WEST);
		listPanel2.add(p4, BorderLayout.EAST);

		totalPanel.add(listPanel, BorderLayout.CENTER);
		totalPanel.add(listPanel2, BorderLayout.SOUTH);

		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					id.setText("");
					pass.setText("");
				}

			}

		});

		return totalPanel;
	}

	private void registerTableListener() {
		mTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// ǥ�� �� Ŭ���� [�Է�/����]�� ���� ��Ÿ����
				int row = mTable.getSelectedRow();

				InfoTableModel info = (InfoTableModel) (mTable.getModel());
				siteInfo = info.getSiteInfo(row);

				tf_siteName.setText(siteInfo.getSiteName());
				tf_url.setText(siteInfo.getUrl());
				tf_id.setText(siteInfo.getId());
				tf_passwd.setText("");
				// list_categ.setSelectedItem(siteInfo.getCateg());
				int cateIndex = -1;
				Iterator it = v.iterator();
				while (it.hasNext()) {
					cateIndex++;
					SiteCategory sCate = (SiteCategory) it.next();
					if (sCate.getSiteCategory().equals(siteInfo.getCateg())) {
						break;
					}
				}
				list_categ.setSelectedIndex(cateIndex);
				list_prefer.setSelectedItem(siteInfo.getPrefer());
				ta_memo.setText(siteInfo.getMemo());

				// ǥ�� �� Ŭ���ϰ� [������������] üũ��, ���� ��Ÿ����
				if (cb.isSelected()) {
					id.setText(siteInfo.getId());
					pass.setText(siteInfo.getPasswd());
				}

				Input.setText("����(E)");
				Input.setEnabled(true);
				Input.setMnemonic('E');

				deleteButton.setEnabled(true);
				tf_url.setEditable(false); // �ּ� �Է¶� ��Ȱ��ȭ
				reWrite.setEnabled(false); // [�����ۼ�]��ư ��Ȱ��ȭ
			}

		});
	}

	// '�����Ȳ' �� ���� ���� �Լ�
	private JComponent RigiStatusTab() {

		can = new GraphCanvas();
		return can;

	}

	// �����Ȳ �ȳ� �г� �Լ� : BorderLayout.SOUTH
	private JPanel RegiStatus() {
		siteSize = siteInfoList.getSize();
		regiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sizeLabel = new JLabel(siteSize + "���� ����Ʈ�� ��ϵǾ����ϴ�.");
		regiPanel.add(sizeLabel);
		regiPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		return regiPanel;
	}

}