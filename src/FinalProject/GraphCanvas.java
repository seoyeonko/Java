package FinalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JComponent;

public class GraphCanvas extends JComponent {

	int total; // ǥ���� ������ siteInfo�� ��ü ����
	int[] c_num; // �� ī�װ��� ����
	int size;
	int[] arcSize; // �� ī�װ��� �����ϴ� ����
	int startAngle; // ���� ����
	Color[] color;

	public GraphCanvas() {

	}

	@Override
	public void paint(Graphics g) {
		total = 0;
		size = SiteInfoManager.v.size();
		c_num = new int[size];
		arcSize = new int[size];
		startAngle = 90;
		for (int i = 0; i < size; i++) {
			c_num[i] = 0;
		}

		Iterator it = SiteInfoManager.siteInfoList.getList().iterator();

		while (it.hasNext()) {
			SiteInfo info = (SiteInfo) it.next();

			for (int i = 0; i < size; i++) {
				SiteCategory cate = SiteInfoManager.v.get(i);
				if (info.getCateg().equals(cate.getSiteCategory())) {

					c_num[i]++;
				}
			}
			total += 1;

		}

		for (int i = 0; i < size; i++) {
			if (total == 0)
				break;
			arcSize[i] = 360 * c_num[i] / total;
		}

		color = new Color[size];
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			color[i] = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		}

		for (int i = 0; i < size; i++) {
			g.setColor(color[i]);
			g.fillArc(50, 50, 300, 300, startAngle, arcSize[i]);
			startAngle += arcSize[i];
			g.fillRect(400, 50 + (35 * i), 20, 20);
			g.setColor(Color.black);
			g.drawString(SiteInfoManager.v.get(i).getSiteCategory() + " " + c_num[i] + "��", 425, 60 + (35 * i));
		}

	}

}
