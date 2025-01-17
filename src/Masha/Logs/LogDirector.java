package Masha.Logs;

import Masha.Board;
import Masha.Point;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LogDirector {

    private static Logger defaultLog = new Logger();
    private static Point testPointFrom;
    private static Point testPointTo;
    private static Board board;

    public LogDirector(Board board){
        this.board = board;
        testPointFrom = board.getBoard()[2][3];
        testPointTo = board.getBoard()[2][4];
    }

    public static void log(){
//        defaultLog.info("test move, from " + testPointFrom.toString() + " to " + testPointTo.toString() + " | " + board.canMoveTo(testPointFrom,testPointTo));
    }

    public static void logString(String string){
        defaultLog.info(string);
    }


}
