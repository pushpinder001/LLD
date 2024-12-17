package command;

public class ExitCommandHandler implements ICommandHandler{
    @Override
    public void handle(String[] inputs) {
        System.out.println("Exit Command");
        System.exit(0);
    }
}
