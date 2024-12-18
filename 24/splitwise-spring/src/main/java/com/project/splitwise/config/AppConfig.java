package com.project.splitwise.config;

import com.project.splitwise.command.strategy.CreateUserCommandHandler;
import com.project.splitwise.command.strategy.ICommandHandler;
import com.project.splitwise.command.type.CommandType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {
    @Bean
    public Map<CommandType, ICommandHandler> commandHandler() {
        Map<CommandType, ICommandHandler> map = new HashMap<>();
        map.put(CommandType.CREATE_USER, createUser());

        return map;
    }

    @Bean public ICommandHandler createUser() {
        return new CreateUserCommandHandler();
    }
}
