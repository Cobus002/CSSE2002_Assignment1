import org.junit.Test;

import static org.junit.Assert.*;

public class BuilderTest {
    //Set up some reusable elements
    private Tile testTile = new Tile();
    private String builderName = new String("Cobus");
    private Builder testBuilder = new Builder(builderName, testTile);

    //Test the get name function
    @Test
    public void getName() {
        assertEquals(builderName, testBuilder.getName());
    }

    @Test
    public void getCurrentTile() {
        assertEquals(testTile, testBuilder.getCurrentTile());

    }

    @Test
    public void getInventory() {
    }

    @Test
    public void dropFromInventory() {
    }

    @Test
    public void digOnCurrentTile() {
    }

    @Test
    public void canEnter() {
    }

    @Test
    public void moveTo() {
    }
}