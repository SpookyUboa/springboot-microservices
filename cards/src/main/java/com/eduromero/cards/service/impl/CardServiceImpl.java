package com.eduromero.cards.service.impl;

import com.eduromero.cards.constants.Constants;
import com.eduromero.cards.dto.CardDTO;
import com.eduromero.cards.entity.Card;
import com.eduromero.cards.exception.CardAlreadyExistsException;
import com.eduromero.cards.exception.ResourceNotFoundException;
import com.eduromero.cards.mapper.CardsMapper;
import com.eduromero.cards.repository.CardRepository;
import com.eduromero.cards.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    /**
     * @param phoneNumber - Victim's phone number
     */
    @Override
    public void createCard(String phoneNumber) {
        Optional<Card> optionalCards= cardRepository.findByPhoneNumber(phoneNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given phoneNumber "+phoneNumber);
        }
        cardRepository.save(createNewCard(phoneNumber));
    }

    private Card createNewCard(String phoneNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setPhoneNumber(phoneNumber);
        newCard.setCardType(Constants.CREDIT_CARD);
        newCard.setTotalLimit(Constants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(Constants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param phoneNumber - Victim's phone Number
     *  @return Victim's contract details
     */
    @Override
    public CardDTO fetchCard(String phoneNumber) {
        Card card = cardRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "phoneNumber", phoneNumber)
        );
        return CardsMapper.mapToCardDTO(card, new CardDTO());
    }

    /**
     * @param cardDTO - CardDTO Object
     * @return boolean indicating operation result
     */
    @Override
    public boolean updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardDTO.getCardNumber()));
        CardsMapper.mapToCards(cardDTO, card);
        cardRepository.save(card);
        return  true;
    }

    /**
     * @param phoneNumber - Input Phone Number
     * @return boolean indicating operation result
     */
    @Override
    public boolean deleteCard(String phoneNumber) {
        Card card = cardRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "phoneNumber", phoneNumber)
        );
        cardRepository.deleteById(card.getCardId());
        return true;
    }


}
