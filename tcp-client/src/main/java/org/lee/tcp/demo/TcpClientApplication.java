package org.lee.tcp.demo;

import org.lee.tcp.demo.client.TCPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TcpClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TcpClientApplication.class, args);
	}

	@Autowired
	private TCPClient tcpClient;

	@Override
	public void run(String... args) throws Exception {
		tcpClient.run();
	}
}