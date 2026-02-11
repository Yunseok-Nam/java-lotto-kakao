package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
	@DisplayName("로또번호는 1~45 사이의 숫자만을 입력받는다.")
	@Test
	void validLottoNumberTest() {
		assertDoesNotThrow(() -> new LottoNumber(44));
	}

	@DisplayName("로또번호가 1~45 사이의 숫자가 아닐경우 예외가 발생한다.")
	@Test
	void invalidLottoNumberTest() {
		assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
	}
}
