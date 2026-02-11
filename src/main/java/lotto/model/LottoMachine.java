package lotto.model;

import lotto.util.LottoNumberGenerator;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoMachine {
	private final PurchaseAmount purchaseAmount;
	private final Lottos lottos;

	public LottoMachine(PurchaseAmount purchaseAmount, LottoNumberGenerator generator) {
		this.purchaseAmount = purchaseAmount;
		this.lottos = issueLottos(generator);
	}

	public LottoMachine(int purchaseAmount, LottoNumberGenerator generator) {
		this(new PurchaseAmount(purchaseAmount), generator);
	}

	private Lottos issueLottos(LottoNumberGenerator generator) {
		List<Lotto> issuedLottos = Stream.generate(() -> Lotto.from(generator.generate()))
			.limit(purchaseAmount.getLottoCount())
			.toList();
		return new Lottos(issuedLottos);
	}

	public Lottos getLottos() {
		return lottos;
	}

	public LottoStatistics calculateResult(WinningLotto winningLotto) {
		return lottos.calculateStatistics(winningLotto, purchaseAmount);
	}
}
