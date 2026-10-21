package view;

public interface MatchListener
{
    void onPlayerAction(boolean teamA, int playerNumber, UiAction action);
    void onUndo();
    void onTimeout(boolean teamA);
    void onBack();
}