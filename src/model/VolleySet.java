package model;

public class VolleySet {
    private int setNumber;
    private int pointsA;
    private int pointsB;

    public VolleySet (int SetNumber){
        this.setNumber = SetNumber;
    }

    public void addPointA (){
        pointsA++;
    }

    public void addPointB (){
        pointsB++;
    }

    public boolean isFinished (){
        int target;
        if (setNumber == 5) {
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
