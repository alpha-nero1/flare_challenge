import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * * Class BattleShip. run: java BattleShip to play the game!
 */
class BattleShip {

    private Boolean game_lost = false;
    private Boolean adding_ships = true;
    private String[][] playing_board = new String[10][10];
    private String SHIP_PEICE = "#";
    private String HIT_PEICE = "X";
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BattleShip Battleship = new BattleShip();

        Battleship.start_game();
    }

    public void start_game() throws IOException {

        for (String[] row : playing_board)
            Arrays.fill(row, "");

        System.out.println("Battleship game started." + "\n You have been set up with a 10x10 board."
                + "\n Please now add your ships." + "\n You can do so by specifying two coordinates e.g"
                + "\n 0-0 0-4 : creates a 5 block ship on row 0" + "\n 6-1 6-4 : creates a 4 block ship on column 6"
                + "\n When you are done adding ships enter X to continue.");

        add_ships();
        attack();

        System.out.println("Thanks for playing!");

    }

    private void add_ships() {
        String add_ship;
        System.out.println("Add a new ship [yes/no]? ");

        while ((add_ship = input.nextLine()).equals("yes")) {

            System.out.print("Row: ");
            int xx = input.nextInt();
            System.out.print("Col: ");
            int xy = input.nextInt();

            System.out.print("Row: ");
            int yy = input.nextInt();
            System.out.print("Col: ");
            int yx = input.nextInt();

            create_ship(xx, xy, yy, yx);
            System.out.println("Add a new ship [yes/no]? ");
            add_ship = input.nextLine();
        }

    }

    private void attack() {
        System.out.println("time to attack!");

        while (!game_lost) {
            System.out.print("Select row coordinate: ");
            int row = input.nextInt();

            System.out.print("Select col coordinate: ");
            int col = input.nextInt();

            hit_or_miss(row, col);

            System.out.println(Arrays.deepToString(playing_board));
            game_lost = is_over();

        }
    }

    private void create_ship(int xx, int xy, int yy, int yx) {
        System.out.println(xy + ":" + yx);
        if (xx == yy) {
            // horizontal ship

            for (int i = xy; i <= yx; i++) {
                playing_board[xx][i] = SHIP_PEICE;
            }
        } else if (xy == yx) {
            // vertical ship

            for (int i = xx; i <= yy; i++) {
                playing_board[i][xy] = SHIP_PEICE;
            }
        }
    }

    private void hit_or_miss(int row, int col) {
        if (playing_board[row][col] == SHIP_PEICE) {
            playing_board[row][col] = HIT_PEICE;
            System.out.println("HIT");
        } else {
            System.out.println("MISS");
        }
    }

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
