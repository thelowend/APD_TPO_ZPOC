package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public interface IAction {

    boolean isValid(String action);

    String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, RemoteException, JSONException;

}
