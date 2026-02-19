package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import lotto.util.FixedLottoNumberGenerator;
import lotto.model.generator.LottoNumberGenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

class LottoMachineTest {
	@DisplayName("구매 금액에 맞는 개수만큼 로또를 발급한다.")
	@Test
	void issueLottosByPurchaseAmountTest() {
		// given
		int amount = 3000;
		PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
		ManualLottoCount manualLottoCount = new ManualLottoCount(0);
		LottoPurchaseInformation lottoPurchaseInformation = new LottoPurchaseInformation(purchaseAmount, manualLottoCount);
		LottoNumberGenerator generator = new FixedLottoNumberGenerator(1, 6);

		// when
		LottoMachine lottoMachine = new LottoMachine(lottoPurchaseInformation, generator, List.of());

		// then
		int expectedCount = amount / PurchaseAmount.PURCHASE_UNIT;
		assertEquals(expectedCount, lottoMachine.getLottos().values().size());
	}

	@DisplayName("구매한 로또들의 당첨 결과를 계산한다.")
	@Test
	void calculateLottoResultsTest() {
		// given
		int amount = 1000;
		PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
		ManualLottoCount manualLottoCount = new ManualLottoCount(0);
		LottoPurchaseInformation lottoPurchaseInformation = new LottoPurchaseInformation(purchaseAmount, manualLottoCount);

		LottoNumberGenerator generator = new FixedLottoNumberGenerator(1, 6);
		LottoMachine lottoMachine = new LottoMachine(lottoPurchaseInformation, generator, List.of());

		WinningLotto winningLotto = new WinningLotto(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

		BigDecimal expectedProfitRate = BigDecimal.valueOf(LottoResult.FIRST.getPrize())
			.divide(BigDecimal.valueOf(amount), MathContext.DECIMAL64);

		// when
		LottoStatistics lottoStatistics = lottoMachine.calculateResult(winningLotto);

		// then
		assertAll(
			() -> assertEquals(1, lottoStatistics.countOf(LottoResult.FIRST)),
			() -> assertEquals(0, expectedProfitRate.compareTo(lottoStatistics.profitRate()))
		);
	}
}
