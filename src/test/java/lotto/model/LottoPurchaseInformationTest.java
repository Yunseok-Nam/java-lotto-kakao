package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseInformationTest {

	@DisplayName("수동 로또 개수가 구매 가능 개수를 초과하면 예외가 발생한다.")
	@Test
	void throwsExceptionWhenManualCountExceedsPurchase() {
		PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);
		ManualLottoCount manual = new ManualLottoCount(15);

		assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseInformation(purchaseAmount, manual));
	}
}
