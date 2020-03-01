// ScoreTableModel.java
package L06.hw;

import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ScoreTableModel extends AbstractTableModel {
        private Vector<String> mColumnNames;
        private Vector<Student> mData;

        public ScoreTableModel(Vector<Student> v) {
                // ��� ����
                String[] s = { "�̸�", "C", "JAVA", "C++", "����", "���" };
                mColumnNames = new Vector<String>(s.length);

                for (int c = 0; c < s.length; c++) {
                        mColumnNames.addElement(s[c]);
                }

                // �� ������ ����
                mData = v;
        }

        // ���̺��� ���� �� [�ʼ�]
        @Override
        public int getColumnCount() {
                return mColumnNames.size();
        }

        // ���̺��� ���� �� [�ʼ�]
        @Override
        public int getRowCount() {
                return mData.size();
        }

        // ���� ������ ���� [�ʼ�]
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                Student st = mData.get(rowIndex);

                switch (columnIndex) {
                case 0:
                        return st.getmName();
                case 1:
                        return st.getmC();
                case 2:
                        return st.getmJAVA();
                case 3:
                        return st.getmCpp();
                case 4:
                        return st.getSum();
                case 5:
                        return st.getAvg();
                }

                return null;
        }

        // ���̺��� Ÿ��Ʋ�� ǥ��
        @Override
        public String getColumnName(int columnIndex) {
                return (String) mColumnNames.get(columnIndex);
        }

        // ���̺� ���� �����ϰ� ��
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) { // �⺻���� ���̺� �𵨿����� ������ �Ұ���
//                return true; // ri, ci ���� ���������� �ϴ��� true
                // ������ �����ϳ� ��ȯ�� �ȵ� -> setValueAt �Լ�

                // ����, ����� ���� �Ұ����ϵ��� �Ϸ���? (0�� column ���� �Ұ��ɴ� ���� ���)
                return columnIndex == getColumnCount()-2 ? false : true; // ���� ������
        }

        // ���̺��� �ʵ尪 �Է����� ���� ����
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) { // Ư�� ��°�� ��� ���� �����ϰ��� �ϴ� �Լ�
                Student st = mData.get(rowIndex);

                switch (columnIndex) {
                case 0:
                        st.setmName((String) aValue);
                case 1:
                        st.setmC(Integer.valueOf((String) aValue));
                case 2:
                        st.setmJAVA(Integer.valueOf((String) aValue));
                case 3:
                        st.setmCpp(Integer.valueOf((String) aValue));
                case 4:
                        st.setSum(Integer.valueOf((String) aValue));
                case 5:
                        st.setAvg(Float.valueOf((String) aValue));
                }

//                rowVector.setElementAt(aValue, columnIndex);
                fireTableCellUpdated(rowIndex, columnIndex);

        }

}

