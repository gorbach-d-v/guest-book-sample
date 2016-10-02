package ru.sandbox.gb;

import java.util.List;

/**
 * Created by dmitry on 02.10.16.
 */
public interface MessageStorage {

    List<Message> getMessages();

    void store(Message message);

}
