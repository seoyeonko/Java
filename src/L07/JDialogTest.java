package L07;

// 약간 다듬을 필요 있는 소스 코드
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JDialogTest extends JFrame {
	JButton b1, b2, b3, b4;

	public JDialogTest() {
		super("JDialogTest");

		buildGUI();
		setBounds(100, 100, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		setLayout(new FlowLayout());
//		JButton button = new JButton("대화상자");

		b1 = new JButton("대화상자1");
		b2 = new JButton("대화상자2");
		b3 = new JButton("대화상자3");
		b4 = new JButton("대화상자4");

//		add(button);
		add(b1);
		add(b2);
		add(b3);
		add(b4);

		b1.addActionListener(l);
		b2.addActionListener(l);
		b3.addActionListener(l);
		b4.addActionListener(l);
	}

	private ActionListener l = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 슬14 : JOptionPane
			Object src = e.getSource();

			if (src == b1) {
				JOptionPane.showMessageDialog(JDialogTest.this, "메세지 다이얼로그박스", "메세지", JOptionPane.INFORMATION_MESSAGE);
			} else if (src == b2) {
				int n = JOptionPane.showConfirmDialog(JDialogTest.this, "확인다이얼로그박스", "확인",
						JOptionPane.YES_NO_CANCEL_OPTION);
			} else if (src == b3) {
				String s = JOptionPane.showInputDialog(JDialogTest.this, "입력다이얼로그박스", "입력",
						JOptionPane.QUESTION_MESSAGE);
				System.out.println(s);
			} else if (src == b4) {
				String[] str = { "로그인", "회원가입" };
				int n = JOptionPane.showOptionDialog(JDialogTest.this, "옵션다이얼로그박스", "옵션", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				System.out.println(str[n]);
			}

			// 슬9 : 대화상자
//			JMyDialog dlg = new JMyDialog(JDialogTest.this); // 액션리스너로 부터 구현된 리스터 객체
//			// this : 프레임이 아닌 리스너 객체를 가르킴
//			// JDialogTest.this 
//			dlg.setVisible(true); // 가시화 시점
//
//			String input = dlg.getInput(); // 위에 가시화된 것이 "닫힌 후"에 실행되는 것에 주의	**
//			if (input != null)
//				System.out.println(input);
//			else
//				System.out.println("입력되지 않음");
		}
	};

	public static void main(String[] args) {
		new JDialogTest();
	}

}
