package za.co.wethinkcode.robotworlds.turtle;

import org.turtle.StdDraw;
import za.co.wethinkcode.robotworlds.JsonHandler;
import za.co.wethinkcode.robotworlds.SoundPlayer;
import za.co.wethinkcode.robotworlds.jsonresponse.JsonResponseObjects;
import za.co.wethinkcode.robotworlds.jsonresponse.enums.Direction;
import za.co.wethinkcode.robotworlds.jsonresponse.enums.Status;
import za.co.wethinkcode.robotworlds.jsonresponse.objects.Position;
import za.co.wethinkcode.robotworlds.jsonresponse.objects.RobotState;
import za.co.wethinkcode.robotworlds.jsonresponse.objects.WorldObjectData;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TurtleScreen {
    private final int visibility = 10;
    private final int fourV = 4 * visibility;
    private final int twoV = 2 * visibility;
    private final double screenScale = visibility / 5D;
    private final double offset1 = visibility + (screenScale / 2.0D);
    private final double offset2 = visibility - (screenScale / 2.0D);

    private JsonResponseObjects jsonResponseObjects;
    private WorldObjectData clientRobot;
    private RobotState robotState;

    private List<WorldObjectData> robots = new ArrayList<>() {};
    private List<WorldObjectData> obstacles = new ArrayList<>() {};
    private List<WorldObjectData> edges = new ArrayList<>() {};

    private boolean firstLaunch = true;

    private int robotXCoordinate;
    private int robotYCoordinate;

    private void resetLists() {
        robots = new ArrayList<>() {};
        obstacles = new ArrayList<>() {};
        edges = new ArrayList<>() {};
    }

    private void setUpTurtleWorld() {
        if (firstLaunch) {
            double screenSize = visibility + screenScale;
            StdDraw.setScale(-screenSize, screenSize);
            StdDraw.enableDoubleBuffering();
            firstLaunch = false;
        }
    }

    private void setDeltas() {
        robotXCoordinate = clientRobot.getPosition().getX();
        robotYCoordinate = clientRobot.getPosition().getY();
    }

    public synchronized void processJsonStringResponse(
            String jsonStringResponse
    ) {
        setUpTurtleWorld();
        jsonResponseObjects = (JsonResponseObjects)
                JsonHandler.convertJsonStringToObject(
                        jsonStringResponse,
                        JsonResponseObjects.class
                );
        unpackJsonObject();
        setDeltas();
        drawTurtleScreen();
    }

    private void processObjects(List<WorldObjectData> objects) {
        resetLists();
        if (objects == null) return;
        for (WorldObjectData object : objects) {
            switch (object.getType()) {
                case ROBOT:
                    robots.add(object);
                    break;
                case OBSTACLE:
                    obstacles.add(object);
                    break;
                case EDGE:
                    edges.add(object);
                    break;
            }
        }
    }

    private void unpackJsonObject() {
        robotState = jsonResponseObjects.getState();
        clientRobot = jsonResponseObjects.getData().get("robot").get(0);

        List<WorldObjectData> objects =
                jsonResponseObjects.getData().get("objects");
        processObjects(objects);
    }

    private void draw(String fileName, WorldObjectData o) {
        Position p = o.getPosition();
        Direction d = o.getDirection();
        int width = o.getWidth();
        int height = o.getHeight();
        int x = p.getX() - robotXCoordinate;
        int y = p.getY() - robotYCoordinate;
        StdDraw.picture(x, y, fileName, width, height, d.getDegrees());
    }

    private void drawObstacles() {
        if (!obstacles.isEmpty()) {
            getClass().getResource("");
            String fileName =
                    "/assets/worldobjects/obstacles/SquareObstacle.png";
            for (WorldObjectData obstacle : obstacles){
                draw(fileName, obstacle);
            }
        }
    }

    private void drawEdges() {
        int x = clientRobot.getPosition().getX();
        int y = clientRobot.getPosition().getY();
        if (edges.isEmpty()) return;
        String fileName = "/assets/worldobjects/obstacles/boundary.png";
        for (WorldObjectData edge : edges) {
            draw(fileName, edge);
        }
    }

    private int getWorldCoordinate1(int coordinate) {
        int coordinateMod = coordinate % fourV;
        
        if (Math.abs(coordinateMod) != twoV) {
            int returnCoordinate = twoV - Math.abs(coordinate % twoV);
            if (coordinateMod > twoV) {
                return returnCoordinate;
            } else if (coordinateMod < -twoV){
                return -returnCoordinate;
            } else {
                return -(coordinate % twoV);
            }
        }
        return Integer.MAX_VALUE;
    }

    private int getWorldCoordinate2(int coordinate) {
        int coordinateMod = coordinate % fourV;

        if (Math.abs(coordinateMod) != 0) {
            int returnCoordinate = twoV - Math.abs(coordinate % twoV);
            if (coordinateMod > 0 && coordinateMod < twoV) {
                return returnCoordinate;
            } else if (coordinateMod < 0 && coordinateMod > -twoV) {
                return  -returnCoordinate;
            } else if (coordinateMod <= -twoV) {
                return twoV - returnCoordinate;
            } else if (coordinateMod >= twoV) {
                return returnCoordinate - twoV;
            }
        }
        return Integer.MAX_VALUE;
    }

    private void drawWorld() {
        String fileName = "/assets/world/worldPiece1.png";

        int x1 = getWorldCoordinate1(robotXCoordinate);
        int x2 = getWorldCoordinate2(robotXCoordinate);
        int y1 = getWorldCoordinate1(robotYCoordinate);
        int y2 = getWorldCoordinate2(robotYCoordinate);

        if (y1 != Integer.MAX_VALUE && x1 != Integer.MAX_VALUE) {
            StdDraw.picture(x1, y1, fileName, twoV, twoV);
        }

        fileName = "/assets/world/worldPiece2.png";

        if (y2 != Integer.MAX_VALUE && x1 != Integer.MAX_VALUE) {
            StdDraw.picture(x1, y2, fileName, twoV, twoV);
        }

        fileName = "/assets/world/worldPiece3.png";

        if (y2 != Integer.MAX_VALUE && x2 != Integer.MAX_VALUE) {
            StdDraw.picture(x2, y2, fileName, twoV, twoV);
        }

        fileName = "/assets/world/worldPiece4.png";

        if (y1 != Integer.MAX_VALUE && x2 != Integer.MAX_VALUE) {
            StdDraw.picture(x2, y1, fileName, twoV, twoV);
        }

    }

    private void drawRobot(String name, Status status, WorldObjectData o) {
        String fileName = "/assets/worldobjects/robots/" + name + "/" + status +
                "/";

        if (Status.DEAD.equals(status)) {
            Position p = o.getPosition();
            StdDraw.picture(
                    p.getX() - robotXCoordinate,
                    p.getY() - robotYCoordinate,
                    fileName + "feet.png",
                    o.getWidth());
            StdDraw.picture(
                    p.getX() - robotXCoordinate,
                    p.getY() - robotYCoordinate,
                    fileName + "robot.png",
                    o.getWidth());
            return;
        }

        draw(fileName + "feet.png", o);
        draw(fileName + "robot.png", o);

        if (!Status.NORMAL.equals(status))
            new Thread(new SoundPlayer(fileName + "sound.wav")).start();

    }

    private void drawRobots() {
        if (!robots.isEmpty()) {
            for (WorldObjectData robot : robots) {
                drawRobot("enemy", robot.getStatus(), robot);
            }
        }
    }

    private void drawPlayerRobot() {
        if (robotState != null)
            drawRobot("player", clientRobot.getStatus(), clientRobot);
    }

    private void drawStatus() {
        StdDraw.textLeft(-offset2, offset1, "" + robotState.getStatus());
    }

    private void drawShots() {
        StdDraw.textLeft(-offset2, -offset1, "x" + robotState.getShots());
    }

    private void drawShields() {
        StdDraw.textLeft(offset2, -offset1, "x" + robotState.getShields());
    }

    private void drawPosition() {
        StdDraw.textLeft(offset2, offset1, "" + clientRobot.getPosition().getY());
        StdDraw.textLeft(
                offset2 - (screenScale * 2.5D),
                offset1,
                "" + clientRobot.getPosition().getX());
    }

    private void drawUI() {
        StdDraw.picture(0, 0, "/assets/UI/UI.png");
        StdDraw.setPenColor(ColorUIResource.green);
        StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        drawShields();
        drawShots();
        drawStatus();
        drawPosition();
    }

    private void drawTurtleScreen() {
        StdDraw.clear();
        drawWorld();
        drawEdges();
        drawObstacles();
        drawRobots();
        drawPlayerRobot();
        drawUI();
        StdDraw.show();
    }
}
