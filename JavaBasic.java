import java.util.Arrays;

public class JavaBasic {

    public static boolean isSafe(char[][] check,int i,int j) {
        for(int row=i-1;row>=0;row--){
            if(check[row][j]=='Q'){
                return false;
            }
        }

        for(int row=i-1,col=j+1;row>=0&&col<check[0].length;row--,col++){
            if(check[row][col]=='Q'){
                return false;
            }
        }

        for(int row=i-1,col=j-1;row>=0&&col>=0;row--,col--){
            if(check[row][col]=='Q'){
                return false;
            }
        }
        return true;
    }

    public static void Nqueen(char[][] board, int i) {

        if(i==board.length){
         printBoard(board);
         return;
        }

        for (int j = 0; j < board[0].length; j++) {
            if(isSafe(board, i, j)){
                board[i][j] = 'Q';
                Nqueen(board, i + 1);
                board[i][j] = '.';
            }
        }
        return ;

    }

    public static  void printBoard(char[][] board){
        System.out.println("-----------");
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
            System.out.print(board[i][j]);
            }
            System.out.println("");
        }

    }
    public static void main(String[] args) {
        int n = 4;
        char board[][] = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        Nqueen(board, 0);

    }
}
