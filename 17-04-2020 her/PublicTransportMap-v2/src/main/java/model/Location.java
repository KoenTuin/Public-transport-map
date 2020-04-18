package model;

public class Location {

    private final double TRAVEL_TIME = 1.5;
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private double TravelTime(Location to){
        //from station 2,3 to 4,6 ETT = abs(2 - 4 = 2 * 1.5) + abs(3 - 6 = 3 *1.5) = 7.5 min
        int differenceX = Math.abs(x - to.x);
        int differenceY = Math.abs(y - to.y);

        return (differenceX * TRAVEL_TIME) + (differenceY * TRAVEL_TIME);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }




}
