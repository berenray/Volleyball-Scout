package view;

import java.util.List;

public interface MainMenuView
{
    void showMenu(String title, List<String> items);
    void setListener(MenuListener listener);
}