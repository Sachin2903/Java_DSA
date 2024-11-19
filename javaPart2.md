# Bracktracking

##### types of backtracking

1. decision ( yes or no )
2. optimization ( get optimise one )
3. enumeration ( listing )

### backtracking on an array
TC --> O(n)  SC --> O(n)

```java
   public static void backtrackArray(int[] arr,int i){
      if(i>=arr.length){
         return;
      }
      arr[i]=i+1;
      backtrackArray(arr, i+1);
      arr[i]=arr[i]-2;
   }
```

or

```java
   public static void backtrackArray(int[] arr,int i){
      if(i<arr.length){
         arr[i]=i+1;
      backtrackArray(arr, i+1);
      arr[i]=arr[i]-2;
      }
   }
```

```java
   public static void backtrackArray(int[] arr,int i){
      if(i>=arr.length){
    return;
      }

        arr[i]=i+1;
      backtrackArray(arr, i+1);
      arr[i]=arr[i]-2;
   }
```

## String Subset 
"abc" -> a b c ab bc ac abc (extra "" null set ) 
 
 total subset =2^n or 2^n -1 ( if null set is not consider  ) work for string and array 

TC --> O(n*2^n)
total substring --> 2^n * time to cat 1 sub string
 
SC --> O(n)

```java

   public static void StringSubSet(String str, String ans, int i) {
      if (i == str.length()) {
         if (ans.length() != 0)
            System.out.println(ans);
         return;
      }

      StringSubSet(str, ans + str.charAt(i), i + 1);
      StringSubSet(str, ans, i + 1);
   }

```

## FInd & Print all permutaion of a string  "abc" -> abc,acb,bac,bca,cab,cba
for n elements -> total permutation are n!;

O(n*n!)
```java
   public static void PermutationPair(String str,String ans) {
      if(str.length()==0){
         System.out.println(ans);
         return;
      }
       
      for(int i=0;i<str.length();i++){
         char ch=str.charAt(i);
 
        String newstr=str.substring(0, i)+str.substring(i+1);
         PermutationPair(newstr, ans+ch);
      }

   }
```

## N queens
place N queens on an N*N chessboard such that no 2 queens can attack each other
N=4

all solution 
yes / no
count solution

```java

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

``` 

