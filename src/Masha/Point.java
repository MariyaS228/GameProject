package Masha;

import java.util.Arrays;

public class Point {
    private static int HAS_FOX = 2;
    private static int HAS_GOOSE = 1;
    private static int EMPTY = 0;
    private static int NULL = -1;


    private int x;
    private int y;
    private int state;

    Point(int x, int y, int state){
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void showCords(){
        System.out.print(state + " ");
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int[] getCords(){
        return new int[]{x,y};
    }

    public int getState(){
        return state;
    }
    public void setState(int state){
        this.state = state;
    }

    public String toString(){
        return Arrays.toString(getCords());
    }


    public static int NULL() {
        return NULL;
    }

    public static int HAS_GOOSE() {
        return HAS_GOOSE;
    }

    public static int EMPTY() {
        return EMPTY;
    }

    public static int HAS_FOX() {
        return HAS_FOX;
    }
}
