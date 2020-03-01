package L09;
// Data inconsistency ���̱�

class Increment {
	private int num = 0;
	
	synchronized void inc() { // synchronized Ű���� : num�� ���� �ڿ� 
		// Ű���� �߰��� ��� �������� ��Ȯ�� 3õ���� ��ϵ�
		// nor, ������ ����ġ ���� �߻�
		num++;
	}
	
	int getNum() { return num; }
}

class IncThread extends Thread {	
	private Increment cnt;
	
	IncThread(Increment inc) {
		this.cnt = inc;
	}
	
	public void run() {
		for(int i = 0; i < 10000; i++)
			for(int j = 0; j < 100; j++)
				cnt.inc();  // �Ӱ� ���� : ��Ƽ�����忡 ���� ���� �ھ��� �����ɼ� �ִ� �ڵ� ����
	}
}

class ThreadSyncError {
	public static void main(String[] args) {
		Increment i = new Increment();
		
		IncThread it1 = new IncThread(i);
		IncThread it2 = new IncThread(i);
		IncThread it3 = new IncThread(i);
		
		it1.start();
		it2.start();
		it3.start();
		
		try {
			it1.join();
			it2.join();
			it3.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(i.getNum());
	}
}