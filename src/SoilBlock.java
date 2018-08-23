public class SoilBlock extends GroundBlock {
    private String type, colour;
    private boolean carryable;

    public SoilBlock() {
        type = new String("soil");
        colour = new String("black");
        carryable = true;
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
