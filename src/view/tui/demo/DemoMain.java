package view.tui.demo;

import com.googlecode.lanterna.input.Key;

import view.tui.Draw;
import view.tui.TuiApp;
import view.tui.TuiScreen;

public class DemoMain
{
    public static void main(String[] args)
    {
        TuiApp app = new TuiApp();

        app.setActiveScreen(new TuiScreen()
        {
            @Override
            public void render()
            {
                int columns = app.getScreen().getTerminalSize().getColumns();
                int rows = app.getScreen().getTerminalSize().getRows();
                Draw.box(app.getScreen(), 0, 0, columns, rows - 1, "VolleyScout");
                Draw.hintBar(app.getScreen(), "Esc wyjscie");
            }

            @Override
            public void handleKey(Key key)
            {
                if (key.getKind() == Key.Kind.Escape)
                {
                    app.stop();
                }
            }
        });

        app.start();
    }
}