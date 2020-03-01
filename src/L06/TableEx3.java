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

public class TableEx3 extends JFrame implements ActionListener {
	private JTable mTable;

	public TableEx3() {
		super("테이블 예제1");

		buildGUI();

		setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void buildGUI() {
		this.add(createTablePanel(), BorderLayout.CENTER);
		this.add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTablePanel() {
		JPanel p = new JPanel(new BorderLayout());

// 		mTable = new JTable(new MyTableModel()); // 인수로 MyTableModel()객체
		mTable = new JTable(new MyTableModel2()); // 인수로 MyTableModel2()객체
		p.add(new JScrollPane(mTable));

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
		new TableEx3();
	}

}
