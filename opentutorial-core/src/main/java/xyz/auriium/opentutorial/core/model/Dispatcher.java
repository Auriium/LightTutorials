package xyz.auriium.opentutorial.core.model;

public interface Dispatcher<T> {

    void dispatchCommand(String command);
    void dispatchAs(T t, String command);

}
