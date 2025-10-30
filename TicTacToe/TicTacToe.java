import java.util.*;

public class TicTacToe
{
    public int sizeX;
    public int sizeY;
    public String[][] board;

    public TicTacToe(int x, int y)
    {
        this.sizeX = x;
        this.sizeY = y;
        this.board = new String[x][y];

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

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(4, 6);
        game.printBoard();
    }
}