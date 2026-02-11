package lotto.model;

public class WinningLotto {
	private static final String DUPLICATED_BONUS_NUMBER_ERROR_MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
		validate(lotto, bonusNumber);
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
		if (winningLotto.contains(bonusNumber)) {
			throw new IllegalArgumentException(DUPLICATED_BONUS_NUMBER_ERROR_MESSAGE);
		}
	}

	public LottoResult calculateResult(Lotto userLotto) {
		int matchCount = userLotto.calculateMatchCount(lotto);
		boolean matchBonus = userLotto.contains(bonusNumber);

		return LottoResult.findByCountAndBonus(matchCount, matchBonus);
	}
}
