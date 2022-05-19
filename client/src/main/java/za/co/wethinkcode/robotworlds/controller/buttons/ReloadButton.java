package za.co.wethinkcode.robotworlds.controller.buttons;

import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class ReloadButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public ReloadButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.isButtonPressed(6)) {
                client.sendRequestToServer("reload");
                try {
                    Thread.sleep(225);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
