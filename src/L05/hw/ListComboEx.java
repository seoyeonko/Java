package L05.hw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListComboEx {

   // 멤버 변수 선언
   private JFrame frame;
   private JPanel p1, p2;
   private JComboBox<String> cbox1, cbox2;
   private JCheckBox cb;
   private JList<String> namelist;

   // 첫번째 콤보박스의 멤버변수
   private String[] sbox1 = { "선택", "과일", "채소" };
//   private Vector<String> v1 = new Vector<String>();

   // 두번째 콤보박스의 멤버변수
   private String[] sbasic = { "            " };
   private String[] sfruit = { "참외", "수박", "포도", "자두", "복숭아", "딸기", "오렌지", "망고" };
   private String[] svege = { "당근", "배추", "상추", "시금치", "양파", "버섯", "대파", "양배추" };
   private Vector<String> v2 = new Vector<String>();
   private Vector<String> vnamelist = new Vector<String>();

   // 생성자
   public ListComboEx() {
      frame = new JFrame("ListComboEx");

      buildGUI(); // 화면 구성 함수
      regEventListener(); // 이벤트 등록 함수

      frame.setSize(300, 250);
      frame.setLocation(100, 100);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   // 화면 구성 함수
   private void buildGUI() {
      frame.setLayout(new BorderLayout());

      // 프레임에 패널 부착
      frame.add(FirstGroup(), BorderLayout.NORTH); // p1 부착
      frame.add(SecondGroup(), BorderLayout.CENTER); // p2 부착

   }

   // GUI 첫번재 그룹
   private JPanel FirstGroup() {
      p1 = new JPanel(new BorderLayout());

      //
      JPanel p1_1 = new JPanel();
      JPanel p1_2 = new JPanel();
      p1.add(p1_1, BorderLayout.WEST);
      p1.add(p1_2, BorderLayout.EAST);

      // 첫번째 콤보박스에 요소 추가 : 선택, 과일 , 채소
      cbox1 = new JComboBox<String>();
      for (int i = 0; i < sbox1.length; i++) {
         cbox1.addItem(sbox1[i]);
      }

      // 두번째 콤보박스 : 1) 빈칸, 2) 과일들.., 3) 채소들..
      for (int j = 0; j < sbasic.length; j++) { // String sbasic을 Vector v2에 저장
         v2.add(sbasic[j]);
//         cbox2.addItem(v2[j]); //
      }
      cbox2 = new JComboBox<String>(v2); // 벡터를 가지는 콤보박스2 생성
      cbox2.setMaximumRowCount(5);

      // 좌측정렬, 우측정렬
      cb = new JCheckBox("목록에서 삭제");
      p1_1.add(cbox1, new FlowLayout(FlowLayout.LEFT));
      p1_1.add(cbox2, new FlowLayout(FlowLayout.LEFT));
      p1_2.add(cb, new FlowLayout(FlowLayout.RIGHT));

      return p1;
   }

   // GUI 두번째 그룹
   private JPanel SecondGroup() {
      p2 = new JPanel(new BorderLayout());
      namelist = new JList<String>(vnamelist); //
      p2.add(new JScrollPane(namelist), BorderLayout.CENTER);
      return p2;
   }

   // 이벤트 등록 함수
   private void regEventListener() {
      cbox1.addActionListener(ahandler1);
      cbox2.addActionListener(ahandler2);
      cb.addItemListener(ihandler);
   }

   // 이벤트 처리 (1) : 첫번째와 두번째 콤보박스와 연결되는 이벤트 처리
   private ActionListener ahandler1 = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

         if (cbox1.getSelectedItem().equals("과일")) { // 첫번째 콤보박스에서 "과일" 클릭 -> 두번째 콤보박스에서 과일 목록 나타남
            // 1. 벡터 모든 요소 지우기
            v2.removeAllElements();
            // 2. 벡터에 sfruit 요소 추가
            for (int i = 0; i < sfruit.length; i++) {
               v2.add(sfruit[i]);
            }
         } else if (cbox1.getSelectedItem().equals("채소")) {
            // 1. 벡터 모든 요소 지우기
            v2.removeAllElements();
            // 2. 벡터에 svege 요소 추가
            for (int i = 0; i < svege.length; i++) {
               v2.add(svege[i]);
            }
         }

      }
   };

   // 이벤트 처리 (2) : 두번째 콤보박스와 JList가 연결되는 이벤트 처리
   private ActionListener ahandler2 = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
         String str = (String) cbox2.getSelectedItem();
         vnamelist.add(str);
         namelist.updateUI();
      }
   };

   // 이벤트 처리 (3) : 체크박스가 체크되어 있는 동안 -> 리스트 항목 선택시, 해당 항목 삭제
   private ItemListener ihandler = new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent e) {
         cb = (JCheckBox) e.getSource();
         if (e.getStateChange() == ItemEvent.SELECTED) { // cb("목록에서 삭제") 선택 -> 항목 삭제
            int i = cbox2.getSelectedIndex();
            vnamelist.remove(i);
            namelist.updateUI();
         } else { // cb("목록에서 삭제") 해제 -> 항목 유지
            namelist.updateUI();
         }

      }

   };

   public static void main(String[] args) {
      System.out.println("end");
      new ListComboEx();
   }

}