package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 *  This is the Location class which shows the location on the maze.
 *  It implements the ILocation interface and specifies both x and y
 *  coordinates.
 *
 *  @author niy1987
 *  @version Feb 14, 2014
 */
public class Location implements ILocation
{
    private int x;
    private int y;
    // ----------------------------------------------------------
    /**
     * Create a new Location object.
     * @param x The x coordinate of the position
     * @param y The y coordinate of the position
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    // ----------------------------------------------------------
    /**
     * Get the x coordinate of the position.
     * @return The x coordinate
     */
    public int x()
    {
        return x;
    }
    // ----------------------------------------------------------
    /**
     * Get the y coordinate of the position.
     * @return The y coordinate
     */
    public int y()
    {
        return y;
    }
    // ----------------------------------------------------------
    /**
     * Get the location when it moves to north.
     * @return The location in north
     */
    public Location north()
    {
        return new Location(x, y - 1);
    }
    // ----------------------------------------------------------
    /**
     * Get the location when it moves to south.
     * @return The location in south
     */
    public Location south()
    {
        return new Location(x, y + 1);
    }
    // ----------------------------------------------------------
    /**
     * Get the location when it moves to west.
     * @return The location in west
     */
    public Location west()
    {
        return new Location(x - 1, y);
    }
    // ----------------------------------------------------------
    /**
     * Get the location when it moves to east.
     * @return The location in east
     */
    public Location east()
    {
        return new Location(x + 1, y);
    }
    // ----------------------------------------------------------
    /**
     * Compare if the two locations are the same.
     * @param o The location object to be compared
     * @return True or false based on if the two locations are the same
     */
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (o instanceof Location)
        {
            return (((Location)o).x() == x && ((Location)o).y() == y);
        }
        else
        {
            return false;
        }
    }
    // ----------------------------------------------------------
    /**
     * Makes the location coordinate formatted like (x, y).
     * @return The location object to String
     */
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
