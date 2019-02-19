import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * * Class BattleShip. run: java BattleShip to play the game!
 */
class BattleShip {

    private Boolean game_lost = false;
    private String[][] playing_board = new String[10][10];
    private String SHIP_PEICE = "#";
    private String HIT_PEICE = "X";
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BattleShip Battleship = new BattleShip();

        Battleship.start_game();
        Battleship.add_ships();
        Battleship.attack();

        System.out.println("All ships sunk! \n " + "Thanks for playing!");
    }

    /**
     * * Initalise the game. Fill array and display starting message.
     * 
     * @throws IOException
     */
    public void start_game() throws IOException {

        for (String[] row : playing_board)
            Arrays.fill(row, "");

        System.out.println("--------------------------------------------\n \n" + "Battleship game started. \n"
                + "You have been set up with a 10x10 board. \n" + "Please now add your ships. \n"
                + "You can do so by specifying two coordinates \n"
                + "A ship will be created between the two coordinates you specify. \n"
                + "When you are done adding ships enter no to continue. \n"
                + "--------------------------------------------\n");

    }

    // * Private method loops user input to add ships to the board.
    private void add_ships() {
        String add_ship;
        System.out.println("Add a new ship [yes/no]? ");

        while ((add_ship = input.nextLine()).equals("yes")) {
            int xx = get_integer("Row: ");
            int xy = get_integer("Col: ");

            int yy = get_integer("Row: ");
            int yx = get_integer("Col: ");

            create_ship(xx, xy, yy, yx);
            System.out.println("Add a new ship [yes/no]? ");
            add_ship = input.nextLine();
        }
    }

    // * Private method ensures valid input
    private int get_integer(String msg) throws InputMismatchException {
        System.out.print(msg);
        int num = input.nextInt();
        if (num > 10 || num <= 0) {
            System.out.println("Please select a number between 1 and 10");
            return get_integer(msg);
        }
        return num - 1; // we do this so numbers correspond to the array.
    }

    // * Private method loops attacks whilst game is not over
    private void attack() {
        System.out.println("time to attack!");

        while (!game_lost) {
            int row = get_integer("Select row coordinate: ");
            int col = get_integer("Select col coordinate: ");

            hit_or_miss(row, col);
            game_lost = is_over();
        }
    }

    /**
     * * Private method creates a ship on the board from two coordinates. note that
     * the params suffixed with one belong to the first coordinate and vice versa.
     * 
     * @param row_one
     * @param col_one
     * @param row_two
     * @param col_two
     */
    private void create_ship(int row_one, int col_one, int row_two, int col_two) {
        if (row_one == row_two) {
            if (col_one > col_two) { // if 1-9 1-1
                int swap = col_one;
                col_one = col_two;
                col_two = swap;
            }

            // horizontal ship
            for (int i = col_one; i <= col_two; i++) {
                playing_board[row_one][i] = SHIP_PEICE;
            }

        } else if (col_one == col_two) {
            // vertical ship
            if (row_one > row_two) { // if 1-9 1-1
                int swap = row_one;
                row_one = row_two;
                row_two = swap;
            }

            for (int i = row_one; i <= row_two; i++) {
                playing_board[i][col_one] = SHIP_PEICE;
            }
        }
    }

    // * Private method handles an attack.
    private void hit_or_miss(int row, int col) {
        if (playing_board[row][col] == SHIP_PEICE) {
            playing_board[row][col] = HIT_PEICE;
            System.out.println("HIT");
        } else {
            System.out.println("MISS");
        }
    }

    // * Private method returns true if game is over.
    private boolean is_over() {
        for (int i = 0; i < playing_board.length - 1; i++) {
            for (int j = 0; j < playing_board[i].length - 1; j++) {
                if (playing_board[i][j].equals(SHIP_PEICE))
                    return false;
            }
        }
        return true;
    }
}
