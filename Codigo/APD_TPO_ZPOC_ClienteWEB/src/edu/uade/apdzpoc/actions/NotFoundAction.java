package edu.uade.apdzpoc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundAction implements IAction {

    public static final NotFoundAction NOT_FOUND_ACTION = new NotFoundAction();

    @Override
    public boolean isValid(String action) {
        return false;
    }

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        return "/404.jsp";
    }
}
