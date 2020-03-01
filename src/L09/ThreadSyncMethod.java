package L09;
// cal.add() �������� �� cal.sub()�� ������� �ʾƾ� ��(�� ���� �����ؾ� ��)

class Calculator {
	private int opCnt = 0; // ���� ����

	// ���1) �Լ��� ����ȭ
	// ���� �ΰ��� �����尡 ���ÿ� �������� ���ƾ��� �κ��� opCnt++ ��ü�ε� �Լ��� ��°�� ����ȭ�ϴ� ���� �ð��� ������Ű�� �Ͽ� �Ұ�
	// -> ���� ��Ұ� �ʿ�
//	synchronized int add(int n1, int n2) { // ����ȭ �޼��� ����
//		opCnt++;
//		return n1 + n2;
//	}
//	
//	synchronized int sub(int n1, int n2) { // ����ȭ �޼��� ����
//		opCnt++;
//		return n1 - n2;
//	}

	// ���2) Ư�� ������ ����ȭ(���� ���� �Ұ�) : ���� ���� ������ �� �մ� ������ �ִ� ��
	int add(int n1, int n2) { // ����ȭ �޼��� ����
		synchronized (this) { // this�� ����..?
			opCnt++;
		}
		return n1 + n2;
	}

	int sub(int n1, int n2) { // ����ȭ �޼��� ����
		synchronized (this) {
			opCnt++;
		}
		return n1 - n2;
	}

	void showOpCnt() {
		System.out.println("�� ���� Ƚ��: " + opCnt);
	}
}

class AddThread extends Thread {
	private Calculator cal;

	AddThread(Calculator cal) {
		this.cal = cal;
	}

	public void run() {
		System.out.println("1 + 2 = " + cal.add(1, 2));
		System.out.println("2 + 4 = " + cal.add(2, 4));
	}
}

class MinThread extends Thread {
	private Calculator cal;

	MinThread(Calculator cal) {
		this.cal = cal;
	}

	public void run() {
		System.out.println("2 - 1 = " + cal.sub(2, 1));
		System.out.println("4 - 2 = " + cal.sub(4, 2));
	}
}

class ThreadSyncMethod {
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		// �ϳ��� calculator��ü�� �ΰ��� thread�� ���� : �� 4���� ������ ����
		AddThread at = new AddThread(cal);
		MinThread mt = new MinThread(cal);

		at.start();
		mt.start();

		try {
			at.join();
			mt.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cal.showOpCnt();
	}
}