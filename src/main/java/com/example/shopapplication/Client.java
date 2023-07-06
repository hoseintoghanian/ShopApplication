package com.example.shopapplication;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    BufferedReader stdIn;
    String clientUsername;

    public void run() {
        try {
            // Connect to the server socket
            clientSocket = new Socket("localhost", 5581);
            System.out.println("Connected to the server.");

            // Create input and output streams for the client socket
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read and write messages to and from the server
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            clientUsername = Application.shop.currentSeller.getUsername();
            sendUsername();

        } catch (IOException e) {
            System.out.println("Server isn't online");
        }
    }

    public void sendUsername() {
        out.println(clientUsername);
    }

    public void sendMessageToServer(String msg) {
        out.println(msg);
    }

    public String getMessageFromServer() throws IOException {
        String userInput = "";
        if (in.ready()) {
            userInput = in.readLine();
        }
        return userInput;
    }

    public void closeClientStreams() {
        try {
            if (clientSocket != null) clientSocket.close();
            if (in != null) in.close();
            if (out != null) out.close();
            if (stdIn != null) stdIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}