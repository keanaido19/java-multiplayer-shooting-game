package za.co.wethinkcode.robotworlds.console.commands;

import za.co.wethinkcode.robotworlds.Server;
import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.World;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;

import java.util.List;

public class DumpCommand extends ServerCommand{
    @Override
    public boolean execute(Server server) {
        World world = server.getWorld();

        List<WorldObject> obstacles =
                world.getWorldObjects(ObjectType.OBSTACLE);
        List<WorldObject> robots =
                world.getWorldObjects(ObjectType.ROBOT);

        if (obstacles.isEmpty() && robots.isEmpty()) {
            System.out.println("There are no objects in the world.");
            return true;
        }
        if (!obstacles.isEmpty()) {
            System.out.println("There are some obstacles:");
            for (WorldObject obstacle : obstacles) {
                System.out.println(obstacle);
            }
        }

        if (!robots.isEmpty()) {
            System.out.println("There are some robots:");
            for (WorldObject robot : robots) {
                System.out.println(robot);
            }
        }

        return true;
    }
}
