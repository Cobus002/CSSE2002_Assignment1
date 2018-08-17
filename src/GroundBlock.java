public abstract class GroundBlock implements Block {
    boolean diggable, moveable;

    public GroundBlock(){
        System.out.println("GroundBlock init");
        this.diggable=true;
        this.moveable=false;
    }

    @Override
    public boolean isDiggable() {
        return this.diggable;
    }

    @Override
    public boolean isMoveable() {
        return this.moveable;
    }
}
