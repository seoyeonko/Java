package L08;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

			addMouseListener(new MouseListener() {

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
				public void mouseEntered(MouseEvent e) { // 마우스가 임의의 컴포넌트 위에 올라올 때
				}

				@Override
				public void mouseExited(MouseEvent e) { // 컴포넌트에 올라온 마우스가 컴포넌트를 벗어날 때

				}

				@Override
				public void mousePressed(MouseEvent e) { // 마우스 버튼이 눌러질 때
					// if(원을 클릭한 경우)
					// 기존 원이 삭제되고 원이 드래그 되도록
					p = e.getPoint();
					pointList.remove(p);
 
					// else if(원이 아닌 구역을 클릭한 경우)
					// 원이 그려짐
					
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) { // 마우스 버튼이 떼어질 때
					// if, 도형이 있으면 삭제되고 움직이기
					// else, 드래그 마지막 위치에 원이 그려지기
					p = e.getPoint();
					pointList.add(p);

					repaint();
				}
			});

			// 원을 드래그하는 리스너
			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) { // 마우스를 임의의 컴포넌트 위에서 드래깅할 때
//               setTitle("(" + e.getX() + ", " + e.getY() + ")");
					pointList.remove(p);
					p = e.getPoint();
					pointList.add(p);

					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) { // 마우스를 임의의 컴포넌트 위에서 움직일 때
				}

			});
		}

		// 원을 그리는 paint 메서드
		public void paint(Graphics g) {
//         Color c = new Color(0, 0, 225, 100);
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