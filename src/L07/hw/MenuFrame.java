package L07.hw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuFrame extends JFrame implements ActionListener {

	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu helpMenu;

	private JMenuItem fItem1;
	private JMenuItem fItem2;
	private JMenuItem fItem3;

	private JMenuItem eItem1;
	private JCheckBoxMenuItem eItem2;

	private JMenuItem hItem1;

	private JPanel p;
	private JLabel l;
	private JTextField tf;

	private JButton b;

	public MenuFrame() {
//		f = new JFrame("메뉴와 대화상자 연습");
		super("메뉴와 대화상자 연습");

		createMenu();
		add(buildGUI());

		setSize(420, 300);
		setLocation(550,250);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // [x]버튼 눌러도 아무 동작 하지 않도록 설정
		setVisible(true);

		// [프레임의 종료 버튼] 클릭시 확인대화상자로 종료 여부 물음
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { // 윈도우 시스템 메뉴에서 윈도우 닫기를 시도할 때
				Exit();
			}
		});

	}

	// 메뉴바 구성 함수
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb); // 메뉴바를 프레임에 부착

		// JMenu
		fileMenu = new JMenu("파일(F)");
		editMenu = new JMenu("편집(E)");
		helpMenu = new JMenu("도움말(H)");
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('H');
		helpMenu.setMnemonic('E');

		// JMenuItem
		fItem1 = new JMenuItem("열기(O)...");
		fItem2 = new JMenuItem("저장(S)...");
		fItem3 = new JMenuItem("종료(X)");
		fItem1.setMnemonic('O');
		fItem2.setMnemonic('S');
		fItem3.setMnemonic('X');

		eItem1 = new JMenuItem("입력(I)...");
		eItem2 = new JCheckBoxMenuItem("직접 수정(D)");
		eItem1.setMnemonic('I');
		eItem2.setMnemonic('D');

		hItem1 = new JMenuItem("정보(A)...");
		hItem1.setMnemonic('A');

		// 각 컴포넌트들에 부착
		fileMenu.add(fItem1);
		fileMenu.add(fItem2);
		fileMenu.add(new JSeparator()); // 구분선 추가
		fileMenu.add(fItem3);

		editMenu.add(eItem1);
		editMenu.add(eItem2);

		helpMenu.add(hItem1);

		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(helpMenu);

		// 이벤트 리스너 연결
		// 파일 - 열기
		fItem1.addActionListener(new ActionListener() {
			JFileChooser chooser = new JFileChooser();

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Imanges", "jpg", "gif");
				chooser.setFileFilter(filter);

				// 파일 다이얼로그 출력
				int ret = chooser.showOpenDialog(null); // 열기

				// 사용자가 창을 강제로 닫았거나 취소버튼을 누른 경우
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우 : 메세지 대화상자 열기
				String filePath = chooser.getSelectedFile().getPath();

				if (chooser.getSelectedFile() != null) {
					JOptionPane.showMessageDialog(MenuFrame.this, filePath + "을 열기", "확인",
							JOptionPane.INFORMATION_MESSAGE);
				}

				pack();
			}

		});
		// 파일 - 저장
		fItem2.addActionListener(new ActionListener() {
			JFileChooser chooser = new JFileChooser();

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Imanges", "jpg", "gif");
				chooser.setFileFilter(filter);

				// 파일 다이얼로그 출력
				int ret = chooser.showSaveDialog(null); // 저장

				// 사용자가 창을 강제로 닫았거나 취소버튼을 누른 경우
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 사용자가 파일을 선택하고 "저장" 버튼을 누른 경우 : 메세지 대화상자 열기
				String filePath = chooser.getSelectedFile().getPath();

				if (chooser.getSelectedFile() != null) {
					JOptionPane.showMessageDialog(MenuFrame.this, filePath + "에저장", "확인",
							JOptionPane.INFORMATION_MESSAGE);
				}

				pack();
			}

		});
		fItem3.addActionListener(this); // 파일 - 종료
		eItem1.addActionListener(this); // 편집 -입력
		// 편집 - 직접수정
		eItem2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				eItem2 = (JCheckBoxMenuItem) e.getSource();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tf.setEditable(true);
				} else {
					tf.setEditable(false);
				}
			}
		});
		hItem1.addActionListener(this); // 도움말 - 정보
	}

	// 화면 구성 함수
	private JTabbedPane buildGUI() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP); // 탭 왼쪽 정렬

		// 기본 탭
		p = new JPanel();
		l = new JLabel("이름");
		tf = new JTextField("미입력", 10);
		p.add(l);
		p.add(tf);

		// 추가 탭
		b = new JButton("테스트");

		pane.addTab("기본", p);
		pane.addTab("추가", b);

		return pane;
	}

	// 이벤트 등록 : 대화상자 - JOptionPane 사용
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == eItem1) { // 편집 - 입력
			String name = JOptionPane.showInputDialog("이름을 입력하세요");
			if (name != null) {
				tf.setText(name);
			}
		} else if (src == hItem1) { // 도움말 - 정보
			MyDialog dlg = new MyDialog(MenuFrame.this);
			dlg.setVisible(true);

		} else if (src == fItem3) { // 파일 - 종료
			Exit();
		}
	}

	// 프로그램 종료 처리를 위한 메서드
	public void Exit() {
		int n = JOptionPane.showConfirmDialog(MenuFrame.this, "정말 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
		// [확인]이 선택된 경우에만 종료되도록 처리
		if (n == JOptionPane.YES_OPTION) {
			System.exit(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else if(n == JOptionPane.NO_OPTION) {
			return;
		}
	}

	public static void main(String[] args) {
		new MenuFrame();
	}

}
