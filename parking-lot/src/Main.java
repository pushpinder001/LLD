import com.pushpinder.command.CommandExecutorFactory;
import com.pushpinder.exception.InvalidModeException;
import com.pushpinder.mode.Mode;
import com.pushpinder.mode.ModeFactory;
import com.pushpinder.service.ParkingLotService;
import com.pushpinder.service.impl.ParkingLotServiceImpl;

public class Main {
    public static void main(String[] args) throws InvalidModeException {
        ParkingLotService parkingLotService = new ParkingLotServiceImpl();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);
        Mode mode = ModeFactory.getMode(args, parkingLotService, commandExecutorFactory);

        mode.process();
    }
}