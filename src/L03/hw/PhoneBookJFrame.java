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

	public PhoneBookJFrame() { // 생성자
		frame = new JFrame("PhoneBook");

		buildGUI();

		frame.setSize(250, 400);
		frame.setLocation(200, 200);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	// 화면 구성
	private void buildGUI() {
		createTopButtonPanel();
		createCenterPanel();
		createBottomButtonPanel();

		createBasicInputPanel();
//		createNormalInputPanel();
		createUnivInputPanel();
//		createCompanyInputPanel();

	}

	// 1. TOP : 상단의 세버튼 클릭시 수행되어야 하는 페이지 전환이라는 공통의 작업을 수행 -> 방법 4 이용
	private JPanel createTopButtonPanel() {
		p1 = new JPanel();
		frame.add(p1, BorderLayout.NORTH);

		// JToggleButton : 배타적 선택 처리
		JToggleButton tb_normallnp = new JToggleButton("일반", true);
		JToggleButton tb_univilnp = new JToggleButton("대학");
		JToggleButton tb_companylnp = new JToggleButton("회사");

		// ButtonGroup에 JToggleBUtton을 추가 -> toggle의 기능수행이 가능해짐
		ButtonGroup group = new ButtonGroup();
		group.add(tb_normallnp);
		group.add(tb_univilnp);
		group.add(tb_companylnp);

		// JToggleButton을 Panel에 추가
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

//		p2.setLayout(new GridLayout(2, 1, 0, 10)); // ** 빨간색 부분 배치
		return p22;
	}

	// 2_1.CENTER_basic
	private JPanel createBasicInputPanel() {
		p2_1 = new JPanel();

		p2.add(p2_1, BorderLayout.CENTER); // 하늘색 점선상자
		p2_1.setLayout(new GridLayout(3, 2, 0, 10));
		JLabel l1 = new JLabel("이      름");
		JLabel l2 = new JLabel("전화번호");
		JLabel l3 = new JLabel("생년월일");
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
		p2.add(p2_2);  // 빨간색 점선상자(1)

		return p2_2;
	}

	// 2_3.CENTER_univ
	private JPanel createUnivInputPanel() {
		p2_3 = new JPanel();
		p2.add(p2_3, BorderLayout.SOUTH);  // 빨간색 점선상자(2)
		p2_3.setLayout(new GridLayout(3, 2, 0, 10));

		JLabel l4 = new JLabel("전      공");
		JLabel l5 = new JLabel("학      년");
		JLabel l6 = new JLabel(""); // 임의 생성
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
		p2.add(p2_4, BorderLayout.SOUTH); // 빨간색 점선상자(3)
		p2_4.setLayout(new GridLayout(3, 2, 0, 10));

		JLabel l7 = new JLabel("회      사");
		JLabel l8 = new JLabel(""); // 줄맞추기위해 임의 생성
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

	// 3.BUTTOM : 하단의 두 버튼 클릭시 수행되어야 하는 바는 "서로 상이"합니다 -> 방법 5 이용
	private JPanel createBottomButtonPanel() {
		p3 = new JPanel();
		frame.add(p3, BorderLayout.SOUTH);

		// [입력] 버튼 클릭시 프로그램 종료
		JButton b1 = new JButton("입력");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// [초기화] 버튼 클릭시 첫번째 상태로 설정
		JButton b2 = new JButton("초기화");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// void setText(String s)
				// void setSelecter(boolean b);
			}
		});

//		// [입력] 버튼 클릭시 프로그램 종료
//		JButton b1 = new JButton("입력");
//		b1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//
//		// [초기화] 버튼 클릭시 첫번째 상태로 설정
//		JButton b2 = new JButton("초기화");
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
