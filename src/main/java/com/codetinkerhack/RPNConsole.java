package com.codetinkerhack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by evgeniys on 9/05/2017.
 */
public class RPNConsole {

    public static void main(String[] args) throws IOException {
        RPNInterpreter ni = new RPNInterpreter();

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();

            if (input.contains("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                ni.interpretInput(input);
            } catch (RPNError e) {
                System.out.println(e);
            }

            System.out.println("Stack: " + ni.getStackAsString());

        }
    }

}
