package za.co.wethinkcode.robotworlds.console.commands;

import za.co.wethinkcode.robotworlds.Server;
import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;

import java.util.List;

public class RobotsCommand extends ServerCommand{
    @Override
    public boolean execute(Server server) {
        List<WorldObject> robots =
                server.getWorld().getWorldObjects(ObjectType.ROBOT);

        if (robots.isEmpty()) {
            System.out.println("There are currently no robots in the world.");
        } else {
            System.out.println("There are some robots:");
            for (WorldObject robot: robots) {
                System.out.println(robot);
            }
        }
        return true;
    }
}
