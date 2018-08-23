import org.junit.Test;

import static org.junit.Assert.*;
//Ask tutor: If the following tests will do
public class GrassBlockTest {
    private GrassBlock testBlock = new GrassBlock();
    private static final String BLOCK_TYPE = "grass";
    private static final String BLOCK_COLOUR = "green";
    private static final boolean BLOCK_CARRYABLE = false;

    @Test
    public void getBlockType() {
        assertEquals(BLOCK_TYPE, testBlock.getBlockType());
    }

    @Test
    public void getColour() {
        assertEquals(BLOCK_COLOUR, testBlock.getColour());
    }

    @Test
    public void isCarryable() {
        assertEquals(BLOCK_CARRYABLE, testBlock.isCarryable());
    }
}