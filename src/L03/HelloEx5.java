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

public class HelloEx5 {
	private JFrame frame;
	// buildGUI()외에 사용되지 않으므로 멤버변수로써의 사용은 비효율 -> *1 지역변수로 수정
//	private JTextField text;
//	private JLabel label;

	public HelloEx5() {
		frame = new JFrame("HelloEx2");

		buildGUI();

		frame.setSize(400, 100);
		frame.setLocation(500, 300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buildGUI() {

		final JTextField text = new JTextField(10); // *1 *2
		JButton button = new JButton("확인");

		final JLabel label = new JLabel("Hello World!"); // *1 *2
		button.addActionListener(new ActionListener() {
			// 인터페이스(ActopmListener)이므로 몸체를 재정의할 수 있는 구조를 만듦
			@Override
			public void actionPerformed(ActionEvent e) {// +) 호출되는 시점: buildGUI()가 끝나는? 실행되는? 시점??? 답) 버튼이 눌리는 시점!!!!
				// buildGUI가 끝나고 프레임이 가시화 되어지는 시점에서 버튼이 눌릴때 호출되는 것임
				// *2 지역변수는 함수가 호출되서 동작할 때만 생기고 함수가 끝나면 사라지는 특성있음 이를 보완하기 위해 final 키워드 부여 
				// => 프로그램이 끝나도라도 함수의 값이 사라지지 않고 남아있음
				String name = text.getText();
				text.setText("");

				label.setText("Hello, " + name);
			}

		});

//		button.addActionListener(new ActionListener() { // 익명 내부 클래스 : 클래스 이름 없이 객체를생성한 경우
//
//			@Override
//			public void actionPerformed(ActionEvent e) { // 메서드 구현
//				String name = text.getText();
//				text.setText("");
//
//				label.setText("Hello, " + name);
//			}
//
//		});

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(text);
		panel.add(button);

		panel.setBackground(Color.ORANGE);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new HelloEx5();
	}
}

// *중요
// 방법5: 익명내부클래스 임시 객체 사용
// 리스너 객체가 단 한번만 사용되는 경우에 유용
//예) 다섯개의 이벤트소스가 각기 다른 일을 하는 경우
