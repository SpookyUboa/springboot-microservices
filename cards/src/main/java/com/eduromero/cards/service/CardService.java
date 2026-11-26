package com.eduromero.cards.service;

import com.eduromero.cards.dto.CardDTO;

public interface CardService {

    /**
     * @param phoneNumber - Victim's phone number
     */
    void createCard(String phoneNumber);

    /**
     * @param phoneNumber - Victim's phone Number
     *  @return Victim's contract details
     */
    CardDTO fetchCard(String phoneNumber);

    /**
     * @param cardDTO - CardDTO Object
     * @return boolean indicating operation result
     */
    boolean updateCard(CardDTO cardDTO);

    /**
     * @param phoneNumber - Input Phone Number
     * @return boolean indicating operation result
     */
    boolean deleteCard(String phoneNumber);

}
