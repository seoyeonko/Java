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
		btn = new JButton("��������");
		f.add(btn);

		btn.addActionListener(handler);
	}

	private ActionListener handler = new ActionListener() {

//		private boolean cont = true; // 1,2 �ܰ�
		private int offset = 10;

		@Override
		public void actionPerformed(ActionEvent e) {
			// 1�ܰ�(������ ����) : ����� ��ư Ŭ���� ����
//			String s = e.getActionCommand();
//			f.setLocation(f.getLocation().x + offset, f.getLocation().y);
//			offset = -offset;

			// 2�ܰ�(���� ����) : ��ư Ŭ�� �� �ڵ�����
//			String s = e.getActionCommand();
//			if (s.equals("��������")) {
//				btn.setText("������");
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
//				btn.setText("��������");
//				cont = false;
//			}
//		}

			// 3�ܰ� : ��Ƽ ������ ����
			String s = e.getActionCommand();

			if (s.equals("��������")) {
				btn.setText("������");
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
				btn.setText("��������");
				thread = null;
			}
		}
	};

	public static void main(String[] args) {
		new Vibration1();
	}

}
