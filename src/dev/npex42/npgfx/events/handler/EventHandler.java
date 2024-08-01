package dev.npex42.npgfx.events.handler;

import dev.npex42.npgfx.events.Event;

/**
 * @param <T> the specific Event type that this function operates on.
 */
public interface EventHandler<T extends Event> {
    /**
     *
     * @param event The event being processed
     * @return False if the event should continue to be processed; True if the event has been finished.
     */
    boolean Invoke(T event);
}
