package car_rental.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import car_rental.common.Car;
import car_rental.common.CarRental;

public class CarTest {

	@Test
	public void getCarsWithModelTest() {
		CarRental cr = new CarRental();
		List<Car> car_list_mod = new ArrayList<>();
		car_list_mod = cr.getCarListWithModel("A");
		assertEquals(2, car_list_mod.size());
	}

}
