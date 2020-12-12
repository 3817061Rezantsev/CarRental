package car_rental.common;

public class Car {
	public String carId;
	public String model;
	public String condition;
	public String rentCost;
	public String mileage;
	public boolean rent;
	
	public Car() {
		
	}

	public Car(String carId, String model, String condition, String rentCost, String mileage) {
		this.carId = carId;
		this.model = model;
		this.condition = condition;
		this.rentCost = rentCost;
		this.mileage = mileage;
		this.rent = true;
	}

	public boolean getRent() {
		return rent;
	}

	public void setRent(boolean rent) {
		this.rent = rent;
	}

	public String getCardId() {
		return carId;
	}

	public String getModel() {
		return model;
	}

	public String getMileage() {
		return mileage;
	}

	public String getCondition() {
		return condition;
	}

	public String getRentCost() {
		return rentCost;
	}

	public void setChar(String condition, String mileage) {
		this.condition = condition;
		this.mileage = mileage;
	}

	@Override
	public String toString() {

		String str = carId + "\n" + model + "\n" + condition + "\n" + rentCost + "\n" + mileage + "\n";
		if (!rent) {
			str += "Not available\n";
		}
		return str;
	}
}
