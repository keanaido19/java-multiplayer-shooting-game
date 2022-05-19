package za.co.wethinkcode.robotworlds.controller.buttons;

import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class RepairButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public RepairButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.isButtonPressed(3)) {
                client.sendRequestToServer("repair");
                try {
                    Thread.sleep(225);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
