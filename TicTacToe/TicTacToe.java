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

    public boolean evaluateGame(int[] cell, String symbol)
    {
        boolean win = false;
        int startingX = cell[0];
        int startingY = cell[1];
        int currentX = startingX;
        int currentY = startingY; 
        int horizontal = 1;
        int vertical = 1;
        int diagonalLeft = 1;
        int diagonalRight = 1;

        while(currentX <= sizeX - 1)
        {
            currentX++;
            if (board[currentX][currentY] == symbol)
            {
                horizontal++;
            } else {
                break;
            }
        }
        if (win == true) return true;
        
        currentX = startingX;
        while(currentX >= 0)
        return false;
    }

    public string move(string axis, int direction, int x, int y, int xBounds, int yBounds)
    {
        switch(axis)
        {
            case "horizontal":

                break;
            case "vertical":
                break;
            case "diagonalLeft":
                break;
            case "diagonalRight":
                break;
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
        
        turn.next();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(4, 6);
        game.PlayRound();
    }
}