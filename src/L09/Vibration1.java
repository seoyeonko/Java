package L09;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Vibration1 {
	JFrame f;
	JButton btn;
	Thread thread;

	private Vibration1() {
		f = new JFrame("Vibration1");

		buildGUI();

		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private void buildGUI() {
		btn = new JButton("진동시작");
		f.add(btn);

		btn.addActionListener(handler);
	}

	private ActionListener handler = new ActionListener() {

//		private boolean cont = true; // 1,2 단계
		private int offset = 10;

		@Override
		public void actionPerformed(ActionEvent e) {
			// 1단계(스레드 없음) : 사용자 버튼 클릭때 마다
//			String s = e.getActionCommand();
//			f.setLocation(f.getLocation().x + offset, f.getLocation().y);
//			offset = -offset;

			// 2단계(문제 있음) : 버튼 클릭 후 자동으로
//			String s = e.getActionCommand();
//			if (s.equals("진동시작")) {
//				btn.setText("진동끝");
//				while (cont) {
//					f.setLocation(f.getLocation().x + offset, f.getLocation().y);
//					offset = -offset;
//
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e1) {
//					}
//				}
//			} else {
//				btn.setText("진동시작");
//				cont = false;
//			}
//		}

			// 3단계 : 멀티 스레드 구성
			String s = e.getActionCommand();

			if (s.equals("진동시작")) {
				btn.setText("진동끝");
				thread = new Thread(new Runnable() {

					@Override
					public void run() {

						while (thread == Thread.currentThread()) {
							f.setLocation(f.getLocation().x + offset, f.getLocation().y);
							offset = -offset;
							try {
								Thread.sleep(50);
							} catch (InterruptedException e1) {
							}
						}
					}

				});
				thread.start();
			} else {
				btn.setText("진동시작");
				thread = null;
			}
		}
	};

	public static void main(String[] args) {
		new Vibration1();
	}

}
