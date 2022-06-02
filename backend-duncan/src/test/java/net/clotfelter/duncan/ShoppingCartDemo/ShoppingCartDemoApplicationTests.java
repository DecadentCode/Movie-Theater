package net.clotfelter.duncan.ShoppingCartDemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShoppingCartDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void sendEmail() throws Exception {
		assertTrue(ShoppingController.sendReceipt("duncan@clotfelter.net"));
	}
}
