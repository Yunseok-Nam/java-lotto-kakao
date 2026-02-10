import lotto.controller.LottoController;
import lotto.util.RandomLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        LottoController lottoController = new LottoController(
                inputView,
                outputView,
                new RandomLottoNumberGenerator()
        );

        lottoController.run();
    }
}
