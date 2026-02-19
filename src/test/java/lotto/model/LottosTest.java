package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

class LottosTest {

	@DisplayName("당첨 로또를 기준으로 각 등수 통계를 계산한다.")
	@Test
	void calculateStatisticsTest() {
		// given
		int amount = 3000;
		Lottos lottos = new Lottos(List.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
			Lotto.from(List.of(1, 2, 3, 10, 11, 12))
		));

		WinningLotto winningLotto = new WinningLotto(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

		LottoPurchaseInformation purchaseInfo = new LottoPurchaseInformation(
			new PurchaseAmount(amount),
			new ManualLottoCount(0)
		);

		int totalPrize = LottoResult.FIRST.getPrize()
			+ LottoResult.SECOND.getPrize()
			+ LottoResult.FIFTH.getPrize();

		BigDecimal profitRate = BigDecimal.valueOf(totalPrize)
			.divide(BigDecimal.valueOf(amount), MathContext.DECIMAL64);

		// when
		LottoStatistics statistics = lottos.calculateStatistics(winningLotto, purchaseInfo);

		// then
		assertAll(
			() -> assertEquals(1L, statistics.countOf(LottoResult.FIRST)),
			() -> assertEquals(1L, statistics.countOf(LottoResult.SECOND)),
			() -> assertEquals(1L, statistics.countOf(LottoResult.FIFTH)),
			() -> assertEquals(0L, statistics.countOf(LottoResult.MISS)),
			() -> assertEquals(totalPrize, statistics.calculateTotalPrize()),
			() -> assertEquals(0, profitRate.compareTo(statistics.profitRate()))
		);
	}
}
