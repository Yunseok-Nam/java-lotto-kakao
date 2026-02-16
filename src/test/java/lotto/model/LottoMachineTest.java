package lotto.model;

import lotto.util.FixedLottoNumberGenerator;
import lotto.model.generator.LottoNumberGenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoMachineTest {
	@DisplayName("구매 금액에 맞는 개수만큼 로또를 발급한다.")
	@Test
	void issueLottosByPurchaseAmountTest() {
		int amount = 3000;
		PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
		ManualLottoCount manualLottoCount = new ManualLottoCount(0);
		LottoPurchaseInformation lottoPurchaseInformation = new LottoPurchaseInformation(purchaseAmount,manualLottoCount);
		LottoNumberGenerator generator = new FixedLottoNumberGenerator(1, 6);

		LottoMachine lottoMachine = new LottoMachine(lottoPurchaseInformation, generator, List.of());

		assertEquals(amount / PurchaseAmount.PURCHASE_UNIT, lottoMachine.getLottos().values().size());
	}

	@DisplayName("구매한 로또들의 당첨 결과를 계산한다.")
	@Test
	void calculateLottoResultsTest() {
		int amount = 1000;
		PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
		ManualLottoCount manualLottoCount = new ManualLottoCount(0);
		LottoPurchaseInformation lottoPurchaseInformation = new LottoPurchaseInformation(purchaseAmount,manualLottoCount);
		LottoNumberGenerator generator = new FixedLottoNumberGenerator(1, 6);
		LottoMachine lottoMachine = new LottoMachine(lottoPurchaseInformation, generator, List.of());

		WinningLotto winningLotto = new WinningLotto(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

		LottoStatistics statistics = lottoMachine.calculateResult(winningLotto);

		assertEquals(1, statistics.countOf(LottoResult.FIRST));
		assertEquals(LottoResult.FIRST.getPrize() / (double)amount, statistics.profitRate());
	}
}
