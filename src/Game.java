import java.util.Scanner;

public class Game{
    
    private String status;
    private String winnerPlayerName;
    private String playerNameA;
    private String playerNameB;
    private ConnectFour connectFour;
    
    Scanner sc = new Scanner(System.in);
    Game(String playerNameA, String playerNameB){

        this.connectFour = new ConnectFour();
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        
        status = "IN_PROGRESS";
        winnerPlayerName = null;
        
    }
    
    public String Play(){
        
        while(winnerPlayerName == null){
            
            System.out.println("En que fila desea jugar su ficha? (1-7)");
            int answer = sc.nextInt() - 1;
            
            connectFour.makeMove(answer);
            
            if()
            
            
        }
        
        
    }
}
