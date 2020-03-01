package L06;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VectorTableModelEx extends JFrame {
	private JTable mTable;
	private Vector<Person> mData;

	public VectorTableModelEx() {
		super("테이블테스트4");
		mData = new Vector<Person>();

		buildGUI();

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void buildGUI() {
		// 표
		mTable = new JTable();
		mTable.setModel(new VectorTableModel(mData)); // 연동
		// 버튼
		JPanel p_button = new JPanel();
		JButton b_input = new JButton("추가");
		JButton b_output = new JButton("출력");

		b_input.addActionListener(mAddHandler);
		b_output.addActionListener(mShowHandler);

		p_button.add(b_input);
		p_button.add(b_output);

		// 프레임
		add(new JScrollPane(mTable));
		add("South", p_button); // ??
	}

	private ActionListener mAddHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ae) {
			int num = mTable.getRowCount();
			for (int i = num; i < num + 5; i++) {
				Person p = new Person("홍길동" + i, 20 + i, "010-1234-" + (1000 + i));
				mData.add(p); //
			}

//			mTable.setModel(new VectorTableModel(mData));
			mTable.updateUI();
		}

	};

	private ActionListener mShowHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowNum = mTable.getRowCount();
			int colNum = mTable.getColumnCount();

			for (int c = 0; c < colNum; c++) {
				String colName = mTable.getColumnName(c);
				System.out.print(colName + "\t");
			}
			System.out.println();

			for (int r = 0; r < rowNum; r++) {
				for (int c = 0; c < colNum; c++) {
					Object cell = mTable.getValueAt(r, c);
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
			System.out.println();
		}

	};

	public static void main(String[] args) {
		new VectorTableModelEx();
	}

}
