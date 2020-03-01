package L09.hw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StopWatch extends JFrame{

	// �Ӽ�
	JButton bStart;
	JButton bPause;
	JButton bReset;

	static Thread thread;

	JLabel l_msec;
	JLabel l_sec;
	JLabel l_min;

	// ������
	public StopWatch() {
		super("Stop Watch");

		buildGUI();

		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // ������ ũ�� ���� ��Ȱ��ȭ
		pack(); // ������ ũ�� �ڵ� ����
		setVisible(true);

	}

	// ȭ�� ���� �Լ�
	private void buildGUI() {
		setLayout(new BorderLayout());

		ClockPanel();
		ButtonPanel();

	}

	// �ð� �κ�
	private void ClockPanel() {
		JPanel p_display = new JPanel();

		// ���̺� ��ü ����
		l_msec = new JLabel("0");
		l_sec = new JLabel("00");
		l_min = new JLabel("00");
		JLabel l1 = new JLabel(":");
		JLabel l2 = new JLabel(":");

		// ��Ʈ ����
		l_min.setFont(new Font("Gothic", Font.BOLD, 50));
		l_sec.setFont(new Font("Gothic", Font.BOLD, 50));
		l_msec.setFont(new Font("Gothic", Font.BOLD, 50));
		l1.setFont(new Font("Gothic", Font.BOLD, 50));
		l2.setFont(new Font("Gothic", Font.BOLD, 50));

		// �гο� �߰�
		p_display.add(l_min);
		p_display.add(l1);
		p_display.add(l_sec);
		p_display.add(l2);
		p_display.add(l_msec);
		p_display.setBackground(Color.white);

		// �����ӿ� �߰�
		add(p_display, BorderLayout.CENTER);
	}

	// ��ư �κ�
	private void ButtonPanel() {
		JPanel p_button = new JPanel();

		// ��ư ��ü ����
		bStart = new JButton("START");
		bPause = new JButton("PAUSE");
		bReset = new JButton("RESET");

		// ��Ʈ ����
		bStart.setFont(new Font("Serif", Font.BOLD, 15));
		bPause.setFont(new Font("Serif", Font.BOLD, 15));
		bReset.setFont(new Font("Serif", Font.BOLD, 15));

		// �гο� �߰�
		p_button.add(bStart);
		p_button.add(bPause);
		p_button.add(bReset);

		// �̺�Ʈ ������ ����
		bStart.addActionListener(handler);
		bPause.addActionListener(handler);
		bReset.addActionListener(handler);

		// �����ӿ� �߰�
		add(p_button, BorderLayout.SOUTH);
	}

	// ������ ó��
	private ActionListener handler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == bStart) {
				thread = new Thread(new Runnable() {

					@Override
					public void run() {
						int n=0;
						while(thread!=null) {
							System.out.println(n);
							n++;
							try {
								Thread.sleep(10); // 0.1��
								l_msec.setText(String.format("%d", (Integer.parseInt(l_msec.getText()) + 1) % 100)); // msec�� 10����

								if ((Integer.parseInt(l_msec.getText())) % 100 == 0) { // 99msec�϶� sec�� ��ȭ
									if (Integer.parseInt(l_sec.getText()) < 9) { // ���� sec�� �� �ڸ����� �ƴϸ�
										l_sec.setText(String.format("0" + "%d", Integer.parseInt(l_sec.getText()) + 1)); // �տ� 0 ���̱�
									} else { // ���� sec�� �� �ڸ������
										l_sec.setText(String.format("%d", (Integer.parseInt(l_sec.getText()) + 1) % 60)); // �׳�

										if ((Integer.parseInt(l_sec.getText())) % 60 == 0) { // 59sec�϶� min�� ��ȭ
											l_min.setText(String.format("%d", (Integer.parseInt(l_min.getText()) + 1) % 60)); // sec��
																																// 60����
										}
									}
								}

							} catch (InterruptedException e) {
								return;
							}
						}
						
					}
					
				});
				
					thread.start();
				
			} else if (e.getSource() == bPause) {
				if (thread == null)
					return;
				if (thread.isAlive())
					thread.interrupt();
			} else if (e.getSource() == bReset) {
				if (thread.isAlive()) {
					thread.interrupt();
				}
				l_msec.setText("00");
				l_sec.setText("00");
				l_min.setText("00");

			}
			
		}

	};

	// ���� �޼���
	public static void main(String[] args) {
		new StopWatch();
	}

}
