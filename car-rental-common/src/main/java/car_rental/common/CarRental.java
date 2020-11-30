package car_rental.common;

import java.util.ArrayList;
import java.util.List;

public class CarRental {
	private final String[] CONDITIONS = { "Best", "Good", "Middle" };
	List<Car> car_list = new ArrayList<>();

	public List<Car> getCarList() {
		return car_list;
	}

	public List<Car> getCarListWithModel(String model) {
		List<Car> car_list_mod = new ArrayList<>();
		for (Car c : car_list) {
			if (c.getModel().equals(model))
				car_list_mod.add(c);
		}
		return car_list_mod;
	}

	public String addCar(String model, String condition, String rentCost, String mileage) {
		Integer i = car_list.size() + 1;
		Car c = new Car(i.toString(), model, condition, rentCost, mileage);
		car_list.add(c);
		return i.toString();
	}

	public void changeCarChar(String carId, String condition, String mileage) {
		car_list.get(Integer.parseInt(carId) - 1).setChar(condition, mileage);
	}

	public int orderACar(String carId, int days) {
		if (car_list.get(Integer.parseInt(carId) - 1).getRent()) {
			car_list.get(Integer.parseInt(carId) - 1).setRent(false);
			return Integer.parseInt(car_list.get(Integer.parseInt(carId) - 1).getRentCost()) * days;
		} else {
			return -1;
		}
	}

	public void returnCar(String carId) {
		car_list.get(Integer.parseInt(carId) - 1).setRent(true);
	}
}
