package org.lee.tcp.demo;

import org.lee.tcp.demo.server.TCPServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TcpServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TcpServerApplication.class, args);
	}


	@Autowired
	private TCPServer tcpServer;

	@Override
	public void run(String... args) throws Exception {
		tcpServer.run();
	}
}
