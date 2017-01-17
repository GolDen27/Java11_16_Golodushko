package by.tc.eq.controller.command.impl;

import by.tc.eq.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "illegal command";
    }
}
