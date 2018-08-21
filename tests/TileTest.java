import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    private Tile testTile = new Tile(); //Create the test object each time

    @Test
    public void getExits() {

        Block grass1 = new GrassBlock();
        Tile exitTile = new Tile();
        try {
            exitTile.placeBlock(grass1);
            testTile.addExit("NorthExit", exitTile);
        } catch (TooHighException | InvalidBlockException e) {
            System.out.println("Error occurred when adding block too tile");
        } catch (NoExitException e) {
            System.out.println("Error occurred when adding exit");
        }

        assertEquals(1, testTile.getExits().size());
        assertEquals(true,
                exitTile.equals(testTile.getExits().get("NorthExit")));

    }

    @Test
    public void getBlocks() {


        assertEquals(3, testTile.getBlocks().size());
    }

    @Test
    public void getTopBlock() {

    }

    @Test
    public void removeTopBlock() {
    }

    @Test
    public void addExit() {
    }

    @Test
    public void removeExit() {
    }

    @Test
    public void dig() {
    }

    @Test
    public void moveBlock() {
    }

    @Test
    public void placeBlock() {
    }
}