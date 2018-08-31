public class WoodBlock implements Block {
    private String type, colour;
    private boolean carryable, diggable, moveable;

    public WoodBlock() {
        this.type = new String("wood");
        this.colour = new String("brown");
        this.carryable = true;
        this.diggable = true;
        this.moveable = true;

    }

    /**
     * Check what type the block is
     * @return
     */
    @Override
    public String getBlockType() {
        return this.type;
    }

    /**
     * Check what colour the block is
     * @return
     */
    @Override
    public String getColour() {
        return this.colour;
    }

    /**
     * Check if the block can be carried
     * @return
     */
    @Override
    public boolean isCarryable() {
        return this.carryable;
    }

    /**
     * Chyeck if the block is diggable.
     * @return
     */
    @Override
    public boolean isDiggable() {
        return this.diggable;
    }

    /**
     * Check if the block can be moved
     * @return
     */
    @Override
    public boolean isMoveable() {
        return this.moveable;
    }
}
