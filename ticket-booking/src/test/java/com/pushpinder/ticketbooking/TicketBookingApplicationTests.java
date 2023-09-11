package com.pushpinder.ticketbooking;

import com.pushpinder.ticketbooking.entity.Movie;
import com.pushpinder.ticketbooking.entity.UserBookingSession;
import com.pushpinder.ticketbooking.manager.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TicketBookingApplicationTests {
	@Autowired
	ITheaterManager theaterManager;

	@Autowired
	IUserManager userManager;

	@Autowired
	IUserBookingSessionService userBookingSessionService;

	@BeforeEach
	void setup() {
		userManager.registerUser("U1");
		userManager.registerUser("U2");

		theaterManager.addScreen("Screen1", List.of("S1", "S2", "S3", "S4"));
		theaterManager.addScreen("Screen2", List.of("S1", "S2", "S3", "S4"));

		Movie movie = new Movie("Movie1");

		theaterManager.addShow("Screen1", "Show1", movie,
				new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(600)), 60);

	}

	@Test
	void test1() {
		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));

		UserBookingSession user1Session = userBookingSessionService.createUserSession("U1", "Screen1", "Show1");
		UserBookingSession user2Session = userBookingSessionService.createUserSession("U1", "Screen1", "Show1");

		List<String> availSeats = theaterManager.getAvailSeats("Screen1", "Show1");

		userBookingSessionService.selectSeats(user1Session.getId(), availSeats.subList(0, 2));

		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));
	}

	@Test
	void test2() {
		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));

		UserBookingSession user1Session = userBookingSessionService.createUserSession("U1", "Screen1", "Show1");
		UserBookingSession user2Session = userBookingSessionService.createUserSession("U1", "Screen1", "Show1");

		List<String> availSeats = theaterManager.getAvailSeats("Screen1", "Show1");

		userBookingSessionService.selectSeats(user1Session.getId(), availSeats.subList(0, 2));

		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));

		userBookingSessionService.makePaymentForSession(user1Session.getId(), 100);

		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));
	}

	@Test
	void test3() {
		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));

		UserBookingSession user1Session = userBookingSessionService.createUserSession("U1", "Screen1", "Show1");
		UserBookingSession user2Session = userBookingSessionService.createUserSession("U1", "Screen1", "Show1");

		List<String> availSeats = theaterManager.getAvailSeats("Screen1", "Show1");

		userBookingSessionService.selectSeats(user1Session.getId(), availSeats.subList(0, 2));
		System.out.println(theaterManager.getAvailSeats("Screen1", "Show1"));


		userBookingSessionService.selectSeats(user2Session.getId(), availSeats.subList(0, 2));

	}

}
