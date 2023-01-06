package eu.telecomnancy.flashcards;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.flashcards.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class cardListTest {
    CardList cardList = new CardList();

    @Test
    void addingElement() {
        cardList.addCard(new Card("question", "answer", 2.3, 1.0, 0, 0, 0, 0, 0));
        cardList.addCard(new Card("question2", "answer2", 2.3, 1.0, 0, 0, 0, 0, 0));
        cardList.addCard(new Card("client", "answer3", 2.3, 1.0, 0, 0, 0, 0, 0));
        assertEquals(3, cardList.getCardList().size());
        assertEquals(cardList.isQuestionInCardList("question"), true);
    }
}
