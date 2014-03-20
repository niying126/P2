package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 *  Test the Maze class.
 *  Test each method in Maze class
 *
 *  @author niy1987
 *  @version Feb 14, 2014
 */
public class MazeTest extends student.TestCase
{
    private Maze m;
    private Maze m1;
    private Maze m2;
    private Maze m3;
    private Maze m4;
    private Maze m5;
    private Maze m6;
    // ----------------------------------------------------------
    /**
     * Create a new MazeTest object.
     */
    public MazeTest()
    {
        //this constructor dosen't take ant parameter
    }
    // ----------------------------------------------------------
    /**
     * Initialize the Maze object.
     */
    public void setUp()
    {
        m1 = new Maze(3);
        m = new Maze(3);
        for (int i = 0; i < 3; i++)
        {
            m.setCell(new Location(i, 1), MazeCell.WALL);
        }
        m2 = new Maze(2);
        m2.setCell(new Location(0, 1), MazeCell.WALL);
        m2.setStartLocation(new Location(1, 0));
        m3 = new Maze(2);
        m4 = new Maze(3);
        m4.setStartLocation(new Location(2, 2));
        m4.setGoalLocation(new Location(1, 1));
        m5 = new Maze(4);
        m5.setStartLocation(new Location(2, 2));
        m5.setGoalLocation(new Location(3, 3));
        m5.setCell(new Location(0, 0), MazeCell.WALL);
        m5.setCell(new Location(0, 3), MazeCell.WALL);
        m5.setCell(new Location(1, 3), MazeCell.WALL);
        m5.setCell(new Location(3, 2), MazeCell.WALL);
        m5.setCell(new Location(2, 3), MazeCell.WALL);
        m6 = new Maze(3);
        m6.setStartLocation(new Location(2, 0));
        m6.setGoalLocation(new Location(0, 2));
        m6.setCell(new Location(1, 0), MazeCell.WALL);
    }
    // ----------------------------------------------------------
    /**
     * Test the getCell() method.
     */
    public void testGetCell()
    {
        assertEquals(MazeCell.UNEXPLORED, m1.getCell(new Location(1, 1)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(0, -1)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(-1, -1)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(-1, 0)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(0, 4)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(4, 0)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(4, -1)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(-1, 4)));
        assertEquals(MazeCell.INVALID_CELL, m1.getCell(new Location(4, 4)));
    }
    // ----------------------------------------------------------
    /**
     * Test the getGoalLocation() method.
     */
    public void testGetGoalLocation()
    {
        assertEquals(new Location(2, 2), m.getGoalLocation());
    }
    // ----------------------------------------------------------
    /**
     * Test the getStartLocation() method.
     */
    public void testGetStartLocation()
    {
        assertEquals(new Location(0, 0), m.getStartLocation());
    }
    // ----------------------------------------------------------
    /**
     * Test the setCell() method.
     */
    public void testSetCell()
    {
        m1.setCell(new Location(1, 1), MazeCell.WALL);
        assertEquals(MazeCell.WALL, m1.getCell(new Location(1, 1)));
        ILocation start = m1.getStartLocation();
        m1.setCell(start, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, m1.getCell(start));
        m1.setCell(m1.getGoalLocation(), MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, m1.getCell(m1.getGoalLocation()));
    }
    // ----------------------------------------------------------
    /**
     * Test the setGoalLocation() method.
     */
    public void testSetGoalLocation()
    {
        m1.setGoalLocation(new Location(1, 2));
        assertEquals(new Location(1, 2), m1.getGoalLocation());
        m1.setCell(new Location(1, 1), MazeCell.WALL);
        m1.setGoalLocation(new Location(1, 1));
        assertEquals(MazeCell.UNEXPLORED, m1.getCell(new Location(1, 1)));
    }
    // ----------------------------------------------------------
    /**
     * Test the setStartLocation() method.
     */
    public void testSetStartLocation()
    {
        m1.setStartLocation(new Location(1, 1));
        assertEquals(new Location(1, 1), m1.getStartLocation());
        m3.setCell(new Location(0, 1), MazeCell.WALL);
        m3.setStartLocation(new Location(0, 1));
        assertEquals(MazeCell.UNEXPLORED, m3.getCell(new Location(0, 1)));
    }
    // ----------------------------------------------------------
    /**
     * Test the size() method.
     */
    public void testSize()
    {
        assertEquals(3, m1.size());
    }

    // ----------------------------------------------------------
    /**
     * Test the arrive() method.
     */
    public void testArrive()
    {
        assertEquals(true, m.arrive(new Location(2, 2)));
    }
    // ----------------------------------------------------------
    /**
     * Test the countUnexplored() method.
     */
    public void testCountUnexplored()
    {
        assertEquals(2, m.countUnexplored(new Location(1, 0)));
    }
    // ----------------------------------------------------------
    /**
     * Test the hasUnexplored() method.
     */
    public void testHasUnexplored()
    {
        Maze maze = new Maze(3);
        assertEquals("north", maze.hasUnexplored(new Location(1, 1))[0]);
        assertEquals("south", maze.hasUnexplored(new Location(1, 1))[1]);
        assertEquals("west", maze.hasUnexplored(new Location(1, 1))[2]);
        assertEquals("east", maze.hasUnexplored(new Location(1, 1))[3]);
    }
    // ----------------------------------------------------------
    /**
     * Test the solve() method.
     */
    public void testSolve()
    {
        assertEquals(null, m.solve());
        assertEquals("(1, 0) (1, 1)", m2.solve());
        assertEquals("(2, 2) (1, 2) (0, 2) (0, 1) (1, 1)", m4.solve());
        assertEquals("(2, 0) (2, 1) (1, 1) (0, 1) (0, 2)", m6.solve());
        assertEquals(null, m5.solve());
    }
}
