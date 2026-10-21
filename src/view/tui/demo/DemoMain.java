package view.tui.demo;

import java.util.List;

import view.MenuListener;
import view.TableListener;
import view.dto.TeamRow;
import view.tui.TuiApp;
import view.tui.TuiLeagueTableView;
import view.tui.TuiMainMenuView;

public class DemoMain
{
    private static final List<String> MENU_ITEMS =
            List.of("Liga", "Mecz na zywo", "Ustawienia", "Wyjscie");

    public static void main(String[] args)
    {
        TuiApp app = new TuiApp();
        TuiMainMenuView menu = new TuiMainMenuView(app);
        TuiLeagueTableView table = new TuiLeagueTableView(app);

        menu.setListener(new MenuListener()
        {
            @Override
            public void onMenuItemSelected(int index)
            {
                if (index == 0)
                {
                    table.showTable(fakeTeams());
                }
                else if (index == 3)
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

        table.setListener(new TableListener()
        {
            @Override
            public void onTeamSelected(int index)
            {
                // состав команды сделаем на шаге 5
            }

            @Override
            public void onBack()
            {
                menu.showMenu("Menu glowne", MENU_ITEMS);
            }
        });

        menu.showMenu("Menu glowne", MENU_ITEMS);
        app.start();
    }

    private static List<TeamRow> fakeTeams()
    {
        return List.of(
                new TeamRow(1, "Jastrzębski Węgiel", 10, 9, 1, 28, 6, 27),
                new TeamRow(2, "Zaksa Kędzierzyn", 10, 8, 2, 26, 9, 24),
                new TeamRow(3, "Resovia Rzeszów", 10, 8, 2, 25, 10, 23),
                new TeamRow(4, "Skra Bełchatów", 10, 7, 3, 23, 13, 20),
                new TeamRow(5, "Projekt Warszawa", 10, 6, 4, 21, 15, 18),
                new TeamRow(6, "Trefl Gdańsk", 10, 6, 4, 20, 17, 17),
                new TeamRow(7, "Aluron Zawiercie", 10, 5, 5, 19, 19, 15),
                new TeamRow(8, "Indykpol Olsztyn", 10, 4, 6, 16, 21, 12),
                new TeamRow(9, "Czarni Radom", 10, 4, 6, 15, 22, 11),
                new TeamRow(10, "Cuprum Lubin", 10, 3, 7, 12, 24, 8),
                new TeamRow(11, "Barkom Lwów", 10, 2, 8, 9, 26, 6),
                new TeamRow(12, "Ślepsk Suwałki", 10, 1, 9, 6, 28, 3));
    }
}