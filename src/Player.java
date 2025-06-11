class Player {

    private String playerName;
    private int wins;
    private int draws;
    private int losses;

    public Player(String playerName, int wins, int draws, int losses) {
        this.playerName = playerName;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }

    public void addWin() {
        wins++;
    }

    public void addDraw() {
        draws++;
    }

    public void addLoss() {
        losses++;
    }

    public double winRate() {
        int gamesPlayed = wins + draws + losses;
        if(gamesPlayed != 0) {
            return (double) wins / gamesPlayed; // Se castea a double de tal manera que retorne un decimal
        } else {
            return 0;
        }
    }

}