package kr.co.shortenurlservice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoutPerformanceTests {

    private static final int ITERATIONS = 1_000_000;

    public static void main(String[] args) {
        long systemOutTime = measureSystemOutPerformance();
        long slf4jTime = measureSlf4jPerformance();
//        long printStackTraceTime = measurePrintStackTracePerformance();
//        long getStackTraceTime = measureGetStackTracePerformance();

        System.out.println("\n=== Performance Results ===");
        System.out.println("System.out.println took: " + systemOutTime + "ms");
        System.out.println("SLF4J logging took: " + slf4jTime + "ms");
//        System.out.println("printStackTrace took: " + printStackTraceTime + "ms");
//        System.out.println("getStackTrace took: " + getStackTraceTime + "ms");
    }

    private static long measureSystemOutPerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            System.out.println("This is a test log for System.out.println: iteration " + i);
        }

        return System.currentTimeMillis() - startTime;
    }

    private static long measureSlf4jPerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            log.debug("This is a test log for SLF4J: iteration {}", i);
        }

        return System.currentTimeMillis() - startTime;
    }

    private static long measurePrintStackTracePerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            try {
                level1();
            } catch (Exception e) {
                e.printStackTrace(); // 콘솔 출력
            }
        }

        return System.currentTimeMillis() - startTime;
    }

    private static long measureGetStackTracePerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            try {
                level1();
            } catch (Exception e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                log.error("Exception caught: {}", stackTrace[0]); // 첫 번째 스택 정보만 출력
            }
        }

        return System.currentTimeMillis() - startTime;
    }

    private static void level1() throws Exception {
        level2();
    }

    private static void level2() throws Exception {
        level3();
    }

    private static void level3() throws Exception {
        level4();
    }

    private static void level4() throws Exception {
        level5();
    }

    private static void level5() throws Exception {
        level6();
    }

    private static void level6() throws Exception {
        throw new Exception("Test exception with deep stack trace");
    }
}
