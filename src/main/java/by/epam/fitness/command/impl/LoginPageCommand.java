package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.LOGIN_PAGE;
    }
}
