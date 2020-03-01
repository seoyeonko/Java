// ScoreTableModel.java
package L06.hw;

import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ScoreTableModel extends AbstractTableModel {
        private Vector<String> mColumnNames;
        private Vector<Student> mData;

        public ScoreTableModel(Vector<Student> v) {
                // 헤더 설정
                String[] s = { "이름", "C", "JAVA", "C++", "총점", "평균" };
                mColumnNames = new Vector<String>(s.length);

                for (int c = 0; c < s.length; c++) {
                        mColumnNames.addElement(s[c]);
                }

                // 셀 데이터 설정
                mData = v;
        }

        // 테이블의 열의 수 [필수]
        @Override
        public int getColumnCount() {
                return mColumnNames.size();
        }

        // 테이블의 행의 수 [필수]
        @Override
        public int getRowCount() {
                return mData.size();
        }

        // 실제 데이터 추출 [필수]
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

        // 테이블의 타이틀명 표시
        @Override
        public String getColumnName(int columnIndex) {
                return (String) mColumnNames.get(columnIndex);
        }

        // 테이블 편집 가능하게 함
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) { // 기본적인 테이블 모델에서는 수정을 불가능
//                return true; // ri, ci 지정 가능하지만 일단은 true
                // 수정은 가능하나 변환은 안돼 -> setValueAt 함수

                // 총점, 평균은 수정 불가능하도록 하려면? (0번 column 수정 불가능는 조건 사용)
                return columnIndex == getColumnCount()-2 ? false : true; // 삼항 연산자
        }

        // 테이블의 필드값 입력으로 값이 변함
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) { // 특정 번째에 어떠한 값을 설정하고자 하는 함수
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

