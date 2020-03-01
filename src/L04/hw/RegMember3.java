package L04.hw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegMember3 {
        JFrame frame;
        JPanel p1, p1_1, p1_2, p1_3, p1_4, p1_5;
        JPanel p2, p2_1, p2_2;
        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
        JTextField f1, f3, f4, f5, f6;
        JTextArea t1;
        JRadioButton rb1, rb2;
        JCheckBox cb1, cb2, cb3, cb4;

        public RegMember3() {
                frame = new JFrame("��� ���");

                buildGUI(); // ȭ�� ���� �Լ�
                regEventListener(); // �̺�Ʈ ��� �Լ�

                frame.setSize(400, 500);
                frame.setLocation(200, 200);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }

        // ȭ�� ���� �Լ�
        private void buildGUI() {
                frame.setLayout(new BorderLayout());
                FirstGroup(); // ù��° �׷�
                SecondGroup(); // �ι�° �׷�
        }

        // ù��° �׷�
        private JPanel FirstGroup() {
                p1 = new JPanel();
                frame.add(p1, BorderLayout.NORTH);
                p1.setLayout(new GridLayout(5, 1));

                // ������
                p1_1 = new JPanel();
                p1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_1);

                l1 = new JLabel("��                ��");
                f1 = new JTextField(8);
                l2 = new JLabel("��                ��");
                rb1 = new JRadioButton("��");
                rb2 = new JRadioButton("��");

                p1_1.add(l1);
                p1_1.add(f1);
                p1_1.add(l2);
                p1_1.add(rb1);
                p1_1.add(rb2);

                // ��Ȳ��
                p1_2 = new JPanel();
                p1_2.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_2);

                l3 = new JLabel("�ֹε�Ϲ�ȣ");
                f3 = new JTextField(6);
                l4 = new JLabel("-");
                f4 = new JTextField(7);
                p1_2.add(l3);
                p1_2.add(f3);
                p1_2.add(l4);
                p1_2.add(f4);

                // �����
                p1_3 = new JPanel();
                p1_3.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_3);

                l5 = new JLabel("��                ��");
                f5 = new JTextField(22);
                p1_3.add(l5);
                p1_3.add(f5);

                // �ʷϻ�
                p1_4 = new JPanel();
                p1_4.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_4);

                l6 = new JLabel("��      ��      ��");
                f6 = new JTextField(8);
                p1_4.add(l6);
                p1_4.add(f6);

                // �Ķ���
                p1_5 = new JPanel();
                p1_5.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_5);

                l7 = new JLabel("��                ��");
                cb1 = new JCheckBox("��ȭ");
                cb2 = new JCheckBox("���ǰ���");
                cb3 = new JCheckBox("����");
                cb4 = new JCheckBox("�");
                p1_5.add(l7);
                p1_5.add(cb1);
                p1_5.add(cb2);
                p1_5.add(cb3);
                p1_5.add(cb4);

                // �г� ��ȯ
                return p1;
        }

        // �ι��� �׷�
        private JPanel SecondGroup() {
                p2 = new JPanel();
                frame.add(p2, BorderLayout.CENTER);
                p2.setLayout(new BorderLayout());

                // ����
                p2_1 = new JPanel();
                p2.add(p2_1, BorderLayout.CENTER);

                p2_1.setLayout(new BorderLayout());
                l8 = new JLabel(" ��   ��   ��   ��");
                t1 = new JTextArea(9, 22);
                p2_1.add(l8, BorderLayout.NORTH);
                p2_1.add(t1, BorderLayout.CENTER);

                // �г� ����
                return p2;
        }

        // �̺�Ʈ ��� �Լ�
        private void regEventListener() {
                // ����
                f1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f1 = f1.getText();
                                t1.append(s_f1 + "\n");
                        }
                });

                // ����
                rb1.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                rb1 = (JRadioButton) e.getSource();
                                if (e.getStateChange() == ItemEvent.SELECTED)
                                        t1.append(rb1.getText() + "\n");
                        }
                });
                rb2.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                rb2 = (JRadioButton) e.getSource();
                                if (e.getStateChange() == ItemEvent.SELECTED)
                                        t1.append(rb2.getText() + "\n");
                        }
                });

                // �ֹε�Ϲ�ȣ
                f3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f3 = f3.getText();
                                t1.append(s_f3 + " - ");
                        }
                });
                f4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f4 = f4.getText();
                                t1.append(s_f4 + "\n");
                        }
                });

                // �ּ�
                f5.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f5 = f5.getText();
                                t1.append(s_f5 + "\n");
                        }
                });

                // �μ���
                f6.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f6 = f6.getText();
                                t1.append(s_f6 + "\n");
                        }
                });

                // ���
                cb1.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                cb1 = (JCheckBox) e.getSource();
                                if (e.getStateChange() == ItemEvent.SELECTED)
                                        t1.append(cb1.getText() + " ");
                        }
                });
                cb2.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                cb2 = (JCheckBox) e.getSource();
                                if (e.getStateChange() == ItemEvent.SELECTED)
                                        t1.append(cb2.getText() + " ");
                        }
                });
                cb3.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                cb3 = (JCheckBox) e.getSource();
                                if (e.getStateChange() == ItemEvent.SELECTED)
                                        t1.append(cb3.getText() + " ");
                        }
                });
                cb4.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                cb4 = (JCheckBox) e.getSource();
                                if (e.getStateChange() == ItemEvent.SELECTED)
                                        t1.append(cb4.getText() + " ");
                        }
                });
        }

        public static void main(String[] args) {
                new RegMember3();
        }

}