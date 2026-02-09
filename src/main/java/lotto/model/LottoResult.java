package lotto.model;

import java.util.Arrays;

public enum LottoResult {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int matchCount;
    private final int prize;

    LottoResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoResult findByCountAndBonus(int count, boolean hasBonus) {
        if (SECOND.matchCount == count && hasBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(result -> result.matchCount == count)
                .filter(result -> result != SECOND)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
