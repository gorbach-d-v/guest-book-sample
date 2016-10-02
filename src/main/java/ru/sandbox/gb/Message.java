package ru.sandbox.gb;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dmitry on 02.10.16.
 */
public class Message {

    private String user;
    private String body;

    public Message(String user, String body) {
        this.user = user;
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public static Message fromRequest(HttpServletRequest req) {

        String user = req.getParameter("user");
        String body = req.getParameter("body");

        if(user != null && !user.isEmpty() && body != null && !body.isEmpty()) {
            return new Message(user, body);
        }

        return null;
    }
}
