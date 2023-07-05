package com.example.shopapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends Thread {
    ServerSocket serverSocket;
    HashMap<String, ClientManager> clientsMap = new HashMap<>();


    public void run() {
        try {
            // Create a server socket and bind it to a port
            serverSocket = new ServerSocket(5581);
            System.out.println("Server Created");

            while (true) {
                // wait for client
                // hold an object of Socket for each client
                Socket client = serverSocket.accept();
                System.out.println("Connected to new client");

                // create new thread to manage each client separately
                Thread t = new Thread(new ClientManager(this, client));

                t.start();
            }
        } catch (IOException e) {
            System.out.println("maybe severSocket didn't created or the files closed");
            e.printStackTrace();
        }
    }

    public void closeServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class ClientManager implements Runnable {
        Server server;
        Socket client;
        BufferedReader stdIn;
        BufferedReader in;
        PrintWriter out;


        public ClientManager(Server server, Socket client) {
            this.server = server;
            this.client = client;
        }


        @Override
        public void run() {
            try {
                // Create input and output streams for the client socket
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                stdIn = new BufferedReader(new InputStreamReader(System.in));

                //when a seller logged in send its username to server automatically
                //add "this" to Server "clientsMap" HashMap
                String username = "";
                while (username.equals("")) {
                    username = getMessageFromClient();
                }

                System.out.println(username);
                server.clientsMap.put(username, this);


                for (int i = 0; i < clientsMap.size(); i++) {
                    System.out.println(Application.shop.sellers.get(i).getUsername() + " : " + clientsMap.get(Application.shop.sellers.get(i).getUsername()));
                    System.out.println("this : " + clientsMap.get(username));
                }
                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessageToClient(String msg) {
            out.println(msg);
        }

        public String getMessageFromClient() throws IOException {
            String inputLine = "";
            if (in != null && in.ready()) {
                inputLine = in.readLine();
            }
            return inputLine;
        }

        public void closeClientManagersStreams() {
            try {
                // Close the streams and sockets
                client.close();
                stdIn.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("close");
        }
    }
}