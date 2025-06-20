import java.io.*;

class Main {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Game game = new Game("Martin", "Sergio");

        System.out.println("Cantidad de partidas a jugar: ");

        int answer = -1;
        try {
            answer = Integer.parseInt(reader.readLine());
        } catch(IOException | NumberFormatException e) {
            System.out.println("Error: Entrada invalida");
        }

        for(int i = 0; i < answer; i++) {
            game.play();
        }

        Scoreboard scoreboard = game.getScoreboard();

    }
}