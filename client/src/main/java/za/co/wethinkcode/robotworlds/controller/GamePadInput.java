package za.co.wethinkcode.robotworlds.controller;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import za.co.wethinkcode.robotworlds.Client;
import za.co.wethinkcode.robotworlds.controller.buttons.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GamePadInput implements Runnable {
    private final Client client;

    public GamePadInput(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Controls:\n" +
                "A   -   Launch\n" +
                "B   -   Quit\n" +
                "X   -   Repair\n" +
                "RB   -   Fire\n" +
                "LB   -   Reload\n" +
                "LStickUp   -   Forward\n" +
                "LStickDown   -   Back\n" +
                "RStickRight    -   Turn Right\n" +
                "RStickLeft   -   Turn Left");

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        try {
            Controllers.create();
        } catch (LWJGLException e) {
            throw new RuntimeException(e);
        }

        Controller controller;
        do {
            try {
                Controllers.poll();
                controller = Controllers.getController(1);
                if (controller == null) continue;
                if ("Xbox Wireless Controller".equals(controller.getName()))
                    break;
            } catch (IndexOutOfBoundsException ignored) {

            }
        } while (true);

        new Thread(new LaunchButton(controller, client)).start();
        new Thread(new QuitButton(controller, client)).start();
        new Thread(new ForwardButton(controller, client)).start();
        new Thread(new BackButton(controller, client)).start();
        new Thread(new LeftButton(controller, client)).start();
        new Thread(new RightButton(controller, client)).start();
        new Thread(new FireButton(controller, client)).start();
        new Thread(new ReloadButton(controller, client)).start();
        new Thread(new RepairButton(controller, client)).start();

    }
}
