import dto.Position;
import repo.IDriverRepo;
import repo.IRideRepo;
import repo.IRiderRepo;
import repo.impl.InMemoryDriverRepo;
import repo.impl.InMemoryRideRepo;
import repo.impl.InMemoryRiderRepo;
import service.ICabBookingService;
import service.IRideService;
import service.impl.CabBookingService;
import service.impl.NearestDriverAllocationStrategy;
import service.impl.RideService;

public class Main {
    public static void main(String[] args) {
        IRiderRepo riderRepo = new InMemoryRiderRepo();
        IDriverRepo driverRepo = new InMemoryDriverRepo();
        IRideRepo rideRepo = new InMemoryRideRepo();
        IRideService rideService = new RideService(riderRepo, driverRepo, rideRepo, new NearestDriverAllocationStrategy(driverRepo));
        ICabBookingService cabSharingService = new CabBookingService(riderRepo, driverRepo, rideService);

        cabSharingService.registerRider(new Position(0, 0));
        cabSharingService.registerRider(new Position(5, 5));

        cabSharingService.registerDriver(new Position(1, 0));
        cabSharingService.registerDriver(new Position(1, 1));
        cabSharingService.registerDriver(new Position(4, 1));

        cabSharingService.bookRide(0);
        cabSharingService.bookRide(1);

        System.out.println("Hello world!");
    }
}