package org.lee.tcp.demo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Component
public class TCPClient {

    @Value("${tcp.server.port}")
    private int tcpServerPort;

    @Value("${tcp.server.host}")
    private String tcpServerHost;

    public void run(){
        System.out.println("正在向服务器请求连接。。。");
        Socket socket = null;
        Scanner keyboardScanner = null;
        Scanner inScanner = null;
        PrintWriter printWriter = null;
        try {
            socket = new Socket(tcpServerHost, tcpServerPort);
            inScanner = new Scanner(socket.getInputStream());
            System.out.println(inScanner.nextLine());
            printWriter = new PrintWriter(socket.getOutputStream());
            System.out.print("我(客户端)：");
            //先读取键盘录入方可向服务端发送消息
            keyboardScanner = new Scanner(System.in);
            while(keyboardScanner.hasNextLine()){
                String keyborddata = keyboardScanner.nextLine();
                //写到服务端的的控制台
                printWriter.println(keyborddata);
                printWriter.flush();
                //阻塞等待接收服务端的消息
                String indata = inScanner.nextLine();
                System.out.println("服务端："+indata);
                System.out.print("我(客户端)：");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
                if(socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
