package com.example.flashcards.util;

// This class contains utility methods related to input
public class InputUtil {
    public static String sanitize(String input) {
        return input.trim().replace("\n", " ").replace("\r", "");
    }
}
