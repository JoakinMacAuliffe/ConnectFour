class ConnectFour{
    
    private char[][] grid = new char[7][6];
    private char currentSymbol;
    
    public ConnectFour(){
        
        for(int i=0; i<7; i++){
            
            for(int j=0; j<6; j++){

                this.grid[i][j] = ' ';
            }
        }
        
        this.currentSymbol = 'x';
    }
    
    public Boolean makeMove(int z){
        
        if(z >= 7 || z < 0) return false;
        
        int position = 0; //posicion en la columna
        
        while(position < 6 && grid[z][position] != ' ') position++;

        if(position != 6){

            grid[z][position] = currentSymbol;
            if(currentSymbol == 'x') currentSymbol = 'o';
            else currentSymbol = 'x';

            return true;
        }
        else return false;
    }
    
    public char isGameOver(){        /*luego en la clase game, hacer un switch que decida
                                      que hacer con el char que reciba. */
        Boolean empty;

        for(int i=0; i < 7; i++){
            for(int j=0; j < 6; j++){

                if(grid[i][j] == ' ') empty = true; break;
                //si esta vacio, todavia no hay empate y se salta a la sig linea

                char symbol = grid[i][j];

                if(j+3 < 6 && grid[i][j+1] == symbol && grid[i][j+2] == symbol //busca de forma vertical
                        && grid[i][j+3] == symbol) return symbol;

                if(i+3 < 7 && grid[i+1][j] == symbol && grid[i+2][j] == symbol //busca de forma horizontal
                    && grid[i+3][j] == symbol) return symbol;

                if(i+3 < 7 && j+3 < 6 && grid[i+1][j+1] == symbol && grid[i+2][j+2] == symbol
                    && grid[i+3][j+3] == symbol) return symbol; //busca diagonal hacia arriba 

                if(i+3 < 7 && j-3 >= 0 && grid[i+1][j-1] == symbol && grid[i+2][j-2] == symbol
                    && grid[i+3][j-3] == symbol) return symbol; //busca diagonal hacia abajo
            }
        }

        if(empty) return 'c'; //continue
        return 'd'; //draw
    }

    public static void main(String[] args) {

    }

}
