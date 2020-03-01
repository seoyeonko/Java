package L09;

// 순서 제어 필요성 : 동기화를 이용한 순서제어
// 원본 파일은 null 혹은 "자바의 열기가 뜨겂습니다"로 출력되는데
// 현재 파일은 항상 문자열이 출력됨
class NewsPaper {
	private String todayNews;
	private boolean isTodayNews = false; // 오늘 뉴스가 설정된적이 있는지 여부를 저장하는 값

	void setTodayNews(String news) { // 1) writer가 먼저 설정하고
		// isTodayNews = true; // 이 위치는 불안정함 바로 아래 코드사이에서 스레드가 끊긴다면? ... x
		todayNews = news;
		isTodayNews = true; // 뉴스가 설정된 것을 명시적으로 표시 (바로 윗코드의 하단에 작성하는 것이 안전한 위치)

		// 자고 있는 것을 깨우는 일
		synchronized (this) {
			// "자신"이 다른 것을 "깨우는 것"!!
			notifyAll(); // 모든 구독자를 깨우는 처리를 하기 위해 notifyAll() 함수
		}
	}

	// reader객체에 의해 설정되었을 때,
	String getTodayNews() { // 2) reader가 작동하도록
		if (isTodayNews == false) { // 뉴스 설정이 안되었다면
			// 뉴스가 설정되면 깨워줘!
			synchronized (this) { // 동기화 블럭
				try {
					wait(); // this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} // 설정된다면
		return todayNews; // 현재 뉴스 반환
	}

	// 1)->2) 내가 원하는 순서대로 스레드가 실행되도록 하는 것 : 순서제어
}

class NewsWriter extends Thread {
	private NewsPaper paper;

	NewsWriter(NewsPaper paper) {
		this.paper = paper;
	}

	public void run() {
		paper.setTodayNews("자바의 열기가 뜨겁습니다.");
	}
}

class NewsReader extends Thread {
	private NewsPaper paper;

	NewsReader(NewsPaper paper) {
		this.paper = paper;
	}

	public void run() { // 뉴스가 설정되어지면 가져오도록 처리되어지는 일이 필요함
		System.out.println("오늘의 뉴스: " + paper.getTodayNews());
	}
}

class NewsPaperStory { // public 클래스로 두어도 상관 x
	public static void main(String[] args) {
		NewsPaper paper = new NewsPaper();
		NewsReader reader1 = new NewsReader(paper);
		NewsReader reader2 = new NewsReader(paper);
		NewsWriter writer = new NewsWriter(paper);

		try { // 스레드의 처리가 끝날때 까지 기다리고 마침..?
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