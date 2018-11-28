package org.lee.tcp.demo.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Component
public class TCPServer {

    @Value("${tcp.server.port}")
    private int tcpPort;

    public void run(){
        ServerSocket serverSocket = null;
        Socket client = null;
        PrintWriter printWriter = null;
        Scanner keyboardScanner = null;
        Scanner inScanner = null;
        try {
            serverSocket = new ServerSocket(tcpPort);
            System.out.println("等待客户端连接");
            boolean flag = true;
            while (flag) {
                client = serverSocket.accept();
                System.out.println(client.getInetAddress()+"已成功连接到此台服务器上。");
                printWriter = new PrintWriter(client.getOutputStream());
                printWriter.println("已经连接成功");
                printWriter.flush();
                keyboardScanner = new Scanner(System.in);
                inScanner = new Scanner(client.getInputStream());
                //阻塞等待客户端发送消息过来
                while(inScanner.hasNextLine()){
                    String indata = inScanner.nextLine();
                    System.out.println("客户端："+indata);
                    System.out.print("我(服务端)：");
                    String keyborddata = keyboardScanner.nextLine();
                    printWriter.println(keyborddata);
                    printWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(printWriter!=null){
                printWriter.close();
            }
            if(keyboardScanner!=null){
                keyboardScanner.close();
            }
            if(inScanner!=null){
                inScanner.close();
            }
            try {
                if(serverSocket!=null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
