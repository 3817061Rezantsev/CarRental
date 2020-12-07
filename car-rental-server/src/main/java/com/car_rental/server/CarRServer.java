package com.car_rental.server;

import car_rental.common.CarRental;
import grpc.EchoRequest;
import grpc.EchoResponse;
import grpc.EchoServiceGrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class CarRServer extends EchoServiceGrpc.EchoServiceImplBase {
	private CarRental cs = new CarRental();

	@Override
	public void echo(EchoRequest request, io.grpc.stub.StreamObserver<EchoResponse> responseObserver) {
		String mes = request.getMessage();
		String res = GetCarMethods(mes);
		System.out.println("receive:" + mes);
		System.out.println("Result:" + res);
		EchoResponse response = EchoResponse.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void allCars(ReqAllCars request, io.grpc.stub.StreamObserver<ResAllCars> responseObserver) {
		String res = cs.getAllCars();
		ResAllCars response = ResAllCars.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	public String GetCarMethods(String mes) {
		String res = "";
		String tmp;
		if (mes.equals("e1 ")) {
			res = cs.getAllCars();
		}
		if (mes.equals("c1 ")) {
			res = cs.getAllValCars();
		}
		if (mes.contains("e2 ")) {
			tmp = mes.substring(3);
			System.out.println("temp:" + tmp);
			res = cs.getCarsWithModel(tmp.trim());
		}
		if (mes.contains("c2 ")) {
			tmp = mes.substring(3);
			res = cs.getRentCarsWithModel(tmp);
		}
		if (mes.contains("e3 ")) {
			tmp = mes.substring(3);
			String[] s = tmp.split(" ");
			if (s.length < 4) {
				res = "Error";
			} else {
				res = cs.addCar(s[0].trim(), s[1].trim(), s[2].trim(), s[3].trim());
			}
		}

		if (mes.contains("c3 ")) {
			tmp = mes.substring(3);
			String[] s = tmp.split(" ");
			if (s.length < 2) {
				res = "Error";
			} else {
			res = cs.orderACar(s[0].trim(), Integer.parseInt(s[1].trim()));
			}
		}

		if (mes.contains("e4 ")) {
			tmp = mes.substring(3);
			String[] s = tmp.split(" ");
			if (s.length < 4) {
				res = "Error";
			} else {
			cs.changeCarChar(s[0].trim(), s[1].trim(), s[2].trim(), s[3].trim());
			res = cs.getCar(s[0].trim()).toString();
			}
		}

		if (mes.contains("c4 ")) {
			tmp = mes.substring(3);
			cs.returnCar(tmp);
			res = "Returned";
		}

		return res;
	}

	public static void main(String[] args) throws Exception {
		Server server = ServerBuilder.forPort(8080).addService(new CarRServer()).build();
		server.start();
		System.out.println("Server started");
		server.awaitTermination();
	}
}
