package L03.hw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class PhoneBookJFrame {
	private JFrame frame;
	private JPanel p1;

private JPanel p2_1;
	private JPanel p2_2;
	private JPanel p2_3;
	private JPanel p2_4;
	private JPanel p22;
	private JPanel p2;
	private JPanel p3;

	public PhoneBookJFrame() { // ������
		frame = new JFrame("PhoneBook");

		buildGUI();

		frame.setSize(250, 400);
		frame.setLocation(200, 200);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	// ȭ�� ����
	private void buildGUI() {
		createTopButtonPanel();
		createCenterPanel();
		createBottomButtonPanel();

		createBasicInputPanel();
//		createNormalInputPanel();
		createUnivInputPanel();
//		createCompanyInputPanel();

	}

	// 1. TOP : ����� ����ư Ŭ���� ����Ǿ�� �ϴ� ������ ��ȯ�̶�� ������ �۾��� ���� -> ��� 4 �̿�
	private JPanel createTopButtonPanel() {
		p1 = new JPanel();
		frame.add(p1, BorderLayout.NORTH);

		// JToggleButton : ��Ÿ�� ���� ó��
		JToggleButton tb_normallnp = new JToggleButton("�Ϲ�", true);
		JToggleButton tb_univilnp = new JToggleButton("����");
		JToggleButton tb_companylnp = new JToggleButton("ȸ��");

		// ButtonGroup�� JToggleBUtton�� �߰� -> toggle�� ��ɼ����� ��������
		ButtonGroup group = new ButtonGroup();
		group.add(tb_normallnp);
		group.add(tb_univilnp);
		group.add(tb_companylnp);

		// JToggleButton�� Panel�� �߰�
		p1.add(tb_normallnp);
		p1.add(tb_univilnp);
		p1.add(tb_companylnp);

		return p1;
	}

	// 2.CENTER
	private JPanel createCenterPanel() {
		p22 = new JPanel();
		p2 = new JPanel();
		frame.add(p22, BorderLayout.CENTER);
		p22.add(p2, new FlowLayout(FlowLayout.CENTER));

//		p2.setLayout(new GridLayout(2, 1, 0, 10)); // ** ������ �κ� ��ġ
		return p22;
	}

	// 2_1.CENTER_basic
	private JPanel createBasicInputPanel() {
		p2_1 = new JPanel();

		p2.add(p2_1, BorderLayout.CENTER); // �ϴû� ��������
		p2_1.setLayout(new GridLayout(3, 2, 0, 10));
		JLabel l1 = new JLabel("��      ��");
		JLabel l2 = new JLabel("��ȭ��ȣ");
		JLabel l3 = new JLabel("�������");
		JTextField t1 = new JTextField(8);
		JTextField t2 = new JTextField(8);
		JTextField t3 = new JTextField(8);

		p2_1.add(l1);
		p2_1.add(t1);
		p2_1.add(l2);
		p2_1.add(t2);
		p2_1.add(l3);
		p2_1.add(t3);

		return p2_1;
	}

	// 2_2.CENTER_normal
	private JPanel createNormalInputPanel() {
		p2_2 = new JPanel();
		p2.add(p2_2);  // ������ ��������(1)

		return p2_2;
	}

	// 2_3.CENTER_univ
	private JPanel createUnivInputPanel() {
		p2_3 = new JPanel();
		p2.add(p2_3, BorderLayout.SOUTH);  // ������ ��������(2)
		p2_3.setLayout(new GridLayout(3, 2, 0, 10));

		JLabel l4 = new JLabel("��      ��");
		JLabel l5 = new JLabel("��      ��");
		JLabel l6 = new JLabel(""); // ���� ����
		JTextField t4 = new JTextField(8);
		JTextField t5 = new JTextField(8);

		p2_3.add(l4);
		p2_3.add(t4);
		p2_3.add(l5);
		p2_3.add(t5);
		p2_3.add(l6);

		return p2_3;
	}

	// 2_4.CENTER_company
	private JPanel createCompanyInputPanel() {
		p2_4 = new JPanel();
		p2.add(p2_4, BorderLayout.SOUTH); // ������ ��������(3)
		p2_4.setLayout(new GridLayout(3, 2, 0, 10));

		JLabel l7 = new JLabel("ȸ      ��");
		JLabel l8 = new JLabel(""); // �ٸ��߱����� ���� ����
		JLabel l9 = new JLabel("");
		JLabel l10 = new JLabel("");
		JTextField t7 = new JTextField(8);

		p2_4.add(l7);
		p2_4.add(t7);
		p2_4.add(l8);
		p2_4.add(l9);
		p2_4.add(l10);
		return p2_4;
	}

	// 3.BUTTOM : �ϴ��� �� ��ư Ŭ���� ����Ǿ�� �ϴ� �ٴ� "���� ����"�մϴ� -> ��� 5 �̿�
	private JPanel createBottomButtonPanel() {
		p3 = new JPanel();
		frame.add(p3, BorderLayout.SOUTH);

		// [�Է�] ��ư Ŭ���� ���α׷� ����
		JButton b1 = new JButton("�Է�");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// [�ʱ�ȭ] ��ư Ŭ���� ù��° ���·� ����
		JButton b2 = new JButton("�ʱ�ȭ");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// void setText(String s)
				// void setSelecter(boolean b);
			}
		});

//		// [�Է�] ��ư Ŭ���� ���α׷� ����
//		JButton b1 = new JButton("�Է�");
//		b1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//
//		// [�ʱ�ȭ] ��ư Ŭ���� ù��° ���·� ����
//		JButton b2 = new JButton("�ʱ�ȭ");
//		b2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// void setText(String s)
//				// void setSelecter(boolean b);
//			}
//		});

		p3.add(b1);
		p3.add(b2);

		return p3;
	}

}
