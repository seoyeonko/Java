
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {
   static JTextField f;

   public LoginDialog(JFrame f) {
      super(f, "사이트 분류 관리", true);
      buildGUI();

      pack();

      setLocation((f.getSize().width / 2) - this.getSize().width / 2 + 50,
            (f.getSize().height / 2) - this.getSize().height / 2 + 100);

      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
   }

   private void buildGUI() {
      JPanel panel = new JPanel(new GridLayout(0, 1));
      JPanel p1 = new JPanel();

      p1.add(new JLabel("비밀번호 "));

      f = new JTextField(10);
      p1.add(f);

      JPanel p2 = new JPanel();
      JButton login = new JButton("로그인");
      JButton exit = new JButton("종료");
      p2.add(login);
      p2.add(exit);

      panel.add(p1);
      panel.add(p2);

      panel.setBorder(BorderFactory.createTitledBorder("안녕하세요?"));
      add(panel);

      login.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
//            String s = f.getText();
            if (passCheck())
               setVisible(false);
            else {
               f.setText("");
            }

         }

      });

      exit.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }

      });
   }

   public String getText() {
      String s = f.getText();
      if (s != null)
         return s;
      else
         return null;
   }
   
   // 파일 입출력 함수
   private boolean passCheck() {
      File file = new File("./passCheck.txt");
      if (file.exists()) {
         try {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String sLine = null;
            try {
               sLine = inFile.readLine();
               if (sLine.equals(f.getText()))
                  return true;
               else if (!sLine.equals(f.getText()))
                  return false;
            } catch (IOException e) {
               e.printStackTrace();
            }

         } catch (FileNotFoundException e) {
            e.printStackTrace();
         }
      }
      return false;
   }
}