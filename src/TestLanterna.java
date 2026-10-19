import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class TestLanterna {
    public static void main(String[] args) throws Exception {
        Screen screen = TerminalFacade.createScreen();
        screen.startScreen();
        screen.putString(4, 2, "VolleyScout dziala!", 
            Terminal.Color.YELLOW, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
        screen.putString(4, 4, "Nacisnij dowolny klawisz...", 
            Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();

        while (screen.readInput() == null) {
            Thread.sleep(100);
        }
        screen.stopScreen();
    }
}