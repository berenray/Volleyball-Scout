package view.tui;

import java.util.List;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

import view.MainMenuView;
import view.MenuListener;

public class TuiMainMenuView implements MainMenuView, TuiScreen
{
    private static final int ITEM_WIDTH = 24;
    private static final int ITEMS_START_Y = 9;

    private final TuiApp app;
    private String title = "";
    private List<String> items = List.of();
    private int selected = 0;
    private MenuListener listener;

    public TuiMainMenuView(TuiApp app)
    {
        this.app = app;
    }

    @Override
    public void showMenu(String title, List<String> items)
    {
        this.title = title;
        this.items = items;
        this.selected = 0;
        app.setActiveScreen(this);
    }

    @Override
    public void setListener(MenuListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void render()
    {
        Screen screen = app.getScreen();
        int columns = screen.getTerminalSize().getColumns();
        int rows = screen.getTerminalSize().getRows();

        Draw.box(screen, 0, 0, columns, rows - 1, title);

        String logo = "o   V O L L E Y S C O U T   o";
        Draw.text(screen, (columns - logo.length()) / 2, 3, logo,
                Theme.ACCENT, Theme.BACKGROUND);
        String line = "=".repeat(logo.length());
        Draw.text(screen, (columns - logo.length()) / 2, 2, line,
                Theme.ACCENT, Theme.BACKGROUND);
        Draw.text(screen, (columns - logo.length()) / 2, 4, line,
                Theme.ACCENT, Theme.BACKGROUND);

        for (int i = 0; i < items.size(); i++)
        {
            drawItem(i);
        }

        Draw.hintBar(screen, "↑↓ wybór   Enter OK   Esc wstecz");
    }

    @Override
    public void handleKey(Key key)
    {
        if (items.isEmpty() || listener == null)
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
            listener.onMenuItemSelected(selected);
        }
        else if (key.getKind() == Key.Kind.Escape)
        {
            listener.onBack();
        }
    }

    private void moveSelection(int delta)
    {
        int old = selected;
        selected = (selected + delta + items.size()) % items.size();
        drawItem(old);
        drawItem(selected);
    }

    private void drawItem(int index)
    {
        Screen screen = app.getScreen();
        int columns = screen.getTerminalSize().getColumns();
        int x = (columns - ITEM_WIDTH) / 2;
        int y = ITEMS_START_Y + index * 2;

        boolean isSelected = (index == selected);
        String text = (isSelected ? "> " : "  ") + items.get(index);
        text = String.format("%-" + ITEM_WIDTH + "s", text);

        if (isSelected)
        {
            Draw.text(screen, x, y, text, Theme.SELECTION_FG, Theme.SELECTION_BG);
        }
        else
        {
            Draw.text(screen, x, y, text, Theme.TEXT, Theme.BACKGROUND);
        }
    }
}