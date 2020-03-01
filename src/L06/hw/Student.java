package L06.hw;

public class Student {
        private String mName;
        private int mC;
        private int mJAVA;
        private int mCpp;
        private int sum;
        private float avg;

        public Student(String mName, int mC, int mJAVA, int mCpp, int sum, float avg) {
                this.mName = mName;
                this.mC = mC;
                this.mJAVA = mJAVA;
                this.mCpp = mCpp;
                this.sum = sum;
                this.avg = avg;
        }

        // setter, getter
        public String getmName() {
                return mName;
        }

        public void setmName(String mName) {
                this.mName = mName;
        }

        public int getmC() {
                return mC;
        }

        public void setmC(int mC) {
                this.mC = mC;
        }

        public int getmJAVA() {
                return mJAVA;
        }

        public void setmJAVA(int mJAVA) {
                this.mJAVA = mJAVA;
        }

        public int getmCpp() {
                return mCpp;
        }

        public void setmCpp(int mCpp) {
                this.mCpp = mCpp;
        }

        public int getSum() {
                return sum;
        }

        public void setSum(int sum) {
                sum = getmC() + getmJAVA() + getmCpp();
                this.sum = sum;
        }

        public float getAvg() {
                return avg;
        }

        public void setAvg(float avg) {
//                avg = getSum() / 3;
                this.avg = avg;
        }

}