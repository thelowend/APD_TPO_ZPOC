package edu.uade.apdzpoc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

    String doAction(HttpServletRequest request, HttpServletResponse response);

}
