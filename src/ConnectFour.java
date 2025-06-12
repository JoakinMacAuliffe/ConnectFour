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
    
    public char isGameOver(){
        return ' ';
    }

    public static void main(String[] args) {

    }

}
