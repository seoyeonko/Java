package L08.hw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circle extends JFrame {

	public Circle() {
		super("Paint Ex");

		buildGUI();

		setLocation(200, 200);
		setSize(500, 500);
		setContentPane(new PaintCanvas());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		JPanel p = new JPanel();
		PaintCanvas paint = new PaintCanvas();
		p.add(paint);
		add(p);
	}

	// 내부 클래스로 해결
	public class PaintCanvas extends JComponent {

		Point p = null;

		static final int width = 50;
		static final int height = 50;

		ArrayList<Point> pointList = new ArrayList<Point>();
		int dragId = -1;

		PaintCanvas() {
			super();
			drawingCircleListener();
		}

		// 원을 그리는 리스너
		private void drawingCircleListener() {

			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) { // 마우스 클릴될 때
					if (pointList.size() < 100) {
						p = e.getPoint();
						pointList.add(p);
					} else {
						return;
					}
					repaint();
				}

				@Override
				public void mousePressed(MouseEvent e) { // 마우스 버튼이 눌러질 때
					// if(원을 클릭한 경우)
					// 기존 원이 삭제되고 원이 드래그 되도록
					// else (배경을 클릭한 경우)
					int x = e.getX();
					int y = e.getY();

					if (((x - 20 <= x) && (x <= x + 20)) && ((y - 20 <= y) && (y <= y + 20))) {
						pointList.remove(p);
					} else {
						p = e.getPoint();
						pointList.add(p);
					}
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) { // 마우스 버튼이 떼어질 때

					p = e.getPoint();
					pointList.add(p);

					repaint();
				}
			});

			// 원을 드래그하는 리스너
			addMouseMotionListener(new MouseMotionAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) { // 마우스를 임의의 컴포넌트 위에서 드래깅할 때
					pointList.remove(p);
					p = e.getPoint();
					pointList.add(p);

					repaint();
				}

			});
		}

		// 원을 그리는 paint 메서드
		public void paint(Graphics g) {
			g.setColor(Color.BLUE);
			for (int i = 0; i < pointList.size(); i++) {
				g.fillOval(pointList.get(i).x - 20, pointList.get(i).y - 20, 40, 40); // get(i).x와 get(i).y가 원의 중심
			}
		}
	}

	public static void main(String[] args) {
		new Circle();
	}

}
