import java.util.*;

class Scoreboard {

    private BST winTree;
    private HashST players;
    private TreeMap<Integer, List<Player> > playersByWins = new TreeMap<>();
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
            // Añade un elemento al map sólo si no estaba añadido previamente. Asigna una lista de jugadores a cada atributo wins.
            playersByWins.computeIfAbsent(players.get(playerName).getWins(), k -> new ArrayList<>());
        }
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public boolean checkPlayer(String playerName) {
        return players.get(playerName) != null;
    }

    public Player[] winRange(int low, int high) { // Aquí se utiliza el treemap debido a que resulta más fácil buscar en un rango específico
        List<Player> result = new ArrayList<>();
        for(List<Player> list : playersByWins.subMap(low, true, high, true).values()) {
            result.addAll(list);
        }
        // En estricto rigor, tendría más sentido retornar la lista result, pero las instrucciones dicen que hay que retornar
        // un arreglo, por tanto hay que copiar la lista al arreglo...
        Player[] array = new Player[result.size()];
        for(int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        return array;
    }

}