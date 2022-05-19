package za.co.wethinkcode.robotworlds.controller.buttons;

import org.apache.commons.lang3.RandomStringUtils;
import org.lwjgl.input.Controller;
import za.co.wethinkcode.robotworlds.Client;

public class LaunchButton implements Runnable {
    private final Controller controller;
    private final Client client;

    public LaunchButton(Controller controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    private String createStringRequest() {
        String randomString =
                RandomStringUtils.random(5, true, false);
        return "launch " + randomString + " sniper 5000 5000";
    }


    @Override
    public void run() {
        while (true) {
            controller.poll();
            if (controller.isButtonPressed(0)) {
                client.sendRequestToServer(createStringRequest());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
