package com.eduromero.cards.service.impl;

import com.eduromero.cards.constants.CardConstants;
import com.eduromero.cards.dto.CardDTO;
import com.eduromero.cards.entity.Card;
import com.eduromero.cards.exception.CardAlreadyExistsException;
import com.eduromero.cards.exception.ResourceNotFoundException;
import com.eduromero.cards.mapper.CardMapper;
import com.eduromero.cards.repository.CardRepository;
import com.eduromero.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card with provided mobile number already exists");
        }

        cardRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardDTO fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        return CardMapper.mapToCardDTO(card, new CardDTO());
    }

    /**
     *
     * @param cardDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardDTO.getCardNumber())
        );

        CardMapper.mapToCard(cardDTO, card);
        cardRepository.save(card);
        return true;
    }

    /**
     * @param mobileNumber - Input mobileNumber
     * @return boolean indicating if the deletion of card details was successful
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        cardRepository.deleteById(card.getCardId());
        return true;
    }
}
