package L02.hw;

import javax.swing.*;
import java.awt.*;

public class L02Ex {
        private JFrame frame;

        public L02Ex() {
                frame = new JFrame("��� ���");

                // ȭ�� ����
                buildGUI();

                frame.setSize(350, 420);
                frame.setLocation(100, 100);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }

        // ȭ�� ���� �Լ�
        private void buildGUI() {
                frame.setLayout(new BorderLayout()); // *

//                frame.add(FirstGroup()); // Ʋ��!!!! ������ ��.
//                frame.add(SecondGroup());
                FirstGroup();
                SecondGroup();
        }

        // ù��° �׷�
        private JPanel FirstGroup() {
                JPanel p1 = new JPanel(); // *
//                frame.setLayout(new BorderLayout()); // �ƴ�
                frame.add(p1, BorderLayout.NORTH);  // *
                p1.setLayout(new GridLayout(5, 1));

                // ������
                JPanel p1_1 = new JPanel();
                p1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_1);

                JLabel l1 = new JLabel("��                ��");
                JTextField f1 = new JTextField(8);
                JLabel l2 = new JLabel("��                ��");
                JTextField f2 = new JTextField(3);
                p1_1.add(l1);
                p1_1.add(f1);
                p1_1.add(l2);
                p1_1.add(f2);

                // ��Ȳ��
                JPanel p1_2 = new JPanel();
                p1_2.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_2);

                JLabel l3 = new JLabel("�ֹε�Ϲ�ȣ");
                JTextField f3 = new JTextField(6);
                JLabel l4 = new JLabel("-");
                JTextField f4 = new JTextField(7);
                p1_2.add(l3);
                p1_2.add(f3);
                p1_2.add(l4);
                p1_2.add(f4);

                // �����
                JPanel p1_3 = new JPanel();
                p1_3.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_3);

                JLabel l5 = new JLabel("��                ��");
                JTextField f5 = new JTextField(22);
                p1_3.add(l5);
                p1_3.add(f5);

                // �ʷϻ�
                JPanel p1_4 = new JPanel();
                p1_4.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_4);

                JLabel l6 = new JLabel("��      ��      ��");
                JTextField f6 = new JTextField(8);
                p1_4.add(l6);
                p1_4.add(f6);

                // �Ķ���
                JPanel p1_5 = new JPanel();
                p1_5.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_5);

                JLabel l7 = new JLabel("��                ��");
                JTextField f7 = new JTextField(22);
                p1_5.add(l7);
                p1_5.add(f7);

                // �г� ��ȯ
                return p1;
        }

        // �ι��� �׷�
        private JPanel SecondGroup() {
                JPanel p2 = new JPanel();  // *
//                frame.setLayout(new BorderLayout()); // �ƴ�
                frame.add(p2, BorderLayout.CENTER); // *
                p2.setLayout(new BorderLayout());

                // ����
                JPanel p2_1 = new JPanel();
                p2.add(p2_1, BorderLayout.CENTER);

                p2_1.setLayout(new BorderLayout());
                JLabel l1 = new JLabel(" ��   ��   ��   ��");
                JTextArea t1 = new JTextArea(9, 22);
                p2_1.add(l1, BorderLayout.NORTH);
                p2_1.add(t1, BorderLayout.CENTER);

                // �����
                JPanel p2_2 = new JPanel();
                p2.add(p2_2, BorderLayout.SOUTH);
                
                p2_2.add(createButtonPanel()); // ��ư ���� �Լ�
                
                // �г� ����
                return p2;
        }

        // �߰� ���� : ������� ��ư ���� �Լ�
        private JPanel createButtonPanel() {
                JButton bSave = new JButton("����");
                JButton bExit = new JButton("����");

                JPanel p = new JPanel(); // �ΰ��� ��ư�� �ϳ��� ��ü�� ����
                p.add(bSave);
                p.add(bExit);

                return p; // �ΰ��� ��ư�� ���� �ϳ��� ��ü�� ��ȯ
        }

}