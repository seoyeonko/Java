// CategoryManageDialog.java

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CategoryManageDialog extends JDialog {

	// ������ SiteCategoryList��ü�� ���� ������ ����
	// ���� �߰��� �׸��� JList�� ǥ��

	// �Ӽ�
	JList<SiteCategory> list;
	//static Vector<SiteCategory> v = new Vector<SiteCategory>();
	SiteCategoryList siteList;
	Vector<SiteCategory> v;
	Vector<SiteCategory> v2;

	JButton bDel; // ����
	JButton bAdd; // �߰�
	JButton bNew; // �ű�
	JButton bDone; // �Ϸ�

	JTextField f; // �׸��̸� �Է¶�(

	public CategoryManageDialog(JFrame f, SiteCategoryList list, Vector<SiteCategory> v, Vector<SiteCategory> v2) {
		super(f, "����Ʈ �з� ����", true);

		siteList = list;
		this.v = v;
		this.v2 = v2;
		buildGUI();
		registerListener();

		setLocation(200, 200);
		setSize(400, 300);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void buildGUI() {
		setLayout(new BorderLayout());

		// [��� �׸�] ����
		JPanel p1 = new JPanel();
		p1.setBorder(BorderFactory.createTitledBorder("��� �׸�"));

		SiteCategory sc1 = new SiteCategory("�б�");
		SiteCategory sc2 = new SiteCategory("����");
		SiteCategory sc3 = new SiteCategory("��Ż");

		list = new JList<SiteCategory>(v);


		list.setFixedCellWidth(150);

		p1.add(new JScrollPane(list));

		// [��������] ����
		JPanel p2 = new JPanel(new BorderLayout());
		JPanel p22 = new JPanel(new GridLayout(2, 1));
		JPanel p2_1 = new JPanel();
		JPanel p2_2 = new JPanel();
		JPanel p2_1_1 = new JPanel();
		JPanel p2_1_2 = new JPanel();

		p2_1.setBorder(BorderFactory.createTitledBorder("���� ����"));

		JLabel label = new JLabel("�׸� �̸�");
		f = new JTextField(10);

		bDel = new JButton("����(D)");
		bAdd = new JButton("�߰�(A)");
		bNew = new JButton("�ű�(N)");
		bDone = new JButton("�Ϸ�(D)");

		bDel.setMnemonic('D');
		bAdd.setMnemonic('A');
		bNew.setMnemonic('N');
		bDone.setMnemonic('D');

		p2_1_1.add(label);
		p2_1_1.add(f);

		p2_1_2.add(bDel);
		p2_1_2.add(bAdd);

		p22.add(p2_1_1);
		p22.add(p2_1_2);

		p2_1.add(p22);

		p2_2.add(bNew);
		p2_2.add(bDone);

		p2.add(p2_1, BorderLayout.CENTER);
		p2.add(p2_2, BorderLayout.SOUTH);

		add(p1, BorderLayout.WEST);
		add(p2, BorderLayout.EAST);
	}

	private void registerListener() {

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int idx = e.getFirstIndex();
					f.setText(list.getSelectedValue().toString());
				}
			}

		});

		// [����]
		bDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx = list.getSelectedIndex();
				siteList.removeList(list.getSelectedValue());
				v.remove(idx);
				v2.remove(idx);
				
				list.updateUI();
				f.setText("");
			}

		});

		bAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = f.getText();
				v.add(new SiteCategory(s));
				v2.add(new SiteCategory(s));

				siteList.addList(new SiteCategory(s));

				list.updateUI();
				f.setText("");

			}

		});

		// [�ű�]
		bNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setText("");
			}

		});

		// [�Ϸ�]
		bDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});
	}

}
