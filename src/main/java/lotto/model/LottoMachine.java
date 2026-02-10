package lotto.model;

import lotto.util.LottoNumberGenerator;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {
    private final PurchaseAmount purchaseAmount;
    private final Lottos lottos;

    public LottoMachine(int purchaseAmount, LottoNumberGenerator generator) {
        this.purchaseAmount = new PurchaseAmount(purchaseAmount);
        this.lottos = issueLottos(generator);
    }

    private Lottos issueLottos(LottoNumberGenerator generator) {
        List<Lotto> issuedLottos = IntStream.range(0, purchaseAmount.getLottoCount())
                .mapToObj(index -> Lotto.from(generator.generate()))
                .toList();
        return new Lottos(issuedLottos);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public LottoStatistics calculateResult(List<Integer> winningNumbers, int bonusNumber) {
        WinningLotto winningLotto = new WinningLotto(Lotto.from(winningNumbers), new LottoNumber(bonusNumber));
        return lottos.calculateStatistics(winningLotto, purchaseAmount);
    }
}
