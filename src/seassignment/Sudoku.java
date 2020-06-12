/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seassignment;

/**
 *
 * @author Vishal
 */
public class Sudoku {

    // Given grid to solve. Grid is stored in a 2D Array
    public static int[][] GRID_TO_SOLVE = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}};

    private int[][] sudoku;
    public static final int EMPTY = 0; // empty cell
    public static final int SIZE = 9; // size of our Sudoku grids

    public Sudoku(int[][] sudoku) {
        this.sudoku = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.sudoku[i][j] = sudoku[i][j];
            }
        }
    }

    // we check if a possible number is already in a row
    private boolean isInRow(int sudoku[][],int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[row][i] == number) {
                return true;
            }
        }

        return false;
    }

    // we check if a possible number is already in a column
    private boolean isInCol(int sudoku[][],int col, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[i][col] == number) {
                return true;
            }
        }

        return false;
    }

    // we check if a possible number is in its 3x3 box
    private boolean isInBox(int sudoku[][],int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (sudoku[i][j] == number) {
                    return true;
                }
            }
        }

        return false;
    }

    // combined method to check if a number possible to a row,col position is valid
    private boolean checkValidity(int sudoku[][],int row, int col, int digit) {
        return !isInRow(sudoku,row, digit) && !isInCol(sudoku,col, digit) && !isInBox(sudoku,row, col, digit);
    }

    // Solve method. We will use a recursive BackTracking algorithm.
    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // we search an empty cell
                if (sudoku[row][col] == EMPTY) {
                    // we try possible numbers
                    for (int number = 1; number <= SIZE; number++) {
                        if (checkValidity(this.sudoku,row, col, number)) {
                            // number ok. it respects sudoku constraints
                            sudoku[row][col] = number;

                            if (solve()) { // we start backtracking recursively
                                return true;
                            } else { // if not a solution, we empty the cell and we continue
                                sudoku[row][col] = EMPTY;
                            }
                        }
                    }

                    return false; // we return false
                }
            }
        }

        return true; // sudoku solved
    }

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + sudoku[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
        System.out.println("Sudoku grid to solve");
        sudoku.display();

        if (sudoku.solve()) {
            System.out.println("Sudoku Grid solved with simple BT");
            sudoku.display();
        } else {
            System.out.println("Unsolvable");
        }
    }
}
