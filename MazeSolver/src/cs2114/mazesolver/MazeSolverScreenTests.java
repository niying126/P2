package cs2114.mazesolver;

import android.widget.*;
import sofia.graphics.ShapeView;

//-------------------------------------------------------------------------
/**
*  Write a one-sentence summary of your class here.
*  Follow it with additional details about its purpose, what abstraction
*  it represents, and how to use it.
*
*  @author  (your name here)
*  @version (type the date in the format yyyy.mm.dd)
*/
public class MazeSolverScreenTests
    extends student.AndroidTestCase<MazeSolverScreen>
{
    //~ Fields ................................................................

    private ShapeView shapeView;
    private TextView infoLabel;

    // This field will store the pixel width/height of a cell in the maze.
    private int cellSize;
    private Button drawWalls;
    private Button eraseWalls;
    private Button solve;

    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        float viewSize =
            Math.min(shapeView.getWidth(), shapeView.getHeight());
        float width = viewSize / cellSize;
        cellSize = (int) width;
        // TODO Add any other setup code that you need.
    }
    // ----------------------------------------------------------
    /**
     * Test the drawWallsClicked() method.
     */
    public void testDrawWallsClicked()
    {
        Maze maze = getScreen().getMaze();
        click(drawWalls);
        touchDownCell(3, 4);
        assertEquals(MazeCell.WALL, maze.getCell(new Location(3, 4)));
    }
    // ----------------------------------------------------------
    /**
     * Test the eraseWallsClicked() method.
     */
    public void testEraseWallsClicked()
    {
        Maze maze = getScreen().getMaze();
        click(eraseWalls);
        touchDownCell(3, 4);
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(new Location(3, 4)));
    }


    //~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }
}
