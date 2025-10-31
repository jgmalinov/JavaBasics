import java.util.*;

public class TicTacToe
{
    public int sizeX;
    public int sizeY;
    public boolean winner = false;
    public enum Player {
        PLAYER1,
        PLAYER2;

        public Player next()
        {
            return this == PLAYER1 ? PLAYER2 : PLAYER1;
        }
    }
    public Player turn;
    public String[][] board;

    public TicTacToe(int x, int y)
    {
        this.sizeX = x;
        this.sizeY = y;
        this.board = new String[x][y];
        this.turn = Player.PLAYER1;

        int num = 1;
        for(String[] row: board)
        {
            for(int i = 0; i < y; i++)
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
        int row = (int) Math.ceil(sizeY / num) - 1;
        int column = num - 1;
        return new int[] {row, column};
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
                while (y <= yBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell == symbol)
                    {
                        runningCount++;
                    } else {
                        runningCount = 0;
                    }
                    y++;
                }
                break;
            case "vertical":
                startingX = 0;
                startingY = y;
                while (y <= xBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell == symbol)
                    {
                        runningCount++;
                    } else {
                        runningCount = 0;
                    }
                    y++;
                }
                break;
            case "diagonalLeft":
                stepsX = xBounds - x;
                stepsY = y + 1; 
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
                    if (cell == symbol)
                    {
                        runningCount++;
                    } else {
                        runningCount = 0;
                    }
                    startingX--;
                    startingY++;
                }
                break;
            case "diagonalRight":
                stepsX = x - 1;
                stepsY = y - 1; 
                if (stepsX < stepsY)
                {
                    startingX = 0;
                    startingY = y - stepsX;
                } else {
                    startingX = x - stepsY;
                    startingY = 0;
                while(startingX <= xBounds && startingY <= yBounds)
                {
                    String cell = board[startingX][startingY];
                    if (cell == symbol)
                    {
                        runningCount++;
                    } else {
                        runningCount = 0;
                    }
                    startingX++;
                    startingY++;
                }
                break;
            }
        }
        if (runningCount >= 3)
        {
            return true;
        } else {
            return false;
        }
    }

    public void PlayRound()
    {
        Scanner scn = new Scanner(System.in);
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
            System.out.print(turn.toString() + "WINS!");
            System.exit(0);
        } else {
            turn.next();
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