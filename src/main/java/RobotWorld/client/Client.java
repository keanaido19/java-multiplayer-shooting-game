package RobotWorld.client;

import RobotWorld.commands.Command;
import RobotWorld.robot.Robot;

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
        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        System.out.println(robot.toString());

        Command command=null;
        boolean shouldContinue = true;

        try(
                Socket socket = new Socket("localhost", 5000);
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
        ) {
            do {
                String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();

                try {
                    command = Command.create(instruction);
                    shouldContinue = robot.handleCommand(command);

                } catch (IllegalArgumentException e) {
                    robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
                }
            } while (shouldContinue);

            out.println(command);
            out.flush();
            String messageFromServer = in.readLine();
            System.out.println("Response: "+messageFromServer);
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






