package model;

public class Player {
    private String name;
    private int number;
    private Position position;


    public Player (String name, int number, Position position){
        this.name = name;
        this.number = number;
        this.position = position;
    }

    public String getName (){return name;}
    public int getNumber (){return number;}
    public Position getPosition (){return position;}
}
