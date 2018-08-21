import org.junit.Test;

import static org.junit.Assert.*;

public class BuilderTest {
    private Tile testTile = new Tile();
    private String builderName = new String("Cobus");
    private Builder testBuilder = new Builder(builderName, testTile);


    @Test
    public void getName() {
        assertEquals(builderName, testBuilder.getName());
    }

    @Test
    public void getCurrentTile() {

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