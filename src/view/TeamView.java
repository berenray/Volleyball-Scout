package view;

import java.util.List;
import view.dto.PlayerRow;

public interface TeamView
{
    void showTeam(String teamName, List<PlayerRow> players);
    void setListener(BackListener listener);
}