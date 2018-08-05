package quick.pager.pay.common;

public class TestRunnable implements Runnable {

    private int count;

    public TestRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for(int i=0;i<count;i++){
            System.out.println(Thread.currentThread().getName() + "===" + i);
        }
    }
}
