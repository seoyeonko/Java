// �׸��⿡ ���� ó���� ���콺 ���۰� �����Ǿ� ���� ���
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
		JPanel p = new JPanel(null); // null : Ư���� ��ġ�����ڸ� ������� ����
		final JLabel disp = new JLabel("START"); // final ��� ó�� : ���콺 ó�� �̺�Ʈ�� ���� or ��� ���� ������ ����
	
		disp.setBounds(10, 20, 50, 20); // ��ġ�����ڸ� ������� �����Ƿ� ����ڰ� ���� ��ġ ���� �����ؾ���!
		p.add(disp);

		// ���� �ʿ���ϴ� �޼��常 �������ϱ� ���� adapter ���
		p.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) { // MouseMotion�� ���� �̺�Ʈ ��ü�� MouseMotionEvent�� �ƴ� MouseEvent�ӿ� ����!
				int x = e.getX();
				int y = e.getY();
				disp.setText(x+", "+y);
			}

		});

		// ���� �ʿ���ϴ� �޼��常 �������ϱ� ���� adapter ���
		p.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) { // Ŭ�� Ƚ���� ����
					disp.setText("DBCLCK");
				}
			}

				
		});
		
		this.add(p); // frame�� panel �߰�
	}

	public static void main(String[] args) {
		new MouseTest1();
	}

}

// �͸��� Ŭ����
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
//}); // ���콺 �����ӿ� ���� ������


