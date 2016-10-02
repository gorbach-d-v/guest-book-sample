package ru.sandbox.gb;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by dmitry on 02.10.16.
 */
public class GuestBookRender {

    private GuestBookRender() {
    }

    public static void renderPage(List<Message> message, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html");
        OutputStream os = resp.getOutputStream();

        StringBuilder sb = new StringBuilder();

        sb.append("<!doctype html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta charset='UTF-8'>");
        sb.append("<title> Guest Book </title> </head>");
        sb.append("<body>");
        sb.append("<form method='POST'>");
        sb.append("User: <input type='text' name='user'/><br/>");
        sb.append("Message:<br>");
        sb.append("<textarea name='body'></textarea><br>");
        sb.append("<input type='submit' value='Отправить'/> ");
        sb.append("</form>");
        sb.append("<hr>");
        renderMessages(sb, message);
        sb.append("</body>");

        os.write(sb.toString().getBytes(Charset.forName("UTF-8")));
        os.close();
    }

    private static void renderMessages(StringBuilder sb, List<Message> message) {
        if(message.isEmpty()) {
            sb.append("<h2>Сообщений нет</h2>");
        } else {
            sb.append("<ul>");
            for (Message m : message) {
               renderMessage(sb, m);
            }
            sb.append("</ul>");
        }
    }

    private static void renderMessage(StringBuilder sb, Message message) {
        sb.append("<li>");
        sb.append("<div class='user'>").append(message.getUser()).append("</div>");
        sb.append("<div class='body'>").append(message.getBody()).append("</body>");
        sb.append("</li>");
    }

}
