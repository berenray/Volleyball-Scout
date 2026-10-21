package view;

public interface AppView
{
    void start();
    void stop();
    void confirm(String question, ConfirmListener listener);
}