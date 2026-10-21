package view.tui.demo;

import java.util.List;

import view.MenuListener;
import view.tui.TuiApp;
import view.tui.TuiMainMenuView;

public class DemoMain
{
    public static void main(String[] args)
    {
        TuiApp app = new TuiApp();
        TuiMainMenuView menu = new TuiMainMenuView(app);

        menu.setListener(new MenuListener()
        {
            @Override
            public void onMenuItemSelected(int index)
            {
                if (index == 3)
                {
                    app.stop();
                }
            }

            @Override
            public void onBack()
            {
                app.stop();
            }
        });

        menu.showMenu("Menu glowne",
                List.of("Liga", "Mecz na zywo", "Ustawienia", "Wyjscie"));

        app.start();
    }
}