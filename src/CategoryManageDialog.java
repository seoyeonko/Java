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

	// 생성된 SiteCategoryList객체에 대한 참조를 가짐
	// 현재 추가된 항목은 JList로 표시

	// 속성
	JList<SiteCategory> list;
	//static Vector<SiteCategory> v = new Vector<SiteCategory>();
	SiteCategoryList siteList;
	Vector<SiteCategory> v;
	Vector<SiteCategory> v2;

	JButton bDel; // 삭제
	JButton bAdd; // 추가
	JButton bNew; // 신규
	JButton bDone; // 완료

	JTextField f; // 항목이름 입력란(

	public CategoryManageDialog(JFrame f, SiteCategoryList list, Vector<SiteCategory> v, Vector<SiteCategory> v2) {
		super(f, "사이트 분류 관리", true);

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

		// [등록 항목] 영역
		JPanel p1 = new JPanel();
		p1.setBorder(BorderFactory.createTitledBorder("등록 항목"));

		SiteCategory sc1 = new SiteCategory("학교");
		SiteCategory sc2 = new SiteCategory("정보");
		SiteCategory sc3 = new SiteCategory("포탈");

		list = new JList<SiteCategory>(v);


		list.setFixedCellWidth(150);

		p1.add(new JScrollPane(list));

		// [편집내용] 영역
		JPanel p2 = new JPanel(new BorderLayout());
		JPanel p22 = new JPanel(new GridLayout(2, 1));
		JPanel p2_1 = new JPanel();
		JPanel p2_2 = new JPanel();
		JPanel p2_1_1 = new JPanel();
		JPanel p2_1_2 = new JPanel();

		p2_1.setBorder(BorderFactory.createTitledBorder("편집 내용"));

		JLabel label = new JLabel("항목 이름");
		f = new JTextField(10);

		bDel = new JButton("삭제(D)");
		bAdd = new JButton("추가(A)");
		bNew = new JButton("신규(N)");
		bDone = new JButton("완료(D)");

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

		// [삭제]
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

		// [신규]
		bNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setText("");
			}

		});

		// [완료]
		bDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});
	}

}
