import java.util.*;

public class TicTacToe
{
    public int sizeX;
    public int sizeY;
    public boolean winner = false;
    public enum Player {
        PLAYER1,
        PLAYER2;
    }
    public int Player1Wins;
    public int Player2Wins;
    public Player turn;
    public String[][] board;

    public TicTacToe(int x, int y)
    {
        this.sizeX = x;
        this.sizeY = y;
        this.board = new String[x][y];
        this.turn = Player.PLAYER1;
        setBoard();
    }

    public void setBoard()
    {
        turn = Player.PLAYER1;
        int num = 1;
        for(String[] row: board)
        {
            for(int i = 0; i < sizeY; i++)
            {
                row[i] = Integer.toString(num);
                num++;
            }
        }
    }

    public void printBoard()
    {
        StringBuilder sb = new StringBuilder();
        String[] frameworkArr = new String[sizeY];
        Arrays.fill(frameworkArr, " --- ");
        String framework = "|" + String.join("|", frameworkArr) + "|";
        String dividerLine = "\n|" + "-".repeat((sizeY * 5 + sizeY + 1) - 2) + "|" + "\n";
        sb.append(framework);
        sb.append("\n");

        String[] rowsFormatted = new String[sizeX];
        int rowIndex = 0;
        for(String[] row: board)
        {
            String[] cols = new String[sizeY];
            for(int i=0; i < sizeY; i++)
            {
                String val = row[i];
                String valFormatted = "  " + val + " ".repeat(3 - val.length());
                cols[i] = valFormatted;
            }
            String rowValue = "|" + String.join("|", cols) + "|";
            rowsFormatted[rowIndex] = rowValue;
            rowIndex++;
        }
        sb.append(String.join(dividerLine, rowsFormatted));
        sb.append("\n");
        sb.append(framework);
        System.out.println(sb.toString());
    }

    private int[] findCell(int num)
    {
        double row = Math.ceil((double)num / (double)sizeY) - 1;
        double column;
        if (num <= sizeY)
        {
            column = num - 1;
        } else {
            column = num - (row * sizeY + 1);
        }
        return new int[] {(int) row, (int) column};
    }

    public boolean evaluateGame(int[] cell)
    {
        int startingX = cell[0];
        int startingY = cell[1];
        boolean horizontal = check("horizontal", startingX, startingY);
        boolean vertical = check("vertical", startingX, startingY);
        boolean diagonalLeft = check("diagonalLeft", startingX, startingY);
        boolean diagonalRight = check("diagonalRight", startingX, startingY);

        if (horizontal == true || vertical == true || diagonalLeft == true || diagonalRight == true)
        {
            return true;
        } else {
            return false;
        }
    }

    public void next()
    {
        this.turn = this.turn == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
    }

    public void printLogo() {
    System.out.println("  _______ _        _______           _______           ");
    System.out.println(" |__   __(_)      |__   __|         |__   __|          ");
    System.out.println("    | |   _  ___     | | __ _  ___     | | ___   ___   ");
    System.out.println("    | |  | |/ __|    | |/ _` |/ __|    | |/ _ \\ / _ \\  ");
    System.out.println("    | |  | | (__     | | (_| | (__     | | (_) | (_) | ");
    System.out.println("    |_|  |_|\\___|    |_|\\__,_|\\___|    |_|\\___/ \\___/  ");
    System.out.println("                                                      ");
    System.out.println("               Welcome to TicTacToe!                  ");
    System.out.println("------------------------------------------------------");
}


public void printTally() {
    System.out.println();
    System.out.println("========== SCORE TALLY ==========");
    System.out.println(" Player 1 Wins: " + Player1Wins);
    System.out.println(" Player 2 Wins: " + Player2Wins);
    System.out.println("=================================");
    System.out.println();
}

public boolean newGame() {
    System.out.println();
    System.out.println("========== Continue? Y/N ==========");
    Scanner scn = new Scanner(System.in);
    while (true)
    {
        String inpt = scn.nextLine().trim().toUpperCase();
        if (inpt.equals("Y")){
            return true;
        } else if(inpt.equals("N")) {
            return false;
        } else {
            System.out.println("You must select either 'Y' or 'N");
        }
    }
}


    public void clearConsole() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
}

    public boolean check(String axis, int x, int y)
    {
        String symbol = turn == Player.PLAYER1 ? "X" : "O";
        int xBounds = sizeX - 1;
        int yBounds = sizeY - 1;
        int startingX;
        int startingY;
        int stepsX;
        int stepsY;
        int runningCount = 0;
        switch(axis)
        {
            case "horizontal":
                startingX = x;
                startingY = 0;
                while (startingY <= yBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell.equals(symbol))
                    {
                        runningCount++;
                        if (runningCount == 3)
                        {
                            return true;
                        }
                    } else {
                        runningCount = 0;
                    }
                    startingY++;
                }
                break;
            case "vertical":
                startingX = 0;
                startingY = y;
                while (startingX <= xBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell.equals(symbol))
                    {
                        runningCount++;
                        if (runningCount == 3)
                        {
                            return true;
                        }
                    } else {
                        runningCount = 0;
                    }
                    startingX++;
                }
                break;
            case "diagonalLeft":
                stepsX = x;
                stepsY = y; 
                if (stepsX < stepsY)
                {
                    startingX = 0;
                    startingY = y - stepsX;
                } else {
                    startingX = x - stepsY;
                    startingY = 0;
                }

                while(startingX <= xBounds && startingY <= yBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell.equals(symbol))
                    {
                        runningCount++;
                        if (runningCount == 3)
                        {
                            return true;
                        }
                    } else {
                        runningCount = 0;
                    }
                    startingX++;
                    startingY++;
                }
                break;
            case "diagonalRight":
                stepsX = xBounds - x;
                stepsY = y; 
                if (stepsX < stepsY)
                {
                    startingX = xBounds;
                    startingY = y - stepsX;
                } else {
                    startingX = x + stepsY;
                    startingY = 0;
                }
                while(startingX >= 0 && startingY <= yBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell.equals(symbol))
                    {
                        runningCount++;
                        if (runningCount == 3)
                        {
                            return true;
                        }
                    } else {
                        runningCount = 0;
                    }
                    startingX--;
                    startingY++;
                }
                break;
        }
        return false;
    }

    public void PlayRound()
    {
        Scanner scn = new Scanner(System.in);
        clearConsole();
        printLogo();
        System.out.println(turn + "'s turn!");
        printBoard();
        System.out.println("Make your move!");
        int boardNum = 0;

        while (boardNum == 0)
        {
            try {
                boardNum = scn.nextInt();    
            } catch (Exception e) {
                System.out.println("You must choose one of the numbered slots!");
            }
        }

        
        int[] cell = findCell(boardNum);
        if (turn == Player.PLAYER1)
        {
            board[cell[0]][cell[1]] = "X";
        } else {
            board[cell[0]][cell[1]] = "O";
        }
        
        winner = evaluateGame(cell);
        if (winner)
        {
            if (turn == Player.PLAYER1) {
                Player1Wins++;
            } else {
                Player2Wins++;
            }
            clearConsole();
            System.out.print(turn.toString() + "WINS!\n");
            printTally();
            printBoard();
            boolean continuePlaying =  newGame();
            if (continuePlaying)
            {
                setBoard();
                turn = Player.PLAYER2;
                next();
            } else {
                System.exit(0);
            }
        } else {
            next();
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(4, 6);
        while (true)
        {
            game.PlayRound();
        }
    }
}