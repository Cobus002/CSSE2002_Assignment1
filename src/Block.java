public interface Block {

    /**
     * Get the block type
      * @return
     */
    String getBlockType();

    /**
     * Get the block colour
     * @return
     */
    String getColour();

    /**
     * Check if the block is carryable
     * @return
     */
    boolean isCarryable();

    /**
     * Check if the block is diggable
     * @return
     */
    boolean isDiggable();

    /**
     * Check if the block is moveable.
     * @return
     */
    boolean isMoveable();

}
