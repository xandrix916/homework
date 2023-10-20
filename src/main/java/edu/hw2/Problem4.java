package edu.hw2;

public class Problem4 {
    public record CallingInfo(String className, String methodName) {
        @Override
        public String toString() {
            return "Class name: %s\nMethod name: %s".formatted(className, methodName);
        }
    }

    public static CallingInfo callingInfo() {
        Thread current = Thread.currentThread();
        StackTraceElement[] methods = current.getStackTrace();
        return new CallingInfo(methods[2].getClassName(), methods[2].getMethodName());
    }

    public void problem4() {
        System.out.printf("%s\n",callingInfo());
    }

    public void doubleProblem4() {
        problem4();
        System.out.printf("%s\n",callingInfo());
    }
}
