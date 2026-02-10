package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.PurchaseAmount;
import lotto.model.LottoStatistics;
import lotto.model.WinningLotto;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoController(InputView inputView, OutputView outputView, LottoNumberGenerator lottoNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public void run() {
        int purchaseAmount = readValidPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, lottoNumberGenerator);
        outputView.printPurchasedLottos(lottoMachine.getLottos().values());

        List<Integer> winningNumbers = readValidWinningNumbers();
        int bonusNumber = readValidBonusNumber(winningNumbers);

        LottoStatistics lottoStatistics = lottoMachine.calculateResult(winningNumbers, bonusNumber);
        outputView.printStatistics(lottoStatistics);
    }

    private int readValidPurchaseAmount() {
        return readUntilValid(() -> {
            int purchaseAmount = inputView.readPurchaseAmount();
            validatePurchaseAmount(purchaseAmount);
            return purchaseAmount;
        });
    }

    private List<Integer> readValidWinningNumbers() {
        return readUntilValid(() -> {
            List<Integer> numbers = inputView.readWinningNumbers();
            Lotto.from(numbers);
            return numbers;
        });
    }

    private int readValidBonusNumber(List<Integer> winningNumbers) {
        return readUntilValid(() -> {
            int bonusNumber = inputView.readBonusNumber();
            validateBonusNumber(winningNumbers, bonusNumber);
            return bonusNumber;
        });
    }

    private void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        new WinningLotto(Lotto.from(winningNumbers), new LottoNumber(bonusNumber));
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        new PurchaseAmount(purchaseAmount);
    }

    private <T> T readUntilValid(Supplier<T> reader) {
        while (true) {
            try {
                return reader.get();
            } catch (IllegalArgumentException exception) {
                outputView.printError(exception.getMessage());
            }
        }
    }
}
