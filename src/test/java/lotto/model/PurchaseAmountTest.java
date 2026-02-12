package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseAmountTest {
	@DisplayName("구매금액은 로또 가격을 나누어 떨어져야한다.")
	@Test
	void validLottoNumberTest() {
		assertDoesNotThrow(() -> new PurchaseAmount(PurchaseAmount.PURCHASE_UNIT * 4, 0));
	}

	@DisplayName("구매금액이 로또 가격으로 나누어 떨어지지 않으면, 예외가 발생한다.")
	@Test
	void invalidLottoNumberTest() {
		assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(PurchaseAmount.PURCHASE_UNIT * 4 + 1, 0));
	}

	@DisplayName("구매금액은 1,000원 이상이어야 한다.")
	@Test
	void invalidMinimumPurchaseAmountTest() {
		assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(0, 0));
		assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(-PurchaseAmount.PURCHASE_UNIT, 0));
	}

	@DisplayName("수동 로또 개수가 구매 금액을 넘을 경우, 예외가 발생한다.")
	@Test
	void invalidManualLottoCountTest() {
		assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(PurchaseAmount.PURCHASE_UNIT * 3, 4));
	}
}
