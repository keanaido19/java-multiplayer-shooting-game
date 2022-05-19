package za.co.wethinkcode.robotworlds.controller.buttons;

import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class QuitButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public QuitButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.isButtonPressed(1)) {
                client.sendRequestToServer("quit");
            }
        }
    }
}