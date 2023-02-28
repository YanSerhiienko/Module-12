package homework.task1;

import homework.NumberChecker;
import java.util.ArrayList;
import java.util.List;

public class TimePrinter {

    NumberChecker everySingleSec = new NumberChecker((n) -> {
        if (n % 5 != 0 || n == 0 ) {
            System.out.println(n + " seconds from the start");
        }
    });
    NumberChecker everyFiveSec = new NumberChecker((n) -> {
        if (n % 5 == 0 && n != 0 ) {
            System.out.println("5 seconds have passed");
        }
    });

    public void print() {
        int seconds = 0;
        List<NumberChecker> threads = new ArrayList<>();
        threads.add(everySingleSec);
        threads.add(everyFiveSec);

        for (NumberChecker thread : threads) {
            thread.start();
        }

        while (true) {
            for (NumberChecker thread : threads) {
                thread.checkNumber(seconds);
            }

            while (true) {
                int processedCount = 0;
                for (NumberChecker thread : threads) {
                    if (thread.isNumberChecked()) {
                        processedCount++;
                    }
                }
                if (processedCount == threads.size()) {
                    break;
                }
            }
            seconds++;
        }
    }
}
