package L06;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableEx1 extends JFrame implements ActionListener {
	private JTable mTable;

	public TableEx1() {
		super("테이블 예제1");

		buildGUI();

		setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void buildGUI() {
		this.add(createTablePanel(), BorderLayout.CENTER); // this는 frame 나 자신
		this.add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTablePanel() {
		JPanel p = new JPanel(new BorderLayout());
		// Q1. 테이블이 꽉채워나오지 않음?
		// A1. 테이블은 center영역에 꽉채워졌으나 테이블이 더해진 JPanel은 flowlayout이 기본값이기에 꽉채워 나오지 않음
		// 따라서, JPanel도 borderLayout설정 해야해

		String[] columnNames = { "이름", "나이", "성별" };
		Object[][] data = { { new String("고주몽"), new Integer(22), "남" }, { "소서노", 20, "여" } }; // wrapper 클래스

		mTable = new JTable(data, columnNames); // 방법1: 배열 인수
		p.add(new JScrollPane(mTable));
		// Q2. columnNames는 어디로?
		// A2. 스크롤 생성 외에도 columnNames 생성을 위해 JScrollPane과 연결은 반드시 필요

		mTable.setRowHeight(30); // 높이값 픽셀 설정
		return p;
	}

	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new BorderLayout());
		JButton b = new JButton("출력");
		p.add(b);
		b.addActionListener(this);
		return p;
	}

	// 이벤트 처리 : 출력버튼 누르면 콘솔 창에 해당 내용 출력하기 (방법1 : 현재 클래스 사용)
	
	// 방법1
//	public void actionPerformed(ActionEvent e) {
//		int rowNum = mTable.getRowCount();
//		int colNum = mTable.getColumnCount();
//
//		for (int c = 0; c < colNum; c++) {
//			String colName = mTable.getColumnName(c);
//			System.out.print(colName + "\t");
//		}
//		System.out.println();
//
//		for (int r = 0; r < rowNum; r++) {
//			for (int c = 0; c < colNum; c++) {
//				Object cell = mTable.getValueAt(r, c);
//				System.out.print(cell + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	
	// 방법2
	public void actionPerformed(ActionEvent e) {
		TableModel model = mTable.getModel();
		
		int rowNum = model.getRowCount();
		int colNum = model.getColumnCount();
		
		for (int c = 0; c < colNum; c++) {
			String colName = model.getColumnName(c);
			System.out.print(colName + "\t");
		}
		System.out.println();

		for (int r = 0; r < rowNum; r++) {
			for (int c = 0; c < colNum; c++) {
				Object cell = model.getValueAt(r, c);
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
		
	
	public static void main(String[] args) {
		new TableEx1();
	}

}
