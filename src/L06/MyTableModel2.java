package L06;

import javax.swing.table.AbstractTableModel;

public class MyTableModel2 extends AbstractTableModel {

	String[] columnNames = { "이름", "나이", "성별" };
	Object[][] data = { { "고주몽", "소서노" }, { 22, 20 }, { "남", "여" } }; // 항목별로 분류

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data[0].length; //
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[columnIndex][rowIndex]; // 보여질 테이블과 가지고있는 테이블의 인덱스 값이 대칭관계임
	}

}
