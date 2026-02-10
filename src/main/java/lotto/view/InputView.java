package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요.";
    private static final String COMMA_DELIMITER = ",";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 숫자만 입력해 주세요.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
        return parseNumber(scanner.nextLine());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(WINNING_NUMBERS_PROMPT);
        return parseCommaSeparatedNumbers(scanner.nextLine());
    }

    public int readBonusNumber() {
        System.out.println(BONUS_NUMBER_PROMPT);
        return parseNumber(scanner.nextLine());
    }

    private int parseNumber(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    private List<Integer> parseCommaSeparatedNumbers(String value) {
        return Arrays.stream(value.split(COMMA_DELIMITER))
                .map(String::trim)
                .map(this::parseNumber)
                .toList();
    }
}
