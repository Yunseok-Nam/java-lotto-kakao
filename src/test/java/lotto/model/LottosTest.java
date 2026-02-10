package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottosTest {

    @DisplayName("당첨 로또를 기준으로 각 로또의 결과를 계산한다.")
    @Test
    void calculateLottoResultsTest() {
        Lottos lottos = new Lottos(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 10, 11, 12))
        ));
        WinningLotto winningLotto = new WinningLotto(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7)
        );

        List<LottoResult> results = lottos.calculateLottoResults(winningLotto);

        assertEquals(List.of(LottoResult.FIRST, LottoResult.SECOND, LottoResult.FIFTH), results);
    }
}
