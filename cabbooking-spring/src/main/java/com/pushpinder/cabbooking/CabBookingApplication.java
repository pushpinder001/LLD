package com.pushpinder.cabbooking;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.repo.IDriverRepo;
import com.pushpinder.cabbooking.repo.IRideRepo;
import com.pushpinder.cabbooking.repo.IRiderRepo;
import com.pushpinder.cabbooking.repo.impl.InMemoryDriverRepo;
import com.pushpinder.cabbooking.repo.impl.InMemoryRideRepo;
import com.pushpinder.cabbooking.repo.impl.InMemoryRiderRepo;
import com.pushpinder.cabbooking.service.ICabBookingService;
import com.pushpinder.cabbooking.service.IRideService;
import com.pushpinder.cabbooking.service.impl.CabBookingService;
import com.pushpinder.cabbooking.service.impl.RideService;
import com.pushpinder.cabbooking.service.strategy.NearestDriverAllocationStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CabBookingApplication {

	public static void main(String[] args) {

		SpringApplication.run(CabBookingApplication.class, args);
		IRiderRepo riderRepo = new InMemoryRiderRepo();
		IDriverRepo driverRepo = new InMemoryDriverRepo();
		IRideRepo rideRepo = new InMemoryRideRepo();
		IRideService rideService = new RideService(riderRepo, driverRepo, rideRepo, new NearestDriverAllocationStrategy(driverRepo));
		ICabBookingService cabSharingService = new CabBookingService(riderRepo, driverRepo, rideService);

		cabSharingService.registerRider("Rider1");
		cabSharingService.registerRider("Rider2");

		cabSharingService.registerDriver(new Position(1, 0));
		cabSharingService.registerDriver(new Position(1, 1));
		cabSharingService.registerDriver(new Position(4, 1));

		cabSharingService.bookRide(0, new Position(0, 0), new Position(4, 7));
		cabSharingService.bookRide(1, new Position(5, 5), new Position(1, -1));

		System.out.println("Hello world!");
	}

}
