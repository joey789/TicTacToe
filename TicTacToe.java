import java.util.Scanner;

import java.util.*;

/**
 * TicTacToe
 */
public class TicTacToe {

    public static void main(String[] args) {
        char[][] gameBoard = new char[3][3];
        for (char[] row : gameBoard) {
            Arrays.fill(row, '-');
        }

        Scanner in = new Scanner(System.in);
        String[] playerName = new String[2];

        System.out.println("Let's play Tic Tac Toe!");
        System.out.print("Player 1, what is your name? ");
        playerName[0] = in.next();

        System.out.print("Player 2, what is your name? ");
        playerName[1] = in.next();

        boolean gameStart = true;
        int winner = 0;
        boolean validPlay = false;
        printBoard(gameBoard);
        while (gameStart) {
            System.out.println(playerName[0] + "'s Turn (x)");
            do {
                validPlay = getPlayerInput(0, in, gameBoard);
            } while (!validPlay);
            printBoard(gameBoard);
            winner = getGameStatus(gameBoard);
            if (winner == -1) {
                System.out.println("It's a tie");
                System.exit(1);
            } else if (winner != 0) {
                gameStart = false;
                System.out.println("Player " + winner + " " + playerName[winner - 1] + " won!");
                System.exit(1);
            }

            System.out.println(playerName[1] + "'s Turn (o)");
            do {
                validPlay = getPlayerInput(1, in, gameBoard);
            } while (!validPlay);
            printBoard(gameBoard);
            winner = getGameStatus(gameBoard);
            if (winner == -1) {
                System.out.println("It's a tie");
                System.exit(1);
            } else if (winner != 0) {
                gameStart = false;
                System.out.println("Player " + winner + " " + playerName[winner - 1] + " won!");
                System.exit(1);
            }
        }
    }

    public static int getGameStatus(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            char[] row = gameBoard[i];
            if (row[0] != '-') {
                if (row[0] == row[1] && row[1] == row[2]) {
                    return (int) row[0] - (int) '0';
                }
            }
            if (gameBoard[0][i] != '-') {
                if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i]) {
                    return (int) gameBoard[0][i] - (int) '0';
                }
            }
        }
        if (gameBoard[0][0] != '-' && gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) {
            return (int) gameBoard[0][0] - (int) '0';
        } else if (gameBoard[0][2] != '-' && gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) {
            return (int) gameBoard[0][2] - (int) '0';
        }

        boolean hasEmptySpace = false;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[1].length; j++) {
                if (gameBoard[i][j] == '-') {
                    hasEmptySpace = true;
                    break;
                }
            }
        }
        if (!hasEmptySpace) {
            return -1;
        }

        return 0;
    }

    public static boolean getPlayerInput(int player, Scanner in, char[][] gameBoard) {
        boolean validInput = false;
        // String rowStr = "";
        // String columnStr = "";
        int row = 0;
        int column = 0;
        while (!validInput) {
            System.out.print("Enter a row number (1, 2, or 3): ");
            row = in.nextInt();
            if (row == 3 || row == 1 || row == 2) {
                validInput = true;
                // row = (int) rowStr.charAt(0);
            } else {
                System.out.println("This position is off the bounds of the board! Try again.");
            }
        }
        validInput = false;
        while (!validInput) {
            System.out.print("Enter a column number (1, 2, or 3): ");
            column = in.nextInt();
            if (column == 3 || column == 1 || column == 2) {
                validInput = true;
                // column = (int) columnStr.charAt(0);
            } else {
                System.out.println("This position is off the bounds of the board! Try again.");
            }
        }

        if (gameBoard[row - 1][column - 1] != '-') {
            System.out.println("Someone has already made a move at this position! Try again.");
            return false;
        }

        if (player == 0) {
            gameBoard[row - 1][column - 1] = '1';
        } else {
            gameBoard[row - 1][column - 1] = '2';
        }
        return true;

    }

    public static void printBoard(char[][] gameBoard) {
        if (gameBoard.length != 3) {
            throw new IllegalArgumentException("Invalid gameBoard - rows");
        }
        String boardOutput = "";
        System.out.println("   1 2 3");
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].length != 3) {
                throw new IllegalArgumentException("Invalid gameBoard - columns - row" + i);
            }
            boardOutput += (i + 1 + "  ");
            for (int j = 0; j < gameBoard.length; j++) {
                switch (gameBoard[i][j]) {
                    case '1':
                        boardOutput += "x ";
                        break;
                    case '2':
                        boardOutput += "o ";
                        break;
                    case '-':
                        boardOutput += "- ";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid gameBoard - element - " + i + j);
                }
            }
            boardOutput += "\n";

        }
        System.out.print(boardOutput);
    }
}