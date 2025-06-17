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

        int startingPlayer = -1;

        while(startingPlayer != 1 && startingPlayer != 2) {
            System.out.println("Seleccione quién empieza: ");
            System.out.println("1. " + playerNameA + " (" + playerA_Symbol + ")");
            System.out.println("2. " + playerNameB + " (" + playerB_Symbol + ")");
            try {
                startingPlayer = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: Entrada inválida");
            }
        }

        if(startingPlayer == 1) {
            connectFour.setCurrentSymbol(playerA_Symbol);
        } else {
            connectFour.setCurrentSymbol(playerB_Symbol);
        }

        connectFour.printBoard();

        while(winnerPlayerName == null){

            String currentPlayer;

            if(connectFour.getCurrentSymbol() == playerA_Symbol) {
                currentPlayer = playerNameA;
            } else {
                currentPlayer = playerNameB;
            }

            System.out.println("Turno de " + currentPlayer + " (" + connectFour.getCurrentSymbol() + ")");
            System.out.println("En que fila desea jugar su ficha? (1-7)");
            int answer = -1;
            try {
                answer = Integer.parseInt(reader.readLine()) - 1;
            } catch(IOException | NumberFormatException e) {
                System.out.println("Error: Entrada inválida.");
                continue;
            }
            
            if(!connectFour.makeMove(answer)) {
                System.out.println("Entrada inválida.");
                continue;
            }

            connectFour.printBoard();

            switch(connectFour.isGameOver()) {

                case 'c':
                    continue;

                case 'd':
                    System.out.println("Empate.");
                    scoreboard.addGameResult(playerNameA, playerNameB, true);
                    status = "DRAW";
                    return "";

                case 'x': // Debido a que el primer símbolo es x, se asigna X a playerNameA
                    winnerPlayerName = playerNameA;
                    System.out.println("El jugador " + winnerPlayerName + " (" + playerA_Symbol + ")" + " gana la partida!");
                    scoreboard.addGameResult(winnerPlayerName, playerNameB, false);
                    status = "VICTORY";
                    return winnerPlayerName;

                case 'o': // Gana playerNameB
                    winnerPlayerName = playerNameB;
                    System.out.println("El jugador " + winnerPlayerName + " (" + playerB_Symbol + ")" + " gana la partida!");
                    scoreboard.addGameResult(winnerPlayerName, playerNameA, false);
                    status = "VICTORY";
                    return winnerPlayerName;
            }
        }
        
        return null;

    }

    public static void main(String[] args) {
        Game game = new Game("Martin", "Sergio");
        game.play();
    }
}
