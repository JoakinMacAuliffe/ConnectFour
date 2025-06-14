import java.util.*;

class Scoreboard {

    private BST winTree;
    private HashST players;
    private int playedGames = 0;

    public void addGameResult(String winnerPlayerName, String loserPlayerName, boolean draw) {
        playedGames++;
        if(draw) { // En caso de empate, da lo mismo quién es winnerPlayerName y loserPlayerName, porque ninguno ganó
            players.get(winnerPlayerName).addDraw();
            players.get(loserPlayerName).addDraw();
        } else {
            players.get(winnerPlayerName).addWin();
            players.get(loserPlayerName).addLoss();
            winTree.insert(players.get(winnerPlayerName));
        }
    }

    public void registerPlayer(String playerName) {
        if(!checkPlayer(playerName)) { // Verifica si ya está registrado el jugador
            players.insert(new Player(playerName));
        }
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public boolean checkPlayer(String playerName) {
        return players.get(playerName) != null;
    }

    public int[] winRange(int low, int high) {
        List<Player> result = new ArrayList<>();
        Node<String, Player> root = players.get
    }

}