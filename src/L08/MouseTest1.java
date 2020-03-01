// 그리기에 대한 처리는 마우스 조작과 연관되어 많이 사용
package L08;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseTest1 extends JFrame {
	
	public MouseTest1() {
		super("MouseTest1");

		buildGUI();

		setLocation(500, 200);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		JPanel p = new JPanel(null); // null : 특정한 배치관리자를 사용하지 않음
		final JLabel disp = new JLabel("START"); // final 상수 처리 : 마우스 처리 이벤트를 위해 or 멤버 변수 영역에 선언
	
		disp.setBounds(10, 20, 50, 20); // 배치관리자를 사용하지 않으므로 사용자가 직접 배치 값을 설정해야함!
		p.add(disp);

		// 내가 필요로하는 메서드만 재정의하기 위해 adapter 사용
		p.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) { // MouseMotion에 관한 이벤트 객체는 MouseMotionEvent가 아닌 MouseEvent임에 주의!
				int x = e.getX();
				int y = e.getY();
				disp.setText(x+", "+y);
			}

		});

		// 내가 필요로하는 메서드만 재정의하기 위해 adapter 사용
		p.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) { // 클릭 횟수를 얻어옴
					disp.setText("DBCLCK");
				}
			}

				
		});
		
		this.add(p); // frame에 panel 추가
	}

	public static void main(String[] args) {
		new MouseTest1();
	}

}

// 익명내부 클래스
//p.addMouseMotionListener(new MouseMotionListener() { 
//
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		
//	}	
//}); // 마우스 움직임에 대한 리스너


