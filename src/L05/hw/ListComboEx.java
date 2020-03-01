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

   // ��� ���� ����
   private JFrame frame;
   private JPanel p1, p2;
   private JComboBox<String> cbox1, cbox2;
   private JCheckBox cb;
   private JList<String> namelist;

   // ù��° �޺��ڽ��� �������
   private String[] sbox1 = { "����", "����", "ä��" };
//   private Vector<String> v1 = new Vector<String>();

   // �ι�° �޺��ڽ��� �������
   private String[] sbasic = { "            " };
   private String[] sfruit = { "����", "����", "����", "�ڵ�", "������", "����", "������", "����" };
   private String[] svege = { "���", "����", "����", "�ñ�ġ", "����", "����", "����", "�����" };
   private Vector<String> v2 = new Vector<String>();
   private Vector<String> vnamelist = new Vector<String>();

   // ������
   public ListComboEx() {
      frame = new JFrame("ListComboEx");

      buildGUI(); // ȭ�� ���� �Լ�
      regEventListener(); // �̺�Ʈ ��� �Լ�

      frame.setSize(300, 250);
      frame.setLocation(100, 100);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   // ȭ�� ���� �Լ�
   private void buildGUI() {
      frame.setLayout(new BorderLayout());

      // �����ӿ� �г� ����
      frame.add(FirstGroup(), BorderLayout.NORTH); // p1 ����
      frame.add(SecondGroup(), BorderLayout.CENTER); // p2 ����

   }

   // GUI ù���� �׷�
   private JPanel FirstGroup() {
      p1 = new JPanel(new BorderLayout());

      //
      JPanel p1_1 = new JPanel();
      JPanel p1_2 = new JPanel();
      p1.add(p1_1, BorderLayout.WEST);
      p1.add(p1_2, BorderLayout.EAST);

      // ù��° �޺��ڽ��� ��� �߰� : ����, ���� , ä��
      cbox1 = new JComboBox<String>();
      for (int i = 0; i < sbox1.length; i++) {
         cbox1.addItem(sbox1[i]);
      }

      // �ι�° �޺��ڽ� : 1) ��ĭ, 2) ���ϵ�.., 3) ä�ҵ�..
      for (int j = 0; j < sbasic.length; j++) { // String sbasic�� Vector v2�� ����
         v2.add(sbasic[j]);
//         cbox2.addItem(v2[j]); //
      }
      cbox2 = new JComboBox<String>(v2); // ���͸� ������ �޺��ڽ�2 ����
      cbox2.setMaximumRowCount(5);

      // ��������, ��������
      cb = new JCheckBox("��Ͽ��� ����");
      p1_1.add(cbox1, new FlowLayout(FlowLayout.LEFT));
      p1_1.add(cbox2, new FlowLayout(FlowLayout.LEFT));
      p1_2.add(cb, new FlowLayout(FlowLayout.RIGHT));

      return p1;
   }

   // GUI �ι�° �׷�
   private JPanel SecondGroup() {
      p2 = new JPanel(new BorderLayout());
      namelist = new JList<String>(vnamelist); //
      p2.add(new JScrollPane(namelist), BorderLayout.CENTER);
      return p2;
   }

   // �̺�Ʈ ��� �Լ�
   private void regEventListener() {
      cbox1.addActionListener(ahandler1);
      cbox2.addActionListener(ahandler2);
      cb.addItemListener(ihandler);
   }

   // �̺�Ʈ ó�� (1) : ù��°�� �ι�° �޺��ڽ��� ����Ǵ� �̺�Ʈ ó��
   private ActionListener ahandler1 = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

         if (cbox1.getSelectedItem().equals("����")) { // ù��° �޺��ڽ����� "����" Ŭ�� -> �ι�° �޺��ڽ����� ���� ��� ��Ÿ��
            // 1. ���� ��� ��� �����
            v2.removeAllElements();
            // 2. ���Ϳ� sfruit ��� �߰�
            for (int i = 0; i < sfruit.length; i++) {
               v2.add(sfruit[i]);
            }
         } else if (cbox1.getSelectedItem().equals("ä��")) {
            // 1. ���� ��� ��� �����
            v2.removeAllElements();
            // 2. ���Ϳ� svege ��� �߰�
            for (int i = 0; i < svege.length; i++) {
               v2.add(svege[i]);
            }
         }

      }
   };

   // �̺�Ʈ ó�� (2) : �ι�° �޺��ڽ��� JList�� ����Ǵ� �̺�Ʈ ó��
   private ActionListener ahandler2 = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
         String str = (String) cbox2.getSelectedItem();
         vnamelist.add(str);
         namelist.updateUI();
      }
   };

   // �̺�Ʈ ó�� (3) : üũ�ڽ��� üũ�Ǿ� �ִ� ���� -> ����Ʈ �׸� ���ý�, �ش� �׸� ����
   private ItemListener ihandler = new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent e) {
         cb = (JCheckBox) e.getSource();
         if (e.getStateChange() == ItemEvent.SELECTED) { // cb("��Ͽ��� ����") ���� -> �׸� ����
            int i = cbox2.getSelectedIndex();
            vnamelist.remove(i);
            namelist.updateUI();
         } else { // cb("��Ͽ��� ����") ���� -> �׸� ����
            namelist.updateUI();
         }

      }

   };

   public static void main(String[] args) {
      System.out.println("end");
      new ListComboEx();
   }

}