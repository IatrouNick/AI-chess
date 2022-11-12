package ChessClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    //all the next states of a node are to be stored in this
    //static ArrayList<int[][]> nextStates;
    //States (nodes) that have been visited
    static ArrayList<int[][]> visitedStates;
    //Board size, it will be reset in the main
    static int nRows = 8;
    static int nCols = 8;
    // static int bestValue = 0;
    static int bestBoardIdx = -1;
    static int[][] bestBoard = new int[nRows][nCols];
    //turn:1 for the 1st player
    //turn:2 for the 2nd player
    static int turn = 1;

    static boolean whiteBishopPieceAlive = true;
    // What is the sign of max and min player
    static int whiteKingPiece = 1;
    static int whiteBishopPiece = 2;
    static int blackKingPiece = 3;
    static int blackBishopPiece = 4;


    public static int getValueBoard2(int[][] board) {

        int whiteKingTile = -1;
        int whiteBishopTile = -1;
        int blackKingTile = -1;
        int blackBishopTile = -1;

        int currentWhiteKingTile = -1;
        int currentWhiteBishopTile = -1;
        int currentBlackKingTile = -1;
        int currentBlackBishopTile = -1;
//find current positions for all pieces
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (board[i][j] == whiteKingPiece) currentWhiteKingTile = concat(i, j);
                if (board[i][j] == whiteBishopPiece) currentWhiteBishopTile = concat(i, j);
                if (board[i][j] == blackKingPiece) currentBlackKingTile = concat(i, j);
                if (board[i][j] == blackBishopPiece) currentBlackBishopTile = concat(i, j);
            }
        }

        ArrayList<Integer> whiteKingPieceMoves = new ArrayList<Integer>();
        ArrayList<Integer> blackKingPieceMoves = new ArrayList<Integer>();
        ArrayList<Integer> whiteBishopPieceMoves = new ArrayList<Integer>();
        ArrayList<Integer> blackBishopPieceMoves = new ArrayList<Integer>();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (board[i][j] == whiteKingPiece) {
                    //white king moves
                    whiteKingTile = concat(i, j);
                    whiteKingPieceMoves.add(whiteKingTile - 11);
                    whiteKingPieceMoves.add(whiteKingTile + 11);
                    whiteKingPieceMoves.add(whiteKingTile - 10);
                    whiteKingPieceMoves.add(whiteKingTile + 10);
                    whiteKingPieceMoves.add(whiteKingTile - 9);
                    whiteKingPieceMoves.add(whiteKingTile + 9);
                    whiteKingPieceMoves.add(whiteKingTile - 1);
                    whiteKingPieceMoves.add(whiteKingTile + 1);

                    for (int k1 = whiteKingPieceMoves.size() - 1; k1 >= 0; k1--) {

                        int number1 = whiteKingPieceMoves.get(k1);
                        int secondDigit1 = number1 % 10;

                        if (whiteKingPieceMoves.get(k1) < 0 || whiteKingPieceMoves.get(k1) > 77 || secondDigit1 == 8 || secondDigit1 == 9
                                || (board[whiteKingPieceMoves.get(k1) / 10][whiteKingPieceMoves.get(k1) % 10] == whiteBishopPiece)) {
                            whiteKingPieceMoves.remove(k1);
                        }

                    }

                    //White bishop moves

                } else if (board[i][j] == 2) {
                    whiteBishopTile = concat(i, j);
                    for (int k = 1; k < 8; k++) {
                        if (whiteBishopTile + (k * 11) == currentWhiteKingTile || (whiteBishopTile + (k * 11)) % 10 == 9 || (whiteBishopTile + (k * 11)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(whiteBishopTile + (k * 11));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (whiteBishopTile - (k * 11) == currentWhiteKingTile || (whiteBishopTile - (k * 11)) % 10 == 9 || (whiteBishopTile - (k * 11)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(whiteBishopTile - (k * 11));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (whiteBishopTile + (k * 9) == currentWhiteKingTile || (whiteBishopTile + (k * 9)) % 10 == 9 || (whiteBishopTile + (k * 9)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(whiteBishopTile + (k * 9));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (whiteBishopTile - (k * 9) == currentWhiteKingTile || (whiteBishopTile - (k * 9)) % 10 == 9 || (whiteBishopTile - (k * 9)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(whiteBishopTile - (k * 9));
                    }
                    for (int k23 = whiteBishopPieceMoves.size() - 1; k23 >= 0; k23--) {

                        int number2 = whiteBishopPieceMoves.get(k23);
                        int secondDigit2 = number2 % 10;
                        if (whiteBishopPieceMoves.get(k23) < 0 || whiteBishopPieceMoves.get(k23) > 77 || secondDigit2 == 8 || secondDigit2 == 9
                                || (board[whiteBishopPieceMoves.get(k23) / 10][whiteBishopPieceMoves.get(k23) % 10] == whiteKingPiece)) {
                            whiteBishopPieceMoves.remove(k23);
                        }
                    }
                    //black king moves
                } else if (board[i][j] == 3) {
                    blackKingTile = concat(i, j);
                    blackKingPieceMoves.add(blackKingTile - 11);
                    blackKingPieceMoves.add(blackKingTile + 11);
                    blackKingPieceMoves.add(blackKingTile - 10);
                    blackKingPieceMoves.add(blackKingTile + 10);
                    blackKingPieceMoves.add(blackKingTile - 9);
                    blackKingPieceMoves.add(blackKingTile + 9);
                    blackKingPieceMoves.add(blackKingTile - 1);
                    blackKingPieceMoves.add(blackKingTile + 1);
                    for (int k31 = blackKingPieceMoves.size() - 1; k31 >= 0; k31--) {
                        int number3 = blackKingPieceMoves.get(k31);
                        int secondDigit3 = number3 % 10;
                        if (blackKingPieceMoves.get(k31) < 0 || blackKingPieceMoves.get(k31) > 77 || secondDigit3 == 8 || secondDigit3 == 9
                                || (board[blackKingPieceMoves.get(k31) / 10][blackKingPieceMoves.get(k31) % 10] == blackBishopPiece)) {
                            blackKingPieceMoves.remove(k31);
                        }
                    }
                    // black bishop moves
                } else if (board[i][j] == 4) {
                    blackBishopTile = concat(i, j);
                    for (int k = 1; k < 8; k++) {
                        if (blackBishopTile + (k * 11) == currentBlackKingTile || (blackBishopTile + (k * 11)) % 10 == 9 || (blackBishopTile + (k * 11)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(blackBishopTile + (k * 11));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (blackBishopTile - (k * 11) == currentBlackKingTile || (blackBishopTile - (k * 11)) % 10 == 9 || (blackBishopTile - (k * 11)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(blackBishopTile - (k * 11));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (blackBishopTile + (k * 9) == currentBlackKingTile || (blackBishopTile + (k * 9)) % 10 == 9 || (blackBishopTile + (k * 9)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(blackBishopTile + (k * 9));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (blackBishopTile - (k * 9) == currentBlackKingTile || (blackBishopTile - (k * 9)) % 10 == 9 || (blackBishopTile - (k * 9)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(blackBishopTile - (k * 9));
                    }
                    for (int k43 = blackBishopPieceMoves.size() - 1; k43 >= 0; k43--) {
                        int number4 = blackBishopPieceMoves.get(k43);
                        int secondDigit4 = number4 % 10;
                        if (blackBishopPieceMoves.get(k43) < 0 || blackBishopPieceMoves.get(k43) > 77 || secondDigit4 == 8 || secondDigit4 == 9
                                || (board[blackBishopPieceMoves.get(k43) / 10][blackBishopPieceMoves.get(k43) % 10] == blackKingPiece)) {
                            blackBishopPieceMoves.remove(k43);
                        }
                    }
                }
            }
        }

//----------------------------------------------




        int whitePlayerScore = 0, blackPlayerScore = 0;
//if bishops are killed
        if (blackBishopTile == -1)
            whitePlayerScore += 230;
        if (whiteBishopTile == -1)
            blackPlayerScore += 230;
        //We check for the position of the king. The further from the center of the board the less value it take
        // white king near the center
        int whiteI = whiteKingTile / 10;
        int whiteJ = whiteKingTile % 10;
        int whiteDistance = Math.min(whiteI, 7 - whiteI) + Math.min(whiteJ, 7 - whiteJ);
        whitePlayerScore += whiteDistance * 14;

        // black king near the center
        int blackI = blackKingTile / 10;
        int blackJ = blackKingTile % 10;
        int blackDistance = Math.min(blackI, 7 - blackI) + Math.min(blackJ, 7 - blackJ);
        blackPlayerScore += blackDistance * 14;

        // white king near black king
        if (whiteDistance != blackDistance) {
            int horizontalDistance = Math.abs(whiteJ - blackJ);
            int verticalDistance = Math.abs(whiteI - blackI);
            int distance = Math.max(horizontalDistance, verticalDistance);

            if (whiteDistance > blackDistance) {
                whitePlayerScore += (6 - distance) * 18;
            } else {
                blackPlayerScore += (6 - distance) * 18;
            }
        }

        int X = 65;
        for (int move : whiteBishopPieceMoves) {
            if (move == blackKingTile)
                whitePlayerScore += X;
        }

        for (int move : blackBishopPieceMoves) {
            if (move == whiteKingTile)
                blackPlayerScore += X;
        }

        return whitePlayerScore - blackPlayerScore;
    }

    // Check if a state is final state
    // finalState2: is a win/ loss/ draw
    // if the max player has three consecutive horizontally placed pieces, it wins
    // if the min player has three consecutive horizontally placed pieces, it wins
    static int finalState2(int[][] state) {

        if (getNextState(state, false).size() == 0) {
            boolean check = false;

            int whiteKingTile = -1;
            int whiteBishopTile = -1;
            int blackKingTile = -1;
            int blackBishopTile = -1;

            int currentWhiteKingTile = -1;
            int currentWhiteBishopTile = -1;
            int currentBlackKingTile = -1;
            int currentBlackBishopTile = -1;
//check the positions of the pieces
            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nCols; j++) {
                    if (state[i][j] == whiteKingPiece) currentWhiteKingTile = concat(i, j);
                    if (state[i][j] == whiteBishopPiece) currentWhiteBishopTile = concat(i, j);
                    if (state[i][j] == blackKingPiece) currentBlackKingTile = concat(i, j);
                    if (state[i][j] == blackBishopPiece) currentBlackBishopTile = concat(i, j);
                }
            }

            ArrayList<Integer> whiteKingPieceMoves = new ArrayList<Integer>();
            ArrayList<Integer> blackKingPieceMoves = new ArrayList<Integer>();
            ArrayList<Integer> whiteBishopPieceMoves = new ArrayList<Integer>();
            ArrayList<Integer> blackBishopPieceMoves = new ArrayList<Integer>();

            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nCols; j++) {
                    if (state[i][j] == whiteKingPiece) {
                        //white king moves
                        whiteKingTile = concat(i, j);
                        whiteKingPieceMoves.add(whiteKingTile - 11);
                        whiteKingPieceMoves.add(whiteKingTile + 11);
                        whiteKingPieceMoves.add(whiteKingTile - 10);
                        whiteKingPieceMoves.add(whiteKingTile + 10);
                        whiteKingPieceMoves.add(whiteKingTile - 9);
                        whiteKingPieceMoves.add(whiteKingTile + 9);
                        whiteKingPieceMoves.add(whiteKingTile - 1);
                        whiteKingPieceMoves.add(whiteKingTile + 1);


                        for (int k1 = whiteKingPieceMoves.size() - 1; k1 >= 0; k1--) {

                            int number1 = whiteKingPieceMoves.get(k1);
                            int secondDigit1 = number1 % 10;

                            if (whiteKingPieceMoves.get(k1) < 0 || whiteKingPieceMoves.get(k1) > 77 || secondDigit1 == 8 || secondDigit1 == 9
                                    || (state[whiteKingPieceMoves.get(k1) / 10][whiteKingPieceMoves.get(k1) % 10] == whiteBishopPiece)) {
                                whiteKingPieceMoves.remove(k1);
                            }
                        }

                    } else if (state[i][j] == 2) {
                        whiteBishopTile = concat(i, j);
                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile + (k * 11) == currentWhiteKingTile || (whiteBishopTile + (k * 11)) % 10 == 9 || (whiteBishopTile + (k * 11)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile + (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile - (k * 11) == currentWhiteKingTile || (whiteBishopTile - (k * 11)) % 10 == 9 || (whiteBishopTile - (k * 11)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile - (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile + (k * 9) == currentWhiteKingTile || (whiteBishopTile + (k * 9)) % 10 == 9 || (whiteBishopTile + (k * 9)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile + (k * 9));
                        }
                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile - (k * 9) == currentWhiteKingTile || (whiteBishopTile - (k * 9)) % 10 == 9 || (whiteBishopTile - (k * 9)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile - (k * 9));
                        }
                        for (int k23 = whiteBishopPieceMoves.size() - 1; k23 >= 0; k23--) {

                            int number2 = whiteBishopPieceMoves.get(k23);
                            int secondDigit2 = number2 % 10;

                            if (whiteBishopPieceMoves.get(k23) < 0 || whiteBishopPieceMoves.get(k23) > 77 || secondDigit2 == 8 || secondDigit2 == 9
                                    || (state[whiteBishopPieceMoves.get(k23) / 10][whiteBishopPieceMoves.get(k23) % 10] == whiteKingPiece)) {
                                whiteBishopPieceMoves.remove(k23);
                            }
                        }

                    } else if (state[i][j] == 3) {
                        blackKingTile = concat(i, j);
                        blackKingPieceMoves.add(blackKingTile - 11);
                        blackKingPieceMoves.add(blackKingTile + 11);
                        blackKingPieceMoves.add(blackKingTile - 10);
                        blackKingPieceMoves.add(blackKingTile + 10);
                        blackKingPieceMoves.add(blackKingTile - 9);
                        blackKingPieceMoves.add(blackKingTile + 9);
                        blackKingPieceMoves.add(blackKingTile - 1);
                        blackKingPieceMoves.add(blackKingTile + 1);
                        for (int k31 = blackKingPieceMoves.size() - 1; k31 >= 0; k31--) {

                            int number3 = blackKingPieceMoves.get(k31);
                            int secondDigit3 = number3 % 10;


                            if (blackKingPieceMoves.get(k31) < 0 || blackKingPieceMoves.get(k31) > 77 || secondDigit3 == 8 || secondDigit3 == 9
                                    || (state[blackKingPieceMoves.get(k31) / 10][blackKingPieceMoves.get(k31) % 10] == blackBishopPiece)) {
                                blackKingPieceMoves.remove(k31);
                            }
                        }

                    } else if (state[i][j] == 4) {
                        blackBishopTile = concat(i, j);
                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile + (k * 11) == currentBlackKingTile || (blackBishopTile + (k * 11)) % 10 == 9 || (blackBishopTile + (k * 11)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile + (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile - (k * 11) == currentBlackKingTile || (blackBishopTile - (k * 11)) % 10 == 9 || (blackBishopTile - (k * 11)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile - (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile + (k * 9) == currentBlackKingTile || (blackBishopTile + (k * 9)) % 10 == 9 || (blackBishopTile + (k * 9)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile + (k * 9));
                        }
                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile - (k * 9) == currentBlackKingTile || (blackBishopTile - (k * 9)) % 10 == 9 || (blackBishopTile - (k * 9)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile - (k * 9));
                        }

                        for (int k43 = blackBishopPieceMoves.size() - 1; k43 >= 0; k43--) {

                            int number4 = blackBishopPieceMoves.get(k43);
                            int secondDigit4 = number4 % 10;


                            if (blackBishopPieceMoves.get(k43) < 0 || blackBishopPieceMoves.get(k43) > 77 || secondDigit4 == 8 || secondDigit4 == 9
                                    || (state[blackBishopPieceMoves.get(k43) / 10][blackBishopPieceMoves.get(k43) % 10] == blackKingPiece)) {
                                blackBishopPieceMoves.remove(k43);
                            }
                        }
                    }
                }
            }


            // check for check
            for (int moveWhiteKing : whiteBishopPieceMoves) {
                if (moveWhiteKing == blackKingTile)
                    check = true;
            }

            if (check) return 1;



        }
        if (getNextState(state, true).size() == 0) {
            boolean check = false;

            int whiteKingTile = -1;
            int whiteBishopTile = -1;
            int blackKingTile = -1;
            int blackBishopTile = -1;

            int currentWhiteKingTile = -1;
            int currentWhiteBishopTile = -1;
            int currentBlackKingTile = -1;
            int currentBlackBishopTile = -1;

            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nCols; j++) {
                    if (state[i][j] == whiteKingPiece) currentWhiteKingTile = concat(i, j);
                    if (state[i][j] == whiteBishopPiece) currentWhiteBishopTile = concat(i, j);
                    if (state[i][j] == blackKingPiece) currentBlackKingTile = concat(i, j);
                    if (state[i][j] == blackBishopPiece) currentBlackBishopTile = concat(i, j);
                }
            }

            ArrayList<Integer> whiteKingPieceMoves = new ArrayList<Integer>();
            ArrayList<Integer> blackKingPieceMoves = new ArrayList<Integer>();
            ArrayList<Integer> whiteBishopPieceMoves = new ArrayList<Integer>();
            ArrayList<Integer> blackBishopPieceMoves = new ArrayList<Integer>();

            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nCols; j++) {
                    if (state[i][j] == whiteKingPiece) {
                        //white king moves
                        whiteKingTile = concat(i, j);
                        whiteKingPieceMoves.add(whiteKingTile - 11);
                        whiteKingPieceMoves.add(whiteKingTile + 11);
                        whiteKingPieceMoves.add(whiteKingTile - 10);
                        whiteKingPieceMoves.add(whiteKingTile + 10);
                        whiteKingPieceMoves.add(whiteKingTile - 9);
                        whiteKingPieceMoves.add(whiteKingTile + 9);
                        whiteKingPieceMoves.add(whiteKingTile - 1);
                        whiteKingPieceMoves.add(whiteKingTile + 1);


                        for (int k1 = whiteKingPieceMoves.size() - 1; k1 >= 0; k1--) {

                            int number1 = whiteKingPieceMoves.get(k1);
                            int secondDigit1 = number1 % 10;

                            if (whiteKingPieceMoves.get(k1) < 0 || whiteKingPieceMoves.get(k1) > 77 || secondDigit1 == 8 || secondDigit1 == 9
                                    || (state[whiteKingPieceMoves.get(k1) / 10][whiteKingPieceMoves.get(k1) % 10] == whiteBishopPiece)) {
                                whiteKingPieceMoves.remove(k1);
                            }
                        }

                    } else if (state[i][j] == 2) {
                        whiteBishopTile = concat(i, j);
                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile + (k * 11) == currentWhiteKingTile || (whiteBishopTile + (k * 11)) % 10 == 9 || (whiteBishopTile + (k * 11)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile + (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile - (k * 11) == currentWhiteKingTile || (whiteBishopTile - (k * 11)) % 10 == 9 || (whiteBishopTile - (k * 11)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile - (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile + (k * 9) == currentWhiteKingTile || (whiteBishopTile + (k * 9)) % 10 == 9 || (whiteBishopTile + (k * 9)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile + (k * 9));
                        }
                        for (int k = 1; k < 8; k++) {
                            if (whiteBishopTile - (k * 9) == currentWhiteKingTile || (whiteBishopTile - (k * 9)) % 10 == 9 || (whiteBishopTile - (k * 9)) % 10 == 8)
                                break;
                            whiteBishopPieceMoves.add(whiteBishopTile - (k * 9));
                        }

                        for (int k23 = whiteBishopPieceMoves.size() - 1; k23 >= 0; k23--) {

                            int number2 = whiteBishopPieceMoves.get(k23);
                            int secondDigit2 = number2 % 10;

                            if (whiteBishopPieceMoves.get(k23) < 0 || whiteBishopPieceMoves.get(k23) > 77 || secondDigit2 == 8 || secondDigit2 == 9
                                    || (state[whiteBishopPieceMoves.get(k23) / 10][whiteBishopPieceMoves.get(k23) % 10] == whiteKingPiece)) {
                                whiteBishopPieceMoves.remove(k23);
                            }
                        }

                    } else if (state[i][j] == 3) {
                        blackKingTile = concat(i, j);
                        blackKingPieceMoves.add(blackKingTile - 11);
                        blackKingPieceMoves.add(blackKingTile + 11);
                        blackKingPieceMoves.add(blackKingTile - 10);
                        blackKingPieceMoves.add(blackKingTile + 10);
                        blackKingPieceMoves.add(blackKingTile - 9);
                        blackKingPieceMoves.add(blackKingTile + 9);
                        blackKingPieceMoves.add(blackKingTile - 1);
                        blackKingPieceMoves.add(blackKingTile + 1);
                        for (int k31 = blackKingPieceMoves.size() - 1; k31 >= 0; k31--) {

                            int number3 = blackKingPieceMoves.get(k31);
                            int secondDigit3 = number3 % 10;


                            if (blackKingPieceMoves.get(k31) < 0 || blackKingPieceMoves.get(k31) > 77 || secondDigit3 == 8 || secondDigit3 == 9
                                    || (state[blackKingPieceMoves.get(k31) / 10][blackKingPieceMoves.get(k31) % 10] == blackBishopPiece)) {
                                blackKingPieceMoves.remove(k31);
                            }
                        }

                    } else if (state[i][j] == 4) {
                        blackBishopTile = concat(i, j);
                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile + (k * 11) == currentBlackKingTile || (blackBishopTile + (k * 11)) % 10 == 9 || (blackBishopTile + (k * 11)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile + (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile - (k * 11) == currentBlackKingTile || (blackBishopTile - (k * 11)) % 10 == 9 || (blackBishopTile - (k * 11)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile - (k * 11));
                        }

                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile + (k * 9) == currentBlackKingTile || (blackBishopTile + (k * 9)) % 10 == 9 || (blackBishopTile + (k * 9)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile + (k * 9));
                        }
                        for (int k = 1; k < 8; k++) {
                            if (blackBishopTile - (k * 9) == currentBlackKingTile || (blackBishopTile - (k * 9)) % 10 == 9 || (blackBishopTile - (k * 9)) % 10 == 8)
                                break;
                            blackBishopPieceMoves.add(blackBishopTile - (k * 9));
                        }

                        for (int k43 = blackBishopPieceMoves.size() - 1; k43 >= 0; k43--) {

                            int number4 = blackBishopPieceMoves.get(k43);
                            int secondDigit4 = number4 % 10;


                            if (blackBishopPieceMoves.get(k43) < 0 || blackBishopPieceMoves.get(k43) > 77 || secondDigit4 == 8 || secondDigit4 == 9
                                    || (state[blackBishopPieceMoves.get(k43) / 10][blackBishopPieceMoves.get(k43) % 10] == blackKingPiece)) {
                                blackBishopPieceMoves.remove(k43);
                            }
                        }
                    }
                }
            }


            // check for check
            for (int moveBlackKing : blackBishopPieceMoves) {
                if (moveBlackKing == whiteKingTile)
                    check = true;
            }

            if (check) return 2;

        }
        return -1;
    }

    // Actual minimax strategy
    public static int minimax(int[][] state, int depth, boolean maximizingPlayer) {

        int gameCode = finalState2(state);


        //Check if you are in a final state
        if (gameCode == 1)   //max wins
            return 1;
        else if (gameCode == 2)  //min wins
            return -1;
        else if (gameCode == 3) // draw
            return 0;
        else if ((gameCode == -1) && (depth == 0)) // no end state
            return getValueBoard2(state);

        else if (maximizingPlayer) {

            ArrayList<int[][]> nextStates = new ArrayList<>();
            int bestValue = Integer.MIN_VALUE;
            visitedStates.add(state);

            nextStates = getNextState(state, true);
            for (int i = 0; i < nextStates.size(); i++) {
                int v = minimax(nextStates.get(i), depth - 1, true);

                if (v > bestValue) {
                    copyBoard(bestBoard, nextStates.get(i));
                    bestBoardIdx = i;
                    bestValue = v;
                }
            }

            return bestValue;


        } else {   // Minimising player
            ArrayList<int[][]> nextStates = new ArrayList<>();
            int bestValue = Integer.MAX_VALUE;

            visitedStates.add(state);

            nextStates = getNextState(state, false);
            for (int i = 0; i < nextStates.size(); i++) {
                // depth=0 because black is playing with greedy
                int v = minimax(nextStates.get(i), 0, false);

                if (bestValue > v) {
                    bestBoardIdx = i;
                    copyBoard(bestBoard, nextStates.get(i));
                    bestValue = v;
                }
            }
            return bestValue;
        }

    }

    public static void printBoard(int[][] node) {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                System.out.print(node[i][j] + " ");
            }
            System.out.println();
        }
    }


    // get the next states of the currentState
    // the result is stored in 'nextStates'
    public static ArrayList<int[][]> getNextState(int[][] currentState, boolean maxPlayer) {
        ArrayList<int[][]> nextStates = new ArrayList<>();

        ArrayList<Integer> whiteKingPieceMoves = new ArrayList<Integer>();
        ArrayList<Integer> blackKingPieceMoves = new ArrayList<Integer>();
        ArrayList<Integer> whiteBishopPieceMoves = new ArrayList<Integer>();
        ArrayList<Integer> blackBishopPieceMoves = new ArrayList<Integer>();
//get the current positions of the pieces
        int currentWhiteKingTile = -1;
        int currentWhiteBishopTile = -1;
        int currentBlackKingTile = -1;
        int currentBlackBishopTile = -1;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (currentState[i][j] == whiteKingPiece) currentWhiteKingTile = concat(i, j);
                if (currentState[i][j] == whiteBishopPiece) currentWhiteBishopTile = concat(i, j);
                if (currentState[i][j] == blackKingPiece) currentBlackKingTile = concat(i, j);
                if (currentState[i][j] == blackBishopPiece) currentBlackBishopTile = concat(i, j);
            }
        }

        // generate the moves for each piece
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                int[][] node = new int[nRows][nCols];


                if (currentState[i][j] == whiteKingPiece) {
                    //white king moves
                    int tile1 = concat(i, j);
                    whiteKingPieceMoves.add(tile1 - 11);
                    whiteKingPieceMoves.add(tile1 + 11);
                    whiteKingPieceMoves.add(tile1 - 10);
                    whiteKingPieceMoves.add(tile1 + 10);
                    whiteKingPieceMoves.add(tile1 - 9);
                    whiteKingPieceMoves.add(tile1 + 9);
                    whiteKingPieceMoves.add(tile1 - 1);
                    whiteKingPieceMoves.add(tile1 + 1);

                    // remove out of bounds
                    for (int k11 = whiteKingPieceMoves.size() - 1; k11 >= 0; k11--) {

                        int number1 = whiteKingPieceMoves.get(k11);
                        int secondDigit11 = number1 % 10;
                        int secondDigit12 = number1 / 10;

                        if (whiteKingPieceMoves.get(k11) < 0 || whiteKingPieceMoves.get(k11) > 77 || secondDigit11 == 8 || secondDigit11 == 9
                                || (currentState[secondDigit12][secondDigit11] == whiteBishopPiece)) {
                            whiteKingPieceMoves.remove(k11);
                        }
                    }
                    //white bishop moves
                } else if (currentState[i][j] == whiteBishopPiece) {
                    int currentTile = concat(i, j);
                    for (int k = 1; k < 8; k++) {
                        if (currentTile + (k * 11) == currentWhiteKingTile || (currentTile + (k * 11)) % 10 == 9 || (currentTile + (k * 11)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(currentTile + (k * 11));
                    }

                    for (int k = 1; k < 8; k++) {
                        if (currentTile - (k * 11) == currentWhiteKingTile || (currentTile - (k * 11)) % 10 == 9 || (currentTile - (k * 11)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(currentTile - (k * 11));
                    }

                    for (int k = 1; k < 8; k++) {
                        if (currentTile + (k * 9) == currentWhiteKingTile || (currentTile + (k * 9)) % 10 == 9 || (currentTile + (k * 9)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(currentTile + (k * 9));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (currentTile - (k * 9) == currentWhiteKingTile || (currentTile - (k * 9)) % 10 == 9 || (currentTile - (k * 9)) % 10 == 8)
                            break;
                        whiteBishopPieceMoves.add(currentTile - (k * 9));
                    }

                    // remove out of the chess board moves
                    for (int k23 = whiteBishopPieceMoves.size() - 1; k23 >= 0; k23--) {
                        int number2 = whiteBishopPieceMoves.get(k23);
                        int secondDigit21 = number2 % 10;
                        int secondDigit22 = number2 / 10;

                        if (whiteBishopPieceMoves.get(k23) < 0 || whiteBishopPieceMoves.get(k23) > 77 || secondDigit21 == 8 || secondDigit21 == 9
                                || (currentState[secondDigit22][secondDigit21] == whiteKingPiece)) {
                            whiteBishopPieceMoves.remove(k23);
                        }
                    }
                }
                // generate moves for black player
                if (currentState[i][j] == blackKingPiece) {
                    int tile3 = concat(i, j);
                    blackKingPieceMoves.add(tile3 - 11);
                    blackKingPieceMoves.add(tile3 + 11);
                    blackKingPieceMoves.add(tile3 - 10);
                    blackKingPieceMoves.add(tile3 + 10);
                    blackKingPieceMoves.add(tile3 - 9);
                    blackKingPieceMoves.add(tile3 + 9);
                    blackKingPieceMoves.add(tile3 - 1);
                    blackKingPieceMoves.add(tile3 + 1);

                    // remove out of bounds
                    for (int k11 = blackKingPieceMoves.size() - 1; k11 >= 0; k11--) {

                        int number1 = blackKingPieceMoves.get(k11);
                        int secondDigit11 = number1 % 10;
                        int secondDigit12 = number1 / 10;

                        if (blackKingPieceMoves.get(k11) < 0 || blackKingPieceMoves.get(k11) > 77 || secondDigit11 == 8 || secondDigit11 == 9
                                || (currentState[secondDigit12][secondDigit11] == blackBishopPiece)) {
                            blackKingPieceMoves.remove(k11);
                        }
                    }

                    //blackBishopPiece moves
                } else if (currentState[i][j] == blackBishopPiece) {

                    int currentTile = concat(i, j);
                    for (int k = 1; k < 8; k++) {
                        if (currentTile + (k * 11) == currentWhiteKingTile || (currentTile + (k * 11)) % 10 == 9 || (currentTile + (k * 11)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(currentTile + (k * 11));
                    }

                    for (int k = 1; k < 8; k++) {
                        if (currentTile - (k * 11) == currentWhiteKingTile || (currentTile - (k * 11)) % 10 == 9 || (currentTile - (k * 11)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(currentTile - (k * 11));
                    }

                    for (int k = 1; k < 8; k++) {
                        if (currentTile + (k * 9) == currentWhiteKingTile || (currentTile + (k * 9)) % 10 == 9 || (currentTile + (k * 9)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(currentTile + (k * 9));
                    }
                    for (int k = 1; k < 8; k++) {
                        if (currentTile - (k * 9) == currentWhiteKingTile || (currentTile - (k * 9)) % 10 == 9 || (currentTile - (k * 9)) % 10 == 8)
                            break;
                        blackBishopPieceMoves.add(currentTile - (k * 9));
                    }

                    // remove out of the chess board moves

                    for (int k43 = blackBishopPieceMoves.size() - 1; k43 >= 0; k43--) {
                        int number4 = blackBishopPieceMoves.get(k43);
                        int secondDigit41 = number4 % 10;
                        int secondDigit42 = number4 / 10;

                        if (blackBishopPieceMoves.get(k43) < 0 || blackBishopPieceMoves.get(k43) > 77 || secondDigit41 == 8 || secondDigit41 == 9
                                || (currentState[secondDigit42][secondDigit41] == blackKingPiece)) {
                            blackBishopPieceMoves.remove(k43);
                        }
                    }

                }
            }
        }

        // make all moves and add next states
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                // If the square is empty

                if (maxPlayer) {
                    if (currentState[i][j] == whiteKingPiece) {
                        for (int whiteKingPieceMove : whiteKingPieceMoves) {
                            boolean whiteKingIllegalMove = false;
                            // check for check
                            for (int move : blackBishopPieceMoves) {
                                if (move == whiteKingPieceMove)
                                    whiteKingIllegalMove = true;
                            }
                            for (int move : blackKingPieceMoves) {
                                if (move == whiteKingPieceMove)
                                    whiteKingIllegalMove = true;
                            }
                            if (whiteKingIllegalMove){
                                continue;
                            }

                            int[][] newState = new int[nRows][nCols];
                            copyBoard(newState, currentState);

                            int moveCol = whiteKingPieceMove % 10;
                            int moveRow = whiteKingPieceMove / 10;
                            newState[i][j] = 0;
                            newState[moveRow][moveCol] = whiteKingPiece;
                            //if the move is legal for the white king add it to next state
                            if (!nextStatesContains(nextStates, newState)) {
                                nextStates.add(newState);
                            }
                        }
                    } else if (currentState[i][j] == whiteBishopPiece) {

                        for (int whiteBishopPieceMove : whiteBishopPieceMoves) {

                            int[][] newState = new int[nRows][nCols];
                            copyBoard(newState, currentState);

                            int moveCol = whiteBishopPieceMove % 10;
                            int moveRow = whiteBishopPieceMove / 10;
                            newState[i][j] = 0;
                            newState[moveRow][moveCol] = whiteBishopPiece;
                            //if the move is legal for the white bishop add it to next state
                            if (!nextStatesContains(nextStates, newState)) {
                                nextStates.add(newState);
                            }
                        }
                    }
                } else {
                    if (currentState[i][j] == blackKingPiece) {

                        for (int blackKingPieceMove : blackKingPieceMoves) {
                            boolean blackKingIllegalMove = false;

                            // check for check
                            for (int move : whiteBishopPieceMoves) {
                                if (move == blackKingPieceMove)
                                    blackKingIllegalMove= true;
                            }
                            for (int move : whiteKingPieceMoves) {
                                if (move == blackKingPieceMove)
                                    blackKingIllegalMove= true;
                            }
                            if (blackKingIllegalMove)
                                continue;


                            int[][] newState = new int[nRows][nCols];
                            copyBoard(newState, currentState);

                            int moveCol = blackKingPieceMove % 10;
                            int moveRow = blackKingPieceMove / 10;
                            newState[i][j] = 0;
                            newState[moveRow][moveCol] = blackKingPiece;
                            //if the move is legal for the black king add it to next state
                            if (!nextStatesContains(nextStates, newState)) {
                                nextStates.add(newState);
                            }
                        }
                    } else if (currentState[i][j] == blackBishopPiece) {

                        for (int blackBishopPieceMove : blackBishopPieceMoves) {

                            int[][] newState = new int[nRows][nCols];
                            copyBoard(newState, currentState);

                            int moveCol = blackBishopPieceMove % 10;
                            int moveRow = blackBishopPieceMove / 10;
                            newState[i][j] = 0;
                            newState[moveRow][moveCol] = blackBishopPiece;
                            //if the move is legal for the black bishop add it to next state
                            if (!nextStatesContains(nextStates, newState)) {
                                nextStates.add(newState);
                            }
                        }
                    }
                }
            }
        }

        return nextStates;
    }

    static int concat(int a, int b) {
        // return 10*a + b;

        // Convert both the integers to string
        String s1 = Integer.toString(a);
        String s2 = Integer.toString(b);

        // Concatenate both strings
        String s = s1 + s2;

        // Convert the concatenated string
        // to integer
        int c = Integer.parseInt(s);

        // return the formed integer
        return c;
    }

    // copy the 'fromArray' to 'toArray'
    public static void copyBoard(int[][] toArray, int[][] fromArray) {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                toArray[i][j] = fromArray[i][j];
            }
        }
    }


    // checks whether the 'newState' is in the data structure 'nextStates'
    public static boolean nextStatesContains(ArrayList<int[][]> nextStates, int newState[][]) {
        for (int[][] existingState : nextStates) {
            if (compareArrays(existingState, newState)) {
                return true;
            }
        }
        return false;
    }

    // Compares two 2-D arrays for equality
    public static boolean compareArrays(int[][] a1, int[][] a2) {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (a1[i][j] != a2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean visitedNodesContains(int nextState[][]) {
        for (int[][] existingState : visitedStates) {
            if (Arrays.equals(existingState, nextState)) {
                return true;
            }
        }
        return false;
    }

    public static <Integer> void writeToFile(File DATAFILE, String collection) {
        try (BufferedWriter file = Files.newBufferedWriter(Paths.get("Iatrou-Steps-Fall2021.data"),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            file.write(' ');
            file.write(String.valueOf(collection));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        //find the files
        File fileInitialBoard = new File("initial.board.data");
        File fileIatrou = new File("Iatrou-Steps-Fall2021.data");

        //All states visited. Used for statistical reasosn
        visitedStates = new ArrayList<>();
        int[][] newState = new int[nRows][nCols];

        //We build the chess board a1-h8
        String boardCoordinates[][] = new String[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (j == 0) {
                    boardCoordinates[i][j] = "a," + (8 - i);
                }
                if (j == 1) {
                    boardCoordinates[i][j] = "b," + (8 - i);
                }
                if (j == 2) {
                    boardCoordinates[i][j] = "c," + (8 - i);
                }
                if (j == 3) {
                    boardCoordinates[i][j] = "d," + (8 - i);
                }
                if (j == 4) {
                    boardCoordinates[i][j] = "e," + (8 - i);
                }
                if (j == 5) {
                    boardCoordinates[i][j] = "f," + (8 - i);
                }
                if (j == 6) {
                    boardCoordinates[i][j] = "g," + (8 - i);
                }
                if (j == 7) {
                    boardCoordinates[i][j] = "h," + (8 - i);
                }
            }
        }

        String wKing = null;
        String wBishop = null;
        String bKing = null;
        String bBishop = null;

        //read initial board file and get the positions of the pieces
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileInitialBoard.getAbsoluteFile()));
            String strLine;
            while ((strLine = br.readLine()) != null) {

                String[] delims = strLine.split(",");
                wKing = delims[0] + "," + delims[1];
                wBishop = delims[2].replaceAll("\\s+", "") + "," + delims[3];
                bKing = delims[4].replaceAll("\\s+", "") + "," + delims[5];
                bBishop = delims[6].replaceAll("\\s+", "") + "," + delims[7];
            }

            //Close the input stream
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.out.println(e);
        }

        //Put the pieces to our board
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (wKing.equals(boardCoordinates[i][j])) {
                    newState[i][j] = whiteKingPiece;
                }
                if (wBishop.equals(boardCoordinates[i][j])) {
                    newState[i][j] = whiteBishopPiece;
                }
                if (bKing.equals(boardCoordinates[i][j])) {
                    newState[i][j] = blackKingPiece;
                }
                if (bBishop.equals(boardCoordinates[i][j])) {
                    newState[i][j] = blackBishopPiece;
                }
            }
        }

        //write initial board
        writeToFile(fileIatrou, "\n   Initial Board\n");
        for (int i = 0; i < 8; i++) {
            int[] array = new int[8];
            for (int j = 0; j < 8; j++) {
                writeToFile(fileIatrou, String.valueOf(newState[i][j]));
            }
            writeToFile(fileIatrou, "\n");
        }
        writeToFile(fileIatrou, "\n");

        int val = 0;

        //Children of a node
        ArrayList<int[][]> children;

        //maximum game steps: for first and for second player
        int maxSteps = 30;


        // how deep min-max goes or number of plies
        int depth = 1;


        // 1st player followed by 2nd player
        for (int steps = 0; steps < maxSteps; steps++) {
            // 1st player plays according to min-max
            turn = 1;
            if (turn == 1) {
                whiteKingPiece = 1;
                whiteBishopPiece = 2;
                blackKingPiece = 3;
                blackBishopPiece = 4;

            }

            val = minimax(newState, depth, true);
            children = new ArrayList<>();
            children = getNextState(newState, true);
            // use the bestBoardIdx to obtain the "best" child of newState
            // i.e. to obtain the best board
            // newState = children.get(bestBoardIdx);
            newState = bestBoard;
            //write into the file the White player moves
            writeToFile(fileIatrou, "\n   White player\n");
            for (int i = 0; i < 8; i++) {
                int[] array = new int[8];
                for (int j = 0; j < 8; j++) {
                    writeToFile(fileIatrou, String.valueOf(newState[i][j]));
                }
                writeToFile(fileIatrou, "\n");
            }
            writeToFile(fileIatrou, "\n");

            // 2nd player plays according to a greedy strategy
            // which is essential min-max with depth 1
            turn = 2;
            if (turn == 2) {
                whiteKingPiece = 1;
                whiteBishopPiece = 2;
                blackKingPiece = 3;
                blackBishopPiece = 4;
            }

            val = minimax(newState, 1, false);
            children = new ArrayList<>();
            children = getNextState(newState, true);
            newState = bestBoard;
            //write into the file the Black player moves
            writeToFile(fileIatrou, "\n   Black player\n");
            for (int i = 0; i < 8; i++) {
                int[] array = new int[8];
                for (int j = 0; j < 8; j++) {
                    writeToFile(fileIatrou, String.valueOf(newState[i][j]));
                }
                writeToFile(fileIatrou, "\n");
            }
            writeToFile(fileIatrou, "\n");
        }
        //we check if there is a win loss or tie and we write into the file
        if (val == 1) {
            writeToFile(fileIatrou, wKing + ", " + wBishop+ ", " + bKing + ", " + bBishop +" ,win");
        } else if (val == 2) {
            writeToFile(fileIatrou, wKing + ", " + wBishop + ", " + bKing + ", " + bBishop + " ,lose");
        }else {
            writeToFile(fileIatrou, wKing + ", " + wBishop + ", " + bKing + ", " + bBishop + " ,draw");
        }
    }
}
