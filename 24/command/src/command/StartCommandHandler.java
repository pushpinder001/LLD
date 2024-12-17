package command;

public class StartCommandHandler implements ICommandHandler{
    @Override
    public void handle(String[] inputs) {
        System.out.println("Start Command");
    }
}
