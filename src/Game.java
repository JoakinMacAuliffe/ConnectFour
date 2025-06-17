import java.io.*;

public class Game{
    
    private String status;
    private String winnerPlayerName;
    private String playerNameA;
    private String playerNameB;
    private ConnectFour connectFour;
    private Scoreboard scoreboard;
    private int turn = 0;

    // En estricto rigor no son necesarias estas variables, pero simplifican mucho la vista del código
    char playerA_Symbol = 'x';
    char playerB_Symbol = 'o';

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Game(String playerNameA, String playerNameB){

        this.connectFour = new ConnectFour();
        this.scoreboard = new Scoreboard();
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;

        status = "IN_PROGRESS";
        winnerPlayerName = null;

        scoreboard.registerPlayer(playerNameA);
        scoreboard.registerPlayer(playerNameB);

    }
    
    public String play(){
        
        while(winnerPlayerName == null){

            System.out.println("En que fila desea jugar su ficha? (1-7)");
            int answer = -1;
            try {
                answer = Integer.parseInt(reader.readLine()) - 1;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: Entrada inválida.");
                continue;
            }
            
            connectFour.makeMove(answer);
            
            switch(connectFour.isGameOver()) {

                case 'c':
                    turn++;
                    connectFour.printBoard(); // Imprime el tablero

                    if(turn % 2 == 0) {
                        System.out.println("Turno de " + playerNameA + " (" + playerA_Symbol + ")");
                    } else {
                        System.out.println("Turno de " + playerNameB + " (" + playerB_Symbol + ")");
                    }

                    continue;

                case 'd':
                    System.out.println("Empate.");
                    scoreboard.addGameResult(playerNameA, playerNameB, true);
                    status = "DRAW";
                    return "";

                case 'x': // Debido a que el primer símbolo es x, se asigna X a playerNameA
                    winnerPlayerName = playerNameA;
                    connectFour.printBoard();
                    System.out.println("El jugador " + winnerPlayerName + " (" + playerA_Symbol + ")" + " gana la partida!");
                    scoreboard.addGameResult(winnerPlayerName, playerNameB, false);
                    status = "VICTORY";
                    return winnerPlayerName;

                case 'o': // Gana playerNameB
                    winnerPlayerName = playerNameB;
                    connectFour.printBoard();
                    System.out.println("El jugador " + winnerPlayerName + " (" + playerB_Symbol + ")" + " gana la partida!");
                    scoreboard.addGameResult(winnerPlayerName, playerNameA, false);
                    status = "VICTORY";
                    return winnerPlayerName;
            }
        }
        
        return null;

    }

    public static void main(String[] args) {
        Game game = new Game("Sergio", "Martin");
        game.play();
    }
}
