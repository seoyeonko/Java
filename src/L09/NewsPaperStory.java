package L09;

// ���� ���� �ʿ伺 : ����ȭ�� �̿��� ��������
// ���� ������ null Ȥ�� "�ڹ��� ���Ⱑ �߁����ϴ�"�� ��µǴµ�
// ���� ������ �׻� ���ڿ��� ��µ�
class NewsPaper {
	private String todayNews;
	private boolean isTodayNews = false; // ���� ������ ���������� �ִ��� ���θ� �����ϴ� ��

	void setTodayNews(String news) { // 1) writer�� ���� �����ϰ�
		// isTodayNews = true; // �� ��ġ�� �Ҿ����� �ٷ� �Ʒ� �ڵ���̿��� �����尡 ����ٸ�? ... x
		todayNews = news;
		isTodayNews = true; // ������ ������ ���� ��������� ǥ�� (�ٷ� ���ڵ��� �ϴܿ� �ۼ��ϴ� ���� ������ ��ġ)

		// �ڰ� �ִ� ���� ����� ��
		synchronized (this) {
			// "�ڽ�"�� �ٸ� ���� "����� ��"!!
			notifyAll(); // ��� �����ڸ� ����� ó���� �ϱ� ���� notifyAll() �Լ�
		}
	}

	// reader��ü�� ���� �����Ǿ��� ��,
	String getTodayNews() { // 2) reader�� �۵��ϵ���
		if (isTodayNews == false) { // ���� ������ �ȵǾ��ٸ�
			// ������ �����Ǹ� ������!
			synchronized (this) { // ����ȭ ��
				try {
					wait(); // this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} // �����ȴٸ�
		return todayNews; // ���� ���� ��ȯ
	}

	// 1)->2) ���� ���ϴ� ������� �����尡 ����ǵ��� �ϴ� �� : ��������
}

class NewsWriter extends Thread {
	private NewsPaper paper;

	NewsWriter(NewsPaper paper) {
		this.paper = paper;
	}

	public void run() {
		paper.setTodayNews("�ڹ��� ���Ⱑ �߰̽��ϴ�.");
	}
}

class NewsReader extends Thread {
	private NewsPaper paper;

	NewsReader(NewsPaper paper) {
		this.paper = paper;
	}

	public void run() { // ������ �����Ǿ����� ���������� ó���Ǿ����� ���� �ʿ���
		System.out.println("������ ����: " + paper.getTodayNews());
	}
}

class NewsPaperStory { // public Ŭ������ �ξ ��� x
	public static void main(String[] args) {
		NewsPaper paper = new NewsPaper();
		NewsReader reader1 = new NewsReader(paper);
		NewsReader reader2 = new NewsReader(paper);
		NewsWriter writer = new NewsWriter(paper);

		try { // �������� ó���� ������ ���� ��ٸ��� ��ħ..?
			reader1.start();
			reader2.start();

			Thread.sleep(1000);
			writer.start();

			reader1.join();
			reader2.join();
			writer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}