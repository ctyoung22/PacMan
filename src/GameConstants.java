public class GameConstants {
    private GameConstants() {} // Prevent instantiation

    // Game board dimensions
    public static final int TILE_SIZE = 32;
    public static final int ROW_COUNT = 21;
    public static final int COLUMN_COUNT = 19;

    // Derived dimensions
    public static final int BOARD_WIDTH = COLUMN_COUNT * TILE_SIZE; // 608
    public static final int BOARD_HEIGHT = ROW_COUNT * TILE_SIZE; // 672

    // Character speeds
    public static final int GHOST_SPEED = 1;
    public static final int PACMAN_SPEED = 1;
    public static final int PACMAN_SUPERSPEED = 2;



    // Timing constants
    public static final int GHOST_AI_DELAY = 300; // milliseconds
    public static final int GHOST_SCATTER_TIME = 2000; // milliseconds
    public static final int GHOST_CHASE_TIME = 20000; // milliseconds
    public static final int POWER_DURATION = 7000; // milliseconds
    public static final int FRIGHTENED_DURATION = 6000; //milliseconds

    //tunnel 
    public static final int TUNNEL_ROW = 9;
    public static final int SPAWN_ROW = 9;
    //public static final int INITIAL_LIVES = 3;
    //public static final int FRUIT_SPAWN_SCORE = 100;
    
}
