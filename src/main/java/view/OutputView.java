package view;

import dto.response.BattingResultDto;
import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.getProperty("line.separator");

    public void printCardSplitMessage(DrawnCardsInfo dealerCardInfo, final List<DrawnCardsInfo> playerCardInfos) {
        String names = playerCardInfos.stream()
                .map(DrawnCardsInfo::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(NEW_LINE + "딜러와 " + names + "에게 2장을 나누었습니다.");

        printCardInfo(dealerCardInfo);
        playerCardInfos.forEach(this::printCardInfo);
    }

    private void printCardInfo(DrawnCardsInfo cardInfo) {
        System.out.println(cardInfo.getName() + ": " + getCardsInfo(cardInfo.getDrawnCards()));
    }

    public void printPlayerCardInfo(final DrawnCardsInfo info) {
        System.out.println(info.getName() + "카드: " + getCardsInfo(info.getDrawnCards()));
    }

    private String getCardsInfo(final List<String> cards) {
        return cards.stream()
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerCardPickMessage(int dealerDrawLimitScore) {
        System.out.println(String.format("딜러는 %s이하라 한장의 카드를 더 받았습니다.", dealerDrawLimitScore));
    }

    public void printParticipantResults(ParticipantResult dealerResult, final List<ParticipantResult> results) {
        System.out.println();
        printCardsAndScore(dealerResult);
        results.forEach(this::printCardsAndScore);
    }

    private void printCardsAndScore(ParticipantResult result) {
        System.out.println(result.getName() + " 카드: " + getCardsInfo(result.getDrawnCards()) +
                        " - 결과: " + result.getScore());
    }

    public void printBattingResults(List<BattingResultDto> battingResultDtos) {
        int dealerBattingResult = battingResultDtos
                .stream()
                .mapToInt(BattingResultDto::getMoney)
                .sum() * -1;

        System.out.println("최종 수익");
        System.out.println("딜러: " + dealerBattingResult);
        battingResultDtos.forEach(OutputView::printPlayerBattingResult);
    }

    private static void printPlayerBattingResult(BattingResultDto battingResultDto) {
        System.out.println(battingResultDto.getName() + ": " + battingResultDto.getMoney());
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
