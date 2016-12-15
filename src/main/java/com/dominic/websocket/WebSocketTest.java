package com.dominic.websocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class WebSocketTest {

	public Session session;

	protected void start() {

		WebSocketContainer container = ContainerProvider
				.getWebSocketContainer();

		String uri = "ws://127.0.0.1:8080/websocket";
		System.out.println("Connecting to" + uri);
		try {
			session = container.connectToServer(WebSocketTest.class,
					URI.create(uri));
		} catch (DeploymentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
//		WebSocketTest client = new WebSocketTest();
//		client.start();
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String input = "";
//		try {
//			do {
//				input = br.readLine();
//				if (!input.equals("exit"))
//					client.session.getBasicRemote().sendText(input);
//
//			} while (!input.equals("exit"));
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		  while (true) {    
	            Socket socket = null;  
	            try {  
	                //创建一个流套接字并将其连接到指定主机上的指定端口号  
	                socket = new Socket("127.0.0.1", 8080);    
	                    
	                //读取服务器端数据    
	                DataInputStream input = new DataInputStream(socket.getInputStream());    
	                //向服务器端发送数据    
	                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
	                System.out.print("请输入: \t");    
	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
	                out.writeUTF(str);    
	                    
	                String ret = input.readUTF();     
	                System.out.println("服务器端返回过来的是: " + ret);    
	                // 如接收到 "OK" 则断开连接    
	                if ("OK".equals(ret)) {    
	                    System.out.println("客户端将关闭连接");    
	                    Thread.sleep(500);    
	                    break;    
	                }    
	                  
	                out.close();  
	                input.close();  
	            } catch (Exception e) {  
	                System.out.println("客户端异常:" + e.getMessage());   
	            } finally {  
	                if (socket != null) {  
	                    try {  
	                        socket.close();  
	                    } catch (IOException e) {  
	                        socket = null;   
	                        System.out.println("客户端 finally 异常:" + e.getMessage());   
	                    }  
	                }  
	            }  
		  }
	}
}
