package za.co.wethinkcode.robotworlds.controller.buttons;

import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class FireButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public FireButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.isButtonPressed(7)) {
                client.sendRequestToServer("fire");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
