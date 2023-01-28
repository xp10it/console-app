package org.example;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;

        while(isRunning) {
            String input;
            byte[] buffer = new byte[64];

            try {
                int read = System.in.read(buffer, 0, 64);
                input = new String(buffer, 0, read).strip();
            } catch (Exception e) {
                System.out.println(e.getMessage());

                isRunning = false;

                continue;
            }

            System.out.println("User input " +  input);

            if (input.equals("-q")) {
                isRunning = false;
            }
        }
    }
}