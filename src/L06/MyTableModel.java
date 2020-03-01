package L06;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	String[] columnNames = { "�̸�", "����", "����" };
	Object[][] data = { { "���ָ�", 22, "��" }, { "�Ҽ���", 20, "��" } };

	// �ʼ� ������ �Լ���...
	@Override
	public int getColumnCount() { // �� ���� col�� �ִ��� ��ȯ���ִ� �Լ�
		return columnNames.length;
	}

	@Override
	public int getRowCount() { // �� ���� data�� �ִ��� ��ȯ(Ÿ��Ʋ �κ� ����)
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex]; // �� ���� ����� ������ ��ȯ
	}

	// �߰� ������ �Լ���...
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) { // �⺻���� ���̺� �𵨿����� ������ �Ұ���
//		return true; // ri, ci ���� ���������� �ϴ��� true
//		// ������ �����ϳ� ��ȯ�� �ȵ� -> setValueAt �Լ�
		
		// �̸��� ���� �Ұ����ϵ��� �Ϸ���? (0�� column ���� �Ұ��ɴ� ���� ���)
		return columnIndex == 0 ? false : true; // ���� ������
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) { // Ư�� ��°�� ��� ���� �����ϰ��� �ϴ� �Լ�
		data[rowIndex][columnIndex] = aValue;
	}

}
