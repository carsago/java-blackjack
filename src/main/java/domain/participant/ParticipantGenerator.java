package domain.participant;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;

//TODO: 테스트
public class ParticipantGenerator {

    private ParticipantGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static Players createPlayers(final List<Name> names) {
        List<Card> emptyCards = new ArrayList<>();

        return names.stream()
                .map(name -> new Player(name, new DrawnCards(emptyCards)))
                .collect(collectingAndThen(toList(), Players::new));
    }

    public static Dealer createDealer() {
        List<Card> emptyCards = new ArrayList<>();
        return new Dealer(new DrawnCards(emptyCards));
    }
}
