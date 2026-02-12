package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Lotto(List<LottoNumber> numbers) {
	private static final String LOTTO_SIZE_ERROR_MESSAGE = "하나의 로또는 6개의 로또 번호를 가져야 합니다.";
	private static final String DUPLICATED_NUMBER_ERROR_MESSAGE = "하나의 로또 안에서 중복된 숫자를 가질 수 없습니다.";
	public static final int LOTTO_SIZE = 6;

	public Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		this.numbers = List.copyOf(numbers);
	}

	public static Lotto from(List<Integer> numbers) {
		return new Lotto(numbers.stream().map(LottoNumber::new).toList());
	}

	private void validate(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
		}

		Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
		if (uniqueNumbers.size() != numbers.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR_MESSAGE);
		}
	}

	public boolean contains(LottoNumber bonusNumber) {
		return numbers.contains(bonusNumber);
	}

	public int calculateMatchCount(Lotto otherLotto) {
		return (int)numbers.stream()
			.filter(otherLotto::contains)
			.count();
	}
}
