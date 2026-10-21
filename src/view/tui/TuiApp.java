package view.tui;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

import view.AppView;
import view.ConfirmListener;

public class TuiApp implements AppView
{
    private final Screen screen;
    private TuiScreen activeScreen;
    private boolean running;

    public TuiApp()
    {
        screen = TerminalFacade.createScreen();
    }

    public Screen getScreen()
    {
        return screen;
    }

    public void setActiveScreen(TuiScreen newScreen)
    {
        activeScreen = newScreen;
        if (running)
        {
            screen.clear();
            activeScreen.render();
            screen.refresh();
        }
    }

    @Override
    public void start()
    {
        screen.startScreen();
        running = true;

        if (activeScreen != null)
        {
            screen.clear();
            activeScreen.render();
            screen.refresh();
        }

        while (running)
        {
            Key key = screen.readInput();
            if (key == null)
            {
                try
                {
                    Thread.sleep(20);
                }
                catch (InterruptedException e)
                {
                    running = false;
                }
                continue;
            }
            if (activeScreen != null)
            {
                activeScreen.handleKey(key);
            }
            screen.refresh();
        }

        screen.stopScreen();
    }

    @Override
    public void stop()
    {
        running = false;
    }

    @Override
    public void confirm(String question, ConfirmListener listener)
    {
        // заглушка, сделаем на шаге 9
    }
}