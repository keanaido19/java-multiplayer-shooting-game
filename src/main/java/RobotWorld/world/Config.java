package RobotWorld.world;

public class Config {
    private final int width;
    private final int height;
    protected final int shieldStrength;


    public Config(int width, int height, int shieldStrength)
    {
        this.width = width;
        this.height = height;
        this.shieldStrength = shieldStrength;
    }
}
