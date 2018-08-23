
public class GrassBlock extends GroundBlock {
    //Save the variables here
    private String type, colour;
    private boolean carryable;

    /***
     * Initialise the grass block
     */
    public GrassBlock() {
        //Set the class variables here so that if need to change can edit at
        // one location.
        type = new String("grass");
        colour = new String("green");
        carryable = false;
    }

    /***
     * Get the GrassBlock type
     * @return
     */
    @Override
    public String getBlockType() {
        return this.type;
    }

    /***
     * Get the GrassBlock colour
     * @return
     */
    @Override
    public String getColour() {
        return this.colour;
    }

    /***
     * Check to see if the GrassBlock is carryable
     * @return
     */
    @Override
    public boolean isCarryable() {
        return this.carryable;
    }
}
