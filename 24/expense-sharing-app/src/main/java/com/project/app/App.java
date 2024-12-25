package com.project.app;

import com.project.app.command.CommandRunner;
import com.project.app.command.ExpenseCommandHandler;
import com.project.app.command.ShowCommandHandler;
import com.project.app.service.ExpenseService;
import com.project.app.strategy.EqualExpenseTypeSplitStrategy;
import com.project.app.strategy.ExactExpenseTypeSplitStrategy;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ExpenseService expenseService = new ExpenseService(List.of(new EqualExpenseTypeSplitStrategy(),
                new ExactExpenseTypeSplitStrategy()));
        CommandRunner  commandRunner = new CommandRunner(List.of(new ExpenseCommandHandler(expenseService)
            , new ShowCommandHandler(expenseService)));

        while(true) {
            String input = scanner.nextLine();
            commandRunner.run(input);
        }
    }
}
