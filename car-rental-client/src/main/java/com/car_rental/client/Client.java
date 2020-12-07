package com.car_rental.client;

import java.util.Scanner;

import grpc.EchoRequest;
import grpc.EchoResponse;
import grpc.EchoServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

public class Client {
	public static void main(String[] args) {
		EchoServiceGrpc.EchoServiceBlockingStub client = createClient("localhost", 8080);
		System.out.println("Connected to server");
		Scanner console = new Scanner(System.in);
		String message;
		while (true) {
			System.out.println("Client - 1");
			System.out.println("Employee - 2");
			System.out.println("Exit - 0");
			message = console.nextLine();
			if (message.equals("0")) {
				break;
			} else if (message.equals("1")) {
				System.out.println("Get all val cars - 1");
				System.out.println("Get all cars of a certain model - 2");
				System.out.println("Get new car - 3");
				System.out.println("Pay - 4");
				System.out.println("Exit - 0");
				while (true) {

					message = console.nextLine();
					if (message.equals("0")) {
						break;
					} else if (message.equals("1")) {
						EchoRequest request = EchoRequest.newBuilder().setMessage("c1 ").build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					} else if (message.equals("2")) {
						message = console.nextLine();
						EchoRequest request = EchoRequest.newBuilder().setMessage("c2 " + message).build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					} else if (message.equals("3")) {
						message = console.nextLine();
						EchoRequest request = EchoRequest.newBuilder().setMessage("c3 " + message).build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					} else if (message.equals("4")) {
						message = console.nextLine();
						EchoRequest request = EchoRequest.newBuilder().setMessage("c4 " + message).build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					}
				}
			} else if (message.equals("2")) {
				System.out.println("Get all cars - 1");
				System.out.println("Get all cars of a certain model - 2");
				System.out.println("Add new car - 3");
				System.out.println("Change car - 4");
				System.out.println("Exit - 0");
				while (true) {
					message = console.nextLine();
					if (message.equals("0")) {
						break;
					} else if (message.equals("1")) {
						EchoRequest request = EchoRequest.newBuilder().setMessage("e1 ").build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					} else if (message.equals("2")) {
						message = console.nextLine();
						EchoRequest request = EchoRequest.newBuilder().setMessage("e2 " + message).build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					} else if (message.equals("3")) {
						message = console.nextLine();
						EchoRequest request = EchoRequest.newBuilder().setMessage("e3 " + message).build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					} else if (message.equals("4")) {
						message = console.nextLine();
						EchoRequest request = EchoRequest.newBuilder().setMessage("e4 " + message).build();
						EchoResponse response = client.echo(request);
						System.out.println(response.getMessage());
					}
				}
			}
		}
		console.close();
	}

	private static EchoServiceGrpc.EchoServiceBlockingStub createClient(String host, int port) {
		Channel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		return EchoServiceGrpc.newBlockingStub(channel);
	}
}
