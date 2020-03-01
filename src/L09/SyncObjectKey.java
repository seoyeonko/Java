package L09;
// num1�� ���� ����ȭ ó�� �޼���� num2�� ���� ����ȭ ó���� ��Ÿ���� �ʿ� ����

class IHaveTwoNum {
	private int num1 = 0;
	private int num2 = 0;

	// [���1]
	// �̰�� ����ȭ �޼���� ����� ����ȭ ������ ����� ū ���̰� �־� �������� ����
	// 4���� �޼���� �ϳ��� Ű�� ������ �����ϴ� ���, ��� �� �������� �ϳ��� �����常 ������ ���Ǵ� ��
//	synchronized void addOneNum1() {
//		num1 += 1;
//	}
//
//	synchronized void addTwoNum1() {
//		num1 += 2;
//	}
//
//	synchronized void addOneNum2() {
//		num2 += 1;
//	}
//
//	synchronized void addTwoNum2() {
//		num2 += 2;
//	}

	// [���2]
	// but, num1�� ��ȭ��Ű�� one,two~�޼��� ������ ����ȭ�ϰ�
	// num2�� ��ȭ��Ű�� one,two~�޼��� ������ ����ȭ�ϴ� ���� �ξ� ȿ����
	private Object key1 = new Object();
	private Object key2 = new Object();

	void addOneNum1() {
		synchronized (key1) {
			num1 += 1;
		}
	}

	void addTwoNum1() {
		synchronized (key1) {
			num1 += 2;
		}
	}

	void addOneNum2() {
		synchronized (key2) {
			num2 += 1;
		}
	}

	void addTwoNum2() {
		synchronized (key2) {
			num2 += 2;
		}
	}

	void showAllNums() {
		System.out.println("num1: " + num1);
		System.out.println("num2: " + num2);
	}
}

class AccessThread extends Thread {
	private IHaveTwoNum twoNumInst;

	AccessThread(IHaveTwoNum inst) {
		twoNumInst = inst;
	}

	public void run() {
		twoNumInst.addOneNum1();
		twoNumInst.addTwoNum1();

		twoNumInst.addOneNum2();
		twoNumInst.addTwoNum2();
	}
}

class SyncObjectKey {
	public static void main(String[] args) {
		IHaveTwoNum numInst = new IHaveTwoNum();
		// �ΰ��� ������
		AccessThread at1 = new AccessThread(numInst);
		AccessThread at2 = new AccessThread(numInst);
                                                                                                                                                                                                                         
		at1.start();
		at2.start();

		try {
			at1.join();
			at2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		numInst.showAllNums();
	}
}