package view.dto;

public record ScoreState(String teamAName, String teamBName,
        int pointsA, int pointsB,
        int setsA, int setsB,
        int setNumber, boolean servingA)
{

}