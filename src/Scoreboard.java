import java.util.*;

class Scoreboard {

    private BST winTree; // <int, String>
    private HashST players; // <String, Player>
    private TreeMap<Integer, List<Player> > playersByWins; // Utilizado para almacenar una lista de jugadores que posean cierta cantidad de wins
    private int playedGames = 0;

    public Scoreboard() {
        winTree = new BST();
        players = new HashST();
        playersByWins = new TreeMap<>();
    }

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
        // Busca el entry que viene después del entry cuyo key es wins (un entry es el conjunto entre key y value)
        Map.Entry<Integer, List<Player>> entry = playersByWins.higherEntry(wins);
        if(entry == null) {
            return new Player[0];
        }
        List<Player> list = entry.getValue();
        return list.toArray(new Player[0]);
    }

    public void printScoreboard() {

        System.out.println("-=x=-=x=-=x=- SCOREBOARD -=x=-=x=-=x=-");

        for (Integer wins : playersByWins.descendingKeySet()) { // Ordena jugadores con respecto a su numero de victorias
            List<Player> playersList = playersByWins.get(wins);
            for (Player player : playersList) {
                System.out.println(player.getPlayerName() + " | Victorias: " + player.getWins() +
                        " | Derrotas: " + player.getLosses() +
                        " | Empates: " + player.getDraws());
            }
        }
    }

    public Player getPlayer(String name) {
        if (checkPlayer(name)) {
            return players.get(name);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        Scoreboard scoreboard = new Scoreboard();

        scoreboard.registerPlayer("Sergio");
        scoreboard.registerPlayer("Joakin");
        scoreboard.registerPlayer("Martin");

        for (int i = 0; i < 3; i++) {
            scoreboard.addGameResult("Sergio", "Joakin", false);
        }

        for (int i = 0; i < 6; i++) {
            scoreboard.addGameResult("Joakin", "Sergio", false);
        }

        for (int i = 0; i < 9; i++) {
            scoreboard.addGameResult("Martin", "Sergio", false);
        }

        for (int i = 0; i < 7; i++) {
            scoreboard.addGameResult("Joakin", "Martin", false);
        }

        for (int i = 0; i < 4; i++) {
            scoreboard.addGameResult("Joakin", "Martin", true);
        }

        scoreboard.printScoreboard();

        Player[] winSuccesor = scoreboard.winSuccesor(8);
        System.out.print("Siguiente jugador con más de 8 victorias: ");
        for (Player player : winSuccesor) {
            System.out.print(player.getPlayerName());
        }

        Player[] winRange = scoreboard.winRange(6, 14);
        System.out.print("\nJugadores con un rango de victorias entre 6 y 14: ");
        for (Player player : winRange) {
            System.out.print(player.getPlayerName() + " ");
        }

        System.out.println("\nTotal de partidas jugadas: " + scoreboard.getPlayedGames());

        System.out.println("Tasa de victorias de Sergio: " + scoreboard.getPlayer("Sergio").winRate());
        System.out.println("Tasa de victorias de Joakin: " + scoreboard.getPlayer("Joakin").winRate());
        System.out.println("Tasa de victorias de Martin: " + scoreboard.getPlayer("Martin").winRate());



    }

}