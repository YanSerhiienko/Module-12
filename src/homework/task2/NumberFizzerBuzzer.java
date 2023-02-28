package homework.task2;

import homework.NumberChecker;
import java.util.ArrayList;
import java.util.List;

public class NumberFizzerBuzzer {

    NumberChecker fizz = new NumberChecker((n) -> {
        if (n % 3 == 0 ) {
            System.out.println("fizz");
        }
    });
    NumberChecker buzz = new NumberChecker((n) -> {
        if (n % 5 == 0 ) {
            System.out.println("buzz");
        }
    });
    NumberChecker fizzBuzz = new NumberChecker((n) -> {
        if (n % 15 == 0 ) {
            System.out.println("fizzBuzz");
        }
    });
    NumberChecker number = new NumberChecker((n) -> {
        if (n % 3 != 0 && n % 5 != 0) {
            System.out.println(n);
        }
    });

    public void print(int n) {
        List<NumberChecker> threads = new ArrayList<>();
        threads.add(fizz);
        threads.add(buzz);
        threads.add(fizzBuzz);
        threads.add(number);

        for (NumberChecker thread : threads) {
            thread.start();
        }

        for (int i = 1; i <= n; i++) {
            for (NumberChecker thread : threads) {
                thread.checkNumber(i);
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
        }
    }
}
