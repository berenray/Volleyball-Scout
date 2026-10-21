package view.tui;

import com.googlecode.lanterna.input.Key;

public interface TuiScreen
{
    void render();
    void handleKey(Key key);
}