import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightQueens {
    public static char[][] placeQueens(char[][] board) {
        return placeQueensHelper(board, 0);
    }

    private static char[][] placeQueensHelper(char[][] board, int numQueens) {
        if (numQueens == 8) {
            return board;
        }

        for (char[][] option : getValidPlacements(board)) {
            char[][] result = placeQueensHelper(option, numQueens + 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private static List<char[][]> getValidPlacements(char[][] board) {
        List<char[][]> options = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isSafe(board, i, j)) {
                    char[][] possibleBoard = new char[8][8];
                    for (int k = 0; k < 8; k++) {
                        possibleBoard[k] = Arrays.copyOf(board[k], board[k].length);
                    }
                    possibleBoard[i][j] = 'Q';

                    options.add(possibleBoard);
                }
            }
        }

        return options;
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        // Check rows
        for (int i = 0; i < 8; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check lower-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check lower-right diagonal
        for (int i = row, j = col; j >= 0 && i < 8; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j < 8; i--, j++){
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row, j = col; j < 8 && i < 8; i++, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        char[][] solution = placeQueens(new char[8][8]);
        for (char[] row : solution) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
