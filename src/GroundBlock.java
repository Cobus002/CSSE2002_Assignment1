public abstract class GroundBlock implements Block {
    boolean diggable, moveable;

    /***
     * Initialise the GroundBlock with default values
     */
    public GroundBlock(){
        this.diggable=true;
        this.moveable=false;
    }

    /***
     * Check if the block is diggable.
     * @return
     */
    @Override
    public boolean isDiggable() {
        return this.diggable;
    }

    /***
     * Check if the GroundBlock is moveable.
     * @return
     */
    @Override
    public boolean isMoveable() {
        return this.moveable;
    }
}
