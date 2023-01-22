import java.util.*;

public class Main {
    //I made these static because it used in  a static method
    static String playerWon = "Congratulations you won!!!";
    static String cpuWon = "Gave over, try again :(";
    static String draw = "Its a draw, try again :)";
    public static void main(String[] args) {
        //Array to store each play both of the player and the CPU
        ArrayList<Integer> personPlay = new ArrayList<>();
        ArrayList<Integer> CPUplay = new ArrayList<>();

        //An array to store characters that make up the game board
        char[][] board = {{' ', '|', ' ', '|', ' '},
                {'_', '+', '_', '+', '_'},
                {' ', '|', ' ', '|', ' '},
                {'_', '+', '_', '+', '_'},
                {' ', '|', ' ', '|', ' '}};
        printBoard(board);

        //try catch block used to prevent any exception, if string cant be parsed as integer then user didnt enter an integer
        //while loop to keep asking for value, loop breaks if value is valid
        int playerPos = 0;
        boolean valid;
        boolean endLoop = false;
        while(!endLoop) {
            do {
                valid = true;
                try {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Enter a valid position from position 1 - 9");
                    String strpos = in.nextLine();

                    playerPos = Integer.parseInt(strpos);
                    personPlay.add(playerPos);
                } catch (Exception e) {
                    valid = false;
                    System.out.print("Please enter a valid value");
                }
            } while (!valid);

            replaceCharP(playerPos, board);
            printBoard(board);
            String s = endGame(personPlay, CPUplay);
            //System.out.println();
            System.out.println(s);
            if(s.equals(playerWon) || s.equals(cpuWon) || s.equals(draw)){
                endLoop = true;
            }

            //this check if the player played is the same as the random number and also if the new random has already been played before
            if(!endLoop){
                int randNum = 0;
                Random rand = new Random();
                randNum = 1 + rand.nextInt(9);
                while (playerPos == randNum || personPlay.contains(randNum) || CPUplay.contains(randNum)) {
                    randNum = 1 + rand.nextInt(9);
                }
                replaceCharC(randNum, board);
                CPUplay.add(randNum);
                printBoard(board);
            }
        }

    }

    //This method is used to print out the board row by row
    public static void printBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void replaceCharP(int position, char[][] board){
        if(position == 1){
            board[0][0] = 'x';
        }else if(position == 2){
            board[0][2] = 'x';
        }else if(position == 3){
            board[0][4] = 'x';
        }else if(position == 4){
            board[2][0] = 'x';
        }else if(position == 5){
            board[2][2] = 'x';
        }else if(position == 6){
            board[2][4] = 'x';
        }else if(position == 7){
            board[4][0] = 'x';
        }else if(position == 8){
            board[4][2] = 'x';
        }else if(position == 9){
            board[4][4] = 'x';
        }
    }

    public static void replaceCharC(int position, char[][] board){
        if(position == 1){
            board[0][0] = '0';
        }else if(position == 2){
            board[0][2] = '0';
        }else if(position == 3){
            board[0][4] = '0';
        }else if(position == 4){
            board[2][0] = '0';
        }else if(position == 5){
            board[2][2] = '0';
        }else if(position == 6){
            board[2][4] = '0';
        }else if(position == 7){
            board[4][0] = '0';
        }else if(position == 8){
            board[4][2] = '0';
        }else if(position == 9){
            board[4][4] = '0';
        }
    }
    public static String endGame(ArrayList<Integer> personPlay, ArrayList<Integer> CPUplay){
        //the arrays class provides static methods which allows us to access the method without sung an object of the class
        List row1 = Arrays.asList(1, 2, 3);
        List row2 = Arrays.asList(4, 5, 6);
        List row3 = Arrays.asList(7, 8, 9);
        List accross1 = Arrays.asList(1, 5, 9);
        List accross2 = Arrays.asList(3, 5, 7);
        List straightd1 = Arrays.asList(1, 4, 7);
        List straightd2 = Arrays.asList(2, 5, 8);
        List straightd3 = Arrays.asList(3, 6, 9);

        //an array of list, this would help us loop through the array and check every list
        List[] end = {row1, row2, row3, accross1, accross2, straightd1, straightd2, straightd3};

        //check for each list in the array for possible wins
        for(List l : end){
            if(personPlay.containsAll(l)){
                return playerWon;
            }else if(CPUplay.containsAll(l)){
                return cpuWon;
            }else if(personPlay.size() + CPUplay.size() == 9){
                return draw;
            }
        }
        return "coke";
    }
}