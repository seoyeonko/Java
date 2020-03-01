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

	// 속성
	JButton bStart;
	JButton bPause;
	JButton bReset;

	static Thread thread;

	JLabel l_msec;
	JLabel l_sec;
	JLabel l_min;

	// 생성자
	public StopWatch() {
		super("Stop Watch");

		buildGUI();

		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 프레임 크기 변경 비활성화
		pack(); // 프레임 크기 자동 조정
		setVisible(true);

	}

	// 화면 구성 함수
	private void buildGUI() {
		setLayout(new BorderLayout());

		ClockPanel();
		ButtonPanel();

	}

	// 시간 부분
	private void ClockPanel() {
		JPanel p_display = new JPanel();

		// 레이블 객체 생성
		l_msec = new JLabel("0");
		l_sec = new JLabel("00");
		l_min = new JLabel("00");
		JLabel l1 = new JLabel(":");
		JLabel l2 = new JLabel(":");

		// 폰트 설정
		l_min.setFont(new Font("Gothic", Font.BOLD, 50));
		l_sec.setFont(new Font("Gothic", Font.BOLD, 50));
		l_msec.setFont(new Font("Gothic", Font.BOLD, 50));
		l1.setFont(new Font("Gothic", Font.BOLD, 50));
		l2.setFont(new Font("Gothic", Font.BOLD, 50));

		// 패널에 추가
		p_display.add(l_min);
		p_display.add(l1);
		p_display.add(l_sec);
		p_display.add(l2);
		p_display.add(l_msec);
		p_display.setBackground(Color.white);

		// 프레임에 추가
		add(p_display, BorderLayout.CENTER);
	}

	// 버튼 부분
	private void ButtonPanel() {
		JPanel p_button = new JPanel();

		// 버튼 객체 생성
		bStart = new JButton("START");
		bPause = new JButton("PAUSE");
		bReset = new JButton("RESET");

		// 폰트 설정
		bStart.setFont(new Font("Serif", Font.BOLD, 15));
		bPause.setFont(new Font("Serif", Font.BOLD, 15));
		bReset.setFont(new Font("Serif", Font.BOLD, 15));

		// 패널에 추가
		p_button.add(bStart);
		p_button.add(bPause);
		p_button.add(bReset);

		// 이벤트 리스너 설정
		bStart.addActionListener(handler);
		bPause.addActionListener(handler);
		bReset.addActionListener(handler);

		// 프레임에 추가
		add(p_button, BorderLayout.SOUTH);
	}

	// 리스너 처리
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
								Thread.sleep(10); // 0.1초
								l_msec.setText(String.format("%d", (Integer.parseInt(l_msec.getText()) + 1) % 100)); // msec는 10진수

								if ((Integer.parseInt(l_msec.getText())) % 100 == 0) { // 99msec일때 sec의 변화
									if (Integer.parseInt(l_sec.getText()) < 9) { // 현재 sec가 두 자리수가 아니면
										l_sec.setText(String.format("0" + "%d", Integer.parseInt(l_sec.getText()) + 1)); // 앞에 0 붙이기
									} else { // 현재 sec가 두 자리수라면
										l_sec.setText(String.format("%d", (Integer.parseInt(l_sec.getText()) + 1) % 60)); // 그냥

										if ((Integer.parseInt(l_sec.getText())) % 60 == 0) { // 59sec일때 min의 변화
											l_min.setText(String.format("%d", (Integer.parseInt(l_min.getText()) + 1) % 60)); // sec는
																																// 60진수
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

	// 메인 메서드
	public static void main(String[] args) {
		new StopWatch();
	}

}
