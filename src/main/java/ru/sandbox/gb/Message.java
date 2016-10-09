package ru.sandbox.gb;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
/**
 * Created by daniil on 07.10.16.
 */
public class Message {

    private String user;
    private String time;
    private String body;


    public Message(String user, String time, String body) {
        this.user = user;
        this.time = time;
        this.body = body;
    }

    public String getUser() { return user; }
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");
        LocalDateTime TimeNow = LocalDateTime.now();
        LocalDateTime TimeThen =LocalDateTime.parse(time, formatter);

        if ((ChronoUnit.HOURS.between(TimeThen, TimeNow)<1)) {
            return (ChronoUnit.MINUTES.between(TimeThen, TimeNow) + " minutes ago");
        }else if (ChronoUnit.DAYS.between(TimeThen, TimeNow)<1){
            return (ChronoUnit.HOURS.between(TimeThen, TimeNow)+" hours ago");
        }else {
            return TimeThen.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
        }
    }
    public String getBody() {
        return body;
    }

    public static Message fromRequest(HttpServletRequest req) {
        LocalDateTime dateTime = LocalDateTime.now();

        String user = req.getParameter("user");
        String body = req.getParameter("body");
        String time = dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));

        if(user != null && !user.isEmpty() && body != null && !body.isEmpty()&& time != null && !time.isEmpty()) {
            return new Message(user, time, body);
        }

        return null;
    }
}
