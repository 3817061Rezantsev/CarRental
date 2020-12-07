package car_rental.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CarTest {

	@Test
	void getCarsWithModelTest() {
		CarRental cr = new CarRental();
		List<Car> car_list_mod = new ArrayList<>();
		car_list_mod = cr.getCarListWithModel("A");
		assertEquals(2, car_list_mod.size());
	}

}
