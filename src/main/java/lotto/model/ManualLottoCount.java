package lotto.model;

public class ManualLottoCount {
	private static final String NEGATIVE_ERROR = "수동 로또 개수는 0 이상이어야 합니다.";
	private final int count;

	public ManualLottoCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException(NEGATIVE_ERROR);
		}
		this.count = count;
	}

	public int calculateAutoLottoCount(int autoLottoCount) {
		return autoLottoCount - count;
	}

	public int value() {
		return count;
	}
}
