package L04;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CheckBoxTest extends JFrame { // 2번 방법으로 JFrame 생성 : 상속

	JCheckBox[] cb_list;
	JTextArea t_display;

	public CheckBoxTest() {
		super("체크박스 예제");

		buildGUI();
		regEventListener(); // 이벤트 리스너를 등록하는 함수

		this.setSize(500, 300); // this 안해도되나 쓰는 것을 권장
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // this 안써도 되는 것을 보여줌
	}

	private void buildGUI() {
		this.add(createCheckBoxPanel(), BorderLayout.NORTH);
//		JTextArea t_display;
		// ㄴ 이벤트 리스너를 위해 멤버 변수 처리!
		t_display = new JTextArea("hello"); // 창크기를 꽉 채울 것이므로 별도의 크기 지정은 하지 않음
//		t_display.setEnabled(false); // textarea에서 희미한 글씨로 나타나있음
//		t_display.setEditable(false); // textarea에서 희미한 글씨 -> 단, 수정은 불가
		this.add(new JScrollPane(t_display), BorderLayout.CENTER); // new JScrollPane : 스크롤바 생성(가로, 세로 모두!)

	}

	private JPanel createCheckBoxPanel() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JCheckBox[] cb_list; // 주의) 체크박스 5개가 만들어진 것이아니라 체크박스 5개를 참조할수 있는 참조변수가 5개 생성된 것
		// ㄴ 이벤트 리스너를 위해 멤버변수 처리!
		cb_list = new JCheckBox[5];

		for (int i = 0; i < 5; i++) {
			cb_list[i] = new JCheckBox("항목" + (i + 1));
			p.add(cb_list[i]);

			// cb_list[i].addItemListenr(...) // 가능하나 수업에서는 다른 곳에서 진행할 것
		}
		return p;

	}

	// 이벤트리스너를 등록하는 함수 : 체크박스에서 각각의 버튼이 눌리면 어떤 처리를 할 것인가?
	private void regEventListener() {
		for (JCheckBox cb : cb_list) // for-which구문 : 모든 배열에대해 반복적으로 처리하고자 할 때
			cb.addItemListener(handler); // add : 하나 이상 추가 가능하다는 의미
	}

	ItemListener handler = new ItemListener() { // 사실, 아이템 리스너는 인터페이스라 객체 만들수 없음 -> 익명 내부 클래스(방법4)를 통해 해결

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cb = (JCheckBox) e.getSource(); // 다운캐스팅! nor 오류
			String s = cb.getText();
			
			if (e.getStateChange() == ItemEvent.SELECTED)
				s += "선택";
			else
				s += "해제";
			
			t_display.append(s); // setText()는 기존의 항목을 삭제
			// append()는 기존의 내용을 삭제하지 않음!

		}

	};

	public static void main(String[] args) {
		new CheckBoxTest();
		System.out.println("END");
	}

}
