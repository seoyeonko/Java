package L06;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	String[] columnNames = { "이름", "나이", "성별" };
	Object[][] data = { { "고주몽", 22, "남" }, { "소서노", 20, "여" } };

	// 필수 재정의 함수들...
	@Override
	public int getColumnCount() { // 몇 개의 col이 있는지 반환해주는 함수
		return columnNames.length;
	}

	@Override
	public int getRowCount() { // 몇 개의 data가 있는지 반환(타이틀 부분 제외)
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex]; // 각 셀에 출력할 내용을 반환
	}

	// 추가 재정의 함수들...
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) { // 기본적인 테이블 모델에서는 수정을 불가능
//		return true; // ri, ci 지정 가능하지만 일단은 true
//		// 수정은 가능하나 변환은 안돼 -> setValueAt 함수
		
		// 이름은 수정 불가능하도록 하려면? (0번 column 수정 불가능는 조건 사용)
		return columnIndex == 0 ? false : true; // 삼항 연산자
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) { // 특정 번째에 어떠한 값을 설정하고자 하는 함수
		data[rowIndex][columnIndex] = aValue;
	}

}
