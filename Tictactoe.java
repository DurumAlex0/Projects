package tictactoe;

import java.util.Scanner;

public class Tictactoe {

    // Prints the board
    public static void printBoard(char[][] b) {
        System.out.println();
        System.out.println(" " + b[0][0] + " | " + b[0][1] + " | " + b[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + b[1][0] + " | " + b[1][1] + " | " + b[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + b[2][0] + " | " + b[2][1] + " | " + b[2][2]);
        System.out.println();
    }

    // Countdown before game starts
    public static void countDown(Scanner sc) throws InterruptedException {
        System.out.println("Are you ready? Input a number for a counter to start the game! (Don't make it too long...)");
        int seconds = sc.nextInt();

        for (int i = seconds; i > 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("GO!\n");
    }

    // Checks if a player has won
    public static boolean checkWin(char[][] b, char player) {
        // Rows
        for (int r = 0; r < 3; r++) {
            if (b[r][0] == player && b[r][1] == player && b[r][2] == player) return true;
        }

        // Columns
        for (int c = 0; c < 3; c++) {
            if (b[0][c] == player && b[1][c] == player && b[2][c] == player) return true;
        }

        // Diagonals
        if (b[0][0] == player && b[1][1] == player && b[2][2] == player) return true;
        if (b[0][2] == player && b[1][1] == player && b[2][0] == player) return true;

        return false;
    }

    // Checks if the board is full (draw)
    public static boolean isDraw(char[][] b) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (b[r][c] == ' ') return false; // found an empty spot
            }
        }
        return true; // no empty spots
    }

    // Gets a valid move and places the current player's mark
    public static void placeMove(Scanner sc, char[][] b, char currentPlayer) {
        boolean placed = false;

        while (!placed) {
            System.out.println(currentPlayer + "'s turn.");
            System.out.print("Pick a row (1-3): ");
            int playerRow = sc.nextInt();

            System.out.print("Pick a column (1-3): ");
            int playerCol = sc.nextInt();

            int row = playerRow - 1;
            int col = playerCol - 1;

            // Check bounds first (prevents crashing)
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("That is not on the board. Try again.\n");
                continue;
            }

            // Check if empty
            if (b[row][col] != ' ') {
                System.out.println("That spot is already taken. Try again.\n");
                continue;
            }

            // Place the move
            b[row][col] = currentPlayer;
            placed = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        countDown(sc);

        char[][] b = new char[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                b[r][c] = ' ';
            }
        }

        System.out.println("Welcome to the simple Tic-Tac-Toe game!");
        printBoard(b);

        char currentPlayer = 'X';

        while (true) {
            placeMove(sc, b, currentPlayer);
            printBoard(b);

            if (checkWin(b, currentPlayer)) {
                System.out.println(currentPlayer + " wins!");
                break;
            }

            if (isDraw(b)) {
                System.out.println("It's a draw!");
                break;
            }

            // Switch player
            if (currentPlayer == 'X') currentPlayer = 'O';
            else currentPlayer = 'X';
        }

        sc.close();
    }
}
