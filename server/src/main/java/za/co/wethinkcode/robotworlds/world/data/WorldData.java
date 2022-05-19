package za.co.wethinkcode.robotworlds.world.data;

import java.util.Objects;

public class WorldData {
    int width;
    int height;
    int visibility;
    int reload;
    int repair;
    int shields;
    int shots;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getReloadTime() {
        return reload;
    }

    public int getRepairTime() {
        return repair;
    }

    public int getShields() {
        return shields;
    }

    public int getShots() {
        return shots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldData)) return false;
        WorldData worldData = (WorldData) o;
        return width == worldData.width
                && height == worldData.height
                && visibility == worldData.visibility
                && reload == worldData.reload
                && repair == worldData.repair
                && shields == worldData.shields
                && shots == worldData.shots;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                width,
                height,
                visibility,
                reload,
                repair,
                shields,
                shots
        );
    }
}
