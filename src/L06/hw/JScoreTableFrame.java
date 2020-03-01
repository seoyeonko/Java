// JScoreTableFrame.java
package L06.hw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JScoreTableFrame {

        // 속성
        private JFrame frame;
        private JTable mTable;
        private Vector<Student> mData;
        ScoreTableModel model;
        Object obj;
        // JTop 멤버 변수
        JPanel pTop;
        JPanel p1;
        JTextField t1;
        JButton bMake;

        // pTabel 멤버 변수
        JPanel pTable;

        // pButtom 멤버 변수
        JPanel pButtom;
        JPanel p3;
        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;

        // 생성자
        public JScoreTableFrame() {
                frame = new JFrame("성적처리");
                mData = new Vector<Student>();

                buildGUI();

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }

        // 화면 구성 함수
        private void buildGUI() {
//                mTable = new JTable();
//                mTable.setModel(new ScoreTableModel(mData)); // 연동

                frame.add(createTopPanel(), BorderLayout.NORTH);
                frame.add(createTablePanel(), BorderLayout.CENTER);
                frame.add(createButtomPanel(), BorderLayout.SOUTH);
        }

        // 상단 입력창
        private JPanel createTopPanel() {
                pTop = new JPanel(new BorderLayout());
                p1 = new JPanel(new FlowLayout());

                JLabel l1 = new JLabel("인원수 : ");
                t1 = new JTextField(10);
                JLabel l2 = new JLabel("명");
                bMake = new JButton("만들기");

                p1.add(l1);
                p1.add(t1);
                p1.add(l2);
                p1.add(bMake);

                pTop.add(p1);

                // [만들기] 버튼 클릭시 입력한 인원수 만큼 빈 셀을 갖는 테이블 모델 생성 후 연결
                bMake.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // 1. 입력된 학생 수 받기
                                String n = t1.getText(); // 입력된 값을 받아서
                                t1.setText(""); // 빈 텍스트 필드로 만들고
                                int num = Integer.parseInt(n); // 문자열 -> 정수 변환

                                // 2. 학생 수만큼 빈 셀을 생성
                                for (int i = 0; i < num; i++) {
                                        Student st = new Student("", 0, 0, 0, 0, 0);
                                        mData.add(st);
                                }

//                                mTable.setModel(new ScoreTableModel(mData));
                                mTable.updateUI();
                                bMake.setEnabled(false); // 3. 만들기 버튼 비활성화
                        }
                });

                return pTop;
        }

        // 중앙 테이블
        private JPanel createTablePanel() {
                pTable = new JPanel(new BorderLayout());

                model = new ScoreTableModel(mData);
                mTable = new JTable(model);
                mTable.setRowHeight(30);
                mTable.setEnabled(true);

                // 편집 가능하게 하기
                int rowNum = mTable.getRowCount();
                int colNum = mTable.getColumnCount();
                for (int r = 0; r < rowNum; r++) {
                        for (int c = 0; c < colNum - 2; c++) {
                                mTable.isCellEditable(r, c); //
                                mTable.setValueAt(mData, r, c);
                        }
                }

                pTable.add(new JScrollPane(mTable));
                return pTable;
        }

        // 하단 버튼
        private JPanel createButtomPanel() {
                pButtom = new JPanel(new BorderLayout());
                p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                b1 = new JButton("추가");
                b2 = new JButton("출력");
                b3 = new JButton("연산");
                b4 = new JButton("초기화");

                p3.add(b1);
                p3.add(b2);
                p3.add(b3);
                p3.add(b4);

                pButtom.add(p3);

                // 기본 동작
                // 1. [추가] 버튼 클릭시 표에 빈 줄 추가
                b1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int num = mTable.getRowCount();
                                for (int i = num; i < num + 1; i++) {
                                        Student st = new Student("", 0, 0, 0, 0, 0);
                                        mData.add(st);
                                }
//                                mTable.setModel(new ScoreTableModel(mData));
                                mTable.updateUI();
                        }

                });

                // 2. [출력] 버튼 클릭시 표 내용 콘솔에 출력
                b2.addActionListener(new ActionListener() {

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
                });

                // 3. [연산] 버튼 클릭시 각 레코드의 평균 계산
                b3.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int r = mTable.getRowCount();
//                                int c = mTable.getColumnCount();
                                for (int i = 0; i < r; i++) {
                                        Student st = mData.get(r);
                                        int avg = st.getSum() / 3;
                                        st.setAvg(avg);
                                }
                                mTable.updateUI();
                        }

                });

                // 4. [초기화] 버튼 클릭시 표 내용 삭제
                b4.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                mData.removeAllElements();
                                mTable.updateUI();
                                bMake.setEnabled(true); // 만들기 버튼 활성화
                        }
                });

                return pButtom;
        }

        public static void main(String[] args) {
                new JScoreTableFrame();
        }

}