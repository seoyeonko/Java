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

        // �Ӽ�
        private JFrame frame;
        private JTable mTable;
        private Vector<Student> mData;
        ScoreTableModel model;
        Object obj;
        // JTop ��� ����
        JPanel pTop;
        JPanel p1;
        JTextField t1;
        JButton bMake;

        // pTabel ��� ����
        JPanel pTable;

        // pButtom ��� ����
        JPanel pButtom;
        JPanel p3;
        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;

        // ������
        public JScoreTableFrame() {
                frame = new JFrame("����ó��");
                mData = new Vector<Student>();

                buildGUI();

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }

        // ȭ�� ���� �Լ�
        private void buildGUI() {
//                mTable = new JTable();
//                mTable.setModel(new ScoreTableModel(mData)); // ����

                frame.add(createTopPanel(), BorderLayout.NORTH);
                frame.add(createTablePanel(), BorderLayout.CENTER);
                frame.add(createButtomPanel(), BorderLayout.SOUTH);
        }

        // ��� �Է�â
        private JPanel createTopPanel() {
                pTop = new JPanel(new BorderLayout());
                p1 = new JPanel(new FlowLayout());

                JLabel l1 = new JLabel("�ο��� : ");
                t1 = new JTextField(10);
                JLabel l2 = new JLabel("��");
                bMake = new JButton("�����");

                p1.add(l1);
                p1.add(t1);
                p1.add(l2);
                p1.add(bMake);

                pTop.add(p1);

                // [�����] ��ư Ŭ���� �Է��� �ο��� ��ŭ �� ���� ���� ���̺� �� ���� �� ����
                bMake.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // 1. �Էµ� �л� �� �ޱ�
                                String n = t1.getText(); // �Էµ� ���� �޾Ƽ�
                                t1.setText(""); // �� �ؽ�Ʈ �ʵ�� �����
                                int num = Integer.parseInt(n); // ���ڿ� -> ���� ��ȯ

                                // 2. �л� ����ŭ �� ���� ����
                                for (int i = 0; i < num; i++) {
                                        Student st = new Student("", 0, 0, 0, 0, 0);
                                        mData.add(st);
                                }

//                                mTable.setModel(new ScoreTableModel(mData));
                                mTable.updateUI();
                                bMake.setEnabled(false); // 3. ����� ��ư ��Ȱ��ȭ
                        }
                });

                return pTop;
        }

        // �߾� ���̺�
        private JPanel createTablePanel() {
                pTable = new JPanel(new BorderLayout());

                model = new ScoreTableModel(mData);
                mTable = new JTable(model);
                mTable.setRowHeight(30);
                mTable.setEnabled(true);

                // ���� �����ϰ� �ϱ�
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

        // �ϴ� ��ư
        private JPanel createButtomPanel() {
                pButtom = new JPanel(new BorderLayout());
                p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                b1 = new JButton("�߰�");
                b2 = new JButton("���");
                b3 = new JButton("����");
                b4 = new JButton("�ʱ�ȭ");

                p3.add(b1);
                p3.add(b2);
                p3.add(b3);
                p3.add(b4);

                pButtom.add(p3);

                // �⺻ ����
                // 1. [�߰�] ��ư Ŭ���� ǥ�� �� �� �߰�
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

                // 2. [���] ��ư Ŭ���� ǥ ���� �ֿܼ� ���
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

                // 3. [����] ��ư Ŭ���� �� ���ڵ��� ��� ���
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

                // 4. [�ʱ�ȭ] ��ư Ŭ���� ǥ ���� ����
                b4.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                mData.removeAllElements();
                                mTable.updateUI();
                                bMake.setEnabled(true); // ����� ��ư Ȱ��ȭ
                        }
                });

                return pButtom;
        }

        public static void main(String[] args) {
                new JScoreTableFrame();
        }

}