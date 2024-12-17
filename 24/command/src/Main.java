import command.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static CommandRunner commandRunner =
            new CommandRunner(Map.of(CommandType.START.name(),
            new StartCommandHandler(),
            CommandType.EXIT.name(), new ExitCommandHandler()));



    public static void main(String[] args) {
        while(true) {
            System.out.println("Enter the command : ");
            String input = sc.nextLine();
            commandRunner.process(input);
        }
    }
}