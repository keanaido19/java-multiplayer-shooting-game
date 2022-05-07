package RobotWorld.world;

public class World {
    private Config worldConfig;

    public World()
    {
        worldConfig = new DefaultConfig();
    }
    public World(Config config)
    {
        this();
        this.worldConfig = config;
    }
}
