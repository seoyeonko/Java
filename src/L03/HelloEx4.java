package L03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelloEx4 {
	private JFrame frame;
	private JTextField text;
	private JLabel label;

	public HelloEx4() {
		frame = new JFrame("HelloEx2");

		buildGUI();

		frame.setSize(400, 100);
		frame.setLocation(500, 300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {

		text = new JTextField(10);
		JButton button = new JButton("확인");

		label = new JLabel("Hello World!");

		button.addActionListener(listener);

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

//	ActionListener listener = new ActionListener(); // 참조변수 : 리스너 객체 유지하기 위한 변수
//	// 오류?? ActionListener는 인터페이스(추상메서드가 있음) -> 추상적인 구조화 객체 생성 불가

	ActionListener listener = new ActionListener() { // listener: 참조변수
		// 익명 내부 클래스 : 클래스 이름 없이 객체를 생성한 경우

		@Override
		public void actionPerformed(ActionEvent e) { // 메서드 구현
			String name = text.getText();
			text.setText("");

			label.setText("Hello, " + name);

			System.out.println(e.getActionCommand()); // * 프로그램에서 어떤 action이 실행되었는지 출력
//			if (e.getSource == button) ..e. // * button 멤버변수로 선언후, 이벤트가 여러개? 확인버튼이 아닌경우? 이러한 코드로 확인함

			System.exit(0); // * 시스템 강제종료
		}

	}; // ; : 값을 할당하는 문장(line54-64)이 끝난다는 표식

	public static void main(String[] args) {
		new HelloEx4();
	}
}

// *중요
// 방법4: 익명 내부 클래스 사용
// 익명클래스 : 이름이 없는 클래스 -> 클래스를 정의하는 동시에 객체를 만들어야해
// 여러 이벤트 소스가 공유할때 적합
//예)다섯개의 이벤트소스가 (거의/완전히) 동일한 경우