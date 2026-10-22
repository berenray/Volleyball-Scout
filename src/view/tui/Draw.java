package view.tui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class Draw 
{
    public static void text(Screen screen, int x, int y, String text, Terminal.Color fg, Terminal.Color bg )
        {
            screen.putString(x, y, text, fg, bg);
        }   
    

    public static void clearArea(Screen screen, int x, int y, int width, int height)
        {
            String spaces = " ".repeat(width);
            for(int row = y; row < y + height; row++ )
                {
                    screen.putString(x, row, spaces, Theme.TEXT, Theme.BACKGROUND);
                } 
        }

    public static void box(Screen screen, int x, int y, int width, int height, String title)
        {
            String top = "+" + "-".repeat(width - 2) + "+";
            String middle = "|" + " ".repeat(width - 2 ) + "|";

            screen.putString(x, y, top, Theme.ACCENT, Theme.BACKGROUND);
            for(int row = y + 1; row < y + height - 1 ; row++ )
                {
                    screen.putString(x, row, middle, Theme.ACCENT, Theme.BACKGROUND) ;
                } 
            screen.putString(x, y + height - 1, top, Theme.ACCENT, Theme.BACKGROUND);
            if ( title != null)
            {  
                screen.putString( x + 2 , y, " " + title + " ", Theme.TEXT, Theme.BACKGROUND );
            }

        }
    public static void hintBar(Screen screen, String hints)
        {
            int columns = screen.getTerminalSize().getColumns();
            int rows = screen.getTerminalSize().getRows();

            clearArea(screen, 0, rows -1 , columns, 1);
            text(screen, 1, rows-1, hints,  Theme.TEXT, Theme.BACKGROUND);
        }
}
