public class Main {
    public static void main(String[] args) {

        System.out.println("БУДУЩАЯ ИГРА \n");
        Board board = new Board();
        board.showBoardNumbers();
        System.out.println();
        board.showBoardStars();

        System.out.println(board.canMoveTo(new Point(5,3,2),new Point(3,2,0)));
    }
}
