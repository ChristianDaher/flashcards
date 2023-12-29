package com.example.flashcards.util;

public class InputUtil {
    public static String sanitize(String input) {
        return input.trim().replace("\n", " ").replace("\r", "");
    }
}
