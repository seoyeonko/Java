package L02.hw;

import javax.swing.*;
import java.awt.*;

public class L02Ex {
        private JFrame frame;

        public L02Ex() {
                frame = new JFrame("사원 등록");

                // 화면 구성
                buildGUI();

                frame.setSize(350, 420);
                frame.setLocation(100, 100);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }

        // 화면 구성 함수
        private void buildGUI() {
                frame.setLayout(new BorderLayout()); // *

//                frame.add(FirstGroup()); // 틀림!!!! 주의할 것.
//                frame.add(SecondGroup());
                FirstGroup();
                SecondGroup();
        }

        // 첫번째 그룹
        private JPanel FirstGroup() {
                JPanel p1 = new JPanel(); // *
//                frame.setLayout(new BorderLayout()); // 아님
                frame.add(p1, BorderLayout.NORTH);  // *
                p1.setLayout(new GridLayout(5, 1));

                // 빨간색
                JPanel p1_1 = new JPanel();
                p1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_1);

                JLabel l1 = new JLabel("성                명");
                JTextField f1 = new JTextField(8);
                JLabel l2 = new JLabel("성                별");
                JTextField f2 = new JTextField(3);
                p1_1.add(l1);
                p1_1.add(f1);
                p1_1.add(l2);
                p1_1.add(f2);

                // 주황색
                JPanel p1_2 = new JPanel();
                p1_2.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_2);

                JLabel l3 = new JLabel("주민등록번호");
                JTextField f3 = new JTextField(6);
                JLabel l4 = new JLabel("-");
                JTextField f4 = new JTextField(7);
                p1_2.add(l3);
                p1_2.add(f3);
                p1_2.add(l4);
                p1_2.add(f4);

                // 노랑색
                JPanel p1_3 = new JPanel();
                p1_3.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_3);

                JLabel l5 = new JLabel("주                소");
                JTextField f5 = new JTextField(22);
                p1_3.add(l5);
                p1_3.add(f5);

                // 초록색
                JPanel p1_4 = new JPanel();
                p1_4.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_4);

                JLabel l6 = new JLabel("부      서      명");
                JTextField f6 = new JTextField(8);
                p1_4.add(l6);
                p1_4.add(f6);

                // 파란색
                JPanel p1_5 = new JPanel();
                p1_5.setLayout(new FlowLayout(FlowLayout.LEFT));
                p1.add(p1_5);

                JLabel l7 = new JLabel("취                미");
                JTextField f7 = new JTextField(22);
                p1_5.add(l7);
                p1_5.add(f7);

                // 패널 반환
                return p1;
        }

        // 두번쨰 그룹
        private JPanel SecondGroup() {
                JPanel p2 = new JPanel();  // *
//                frame.setLayout(new BorderLayout()); // 아님
                frame.add(p2, BorderLayout.CENTER); // *
                p2.setLayout(new BorderLayout());

                // 남색
                JPanel p2_1 = new JPanel();
                p2.add(p2_1, BorderLayout.CENTER);

                p2_1.setLayout(new BorderLayout());
                JLabel l1 = new JLabel(" 자   기   소   개");
                JTextArea t1 = new JTextArea(9, 22);
                p2_1.add(l1, BorderLayout.NORTH);
                p2_1.add(t1, BorderLayout.CENTER);

                // 보라색
                JPanel p2_2 = new JPanel();
                p2.add(p2_2, BorderLayout.SOUTH);
                
                p2_2.add(createButtonPanel()); // 버튼 생성 함수
                
                // 패널 리턴
                return p2;
        }

        // 추가 설명 : 보라색의 버튼 생성 함수
        private JPanel createButtonPanel() {
                JButton bSave = new JButton("저장");
                JButton bExit = new JButton("종료");

                JPanel p = new JPanel(); // 두개의 버튼을 하나의 객체로 묶음
                p.add(bSave);
                p.add(bExit);

                return p; // 두개의 버튼을 묶은 하나의 객체를 반환
        }

}