package model;

import java.util.List;
import java.util.ArrayList;

public class Team {
    private String name;
    private List<Player> players = new ArrayList<>();

    public Team (String name){
        this.name = name;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public String getName() { return name; }
    public List<Player> getPlayers() {return players;}
}
