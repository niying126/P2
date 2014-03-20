package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 *  Test the location class.
 *  Test each of the method in Location class.
 *
 *  @author niy1987
 *  @version Feb 14, 2014
 */
public class LocationTest extends student.TestCase
{
    private Location l;
    // ----------------------------------------------------------
    /**
     * Create a new LocationTest object.
     */
    public LocationTest()
    {
        //this constructor dosen't take any parameter
    }
    // ----------------------------------------------------------
    /**
     * Initiate the variable.
     */
    public void setUp()
    {
        l = new Location(0, 0);
    }
    // ----------------------------------------------------------
    /**
     * Test the x() method.
     */
    public void testX()
    {
        assertEquals(0, l.x());
    }
    // ----------------------------------------------------------
    /**
     * Test the y() method.
     */
    public void testY()
    {
        assertEquals(0, l.y());
    }
    // ----------------------------------------------------------
    /**
     * Test the north() method.
     */
    public void testNorth()
    {
        assertEquals(new Location(0, -1), l.north());
    }
    // ----------------------------------------------------------
    /**
     * Test the south() method.
     */
    public void testSouth()
    {
        assertEquals(new Location(0, 1), l.south());
    }
    // ----------------------------------------------------------
    /**
     * Test the west() method.
     */
    public void testWest()
    {
        assertEquals(new Location(-1, 0), l.west());
    }
    // ----------------------------------------------------------
    /**
     * Test the east() method.
     */
    public void testEast()
    {
        assertEquals(new Location(1, 0), l.east());
    }
    // ----------------------------------------------------------
    /**
     * Test the equals() method.
     */
    public void testEquals()
    {
        assertEquals(true, l.equals(new Location(0, 0)));
        Location l1 = null;
        assertEquals(false, l.equals(l1));
        assertEquals(false, l.equals(new Object()));
    }
    // ----------------------------------------------------------
    /**
     * Test the toString() method.
     */
    public void testToString()
    {
        assertEquals("(0, 0)", l.toString());
    }
}
