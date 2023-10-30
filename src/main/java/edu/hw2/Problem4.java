package edu.hw2;

@SuppressWarnings({"InnerTypeLast", "RegexpSinglelineJava"})
public class Problem4 {
    public record CallingInfo(String className, String methodName) {
        @Override
        public String toString() {
            return String.format("Class name: %s\nMethod name: %s\n", className, methodName);
        }
    }

    public static CallingInfo callingInfo() {
        Thread current = Thread.currentThread();
        StackTraceElement[] methods = current.getStackTrace();
        return new CallingInfo(methods[2].getClassName(), methods[2].getMethodName());
    }


    public String problem4() {
        return callingInfo().toString();
    }

    public String  doubleProblem4() {
        return problem4() + callingInfo();
    }
}
