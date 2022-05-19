package za.co.wethinkcode.robotworlds.controller.buttons;

import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class ForwardButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public ForwardButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.getAxisValue(2) <= -0.3) {
                client.sendRequestToServer("forward 1");
                try {
                    Thread.sleep(99);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
