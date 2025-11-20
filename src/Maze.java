import javafx.scene.layout.GridPane;

public class Maze extends GridPane{
    // After trying a few ways, and loosing track of what shapes I was working on, I used
    // this 2D array to keep track of the maze using a grid system, if this doesn't work let
    // me know and I will try to figure out another way, but with this, the maze layou can be modified
    // by either using a 1(X) or a 0 in the array
    // TODO: Find another way to add array, maybe read txt or csv file
        int X = 1; // for visualization in array
        private int[][] mazeLayout ={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, X, X, X, X, X, X, X, X, 0, X, X, X, X, X, X, X, X, 0},
            {0, X, 0, 0, X, 0, 0, 0, X, 0, X, 0, 0, 0, X, 0, 0, X, 0},
            {0, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, 0},
            {0, X, 0, 0, X, 0, X, 0, 0, 0, 0, 0, X, 0, X, 0, 0, X, 0},
            {0, X, X, X, X, 0, X, X, X, 0, X, X, X, 0, X, X, X, X, 0},
            {0, 0, 0, 0, X, 0, 0, 0, X, 0, X, 0, 0, 0, X, 0, 0, 0, 0},
            {0, 0, 0, 0, X, 0, X, X, X, X, X, X, X, 0, X, 0, 0, 0, 0},
            {0, 0, 0, 0, X, 0, X, 0, 0, X, 0, 0, X, 0, X, 0, 0, 0, 0},
            {X, X, X, X, X, X, X, 0, X, X, X, 0, X, X, X, X, X, X, X},
            {0, 0, 0, 0, X, 0, X, 0, 0, 0, 0, 0, X, 0, X, 0, 0, 0, 0},
            {0, 0, 0, 0, X, 0, X, X, X, X, X, X, X, 0, X, 0, 0, 0, 0},
            {0, 0, 0, 0, X, 0, X, 0, 0, 0, 0, 0, X, 0, X, 0, 0, 0, 0},
            {0, X, X, X, X, X, X, X, X, 0, X, X, X, X, X, X, X, X, 0},
            {0, X, 0, 0, X, 0, 0, 0, X, 0, X, 0, 0, 0, X, 0, 0, X, 0},
            {0, X, X, 0, X, X, X, X, X, X, X, X, X, X, X, 0, X, X, 0},
            {0, 0, X, 0, X, 0, X, 0, 0, 0, 0, 0, X, 0, X, 0, X, 0, 0},
            {0, X, X, X, X, 0, X, X, X, 0, X, X, X, 0, X, X, X, X, 0},
            {0, X, 0, 0, 0, 0, 0, 0, X, 0, X, 0, 0, 0, 0, 0, 0, X, 0},
            {0, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

    public Maze(){


        setupMazeBorder();
    }

    public void setupMazeBorder(){
        for(int i = 0; i < mazeLayout.length; i++){
            for(int j = 0; j < mazeLayout[i].length; j++){
                if(mazeLayout[i][j] == 0){
                    MazeWall wall = new MazeWall();
                    add(wall, j, i);
                }
            }

        }
        
    }


}
