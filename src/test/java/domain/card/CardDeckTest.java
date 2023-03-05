package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("정상적으로 카드 덱을 생성한다.")
    @Test
    void create_success() {
        // given
        List<Card> expected = createFillCards();
        // when & then
        assertThatNoException()
                .isThrownBy(() -> CardDeck.createShuffled(expected));
    }

    private List<Card> createFillCards() {
        List<Card> cards = new ArrayList<>();

        for (CardType type : CardType.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(type, value));
            }
        }

        return cards;
    }

    @DisplayName("카드의 전체 수가 52장이 아니면 예외를 반환한다.")
    @Test
    void create_fail_by_wrong_size() {
        // given
        List<Card> expected = new ArrayList<>();
        // when & then
        assertThatThrownBy(() -> CardDeck.createShuffled(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("전체 카드의 수는 52장이어야 합니다.");
    }

    @DisplayName("카드가 52장 모두 개별적인 카드가 아니면 예외를 반환한다.")
    @Test
    void create_fail_by_duplicated_cards() {
        // given
        List<Card> expected = createFillSameCards();
        // when & then
        assertThatThrownBy(() -> CardDeck.createShuffled(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Card> createFillSameCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(new Card(CardType.CLUB, CardValue.ACE));
        }
        return cards;
    }

    @DisplayName("카드를 한 장을 맨 위에서 뽑아서 반환한다.")
    @Test
    void returns_drawn_card() {
        // given
        List<Card> givenCards = createFillCards();
        CardDeck cardDeck = CardDeck.createShuffled(givenCards);

        Card expected = givenCards.get(0);
        // when
        Card actual = cardDeck.draw();
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
