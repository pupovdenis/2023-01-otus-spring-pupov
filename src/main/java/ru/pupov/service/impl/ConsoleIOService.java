package ru.pupov.service.impl;

import org.springframework.stereotype.Service;
import ru.pupov.service.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {

    private final Scanner userInput = new Scanner(System.in);
    private final PrintStream userOutput = System.out;

    @Override
    public void outputString(String s) {
        userOutput.println(s);
    }

    @Override
    public void outputString(String s, boolean notNewLine) {
        if (notNewLine) {
            userOutput.print(s);
        } else {
            outputString(s);
        }
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        outputString(prompt, true);
        return Integer.parseInt(userInput.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt, boolean notNewLine) {
        outputString(prompt, notNewLine);
        return userInput.nextLine();
    }
}
