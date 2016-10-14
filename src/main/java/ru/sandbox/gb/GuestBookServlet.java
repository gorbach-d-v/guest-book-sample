package ru.sandbox.gb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.charset.Charset;

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
        String uri = req.getRequestURI();
        if (uri.contains("/assets")){
            String filename = uri.substring("/assets".length());
            InputStream stream = GuestBookServlet.class.getResourceAsStream(filename);
            int b;
            while ((b=stream.read())!=-1){
                resp.getOutputStream().write(b);
            }
        } else {
            GuestBookRender.renderPage(storage.getMessages(), resp);
        }
        req.setCharacterEncoding("UTF-8");
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
