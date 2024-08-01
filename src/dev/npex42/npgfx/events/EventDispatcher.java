package dev.npex42.npgfx.events;

import dev.npex42.npgfx.events.handler.EventHandler;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher<T extends Event> {
    private List<EventHandler<T>> mHandlers = new ArrayList<>();
    public void Dispatch(T event) {
        for (EventHandler<T> handler : mHandlers) {
            if (handler.Invoke(event)) {
                break;
            }
        }
    }

    public void Register(EventHandler<T> handler) {
        mHandlers.add(handler);
    }

}
