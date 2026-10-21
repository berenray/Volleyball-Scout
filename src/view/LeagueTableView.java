package view;

import java.util.List;
import view.dto.TeamRow;

public interface LeagueTableView
{
    void showTable(List<TeamRow> rows);
    void setListener(TableListener listener);
}