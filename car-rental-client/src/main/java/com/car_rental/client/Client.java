package com.car_rental.client;

import java.util.Scanner;


import grpc.EchoServiceGrpc;
import grpc.ReqAllCars;
import grpc.ResAllCars;
import grpc.ReqModCars;
import grpc.Value;
import grpc.Order;
import grpc.NewCar;
import grpc.ChangeCar;
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
						ReqAllCars request = ReqAllCars.newBuilder().build();
						ResAllCars response = client.allValCars(request);
						System.out.println(response.getMessage());
					} else if (message.equals("2")) {
						message = console.nextLine();
						ReqModCars request = ReqModCars.newBuilder().setMessage(message).build();
						ResAllCars response = client.allValModCars(request);
						System.out.println(response.getMessage());
					} else if (message.equals("3")) {
						System.out.println("Id:");
						String message1 = console.nextLine();
						System.out.println("Days:");
						String message2 = console.nextLine();
						Order request = Order.newBuilder().setMessage(message1).setDay(Integer.parseInt(message2)).build();
						Value response = client.reqCar(request);
						System.out.println(response.getMessage());
					} else if (message.equals("4")) {
						System.out.println("Id:");
						message = console.nextLine();
						ReqModCars request = ReqModCars.newBuilder().setMessage(message).build();
						ReqAllCars response = client.reqReturn(request);
						System.out.println("Returned");
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
						ReqAllCars request = ReqAllCars.newBuilder().build();
						ResAllCars response = client.allCars(request);
						System.out.println(response.getMessage());
					} else if (message.equals("2")) {
						message = console.nextLine();
						ReqModCars request = ReqModCars.newBuilder().setMessage(message).build();
						ResAllCars response = client.allModCars(request);
						System.out.println(response.getMessage());
					} else if (message.equals("3")) {
						String[] str = new String[4];
						System.out.println("model");
						str[0] = console.nextLine();
						System.out.println("condition");
						str[1] = console.nextLine();
						System.out.println("rent");
						str[2] = console.nextLine();
						System.out.println("mile");
						str[3] = console.nextLine();
						NewCar request = NewCar.newBuilder().setModel(str[0]).setCondition(str[1]).setRent(str[2]).setMile(str[3]).build();
						ResAllCars response = client.reqNewCar(request);
						System.out.println(response.getMessage());
					} else if (message.equals("4")) {
						String[] str = new String[3];
						System.out.println("id");
						str[0] = console.nextLine();
						System.out.println("mile");
						str[1] = console.nextLine();
						System.out.println("condition");
						str[2] = console.nextLine();
						ChangeCar request = ChangeCar.newBuilder().setId(str[0]).setMile(str[1]).setCondition(str[2]).build();
						ReqAllCars response = client.reqChange(request);
						System.out.println("Changed");
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
