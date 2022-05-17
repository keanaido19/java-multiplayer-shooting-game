package RobotWorld.client;

import RobotWorld.commands.Command;
import RobotWorld.robot.Robot;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
 static Scanner scanner;
    public static void main(String[] args) throws IOException {

        scanner = new Scanner(System.in);
        Robot robot;
        String host = getInput("enter ip address");
        int port = Integer.parseInt(getInput("enter port number"));
        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        Request request = new Request(name);

        try(
                Socket socket = new Socket(host, port);
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
        ) {
            do {
                String command = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();

                try {
                    command = scanner.nextLine();
                    JSONObject clientRequest = request.CreateRequest(command);
                    out.println(clientRequest);
                    out.flush();
                    String messageFromServer = in.readLine();
                    System.out.println("Response: "+messageFromServer);

                } catch (IllegalArgumentException e) {
                    robot.setStatus("Sorry, I did not understand '" + command + "'.");
                }
            } while (!socket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}




