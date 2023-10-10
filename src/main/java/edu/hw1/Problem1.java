package edu.hw1;

public class Problem1 {
    public String say(String word){
        return switch (word){
            case "hello" -> "world";
            case "ping" -> "pong";
            default -> "dunno";
        };
    }
}
