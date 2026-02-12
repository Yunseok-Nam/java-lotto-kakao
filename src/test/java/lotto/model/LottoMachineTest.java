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
		int purchaseAmount = 3000;
		LottoNumberGenerator generator = new FixedLottoNumberGenerator(1, 6);

		LottoMachine lottoMachine = new LottoMachine(purchaseAmount, generator);

		assertEquals(purchaseAmount / PurchaseAmount.PURCHASE_UNIT, lottoMachine.getLottos().values().size());
	}

	@DisplayName("구매한 로또들의 당첨 결과를 계산한다.")
	@Test
	void calculateLottoResultsTest() {
		int purchaseAmount = 1000;
		LottoNumberGenerator generator = new FixedLottoNumberGenerator(1, 6);
		LottoMachine lottoMachine = new LottoMachine(purchaseAmount, generator);

		WinningLotto winningLotto = new WinningLotto(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

		LottoStatistics statistics = lottoMachine.calculateResult(winningLotto);

		assertEquals(1, statistics.countOf(LottoResult.FIRST));
		assertEquals(LottoResult.FIRST.getPrize() / (double)purchaseAmount, statistics.profitRate());
	}
}
