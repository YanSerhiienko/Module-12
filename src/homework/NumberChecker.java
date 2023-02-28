package homework;

import java.util.concurrent.atomic.AtomicBoolean;

public class NumberChecker extends Thread {
    private int number;
    private AtomicBoolean isChecked = new AtomicBoolean(true);
    private Checker checker;

    public NumberChecker(Checker checker) {
        this.checker = checker;
    }

    public synchronized void checkNumber(int number) {
        this.number = number;
        isChecked.set(false);
    }
    public boolean isNumberChecked() {
        return isChecked.get();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isChecked.get()) {
                continue;
            }

            checker.check(number);

            isChecked.set(true);
        }
    }
}
