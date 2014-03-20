package cs2114.mazesolver;
import android.widget.TextView;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  niy1987
 *  @version (the current date, in the format yyyy.mm.dd)
 */
public class MazeSolverScreen extends ShapeScreen
{
    //~ Fields ................................................................
    private Maze maze;
    private boolean drawMode = false;
    private boolean eraseMode = false;
    private boolean setStartMode = false;
    private boolean setGoalMode = false;
    private TextView infoLabel;

    // ----------------------------------------------------------
    public void initialize()
    {
        float min = Math.min(getWidth(), getHeight());
        maze = new Maze(0, 0, min, min, 8);
        add(maze);
        CoverMaze[][] cover = maze.makeCover();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                add(cover[i][j]);
            }
        }
    }
    // ----------------------------------------------------------
    /**
     * Set drawMode on in order to draw walls.
     */
    public void drawWallsClicked()
    {
        eraseMode = false;
        setStartMode = false;
        setGoalMode = false;
        drawMode = true;
    }
    // ----------------------------------------------------------
    /**
     * Set eraseMode on in order to erase walls
     */
    public void eraseWallsClicked()
    {
        drawMode = false;
        setStartMode = false;
        setGoalMode = false;
        eraseMode = true;
    }
    // ----------------------------------------------------------
    /**
     * Make the animation when touching down the screen
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     */
    public void onTouchDown(float x, float y)
    {
        float min = Math.min(getWidth(), getHeight());
        float width = min / 8;
        int xCell = ((int) (x / width));
        int yCell = ((int) (y / width));
        if (drawMode == true)
        {
            maze.setCell(new Location(yCell, xCell), MazeCell.WALL);
        }
        if (eraseMode == true)
        {
            maze.setCell(new Location(yCell, xCell), MazeCell.UNEXPLORED);
        }
        if (setStartMode == true)
        {
            maze.setStartLocation(new Location(yCell, xCell));
        }
        if (setGoalMode == true)
        {
            maze.setGoalLocation(new Location(yCell, xCell));
        }
    }
    // ----------------------------------------------------------
    /**
     * Make the animation when moving on the screen
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     */
    public void onTouchMove(float x, float y)
    {
        float min = Math.min(getWidth(), getHeight());
        float width = min / 8;
        int xCell = ((int) (x / width));
        int yCell = ((int) (y / width));
        if (drawMode == true)
        {
            maze.setCell(new Location(yCell, xCell), MazeCell.WALL);
        }
        if (eraseMode == true)
        {
            maze.setCell(new Location(yCell, xCell), MazeCell.UNEXPLORED);
        }
        if (setStartMode == true)
        {
            maze.setStartLocation(new Location(yCell, xCell));
        }
        if (setGoalMode == true)
        {
            maze.setGoalLocation(new Location(yCell, xCell));
        }
    }
    // ----------------------------------------------------------
    /**
     * Set setStartMode on in order to set start location.
     */
    public void setStartClicked()
    {
        setStartMode = true;
        drawMode = false;
        setGoalMode = false;
        eraseMode = false;
    }
    // ----------------------------------------------------------
    /**
     * Set setGoalMode on in order to set goal location.
     */
    public void setGoalClicked()
    {
        setGoalMode = true;
        drawMode = false;
        setStartMode = false;
        eraseMode = false;
    }
    // ----------------------------------------------------------
    /**
     * After clicking "solve" button the maze is solved and results in
     * corresponding text view.
     */
    public void solveClicked()
    {
        String result = maze.solve();
        if (result != null)
        {
            infoLabel.setText(result);
        }
        else
        {
            infoLabel.setText("No solution was possible.");
        }
    }
    // ----------------------------------------------------------
    /**
     * Get the maze used in the test case.
     * @return the maze initialized in this class
     */
    public Maze getMaze()
    {
        return maze;
    }
}
