package car_rental.common;

public class Car {
	private String carId;
	private String model;
	private String condition;
	private String rentCost;
	private String mileage;
	private boolean rent;

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

	public void setChar(String condition, String mileage, String rentCost) {
		this.condition = condition;
		this.mileage = mileage;
		this.rentCost = rentCost;
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
