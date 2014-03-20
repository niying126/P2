package cs2114.mazesolver;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;
import java.util.*;
// -------------------------------------------------------------------------
/**
 *  The Maze class makes movements on the maze
 *  It implements the IMaze interface. It creates the maze object and shows how
 *  to move on the maze
 *
 *  @author niy1987
 *  @version Feb 14, 2014
 */
public class Maze extends RectangleShape implements IMaze
{
    private MazeCell[][] m;
    private int s;
    private ILocation goalLocation;
    private ILocation startLocation;
    private Stack<ILocation> solution = new Stack<ILocation>();
    private CoverMaze[][] coverMaze;
    private float right;
    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     * @param side The side of the maze
     */
    public Maze(int side)
    {
        s = side;
        m = new MazeCell[s][s];
        for (int i = 0; i < s; i++)
        {
            for (int j = 0; j < s; j++)
            {
                m[i][j] = MazeCell.UNEXPLORED;
            }
        }
        goalLocation = new Location(s - 1, s - 1);
        startLocation = new Location(0, 0);
    }
    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     * @param left The left top x coordinate of the tile
     * @param top The left top y coordinate of the tile
     * @param right The right bottom coordinate of the tile
     * @param bottom The right bottom coordinate of the tile
     * @param side The side of the maze
     */
    public Maze(float left, float top, float right, float bottom, int side)
    {
        super(left, top, right, bottom);
        s = side;
        m = new MazeCell[s][s];
        for (int i = 0; i < s; i++)
        {
            for (int j = 0; j < s; j++)
            {
                m[i][j] = MazeCell.UNEXPLORED;
            }
        }
        goalLocation = new Location(s - 1, s - 1);
        startLocation = new Location(0, 0);
        this.right = right;

    }
    // ----------------------------------------------------------
    /**
     * Create tiles on the maze.
     * @return an array of the CoverMaze
     */
    public CoverMaze[][] makeCover()
    {
        float width = right / s;
        coverMaze = new CoverMaze[s][s];
        float leftCell = 0;
        float rightCell = 0;
        float topCell = 0;
        float bottomCell = 0;
        for (int col = 0; col < s; col++)
        {
            leftCell = col * width;
            rightCell = leftCell + width;
            for (int row = 0; row < 8; row++)
            {
                topCell = row * width;
                bottomCell = topCell + width;
                coverMaze[row][col] = new CoverMaze(leftCell, topCell, rightCell, bottomCell);
                setColor(new Location(row, col));
            }
        }
        return coverMaze;
    }
    // ----------------------------------------------------------
    /**
     * Set the color in a particular cover tile.
     * @param location The location where needs to change or fill in color
     */
    public void setColor(ILocation location)
    {
        if (location.equals(getStartLocation()))
        {
            coverMaze[location.x()][location.y()].setFillColor(Color.white);
        }
        else if (location.equals(getGoalLocation()))
        {
            coverMaze[location.x()][location.y()].setFillColor(Color.yellow);
        }
        else if (getCell(location) == MazeCell.WALL)
        {
            coverMaze[location.x()][location.y()].setFillColor(Color.black);
        }
        else if (getCell(location) == MazeCell.UNEXPLORED)
        {
            coverMaze[location.x()][location.y()].setFillColor(Color.gray);
        }
        else if (getCell(location) == MazeCell.CURRENT_PATH)
        {
            coverMaze[location.x()][location.y()].setFillColor(Color.green);
        }
        else
        {
            coverMaze[location.x()][location.y()].setFillColor(Color.red);
        }
    }
    // ----------------------------------------------------------
    /**
     * Get the content of a certain cell.
     * @param l The location on the maze
     * @return The content of the cell
     */
    public MazeCell getCell(ILocation l)
    {
        int x = l.x();
        int y = l.y();
        if (x < 0 || x >= s || y < 0 || y >= s)
        {
            return MazeCell.INVALID_CELL;
        }
        else
        {
            return m[x][y];
        }
    }
    // ----------------------------------------------------------
    /**
     * Get the goal location.
     * @return The location of the goal
     */
    public ILocation getGoalLocation()
    {
        return goalLocation;
    }
    // ----------------------------------------------------------
    /**
     * Get the start location.
     * @return The location of the start point
     */
    public ILocation getStartLocation()
    {
        return startLocation;
    }
    // ----------------------------------------------------------
    /**
     * Set cell with a certain content and fill in corresponding color.
     * @param l The location of the cell
     * @param cell The desired content of the cell
     */
    public void setCell(ILocation l, MazeCell cell)
    {
        m[l.x()][l.y()] = cell;
        if ((l.equals(getStartLocation()) || l.equals(getGoalLocation()))
            && cell == MazeCell.WALL)
        {
            setCell(l, MazeCell.UNEXPLORED);
        }
        setColor(l);
    }
    // ----------------------------------------------------------
    /**
     * Set the goal location and fill in color of the goal.
     * @param l The location of the goal
     */
    public void setGoalLocation(ILocation l)
    {
        coverMaze[goalLocation.x()][goalLocation.y()].setFillColor(Color.gray);
        goalLocation = l;
        if (getCell(l) == MazeCell.WALL)
        {
            setCell(l, MazeCell.UNEXPLORED);
        }
        setColor(l);
    }
    // ----------------------------------------------------------
    /**
     * Set the start location and fill in color of the start.
     * @param l The location of the start position
     */
    public void setStartLocation(ILocation l)
    {
        coverMaze[startLocation.x()][startLocation.y()].setFillColor(Color.gray);
        startLocation = l;
        if (getCell(l) == MazeCell.WALL)
        {
            setCell(l, MazeCell.UNEXPLORED);
        }
        setColor(l);
    }
    // ----------------------------------------------------------
    /**
     * Get the size of the maze.
     * @return The size of the maze
     */
    public int size()
    {
        return s;
    }
    // ----------------------------------------------------------
    /**
     * Solve the maze and color each step.
     * @return The path for solving the maze
     */
    public String solve()
    {
        solution.push(getStartLocation());
        ILocation curr = getStartLocation();
        setCell(curr, MazeCell.CURRENT_PATH);
        String result = null;
        while (!solution.empty())
        {
            if (arrive(curr))
            {
                result = "";
                while (!solution.empty())
                {
                    result = solution.pop().toString() + " " + result;
                }
                return result.substring(0, result.length() - 1);
            }
            else if (countUnexplored(curr) != 0)
            {
                String[] di = hasUnexplored(curr);
                boolean found = false;
                int i = 0;
                while (!found)
                {
                    if (di[i] != null)
                    {
                        if (di[i].equals("north"))
                        {
                            curr = new Location(curr.x() - 1, curr.y());
                        }
                        else if (di[i].equals("south"))
                        {
                            curr = new Location(curr.x() + 1, curr.y());
                        }
                        else if (di[i].equals("west"))
                        {
                            curr = new Location(curr.x(), curr.y() - 1);
                        }
                        else
                        {
                            curr = new Location(curr.x(), curr.y() + 1);
                        }
                        solution.push(curr);
                        setCell(curr, MazeCell.CURRENT_PATH);
                        found = true;
                    }
                    else
                    {
                        i++;
                    }
                }
            }
            else
            {
                setCell(new Location(curr.x(), curr.y()), MazeCell.FAILED_PATH);
                solution.pop();
                if (!solution.empty())
                {
                    curr = solution.peek();
                }
            }
        }
        return result;
    }
    // ----------------------------------------------------------
    /**
     * To see if the current location is the same as the goal position.
     * @param current The current position at the maze
     * @return If the current position is the goal position
     */
    public boolean arrive(ILocation current)
    {
        return (current.equals(getGoalLocation()));
    }
    // ----------------------------------------------------------
    /**
     * Compute the number of the unexplored cells around the current position.
     * @param cur The current position
     * @return The number of the unexplored cells around the current location
     */
    public int countUnexplored(ILocation cur)
    {
        String[] direction = hasUnexplored(cur);
        int count = 0;
        for (String d : direction)
        {
            if (d != null)
            {
                count++;
            }
        }
        return count;
    }
    // ----------------------------------------------------------
    /**
     * Get the array of the possible unexpected locations.
     * @param cu The location of the current point
     * @return The array of the unexpected location
     */
    public String[] hasUnexplored(ILocation cu)
    {
        String[] string = new String[4];
        if (cu.x() - 1 >= 0 && m[cu.x() - 1][cu.y()] == MazeCell.UNEXPLORED)
        {
            string[0] = "north";
        }
        if (((cu.x() + 1) < size()) && m[cu.x() + 1][cu.y()] ==
            MazeCell.UNEXPLORED)
        {
            string[1] = "south";
        }
        if (cu.y() - 1 >= 0 && m[cu.x()][cu.y() - 1] ==
            MazeCell.UNEXPLORED)
        {
            string[2] = "west";
        }
        if (cu.y() + 1 < size() && m[cu.x()][cu.y() + 1] ==
            MazeCell.UNEXPLORED)
        {
            string[3] = "east";
        }
        return string;
    }
}
