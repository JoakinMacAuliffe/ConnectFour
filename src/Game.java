import java.util.Scanner;

public class Game{
    
    private String status;
    private String winnerPlayerName;
    private String playerNameA;
    private String playerNameB;
    private ConnectFour connectFour;
    
    Scanner sc = new Scanner(System.in);
    connectFour = new ConnectFour();
    
    Game(String playerNameA, String playerNameB){
        
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        
        status = "IN_PROGRESS";
        winnerPlayerName = NULL;
        
    }
    
    public String Play(){
        
        while(winnerPlayerName == NULL){
            
            System.out.println("En que fila desea jugar su ficha? (1-7)")
            answer = sc.nextInt() - 1;
            
            connectFour.makeMove(answer);
            
            if()
            
            
        }
        
        
    }
}
