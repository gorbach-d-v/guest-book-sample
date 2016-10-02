package ru.sandbox.gb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmitry on 02.10.16.
 */
public class InMemoryMessageStorage implements MessageStorage {

    private List<Message> messages = new ArrayList<>();

    @Override
    public synchronized List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    @Override
    public synchronized void store(Message message) {
        messages.add(message);
    }
}
