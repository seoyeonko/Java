package L06;

import javax.swing.table.AbstractTableModel;

public class MyTableModel2 extends AbstractTableModel {

	String[] columnNames = { "�̸�", "����", "����" };
	Object[][] data = { { "���ָ�", "�Ҽ���" }, { 22, 20 }, { "��", "��" } }; // �׸񺰷� �з�

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
		return data[columnIndex][rowIndex]; // ������ ���̺�� �������ִ� ���̺��� �ε��� ���� ��Ī������
	}

}
