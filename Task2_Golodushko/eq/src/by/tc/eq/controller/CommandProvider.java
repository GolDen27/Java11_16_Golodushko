package by.tc.eq.controller;

import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.command.CommandName;
import by.tc.eq.controller.command.impl.AddUser;
import by.tc.eq.controller.command.impl.GiveRent;
import by.tc.eq.controller.command.impl.ReturnRent;
import by.tc.eq.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.ADD_USER, new AddUser());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.GIVE_RENT, new GiveRent());
        repository.put(CommandName.RETUTN_RENT, new ReturnRent());
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
            // ну, и где хоть какой-нибудь лог?
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
