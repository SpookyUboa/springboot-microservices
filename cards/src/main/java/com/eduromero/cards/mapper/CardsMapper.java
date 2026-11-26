package com.eduromero.cards.mapper;

import com.eduromero.cards.dto.CardDTO;
import com.eduromero.cards.entity.Card;

public class CardsMapper {

    public static CardDTO mapToCardDTO(Card card, CardDTO cardDTO) {
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setPhoneNumber(card.getPhoneNumber());
        cardDTO.setTotalLimit(card.getTotalLimit());
        cardDTO.setAvailableAmount(card.getAvailableAmount());
        cardDTO.setAmountUsed(card.getAmountUsed());
        return cardDTO;
    }

    public static Card mapToCards(CardDTO cardDTO, Card card) {
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setPhoneNumber(cardDTO.getPhoneNumber());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAvailableAmount(cardDTO.getAvailableAmount());
        card.setAmountUsed(cardDTO.getAmountUsed());
        return card;
    }

}
