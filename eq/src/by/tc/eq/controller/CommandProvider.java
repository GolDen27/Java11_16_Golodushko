package by.tc.eq.controller;

import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.command.CommandName;
import by.tc.eq.controller.command.impl.AddUser;
import by.tc.eq.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.ADD_USER, new AddUser());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        //...
    }

    Command getCommand(String name){
        CommandName commandName =null;
        Command command = null;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            //write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
