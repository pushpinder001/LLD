package com.project.app;

import com.project.app.command.CommandRunner;
import com.project.app.command.strategy.*;
import com.project.app.command.ICommandHandler;
import com.project.app.command.type.CommandType;
import com.project.app.dao.IGroupDAO;
import com.project.app.dao.ITransactionDAO;
import com.project.app.dao.IUserDAO;
import com.project.app.dao.impl.InMemoryGroupDAO;
import com.project.app.dao.impl.InMemoryTransactionDAO;
import com.project.app.dao.impl.InMemoryUserDAO;
import com.project.app.service.strategy.AmountSplitTransactionStrategy;
import com.project.app.service.strategy.EqualSplitTransactionStrategy;
import com.project.app.entity.Transaction;
import com.project.app.service.GroupService;
import com.project.app.service.TransactionService;
import com.project.app.service.UserService;
import com.project.app.service.strategy.ITransactionSplitStrategy;
import com.project.app.service.strategy.PercentSplitTransactionStrategy;
import com.project.app.type.TransactionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final CommandRunner commandRunner;
    private static final Scanner scanner;

    /**
     * Add configuration for commandRunner
     */
    static {
        IUserDAO userDAO = new InMemoryUserDAO();
        IGroupDAO groupDAO = new InMemoryGroupDAO();
        ITransactionDAO transactionDAO = new InMemoryTransactionDAO();

        UserService userService = new UserService(userDAO);
        GroupService groupService = new GroupService(groupDAO);

        Map<TransactionType, ITransactionSplitStrategy> tnxTypeToTxnSplitStrategy = new HashMap<>() {{
            put(TransactionType.EQUAL_SPLIT, new EqualSplitTransactionStrategy(groupService));
            put(TransactionType.UNEQUAL_SPLIT, new AmountSplitTransactionStrategy());
            put(TransactionType.PERCENT_SPLIT, new PercentSplitTransactionStrategy());
        }};

        TransactionService transactionService = new TransactionService(transactionDAO,
                    tnxTypeToTxnSplitStrategy);

        Map<CommandType, ICommandHandler> map = new HashMap<>(){{
            put(CommandType.CREATE_USER , new CreateUserCommandHandler(userService));
            put(CommandType.LIST_ALL_USERS, new ListAllUserCommandHandler(userService) );
            put(CommandType.CREATE_GROUP , new CreateGroupCommandHandler(groupService));
            put(CommandType.LIST_ALL_GROUPS_FOR_USER, new ListAllGroupCommandHandler(groupService));
            put(CommandType.CREATE_TRANSACTION_TO_GROUP, new AddTransactionCommandHandler(transactionService));
            put(CommandType.LIST_ALL_TXNS_IN_GROUP, new ListAllTransactionForGroupHandler(transactionService));
        }};

        try {
            commandRunner = new CommandRunner(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        while(true) {
            String nextCommand = scanner.nextLine();
            commandRunner.execute(nextCommand);
            System.out.println("-".repeat(30));
        }
    }
}
