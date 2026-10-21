package view.tui;

import java.util.List;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import view.LeagueTableView;
import view.TableListener;
import view.dto.TeamRow;

public class TuiLeagueTableView implements LeagueTableView, TuiScreen
{
    private static final int HEADER_Y = 2;
    private static final int FIRST_ROW_Y = 4;
    private static final int TABLE_X = 3;

    private final TuiApp app;
    private List<TeamRow> rows = List.of();
    private int selected = 0;
    private int scrollOffset = 0;
    private TableListener listener;

    public TuiLeagueTableView(TuiApp app)
    {
        this.app = app;
    }

    @Override
    public void showTable(List<TeamRow> rows)
    {
        this.rows = rows;
        this.selected = 0;
        this.scrollOffset = 0;
        app.setActiveScreen(this);
    }

    @Override
    public void setListener(TableListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void render()
    {
        Screen screen = app.getScreen();
        int columns = screen.getTerminalSize().getColumns();
        int terminalRows = screen.getTerminalSize().getRows();

        Draw.box(screen, 0, 0, columns, terminalRows - 1, "Liga");

        String header = String.format("   %3s  %-18s %3s %3s %3s  %5s  %4s",
                "Poz", "Drużyna", "M", "W", "P", "Sety", "Pkt");
        Draw.text(screen, TABLE_X, HEADER_Y, header,
                Theme.ACCENT, Theme.BACKGROUND);

        drawAllRows();

        Draw.hintBar(screen, "↑↓ wybór   Enter OK   Esc wstecz");
    }

    @Override
    public void handleKey(Key key)
    {
        if (listener == null)
        {
            return;
        }

        if (key.getKind() == Key.Kind.ArrowUp)
        {
            moveSelection(-1);
        }
        else if (key.getKind() == Key.Kind.ArrowDown)
        {
            moveSelection(1);
        }
        else if (key.getKind() == Key.Kind.Enter)
        {
            if (!rows.isEmpty())
            {
                listener.onTeamSelected(selected);
            }
        }
        else if (key.getKind() == Key.Kind.Escape)
        {
            listener.onBack();
        }
    }

    private int visibleRows()
    {
        int terminalRows = app.getScreen().getTerminalSize().getRows();
        return terminalRows - FIRST_ROW_Y - 2;
    }

    private void moveSelection(int delta)
    {
        if (rows.isEmpty())
        {
            return;
        }

        int newSelected = Math.max(0, Math.min(rows.size() - 1, selected + delta));
        if (newSelected == selected)
        {
            return;
        }

        int old = selected;
        selected = newSelected;

        if (selected < scrollOffset)
        {
            scrollOffset = selected;
            drawAllRows();
        }
        else if (selected >= scrollOffset + visibleRows())
        {
            scrollOffset = selected - visibleRows() + 1;
            drawAllRows();
        }
        else
        {
            drawRow(old);
            drawRow(selected);
        }
    }

    private void drawAllRows()
    {
        for (int i = scrollOffset; i < scrollOffset + visibleRows(); i++)
        {
            drawRow(i);
        }
    }

    private void drawRow(int index)
    {
        if (index < 0 || index >= rows.size())
        {
            return;
        }
        if (index < scrollOffset || index >= scrollOffset + visibleRows())
        {
            return;
        }

        TeamRow r = rows.get(index);
        boolean isSelected = (index == selected);
        boolean isLeader = (r.place() == 1);
        boolean isLast = (index == rows.size() - 1);

        String selMarker = isSelected ? ">" : " ";
        String rankMarker = isLeader ? "▲" : (isLast ? "▼" : " ");
        String sets = r.setsWon() + ":" + r.setsLost();

        String line = String.format("%s%s %3d  %-18s %3d %3d %3d  %5s  %4d",
                selMarker, rankMarker, r.place(), r.name(),
                r.played(), r.wins(), r.losses(), sets, r.points());

        Terminal.Color fg = Theme.TEXT;
        Terminal.Color bg = Theme.BACKGROUND;

        if (isSelected)
        {
            fg = Theme.SELECTION_FG;
            bg = Theme.SELECTION_BG;
        }
        else if (isLeader)
        {
            fg = Theme.ACCENT;
        }
        else if (isLast)
        {
            fg = Theme.ERROR;
        }

        int y = FIRST_ROW_Y + index - scrollOffset;
        Draw.text(app.getScreen(), TABLE_X, y, line, fg, bg);
    }
}