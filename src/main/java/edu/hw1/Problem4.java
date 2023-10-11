package edu.hw1;

public class Problem4 {
    public String fixString(String string) {
        if (string.isEmpty())
            return "";
        char[] array = string.toCharArray();
        string = "";
        for (int i = 0; i < (array.length - (array.length)%2); i+= 2) {
            char temp;
            temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
        }
        StringBuilder stringBuilder = new StringBuilder(string);
        for (var c: array)
            stringBuilder.append(c);
        string = stringBuilder.toString();
        return string;
    }
}
