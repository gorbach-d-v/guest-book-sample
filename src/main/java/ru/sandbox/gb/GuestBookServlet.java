package ru.sandbox.gb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitry on 02.10.16.
 */
public class GuestBookServlet extends HttpServlet {

    private MessageStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new InMemoryMessageStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        GuestBookRender.renderPage(storage.getMessages(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Message newMessage = Message.fromRequest(req);

        if (newMessage != null) {
            storage.store(newMessage);
        }

        GuestBookRender.renderPage(storage.getMessages(), resp);

    }
}
