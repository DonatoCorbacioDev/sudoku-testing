package it.uniba.homework1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.MethodOrderer.*;
import static org.junit.jupiter.params.provider.Arguments.of;

class SudokuTest {

    Sudoku sudoku = new Sudoku();

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("Input Program Exploration")
    class InputProgramExploration {

        @Test
        @Order(1)
        @DisplayName("isSafe Method Test: Valid Number")
        void testIsSafe_ValidNumber() {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            assertTrue(sudoku.isSafe(board, 3, 1, 5));
        }

        @Test
        @Order(2)
        @DisplayName("isSafe Method Test: Invalid Number")
        void testIsSafe_InvalidNumber() {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            assertFalse(sudoku.isSafe(board, 0, 0, 5));
        }

        @Test
        @Order(3)
        @DisplayName("isSafe Method Test: Invalid Coordinates Should Throw ArrayIndexOutOfBoundsException")
        void testIsSafe_InvalidCoordinates_ThrowsException() {

            char[][] board = new char[2][2];
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                sudoku.isSafe(board, 0, 2, 3);
            });
        }

        @Test
        @Order(4)
        @DisplayName("Helper Method Test: Valid Board")
        void testHelper_SolvableBoard() {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            assertTrue(sudoku.helper(board, 0, 0));
        }

        @Test
        @Order(5)
        @DisplayName("Helper Method Test: Invalid Board")
        void testHelper_InsolvableBoard() {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '8'}
            };

            assertFalse(sudoku.helper(board, 0, 0));
        }

        @Test
        @Order(6)
        @DisplayName("SolveSudoku Method Test: Valid Solution")
        void testSolveSudoku_ValidSolution() {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            sudoku.solvesudoku(board);

            char[][] expectedBoard = {
                    {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                    {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                    {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                    {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                    {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                    {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                    {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                    {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                    {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
            };

            assertArrayEquals(expectedBoard, board);
        }
    }


    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("Boundary Cases")
    class IdentifyBoundaryCases {

        @ParameterizedTest
        @Order(1)
        @DisplayName("isSafe Method Test: Boundary Case for row")
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
        void testIsSafe_BoundaryCaseRow(int row) {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            assertDoesNotThrow(() -> {
                sudoku.isSafe(board, row, 0, 1);
            });

        }

        @ParameterizedTest
        @Order(2)
        @DisplayName("isSafe Method Test: Boundary Case for col")
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
        void testIsSafe_BoundaryCaseCol(int col) {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            assertDoesNotThrow(() -> {
                sudoku.isSafe(board, 0, col, 1);
            });
        }

        @ParameterizedTest
        @Order(3)
        @DisplayName("Helper Method Test: Boundary Case for row and col")
        @MethodSource("boundaryCaseRowAndCol")
        void testHelper_BoundaryCaseRowAndCol(int row, int col) {

            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            assertTrue(sudoku.helper(board, row, col));
        }

        private static Stream<Object[]> boundaryCaseRowAndCol() {

            return Stream.of(
                    new Object[]{0, 0},
                    new Object[]{8, 9},
                    new Object[]{8, 8},
                    new Object[]{4, 4},
                    new Object[]{3, 5},
                    new Object[]{3, 3},
                    new Object[]{9, 9},
                    new Object[]{10, 1},
                    new Object[]{9, 10}
            );
        }
    }


    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("Devise Test Cases")
    class DeviceTestCases {

        // isSafe Method Test NullPointerException
        @ParameterizedTest
        @Order(1)
        @DisplayName("isSafe Method Test: NullPointerException")
        @MethodSource("testCasesNullPointerExceptionIsSafe")
        void shouldThrowNullPointerExceptionIsSafe(char[][] board, int row, int col, int num) {
            assertThatThrownBy(() -> sudoku.isSafe(board, row, col, num)
            ).isInstanceOf(NullPointerException.class);
        }
        static Stream<Arguments> testCasesNullPointerExceptionIsSafe() {
            return Stream.of(
                    of(null, 0, 0, 1)); // T1
        }

        // isSafe Method Test Correct Result
        @ParameterizedTest
        @Order(2)
        @DisplayName("isSafe Method Test: Devise Test Cases")
        @MethodSource("testCasesReturnCorrectResultIsSafe")
        void shouldReturnCorrectResultIsSafe(char[][] board, int row, int col, int num, boolean expected) {
            assertThat(sudoku.isSafe(board, row, col, num)).isEqualTo(expected);
        }
        static Stream<Arguments> testCasesReturnCorrectResultIsSafe() {

            char[][] emptyBoard = new char[9][9];

            char[][] pointBoard1 = {
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
            };

            char[][] pointBoard2 = {
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
            };

            char[][] solvableBoard = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            char[][] unsolvableBoard = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '6', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '2'},
                    {'.', '8', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '9', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            char[][] fullBoard = {
                    {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                    {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                    {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                    {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                    {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                    {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                    {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                    {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                    {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
            };
            return Stream.of(
                    of(emptyBoard, 0, 0, 1, true), // T2
                    of(pointBoard1, 2 , 2, 5, true), // T7
                    of(pointBoard2, 5, 6, 2, false), // T8
                    of(unsolvableBoard, 4, 2, 5, true), // T9.1
                    of(unsolvableBoard, 8, 5, 1, true), // T9.2
                    of(unsolvableBoard,  6, 4, 4, false), // T9.3
                    of(unsolvableBoard, 2, 1, 3, false), // T10.1
                    of(unsolvableBoard, 5, 0, 8, false), // T10.2
                    of(unsolvableBoard, 4, 4, 7, false), // T10.3
                    of(solvableBoard, 0, 8, 2, true), // T11.1
                    of(solvableBoard, 3, 2, 1, true), // T11.2
                    of(solvableBoard, 7, 6, 3, true), // T11.3
                    of(solvableBoard, 0, 0, 5, false), // T12.1
                    of(solvableBoard, 3, 4, 9, false), // T12.2
                    of(solvableBoard, 8, 4, 4, false), // T12.3
                    of(fullBoard, 2, 6, 5, false), // T13.1
                    of(fullBoard, 8, 2, 4, false), // T13.2
                    of(fullBoard, 7, 2, 1, false), // T13.3
                    of(fullBoard, 5, 3, 8, false), // T14.1
                    of(fullBoard, 4, 5, 6, false), // T14.2
                    of(fullBoard, 1, 2, 3, false), // T14.3
                    of(solvableBoard, 10, 15, 4, false), // T15
                    of(solvableBoard, 3, 20, 7, false), // T16
                    of(solvableBoard, 13, 6, 9, false), // T17
                    of(solvableBoard, 2, 3, 2, true)); // T18
        }

        // Helper Method Test NullPointerException
        @ParameterizedTest
        @Order(3)
        @DisplayName("Helper Method Test: NullPointerException")
        @MethodSource("testCasesNullPointerExceptionHelper")
        void shouldThrowNullPointerExceptionHelper(char[][] board, int row, int col) {
            assertThatThrownBy(() -> sudoku.helper(board, row, col)
            ).isInstanceOf(NullPointerException.class);
        }
        static Stream<Arguments> testCasesNullPointerExceptionHelper() {
            return Stream.of(
                    of(null, 4, 8)); // T3
        }

        // Helper Method Test Correct Result
        @ParameterizedTest
        @Order(4)
        @DisplayName("Helper Method Test: Devise Test Cases")
        @MethodSource("testCasesReturnCorrectResultHelper")
        void shouldReturnCorrectResultHelper(char[][] board, int row, int col, boolean expected) {
            assertThat(sudoku.helper(board, row, col)).isEqualTo(expected);
        }
        static Stream<Arguments> testCasesReturnCorrectResultHelper() {

            char[][] emptyBoard = new char[9][9];

            char[][] solvableBoard = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };
            return Stream.of(
                    of(emptyBoard, 7, 6, true), // T4
                    of(solvableBoard, 5, 3, true)); // T22
        }

        // Helper Method Test ArrayIndexOutOfBoundsException
        @ParameterizedTest
        @Order(5)
        @DisplayName("Helper Method Test: ArrayIndexOutOfBoundsException")
        @MethodSource("testCasesArrayIndexOutOfBoundsExceptionHelper")
        void shouldThrowArrayIndexOutOfBoundsExceptionHelper(char[][] board, int row, int col) {
            assertThatThrownBy(() -> sudoku.helper(board, row, col)
            ).isInstanceOf(ArrayIndexOutOfBoundsException.class);
        }
        static Stream<Arguments> testCasesArrayIndexOutOfBoundsExceptionHelper() {

            char[][] solvableBoard = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };
            return Stream.of(
                    of(solvableBoard, 10, 15), // T19
                    of(solvableBoard, 3, 20), // T20
                    of(solvableBoard, 13, 6)); // T21
        }

        // SolveSudoku Method Test NullPointerException
        @Test
        @Order(6)
        @DisplayName("SolveSudoku Method Test: NullPointer")
        void shouldThrowNullPointerExceptionSolveSudoku() {
            assertThatThrownBy(() -> sudoku.solvesudoku(null)
            ).isInstanceOf(NullPointerException.class); // T5
        }

        // SolveSudoku Method Test DoesNotThrowException
        @Test
        @Order(7)
        @DisplayName("SolveSudoku Method Test: Does Not Throw Exception")
        void shouldDoesNotThrowExceptionSolveSudoku() {
            assertDoesNotThrow(() -> sudoku.solvesudoku(new char[9][9])); // T6
        }
    }


    @Nested
    @DisplayName("Suite Test Creativity")
    class SuiteTestCreativity {

        @ParameterizedTest
        @DisplayName("isSafe Method Test: Special Characters Testing")
        @MethodSource("specialCharactersTestingIsSafe")
        void specialCharactersTestingIsSafe(char[][] board, int row, int col, int num, boolean expected) {
            assertThat(sudoku.isSafe(board, row, col, num)).isEqualTo(expected);
        }
        static Stream<Arguments> specialCharactersTestingIsSafe() {

            char[][] specialBoard = {
                    {'5', '3', '*', '.', '7', '@', '#', '!', '&'},
                    {'6', '+', '-', '1', '9', '5', '*', '%', '_'},
                    {'#', '9', '8', '(', ')', '^', '!', '6', '.'},
                    {'8', '=', '~', '`', '6', ';', ':', '?', '3'},
                    {'4', ',', '[', '8', '}', '3', ']', '>', '1'},
                    {'7', '{', '}', '/', '2', '|', '<', '>', '6'},
                    {'&', '6', '|', '#', '<', '$', '2', '8', '!'},
                    {'#', '!', '@', '4', '1', '9', '+', '&', '5'},
                    {'*', '%', '_', ';', '8', '>', ',', '7', '9'}
            };
            return Stream.of(
                    of(specialBoard, 0, 2, 4, true), // T24
                    of(specialBoard, 3, 5, 8, false), // T25
                    of(specialBoard, 5, 1, 32, true), // T26
                    of(specialBoard, 7, 8, 25, true)); // T27
        }

    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("Check Code Coverage Test")
    class checkCodeCoverageTest {

        // Extra isSafe Method Test
        @ParameterizedTest
        @Order(1)
        @DisplayName("isSafe Method Test: Check Code Coverage Test")
        @MethodSource("generatorIsSafe")
        void checkTestSudokuIsSafe(char[][] board, int row, int col, int num, boolean expected) {
            assertThat(sudoku.isSafe(board, row, col, num)).isEqualTo(expected);
        }
        static Stream<Arguments> generatorIsSafe() {

            char[][] emptyBoard = new char[9][9];

            char[][] solvableBoard = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };
            return Stream.of(
                    of(emptyBoard,10, 5, 1, false), // C1
                    of(emptyBoard, 3, 11, 2, false), // C2
                    of(solvableBoard, 3, 0, 8, false), // C3
                    of(solvableBoard, 0, 3, 3, false), // C4
                    of(solvableBoard, 3, 4, 6, false)); // C5
        }

        // Extra Helper Method Test
        @ParameterizedTest
        @Order(2)
        @DisplayName("Helper Method Test: Check Code Coverage Test")
        @MethodSource("generatorHelper")
        void checkTestSudokuHelper(char[][] board, int row, int col, boolean expected) {
            assertThat(sudoku.helper(board, row, col)).isEqualTo(expected);
        }
        static Stream<Arguments> generatorHelper() {

            char[][] emptyBoard = new char[9][9];

            char[][] solvableBoard = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            return Stream.of(
                    of(emptyBoard,9, 2, true), // C6
                    of(solvableBoard, 6, 6, true), // C7
                    of(solvableBoard, 0, 2, true)); // C8
        }
    }
}












