package L09;
// cal.add() 수행중일 때 cal.sub()은 수행되지 않아야 함(그 역도 성립해야 함)

class Calculator {
	private int opCnt = 0; // 공유 변수

	// 방법1) 함수의 동기화
	// 실제 두개의 스레드가 동시에 접근하지 말아야할 부분은 opCnt++ 객체인데 함수를 통째로 동기화하는 것은 시간을 지연시키는 일에 불과
	// -> 범위 축소가 필요
//	synchronized int add(int n1, int n2) { // 동기화 메서드 정의
//		opCnt++;
//		return n1 + n2;
//	}
//	
//	synchronized int sub(int n1, int n2) { // 동기화 메서드 정의
//		opCnt++;
//		return n1 - n2;
//	}

	// 방법2) 특정 영역만 동기화(동시 접근 불가) : 좀더 빨리 동작할 수 잇는 여지를 주는 것
	int add(int n1, int n2) { // 동기화 메서드 정의
		synchronized (this) { // this는 무엇..?
			opCnt++;
		}
		return n1 + n2;
	}

	int sub(int n1, int n2) { // 동기화 메서드 정의
		synchronized (this) {
			opCnt++;
		}
		return n1 - n2;
	}

	void showOpCnt() {
		System.out.println("총 연산 횟수: " + opCnt);
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
		// 하나의 calculator객체가 두개의 thread와 공유 : 총 4개의 연산이 동작
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