package model;

public class VolleySet {
    private int SetNumber;
    private int pointsA;
    private int pointsB;

    public VolleySet (int SetNumber){
        this.SetNumber = SetNumber;
    }

    public void addPointA (){
        pointsA++;
    }

    public void addPointB (){
        pointsB++;
    }

    public boolean isFinished (){
        int target;
        if (SetNumber == 5) {
            target = 15;
        } else {
            target = 25;
        }

        if(pointsA >= target && pointsA - pointsB >= 2)
        {
            return true;
        }
        if(pointsB >= target && pointsB - pointsA >= 2)
        {
            return true;
        }
        return false;
    }
}
