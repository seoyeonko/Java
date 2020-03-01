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
                frame = new JFrame("사원 등록");

                buildGUI(); // 화면 구성 함수
                regEventListener(); // 이벤트 등록 함수

                frame.setSize(400, 500);
                frame.setLocation(200, 200);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }

        // 화면 구성 함수
        private void buildGUI() {
                frame.setLayout(new BorderLayout());
                FirstGroup(); // 첫번째 그룹
                SecondGroup(); // 두번째 그룹
        }

        // 첫번째 그룹
        private JPanel FirstGroup() {
                p1 = new JPanel();
                frame.add(p1, BorderLayout.NORTH);
                p1.setLayout(new GridLayout(5, 1));

                // 빨간색
                p1_1 = new JPanel();
                p1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_1);

                l1 = new JLabel("성                명");
                f1 = new JTextField(8);
                l2 = new JLabel("성                별");
                rb1 = new JRadioButton("남");
                rb2 = new JRadioButton("여");

                p1_1.add(l1);
                p1_1.add(f1);
                p1_1.add(l2);
                p1_1.add(rb1);
                p1_1.add(rb2);

                // 주황색
                p1_2 = new JPanel();
                p1_2.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_2);

                l3 = new JLabel("주민등록번호");
                f3 = new JTextField(6);
                l4 = new JLabel("-");
                f4 = new JTextField(7);
                p1_2.add(l3);
                p1_2.add(f3);
                p1_2.add(l4);
                p1_2.add(f4);

                // 노랑색
                p1_3 = new JPanel();
                p1_3.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_3);

                l5 = new JLabel("주                소");
                f5 = new JTextField(22);
                p1_3.add(l5);
                p1_3.add(f5);

                // 초록색
                p1_4 = new JPanel();
                p1_4.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_4);

                l6 = new JLabel("부      서      명");
                f6 = new JTextField(8);
                p1_4.add(l6);
                p1_4.add(f6);

                // 파란색
                p1_5 = new JPanel();
                p1_5.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_5);

                l7 = new JLabel("취                미");
                cb1 = new JCheckBox("영화");
                cb2 = new JCheckBox("음악감상");
                cb3 = new JCheckBox("사진");
                cb4 = new JCheckBox("운동");
                p1_5.add(l7);
                p1_5.add(cb1);
                p1_5.add(cb2);
                p1_5.add(cb3);
                p1_5.add(cb4);

                // 패널 반환
                return p1;
        }

        // 두번쨰 그룹
        private JPanel SecondGroup() {
                p2 = new JPanel();
                frame.add(p2, BorderLayout.CENTER);
                p2.setLayout(new BorderLayout());

                // 남색
                p2_1 = new JPanel();
                p2.add(p2_1, BorderLayout.CENTER);

                p2_1.setLayout(new BorderLayout());
                l8 = new JLabel(" 자   기   소   개");
                t1 = new JTextArea(9, 22);
                p2_1.add(l8, BorderLayout.NORTH);
                p2_1.add(t1, BorderLayout.CENTER);

                // 패널 리턴
                return p2;
        }

        // 이벤트 등록 함수
        private void regEventListener() {
                // 성명
                f1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f1 = f1.getText();
                                t1.append(s_f1 + "\n");
                        }
                });

                // 성별
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

                // 주민등록번호
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

                // 주소
                f5.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f5 = f5.getText();
                                t1.append(s_f5 + "\n");
                        }
                });

                // 부서명
                f6.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String s_f6 = f6.getText();
                                t1.append(s_f6 + "\n");
                        }
                });

                // 취미
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