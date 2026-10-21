package view;

import java.util.List;
import view.dto.PlayerRow;
import view.dto.ScoreState;

public interface MatchView
{
    void showMatch(ScoreState score, List<PlayerRow> courtA,
            List<PlayerRow> courtB);
    void updateScore(ScoreState score);
    void addLogLine(String text);
    void showMessage(String text);
    void playPointAnimation(boolean pointForA);
    void setListener(MatchListener listener);
}