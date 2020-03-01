package L06;

public class Person {
	private String mName;
	private String mPhoneNum;
	private int mAge;

	Person(String name, int age, String phoneNum) {
		this.mName = name;
		this.mAge = age;
		this.mPhoneNum = phoneNum;
	}

	int getAge() {
		return mAge;
	}

	String getName() {
		return mName;
	}

	String getPhoneNum() {
		return mPhoneNum;
	}

	void setPhoneNum(String phoneNum) {
		this.mPhoneNum = phoneNum;
	}
}
