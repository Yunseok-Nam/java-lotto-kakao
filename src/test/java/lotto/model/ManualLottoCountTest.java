package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottoCountTest {

	@DisplayName("수동 로또 개수가 0 미만이면 예외가 발생한다.")
	@Test
	void createWithNegativeCountThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new ManualLottoCount(-1));
	}

	@DisplayName("자동 로또 개수에서 수동 로또 개수를 뺀 값을 반환한다.")
	@Test
	void calculateAutoLottoCountReturnsRemaining() {
		ManualLottoCount manual = new ManualLottoCount(3);
		int result = manual.calculateAutoLottoCount(14);

		assertEquals(11, result);
	}
}
