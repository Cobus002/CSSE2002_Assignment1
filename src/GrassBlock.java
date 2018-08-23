
public class GrassBlock extends GroundBlock {
    private String type, colour;
    private boolean carryable;

    public GrassBlock() {
        type = new String("grass");
        colour = new String("green");
        carryable = false;
    }

    @Override
    public String getBlockType() {
        return this.type;
    }

    @Override
    public String getColour() {
        return this.colour;
    }

    @Override
    public boolean isCarryable() {
        return this.carryable;
    }
}
