package L04;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CheckBoxTest extends JFrame { // 2�� ������� JFrame ���� : ���

	JCheckBox[] cb_list;
	JTextArea t_display;

	public CheckBoxTest() {
		super("üũ�ڽ� ����");

		buildGUI();
		regEventListener(); // �̺�Ʈ �����ʸ� ����ϴ� �Լ�

		this.setSize(500, 300); // this ���ص��ǳ� ���� ���� ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // this �Ƚᵵ �Ǵ� ���� ������
	}

	private void buildGUI() {
		this.add(createCheckBoxPanel(), BorderLayout.NORTH);
//		JTextArea t_display;
		// �� �̺�Ʈ �����ʸ� ���� ��� ���� ó��!
		t_display = new JTextArea("hello"); // âũ�⸦ �� ä�� ���̹Ƿ� ������ ũ�� ������ ���� ����
//		t_display.setEnabled(false); // textarea���� ����� �۾��� ��Ÿ������
//		t_display.setEditable(false); // textarea���� ����� �۾� -> ��, ������ �Ұ�
		this.add(new JScrollPane(t_display), BorderLayout.CENTER); // new JScrollPane : ��ũ�ѹ� ����(����, ���� ���!)

	}

	private JPanel createCheckBoxPanel() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JCheckBox[] cb_list; // ����) üũ�ڽ� 5���� ������� ���̾ƴ϶� üũ�ڽ� 5���� �����Ҽ� �ִ� ���������� 5�� ������ ��
		// �� �̺�Ʈ �����ʸ� ���� ������� ó��!
		cb_list = new JCheckBox[5];

		for (int i = 0; i < 5; i++) {
			cb_list[i] = new JCheckBox("�׸�" + (i + 1));
			p.add(cb_list[i]);

			// cb_list[i].addItemListenr(...) // �����ϳ� ���������� �ٸ� ������ ������ ��
		}
		return p;

	}

	// �̺�Ʈ�����ʸ� ����ϴ� �Լ� : üũ�ڽ����� ������ ��ư�� ������ � ó���� �� ���ΰ�?
	private void regEventListener() {
		for (JCheckBox cb : cb_list) // for-which���� : ��� �迭������ �ݺ������� ó���ϰ��� �� ��
			cb.addItemListener(handler); // add : �ϳ� �̻� �߰� �����ϴٴ� �ǹ�
	}

	ItemListener handler = new ItemListener() { // ���, ������ �����ʴ� �������̽��� ��ü ����� ���� -> �͸� ���� Ŭ����(���4)�� ���� �ذ�

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cb = (JCheckBox) e.getSource(); // �ٿ�ĳ����! nor ����
			String s = cb.getText();
			
			if (e.getStateChange() == ItemEvent.SELECTED)
				s += "����";
			else
				s += "����";
			
			t_display.append(s); // setText()�� ������ �׸��� ����
			// append()�� ������ ������ �������� ����!

		}

	};

	public static void main(String[] args) {
		new CheckBoxTest();
		System.out.println("END");
	}

}
