package za.co.wethinkcode.robotworlds.controller.buttons;

import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class RightButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public RightButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.getAxisValue(3) >= 0.2) {
                client.sendRequestToServer("turn right");
                try {
                    Thread.sleep(165);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

