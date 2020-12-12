package com.car_rental.server;

import java.util.ArrayList;
import java.util.List;

import car_rental.common.Car;
import car_rental.common.CarRental;
import grpc.EchoServiceGrpc;
import grpc.ReqAllCars;
import grpc.ResAllCars;
import grpc.ReqModCars;
import grpc.Value;
import grpc.NewCar;
import grpc.ChangeCar;
import grpc.Order;
import grpc.CarInt;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class CarRServer extends EchoServiceGrpc.EchoServiceImplBase {
	private CarRental cs = new CarRental();
	private List<Car> cars = new ArrayList<>();
	@Override
	public void allCars(ReqAllCars request, io.grpc.stub.StreamObserver<ResAllCars> responseObserver) {
		String res = cs.getAllCars();
		ResAllCars response = ResAllCars.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void allValCars(ReqAllCars request, io.grpc.stub.StreamObserver<ResAllCars> responseObserver) {
		String res = cs.getAllValCars();
		ResAllCars response = ResAllCars.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void allModCars(ReqModCars request, io.grpc.stub.StreamObserver<ResAllCars> responseObserver) {
		String mes = request.getMessage();
		String res = cs.getCarsWithModel(mes);
		ResAllCars response = ResAllCars.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void allValModCars(ReqModCars request, io.grpc.stub.StreamObserver<ResAllCars> responseObserver) {
		String mes = request.getMessage();
		String res = cs.getRentCarsWithModel(mes);
		ResAllCars response = ResAllCars.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void allCarsCount(ReqAllCars request, io.grpc.stub.StreamObserver<Value> responseObserver) {
		cars = cs.getCarList();
		Value response = Value.newBuilder().setMessage(cars.size()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void reqCarId(Value request, io.grpc.stub.StreamObserver<CarInt> responseObserver) {
		int mes = request.getMessage();
		Car c = cars.get(mes);
		CarInt response = CarInt.newBuilder().setId(c.carId).setModel(c.model).setCondition(c.condition).setCost(c.rentCost).setMile(c.mileage).setRent(c.rent).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void reqCar(Order request, io.grpc.stub.StreamObserver<Value> responseObserver) {
		String mes = request.getMessage();
		int d = request.getDay();
		int res = cs.orderACar(mes, d);
		Value response = Value.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void reqNewCar(NewCar request, io.grpc.stub.StreamObserver<ResAllCars> responseObserver) {
		String[] s = new String[4];
		s[0] = request.getModel();
		s[1] = request.getCondition();
		s[2] = request.getRent();
		s[3] = request.getMile();
		String res = cs.addCar(s[0], s[1], s[2], s[3]);
		ResAllCars response = ResAllCars.newBuilder().setMessage("response from server:" + res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void reqReturn(ReqModCars request, io.grpc.stub.StreamObserver<ReqAllCars> responseObserver) {
		String mes = request.getMessage();
		cs.returnCar(mes);
		ReqAllCars response = ReqAllCars.newBuilder().build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void reqChange(ChangeCar request, io.grpc.stub.StreamObserver<ReqAllCars> responseObserver) {
		String[] s = new String[3];
		s[0] = request.getId();
		s[1] = request.getMile();
		s[2] = request.getCondition();
		cs.changeCarChar(s[0], s[1], s[2]);
		ReqAllCars response = ReqAllCars.newBuilder().build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	


	public static void main(String[] args) throws Exception {
		Server server = ServerBuilder.forPort(8080).addService(new CarRServer()).build();
		server.start();
		System.out.println("Server started");
		server.awaitTermination();
	}
}
