package com.project.splitwise;

import com.project.splitwise.command.CommandRunner;
import com.project.splitwise.command.strategy.CreateUserCommandHandler;
import com.project.splitwise.command.type.CommandType;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {

	@Autowired
	private CommandRunner commandRunner;
	Scanner scanner ;

	public static void main(String[] args) {
		SpringApplication.run(SplitWiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//This can be made configurable
		scanner = new Scanner(System.in);

		while(true) {
			String nextCommand = scanner.nextLine();
			commandRunner.execute(nextCommand);
			System.out.println("-".repeat(30));
		}
	}
}
