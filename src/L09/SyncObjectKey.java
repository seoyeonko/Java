package L09;
// num1에 대한 동기화 처리 메서드와 num2에 대한 동기화 처리는 배타적일 필요 없음

class IHaveTwoNum {
	private int num1 = 0;
	private int num2 = 0;

	// [방법1]
	// 이경우 동기화 메서드로 만들던 동기화 블럭으로 만들던 큰 차이가 있어 보이지는 않음
	// 4개의 메서드는 하나의 키를 가지고 접근하는 경우, 어느 한 순간에는 하나의 스레드만 진입이 허용되는 것
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

	// [방법2]
	// but, num1을 변화시키는 one,two~메서드 끼리만 동기화하고
	// num2를 변화시키는 one,two~메서드 끼리만 동기화하는 것이 훨씬 효율적
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
		// 두개의 스레드
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