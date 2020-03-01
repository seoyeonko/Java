package L09;
// Data inconsistency 보이기

class Increment {
	private int num = 0;
	
	synchronized void inc() { // synchronized 키워드 : num이 공유 자원 
		// 키워드 추가한 경우 느리지만 정확히 3천만이 기록됨
		// nor, 데이터 불일치 현상 발생
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
				cnt.inc();  // 임계 영역 : 멀티스레드에 의해 공유 자언이 참조될수 있는 코드 범위
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