import java.util.*;

class Scoreboard {

    private BST winTree; // <int, String>
    private HashST players; // <String, Player>
    private TreeMap<Integer, List<Player> > playersByWins = new TreeMap<>(); // Utilizado para almacenar una lista de jugadores que posean cierta cantidad de wins
    private int playedGames = 0;

    public void addGameResult(String winnerPlayerName, String loserPlayerName, boolean draw) {
        playedGames++;
        if(draw) { // En caso de empate, da lo mismo quién es winnerPlayerName y loserPlayerName, porque ninguno ganó
            players.get(winnerPlayerName).addDraw();
            players.get(loserPlayerName).addDraw();
        } else {
            Player winner = players.get(winnerPlayerName);
            Player loser = players.get(loserPlayerName);

            // El siguiente código actualiza el treemap, eliminando al ganador de la lista correspondiente a su número
            // anterior de victorias, pues al haber ganado, esta se incrementa en 1 (leer atributo playersByWins)
            int oldWins = winner.getWins(); // Obtener key del treemap
            List<Player> oldList = playersByWins.get(oldWins); // Obtener value del treemap
            if(oldList != null) {
                oldList.remove(winner);
                if(oldList.isEmpty()) {
                    playersByWins.remove(oldWins); // Elimina tanto el key como el value del map
                }
            }

            winner.addWin();
            loser.addLoss();

            // Añade al ganador a su respectiva lista en el treemap, de no existir, crea una
            int newWins = winner.getWins();
            playersByWins.computeIfAbsent(newWins, k -> new ArrayList<>()).add(winner);

            winTree.insert(players.get(winnerPlayerName)); // Añade ganador al BST
        }
    }

    public void registerPlayer(String playerName) {
        if(!checkPlayer(playerName)) { // Verifica si ya está registrado el jugador
            Player newPlayer = new Player(playerName);
            players.insert(newPlayer);
            // Añade un elemento al map sólo si no estaba añadido previamente. Asigna una lista de jugadores a cada atributo wins.
            playersByWins.computeIfAbsent(newPlayer.getWins(), k -> new ArrayList<>()).add(newPlayer); // O(log n)
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
        for(List<Player> list : playersByWins.subMap(low, true, high, true).values()) { // O(log n + k), donde k es el número de resultados
            result.addAll(list);
        }
        return result.toArray(new Player[0]);
    }

    public Player[] winSuccesor(int wins) {
        Map.Entry<Integer, List<Player>> entry = playersByWins.higherEntry(wins);
        if(entry == null) {
            return new Player[0];
        }
        List<Player> list = entry.getValue();
        return list.toArray(new Player[0]);
    }

}