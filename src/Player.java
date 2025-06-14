class Player {

    private String playerName;
    private int wins;
    private int draws;
    private int losses;

    public Player(String playerName) {
        this.playerName = playerName;
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

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

}