package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Arboretum {

    /**
     * A hiddenState string array is well-formed if it complies with the following rules:
     *
     * hiddenState[0] - Deck
     *                      A number of 2-character card substrings sorted alphanumerically as defined below
     *                      For example: "a3a8b5b6c2c7d1d3d7d8m1"
     *
     * hiddenState[1] - Player A's hand
     *                      0th character is 'A'
     *                      Followed by 7, 8 or 9 2-character card substrings sorted alphanumerically.
     *                      For example a full hand String might look like: "Ab3b4c1j1m2m5m8"
     *
     * hiddenState[2] - Player B's hand
     *                      0th character is 'B'
     *                      Followed by 7, 8 or 9 2-character _card_ substrings
     *                      For example: "Ba6b7b8c8d2j2j8"
     *
     * Where:
     * "card substring" - A 2-character string that represents a single card.
     *                     0th character is 'a', 'b', 'c', 'd', 'j', or 'm' representing the card species.
     *                     1st character is a sequential digit from '1' to '8', representing the value of the card.
     *                     For example: "d7"
     *
     * "alphanumerical(ly)" - This means that cards are sorted first alphabetically and then numerically.
     *                     For example, "m2" appears before "m5" because 2 < 5
     *                     Whilst "b4" appears before "c1" because alphabetical ordering takes precedence over
     *                     numerical ordering.
     *
     * Exceptions:
     *      - If the deck is empty, hiddenState[0] will be the empty string ""
     *      - If a player's hand is empty, then the corresponding hiddenState[i] will contain only the player's ID.
     *      For example: if player A's hand is empty then hiddenState[1] will be "A" with no other characters.
     *
     * @param hiddenState the hidden state array.
     * @return true if the hiddenState array is well-formed, false if it is not well-formed.
     * Author: Samuel Barilaro.
     * TASK 3
     */
    public static boolean isHiddenStateWellFormed(String[] hiddenState) {
        //returns false if the hiddenState is not of the right length.
        if (hiddenState.length != 3) return false; int L1 = hiddenState[1].length(); int L2 = hiddenState[2].length();
        //checks that the hand lengths are within the right ranges, the player ids are correct and the things that need to
        //in alphanumeric order are in such an order,
        return (L1 >= 15 && L1 <= 19 || L1 == 1) && (L2 >= 15 && L2 <= 19 || L2 == 1) && hiddenState[1].charAt(0) == 'A'
                && hiddenState[2].charAt(0) == 'B' && isAlphaNumeric(hiddenState[0])
                && isAlphaNumeric(hiddenState[1].substring(1)) && isAlphaNumeric(hiddenState[2].substring(1));
    }
    //isAlphaNumeric checks if a series of card strings are in alphanumeric order.
    public  static boolean isAlphaNumeric (String string){
        int strLength = string.length();
        //if the string is not even then it cant be in correct alphanumeric card form and so the method returns false.
        //if the string doesn't contain species characters where it should or doesn't contain card values where it should.
        //the method returns false;
        if ((strLength & 1) == 1) return false;
        for (int i = 0; i < strLength; i++) {
            if ((i & 1) == 0 && !"abcdjm".contains(string.charAt(i)+"")
                    || (string.charAt(i) < '1' && string.charAt(i) > '8')) return false;
        }
        //if a species character is greater than a later species character then the method returns false.
        //if a card value is greater than a later card value and the preceding species character is the same
        //the method returns false.
        for (int i = 1; i < strLength-2; i++) {
            if (string.charAt(i) > string.charAt(i + 2) && ((i & 1) == 0
                    || (string.charAt(i - 1) == string.charAt(i +1 )))) return false;
        }
        return true;
    }

    @Test
    void isAlphaNumeric(){
        // test1: out of numerical order with same while species
        Assertions.assertFalse(isAlphaNumeric("a2a3a5b1b4b2c1c2c4j1"));
        // test2: species out of alphabetical order
        Assertions.assertFalse(isAlphaNumeric("b3j1c1j2m2m5m8"));
        // test3: nonexistent species i
        Assertions.assertFalse(isAlphaNumeric("a6b7b8c8d2j2i8"));
        // test4: nonexistent species B
        Assertions.assertFalse(isAlphaNumeric("a8b5B6c2c7d1"));
        // test5: contains card that is not 2 character substring
        Assertions.assertFalse(isAlphaNumeric("c1c2c3c4c5d2d4jj3m2"));
        // test6: substring in alphanumeric order
        Assertions.assertTrue(isAlphaNumeric("c1c2c3c4c5d2d4j3m2"));
        // test7: substring in alphanumeric order
        Assertions.assertTrue(isAlphaNumeric("a6b7b8c8d2j2j8"));
        // test4: space at end of string
        Assertions.assertFalse(isAlphaNumeric("a8b5B6c2c7d1 "));
    }
    /**
     * A sharedState string array is well-formed if it complies with the following rules:
     *
     * sharedState[0] - a single character ID string, either "A" or "B" representing whose turn it is.
     * sharedState[1] - Player A's arboretum
     *                     0th character is 'A'
     *                     Followed by a number of 8-character _placement_ substrings as defined below that occur in
     *                       the order they were played. Note: these are NOT sorted alphanumerically.
     *                     For example: "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01"
     *
     * sharedState[2] - Player A's discard
     *                    0th character is 'A'
     *                    Followed by a number of 2-character _card_ substrings that occur in the order they were
     *                    played. Note: these are NOT sorted alphanumerically.
     *                    For example: "Aa7c4d6"
     *
     * sharedState[3] - Player B's arboretum
     *                    0th character is 'B'
     *                    Followed by a number of 8-character _placement_ substrings that occur in the order they
     *                    were played. Note: these are NOT sorted alphanumerically.
     *                    For example: "Bj5C00C00j6S01C00j7S02W01j4C00W01m6C00E01m3C00W02j3N01W01"
     *
     * sharedState[4] - Player B's discard
     *                    0th character is 'B'
     *                    Followed by a number of 2-character _card_ substrings that occur in the order they were
     *                    played. Note: these are NOT sorted alphanumerically.
     *                    For example: "Bb2d4c5a1d5"
     *
     * Where: "card substring" and "alphanumerical" ordering are defined above in the javaDoc for
     * isHiddenStateWellFormed and placement substrings are defined as follows:
     *
     * "placement substring" - An 8-character string that represents a card placement in a player's arboretum.
     *                  - 0th and 1st characters are a 2-character card substring
     *                  - 2nd character is 'N' for North, 'S' for South, or 'C' for Centre representing the
     *                    direction of this card relative to the first card played.
     *                  - 3rd and 4th characters are a 2-digit number from "00" to "99" representing the distance
     *                    from the first card played to this card, in the direction of the 2nd character.
     *                  - 5th character is 'E' for East, 'W' for West, or 'C' for Centre representing the
     *                    direction of this card relative to the first card played.
     *                  - 6th and 7th characters are a 2-digit number from "00" to "99" representing the distance
     *                    from the first card played to this card, in the direction of the 3rd character.
     *                  For example: "a1C00C00b3N01C00" says that card "a1" was played first and card "b3" was played
     *                  one square north of the first card (which was "a1").
     *
     * Exceptions:
     * If a player's discard or arboretum are empty, (ie: there are no cards in them), then the corresponding string
     * should contain only the player ID.
     * For example:
     *  - If Player A's arboretum is empty, then sharedState[1] would be "A" with no other characters.
     *  - If Player B's discard is empty, then sharedState[4] would be "B" with no other characters.
     *
     * @param sharedState the shared state array.
     * @return true if the sharedState array is well-formed, false if it is not well-formed.
     * Author: Hao Zhang
     * TASK 4
     */
    public static boolean isSharedStateWellFormed(String[] sharedState) {

        // return false if the shared state is not well-formed
        // test the length and whether right thing is put at right position
        // use help function to test whether substrings in the shareState are well-formed
        return sharedState.length == 5       && (sharedState[0].equals("A") || sharedState[0].equals("B"))
                && sharedState[1].charAt(0)  == 'A' && areTheyPlacementSubstrings(sharedState[1].substring(1))
                && sharedState[2].charAt(0)  == 'A' && isCardSubstring(sharedState[2].substring(1))
                && sharedState[3].charAt(0)  == 'B' && areTheyPlacementSubstrings(sharedState[3].substring(1))
                && sharedState[4].charAt(0)  == 'B' && isCardSubstring(sharedState[4].substring(1));
    }
        // return whether the card substring is well-formed, should be like "a1" or "a1a2..."
        // only allow valid species and values
    public static boolean isCardSubstring (String string){
        int strLength = string.length();
        if ((strLength & 1) == 1) return false;
        for (int i = 0; i < strLength; i++) {
            if (((i & 1) == 0) && !"abcdjm".contains(string.charAt(i)+"")
                    || (string.charAt(i) < '1' && string.charAt(i) > '8')) return false;
        }
        return true;
    }
        // delete the turn "A" or "B", return true if it satisfies to be like "a1C00C00", "a1C00E00b2C00W00"...
        // test the length
        // test valid species, values and positions
    public static boolean areTheyPlacementSubstrings (String string){
        int strLength = string.length();
        if (strLength%8 != 0) return false;
        if (string.equals("")) return true;
        for (int i = 0; i < strLength-7; i = i + 8){
            if (!("abcdjm".contains(string.charAt(i) + "") && (string.charAt(i + 1) > '0' || string.charAt(i + 1) < '9')
                    && "NSC".contains(string.charAt(i + 2) + "") && Character.isDigit(string.charAt(i + 3))
                    && Character.isDigit(string.charAt(i + 4))   && "EWC".contains(string.charAt(i + 5) + "")
                    && Character.isDigit(string.charAt(i + 6))   && Character.isDigit(string.charAt(i + 7))))
                return false;
        }
        return true;
    }

    // Test Start:
    @Test
    void isCardSubstringTest (){
        // test1: Capital species
        String test1 = "a1a2a3a4b1b2b3b4c1C2c3c4c5";
        Assertions.assertFalse(isCardSubstring(test1));
        // test2: missing value of card
        String test2 = "a1a2a3a";
        Assertions.assertFalse(isCardSubstring(test2));
        // test3: empty
        String test3 = "";
        Assertions.assertTrue(isCardSubstring(test3));
    }

    @Test
    void areTheyPlacementSubstringTest (){
        // test4: Unknown Direction
        String test4 = "Aa1C00C00a2D00E01";
        Assertions.assertFalse(areTheyPlacementSubstrings(test4));
        // test5: missing char
        String test5 = "Aa1C00C00a3C01C0";
        Assertions.assertFalse(areTheyPlacementSubstrings(test5));
        // test 6: missing first Card
        String test6 ="Aa1S01E01";
        Assertions.assertFalse(areTheyPlacementSubstrings(test6));
    }

    // Test end.

    /**
     * Given a deck string, draw a random card from the deck.
     * You may assume that the deck string is well-formed.
     * @param deck the deck string.
     * @return a random cardString from the deck. If the deck is empty, return the empty string "".
     * TASK 5
     * Author : Vincent
     */
    // get a random card from deck using their indices
    public static String drawFromDeck(String deck) {
        int deckLength = deck.length();
        if (deckLength == 0) return "";
        int random = ((int) (Math.random() * ((deckLength)/2)))*2;
        return deck.substring(random,random+2);
    }

    /**
     * Determine whether this placement is valid for the current player. The "Turn String" determines who is making
     * this placement.
     *
     * A placement is valid if the following conditions are met:
     *
     * - The card is placed adjacent to a card that is already placed, or is placed in the position "C00C00" if this is
     * the first card placed.
     * - The card does not share a location with another card that has been placed by this player.
     * - The card being placed is currently in the hand of this player.
     * - The hand of this player has exactly 9 cards in it.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param placement the placement string of the card to be placed
     * @return false if the placement is valid, false if it is not valid.
     * Author: Samuel Barilaro.
     * TASK 7
     */
    public static boolean isPlacementValid(String[][] gameState, String placement) {
        String a = placement.substring(0, 2); String b = placement.substring(2);
        boolean turn = gameState[0][0].equals("A");
        //Finds the locations adjacent to the placement.
        String[] adjacentPlaces = adjacentLocations(placement);
        //Checks that at least one adjacent location is occupied by a card in the players arboretum,
        //returning false if one is not found.
        if (!b.equals("C00C00")){
            for (int i = 0; i < 5; i++) {
                if (i > 3) return false;
                if ((turn && gameState[0][1].contains(adjacentPlaces[i]))
                        || (!turn && gameState[0][3].contains(adjacentPlaces[i]))) break;
            }
        }
        //Returns weather or not that the card is in hand and is not being played on top of another card and the hand of
        //the player has 9 cards in it.
        return turn && gameState[1][1].contains(a) && gameState[1][1].length() == 19 && !gameState[0][1].contains(b)
                || !turn && gameState[1][2].contains(a) && gameState[1][2].length()==19 && !gameState[0][3].contains(b);
    }
    //adjacentLocations finds the placement substrings adjacent to a card
    public static String[] adjacentLocations (String placement){
        //Removes the location part of the placement substring in to halves and parses the number part of them so that
        //so that the positions can eb shifted.
        String a = placement.substring(2, 5); String b = placement.substring(5, 8);
        int c = Integer.parseInt(placement.substring(3, 5)); int d = Integer.parseInt(placement.substring(6, 8));
        char e = placement.charAt(2); char f = placement.charAt(5);
        //The substring parts are shifter, adjusted with adjustSubCoordinate and reformed into 4 location parts of
        //placement substrings. These are then returned in an array.
        return new String[] {adjustSubCoordinate(e, c + 1, true) + b, adjustSubCoordinate(e, c - 1, true) + b
                , a + adjustSubCoordinate(f, d + 1, false), a + adjustSubCoordinate(f, d - 1, false)};
    }
    //adjustSubCoordinate takes a processed form of half of a placement substring which has be shifted and then adjusts
    // them to be inline with the required placement substring form.
    public static String adjustSubCoordinate (char d, int l, boolean x) {
        if (l == 0) return "C00";
        //takes the absolute value of the number part of the string.
        String str = Integer.toString(Math.abs(l));
        //Adds another zero if the number has become single digit.
        if (l > -10 && l < 10)str = "0" + str;
        //Adjusts the direction depending on the processed number and returns the reformed half of the placement substring.
        if (d == 'C')
            if (l > 0)
                if (x) return "N" + str;
                else return "E" + str;
            else if (x) return "S" + str;
            else return "W" + str;
        return d + str;
    }

    @Test
    void adjacentLocations(){
        // test1: correct locations
        Assertions.assertArrayEquals(adjacentLocations("b2C00C00"), new String[]{"N01C00", "S01C00", "C00E01", "C00W01"});
        // test2: correct locations
        Assertions.assertArrayEquals(adjacentLocations("j4N10E03"), new String[]{"N11E03", "N09E03", "N10E04", "N10E02"});
        // test3: correct locations
        Assertions.assertArrayEquals(adjacentLocations("c2N10W03"), new String[]{"N11W03", "N09W03", "N10W04", "N10W02"});
        // test4: correct locations
        Assertions.assertArrayEquals(adjacentLocations("m8S01W03"), new String[]{"S02W03", "C00W03", "S01W04", "S01W02"});
        // test5: incorrect locations
        Assertions.assertFalse(Arrays.equals(adjacentLocations("b2C00C00"),new String[] {"N01C00","S01C01","C00E01","C00W01"}));
        // test6: incorrect locations
        Assertions.assertFalse(Arrays.equals(adjacentLocations("j4N10E03"),new String[] {"N11E03","N09E03","N10S04","N10E02"}));
        // test7: incorrect locations
        Assertions.assertFalse(Arrays.equals(adjacentLocations("c2N10W03"),new String[] {"N11W03","N09W03","N10E04","N10W02"}));
        // test8: incorrect locations
        Assertions.assertFalse(Arrays.equals(adjacentLocations("m8S01W03"),new String[] {"S22W03","C00W03","S01W04","S01W02"}));
    }

    /**
     * Determine whether the given gameState is valid.
     * A state is valid if the following conditions are met:
     *
     * - There are exactly 48 cards in the game across the deck and each player's hand, arboretum and discard pile.
     * - There are no duplicates of any cards
     * - Every card in each player's arboretum is adjacent to at least one card played _before_ it.
     * - The number of card's in player B's arboretum is equal to, or one less than the number of cards in player
     * A's arboretum.
     * - Each player may have 0 cards in hand only if all cards are in the deck.
     * - Otherwise, a player has exactly 7 cards in their hand if it is not their turn.
     * - If it is a player's turn, they may have 7, 8, or 9 cards in hard.
     * - The number of cards in a player's discard pile is less than or equal to the number of cards in their arboretum.
     *
     * You may assume that the gameState array is well-formed.
     *
     * @param gameState the game state array
     * @return true if the gameState is valid, false if it is not valid.
     * TASK 8
     * Author : Vincent
     */

    public static boolean isStateValid(String[][] gameState) {

        String allCards = removePosition(gameState[0][1]) + gameState[0][2].substring(1)
                + removePosition(gameState[0][3]) + gameState[0][4].substring(1) + gameState[1][0]
                + gameState[1][1].substring(1) + gameState[1][2].substring(1);

        boolean turn = gameState[0][0].equals("A");
        int gs12Length = gameState[1][2].length();
        int gs10Length = gameState[1][0].length();
        int gs01Length = gameState[0][1].length();
        int gs03Length = gameState[0][3].length();
        int gs11Length = gameState[1][1].length();
        int allCardsLength = allCards.length()   ;

        for (int i = 0; i < allCardsLength; i += 2) {
            String card = allCards.substring(i, i+2);
            if (allCards.indexOf(card) != allCards.lastIndexOf(card)) {
                return false;
            }
        }

        for (int i = 1; i < 4; i+=2) {
            for (int c =  1; c < gameState[0][i].length()-1; c+=8) {
                if (!isCardAdjacentToAnotherCard(gameState[0][i].substring(1, c), gameState[0][i].substring(c, c+8)))
                    return false;
            }
        }

        return  allCardsLength == 96 && (((((turn && gs12Length == 15)
                && (gs11Length >= 15 && gs11Length <= 19)) || ((!turn && gs11Length == 15)
                && (gs12Length >= 15 && gs12Length <= 19)))) || gs10Length == 96) && gs01Length >= gs03Length
                && gs01Length - 8 <= gs03Length && !((gameState[0][2].length() - 1) > (gs01Length - 1) / 4
                || (gameState[0][4].length() - 1) > (gs03Length - 1) / 4);
    }

    /**
     * Remove the position of cards (ie. in arboretum (C00C00)) to be a string of cards
     * @param card the card chosen
     * @return a string of cards without the positions
     * Author : Vincent
     */
    public static String removePosition(String card) {
        StringBuilder cards = new StringBuilder();
        for (int i = 1; i < card.length(); i += 8) {
            cards.append(card, i, i+2);
        }
        return cards.toString();
    }

    /**
     * check if a card is adjacent to another card
     * @param arboretum the arboretum of a player
     * @param placement the placement of a card
     * @return true if a card is adjacent to another card and false otherwise
     * Author : Sam
     */
    public static boolean isCardAdjacentToAnotherCard(String arboretum, String placement) {
        String[] adjacentPlaces = adjacentLocations(placement);
        if (placement.substring(2).equals("C00C00")) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (arboretum.contains(adjacentPlaces[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether the given player has the right to score the given species. Note: This does not check whether
     * a viable path exists. You may gain the right to score a species that you do not have a viable scoring path for.
     * See "Gaining the Right to Score" in `README.md`.
     * You may assume that the given gameState array is valid.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array.
     * @param player the player attempting to score.
     * @param species the species that is being scored.
     * @return true if the given player has the right to score this species, false if they do not have the right.
     * TASK 9
     * Author : Vincent
     */
    // compare the species score in both hands
    public static boolean canScore(String[][] gameState, char player, char species) {
        // FIXME TASK 9
        var handASum = 0;
        var handA = gameState[1][1].substring(1);
        var specialA = 0;
        for (int i = 0; i < gameState[1][1].length()-1; i+=2) {
            if (handA.charAt(i) == species) {
                int value = Character.getNumericValue(handA.charAt(i + 1));
                handASum += value;
                if (value == 1) {
                    specialA = 1;
                }
                else if (value == 8) {
                    specialA = 8;
                }
            }
        }

        var handBSum = 0;
        var handB = gameState[1][2].substring(1);
        var specialB = 0;
        for (int i = 0; i < gameState[1][2].length()-1; i+=2) {
            if (handB.charAt(i) == species) {
                int value = Character.getNumericValue(handB.charAt(i + 1));
                handBSum += value;
                if (value == 1) {
                    specialB = 1;
                }
                else if (value == 8) {
                    specialB = 8;
                }
            }
        }

        if (specialA == 1 && specialB == 8) {
            handBSum -= 8;
        }
        else if (specialA == 8 && specialB == 1) {
            handASum -= 8;
        }

        return (player == 'A' && handASum > handBSum) || (player == 'B' && handBSum > handASum) || handASum == handBSum;
    }

    /**
     * Find all the valid placements for the given card for the player whose turn it is.
     * A placement is valid if it satisfies the following conditions:
     * 1. The card is horizontally or vertically adjacent to at least one other placed card.
     * 2. The card does not overlap with an already-placed card.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param card the card to play
     * @return a set of valid card placement strings for the current player.
     * TASK 10
     */
    public static Set<String> getAllValidPlacements(String[][] gameState, String card) {
        // finds the index of the player's arboretum within the sharedState of the gameState.
        int t; if (gameState[0][0].equals("A")) t = 1; else t = 3;
        // If the player's arboretum is empty the function returns the card at the centre position.
        Set<String> validPlacements = new HashSet<>();
        if (gameState[0][t].length() == 1) validPlacements.add(card + "C00C00");
        // The adjacentLocations helper function is used to find the adjacent locations of each card in the players
        // arboretum, adding the locations with the card prepended to them so long that the position is not contained
        // the player's arboretum.
        else for (int c = 1; c < (gameState[0][t].length() - 1); c += 8){
                for (String position : adjacentLocations(gameState[0][t].substring(c, c + 8))) {
                    if (!gameState[0][t].contains(position)) validPlacements.add(card + position);
            }
        }
        return validPlacements;
    }

    /**
     * Find all viable scoring paths for the given player and the given species if this player has the right to
     * score this species.
     *
     * A "scoring path" is a non-zero number of card substrings in order from starting card to ending card.
     * For example: "a1a3b6c7a8" is a path of length 5 starting at "a1" and ending at "a8".
     *
     * A path is viable if the following conditions are met:
     * 1. The player has the right to score the given species.
     * 2. Each card along the path is orthogonally adjacent to the previous card.
     * 3. Each card has value greater than the previous card.
     * 4. The path begins with the specified species.
     * 5. The path ends with the specified species.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param player the given player
     * @param species the species the path must start and end with.
     * @return a set of all viable scoring paths if this player has the right to score this species, or null if this
     * player does not have the right to score this species. If the player has no viable scoring paths (but has the
     * right to score this species), return the empty Set.
     * TASK 12
     * Author: Samuel Barilaro
     */
    public static Set<String> getAllViablePaths(String[][] gameState, char player, char species) {
        //Returns null if the player is unable to score the specified species.
        if (!canScore(gameState, player, species)) return null;
        //Finds the index of the players arboretum.
        int t; if (player ==  'A') t = 1; else t = 3;
        //Finds all of the viable paths that can be formed from each card with the desired species
        // and adds them all to one hashSet.
        Set<String> allPaths = new HashSet<>();
        for (int i = 1; i < gameState[0][t].length(); i += 8) {
            if (gameState[0][t].charAt(i) == species) allPaths.addAll(viablePaths(gameState[0][t], gameState[0][t].
                    substring(i, i + 8), gameState[0][t].substring(i, i + 2), species, new HashSet<>()));
        }
        return allPaths;
    }
    //viablePaths recursively finds all viable paths that can be formed starting at a particular card.
    public static HashSet<String> viablePaths(
            String arboretum, String card, String path, char species, HashSet<String> validPaths) {
        //Finds all of the possible cards that may come next in a path.
        HashSet<String> nextCards = new HashSet<>();
        for (int c = 0; c < 4; c++) {
            int i = arboretum.indexOf(adjacentLocations(card)[c]);
            if (i != -1 && arboretum.charAt(i - 1) > card.charAt(1)) nextCards.add(arboretum.substring(i - 2, i + 6));
        }
        //Adds a path to the validPaths hashSet if the latest card is of the desired species and the path is longer
        //than 2 cards. (It is assumed that the species given is the same as the species of the starting card.
        if (card.charAt(0) == species && path.length() > 2) validPaths.add(path);
        //Calls the function again for all possible next cards in the path and with updated path strings.
        //Each step of recursion that finds new valid paths adds them to the validPaths hashSet past down the recursion.
        //the recursion ends when nextCards is empty.
        nextCards.forEach(nextCard -> validPaths.addAll(viablePaths
                (arboretum, nextCard, path + nextCard.substring(0, 2), species, validPaths)));
        //Returns the valid paths found.
        return validPaths;
    }

    /**
     * Find the highest score of the viable paths for the given player and species.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param player the given player
     * @param species the species to score
     * @return the score of the highest scoring viable path for this player and species.
     * If this player does not have the right to score this species, return -1.
     * If this player has the right to score this species but there is no viable scoring path, return 0.
     * TASK 13
     * Author: Samuel Barilaro
     */
    public static int getHighestViablePathScore(String[][] gameState, char player, char species) {
        Set<String> availablePaths = getAllViablePaths(gameState, player, species);
        //Returns -1 if the player is unable to score the specified species.
        if (availablePaths == null) return -1;
        //scores each path.
        int highestScore = 0;
        for (String path : availablePaths) {
            //+ 1 point per card.
            int score = path.length()/2;
            int scoreCopy = score;
            //Checks if all of the cards in the path are of the same species.
            boolean sameSpecies = true;
            for (int i = 0; i < scoreCopy*2; i += 2) {
                if (path.charAt(i) != species) {sameSpecies = false; break;}
            }
            //+ 1 point per card if all of the cards are the same.
            if (path.length() >= 8 && sameSpecies) score = scoreCopy * 2;
            //+ 2 points if the path ends with an 8.
            if (path.charAt(scoreCopy * 2 - 1) == '8') score += 2;
            //+ 1 point if the first card is a 1.
            if (path.charAt(1) == '1') score++;
            //updates the highest score if the path score just calculated in the step is greater
            //than the previous highest score.
            if (score > highestScore) highestScore = score;
        }
        return highestScore;
    }

    /**
     * AI Part 1:
     * Decide whether to draw a card from the deck or a discard pile.
     * Note: This method only returns the choice, it does not update the game state.
     * If you wish to draw a card from the deck, return "D".
     * If you wish to draw a card from a discard pile, return the cardstring of the (top) card you wish to draw.
     * You may count the number of cards in a players' hand to determine whether this is their first or final draw
     * for the turn.
     *
     * Note: You may only draw the top card of a players discard pile. ie: The last card that was added to that pile.
     * Note: There must be cards in the deck (or alternatively discard) to draw from the deck (or discard) respectively.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @return "D" if you wish to draw from the deck, or the cardstring of the card you wish to draw from a discard
     * pile.
     * TASK 14
     * Author : Vincent
     */
    public static String chooseDrawLocation(String[][] gameState) {
        // FIXME TASK 14
        var player = gameState[0][0].charAt(0);
        return heuristic(gameState,player);
    }

    /**
     * get the card that will extend the path
     * iterate over all the sorted optimal cards and decide where or what to draw
     * if no optimal card is found, draw from deck
     * if deck has no card, dram from discard pile that has bigger card value
     * @param gameState the game state array
     * @param player the given player
     * @return where to draw (deck, discardA or discardB)
     * Author : Vincent
     */
    public static String heuristic(String[][] gameState, char player) {
        Set<String> path = new HashSet<>();
        var discardA = "";
        var discardB = "";
        var deck = gameState[1][0];
        if (gameState[0][2].length() > 1) {
            discardA = gameState[0][2].substring(gameState[0][2].length() - 2);
        }
        if (gameState[0][4].length() > 1) {
            discardB = gameState[0][4].substring(gameState[0][4].length() - 2);
        }
        var species = allSpecies(gameState);
        for (int i = 0; i < species.length(); i++) {
            if (getAllViablePaths(gameState,player,species.charAt(i)) == null) {
                continue;
            }
            var paths = getAllViablePaths(gameState,player,species.charAt(i));
            assert paths != null;
            if (paths.isEmpty()) {
                continue;
            }
            path.addAll(paths);
        }
        var target = sortedOptimalCards(optimalCards(path));
        for (var card : target) {
            if (discardA.equals(card) || discardB.equals(card)) {
                return card;
            }
        }
        if (deck.length() == 0) {
            if (discardA.charAt(1) > discardB.charAt(1)) {
                return discardA;
            }
            return discardB;
        }
        return "D";
    }

    /**
     * get all the species that a player owns
     * considered cards are those in player's hand and arboretum
     * @param gameState the game state array
     * @return all the species available on arboretum as a string
     * Author : Vincent
     */
    public static String allSpecies(String[][] gameState) {
        var cardsA = gameState[1][1].substring(1) + removePosition(gameState[0][1]);
        var cardsB = gameState[1][2].substring(1) + removePosition(gameState[0][3]);
        var play = gameState[0][0];
        StringBuilder species = new StringBuilder();
        if (play.equals("A")) {
            for (int i = 0; i < cardsA.length(); i+=2) {
                if (!species.toString().contains(Character.toString(cardsA.charAt(i)))) {
                    species.append(cardsA.charAt(i));
                }
            }
        }
        else {
            for (int i = 0; i < cardsB.length(); i+=2) {
                if (!species.toString().contains(Character.toString(cardsB.charAt(i)))) {
                    species.append(cardsB.charAt(i));
                }
            }
        }
        return species.toString();
    }
    /**
     * get the start and end cards of each path
     * check if there is a lower or higher card value to connect
     * card at the beginning and in the end must be the same species
     * @param paths the available paths in an arboretum
     * @return list of cards optimal for the player
     * Author : Vincent
     */
    public static ArrayList<String> optimalCards(Set<String> paths) {
        ArrayList<String> cards = new ArrayList<>();
        for (var path : paths) {
            var first = path.substring(0, 2);
            var last = path.substring(path.length() - 2);
            var i = 1;
            while (Character.getNumericValue((first.charAt(1) - i)) > 0) {
                var next = first.substring(0,1) + Character.getNumericValue(first.charAt(1) - i);
                if (!cards.contains(next)) {
                    cards.add(next);
                }
                i++;
            }
            var j = 1;
            if (path.length() > 2) {
                while (Character.getNumericValue((last.charAt(1) + j)) <= 8) {
                    var temp = last.substring(0, 1) + Character.getNumericValue(last.charAt(1) + j);
                    if (!cards.contains(temp)) {
                        cards.add(temp);
                    }
                    j++;
                }
            }
        }
        return cards;
    }

    /**
     * sort the optimal cards by species and value in descending order
     * @param cards a list of cards
     * @return a sorted optimal cards to get the best path score
     * Author : Vincent
     */
    public static ArrayList<String> sortedOptimalCards(ArrayList<String> cards) {
        if (cards.isEmpty()) {
            return cards;
        }
        for (int i = 0; i < cards.size() - 1; i++) {
            var index = i;
            var largest = Integer.parseInt(cards.get(i).substring(1));
            for (int j = i + 1; j < cards.size(); j++)
                if (Integer.parseInt(cards.get(j).substring(1)) > largest) {
                    largest = Integer.parseInt(cards.get(j).substring(1));
                    index = j;
                }
            Collections.swap(cards,i,index);
        }
        return cards;
    }

    /**
     * AI Part 2:
     * Generate a moveString array for the player whose turn it is.
     *
     * A moveString array consists of two parts;
     * moveString[0] is the valid card _placement_ string for the card you wish to place.
     * moveString[1] is the cardstring of the card you wish to discard.
     *
     * For example: If I want to play card "a1" in location "N01E02" and discard card "b4" then my moveString[] would
     * be as follows:
     * moveString[0] = "a1N01E02"
     * moveString[1] = "b4"
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @return a valid move for this player.
     * TASK 15
     * Author : Vincent
     */
    // return the placement for the most optimal card
    // optimal card is the one that creates the highest scoring path
    // discard the least optimal card
    // least optimal card is that it gives lowest scoring path and has little flexibility for future development
    public static String[] generateMove(String[][] gameState) {
        // FIXME TASK 15
        String[] move = new String[2];
        var player = gameState[0][0].charAt(0);
        var hand = gameState[1][1];
        if (player == 'B') {
            hand = gameState[1][2];
        }
        hand = hand.substring(1);
        var cards = cardStringToList(hand);
        var bestCard = cards.get(0);
        var bestScore = -1;
        for (var card : cards) {
             if (optimalPlacement(gameState,card) == null) {
                 continue;
             }
             var score = Integer.parseInt(optimalPlacement(gameState,card).get(1));
             if (score > bestScore) {
                 bestCard = card;
                 bestScore = score;
             }
        }
        move[0] = optimalPlacement(gameState, bestCard).get(0);
        cards.remove(bestCard);
        hand = String.join(", ", cards);
        hand = hand.replaceAll(", ","");
        move[1] = uselessCard(gameState,hand);
        return move;
    }

    /**
     * Get the best placement of a card based on the game state.
     * @param gameState the game state
     * @param card the chosen card
     * @return the most optimal placement that gives the highest score and the score
     * Author : Vincent
     */
    public static ArrayList<String> optimalPlacement(String[][] gameState, String card) {
        var player = gameState[0][0].charAt(0);
        var placements = getAllValidPlacements(gameState,card);
        ArrayList<String> output = new ArrayList<>();
        var paths = getAllViablePaths(gameState,player,card.charAt(0));
        if (paths == null) {
            return null;
        }
        else if (paths.size() == 0) {
            var place = placements.toArray(new String[placements.size()]);
            output.add(place[0]);
            if (player == 'A') {
                gameState[0][1] += place[0];
                output.add(Integer.toString(getHighestViablePathScore(gameState,player,card.charAt(0))));
                gameState[0][1] = gameState[0][1].substring(0,gameState[0][1].length()-8);
            }
            else {
                gameState[0][3] += place[0];
                output.add(Integer.toString(getHighestViablePathScore(gameState,player,card.charAt(0))));
                gameState[0][3] = gameState[0][3].substring(0,gameState[0][1].length()-8);
            }
            return output;
        }
        // assume first card is the best placement ie. a1C00C00
        var best= placements.toArray(String[] :: new)[0];
        var bestPathScore = 0;
        // check if new arboretum has the highest path that is better previous highest
        // get the highest path score so if it has higher path score save it and iterate over all the cards
        for (var place : placements) {
            var temp2 = 0;
            if (player == 'A') {
                gameState[0][1] += place;
                temp2 = getHighestViablePathScore(gameState,player,card.charAt(0));
                gameState[0][1] = gameState[0][1].substring(0,gameState[0][1].length()-8);
            }
            else {
                gameState[0][3] += place;
                temp2 = getHighestViablePathScore(gameState,player,card.charAt(0));
                gameState[0][3] = gameState[0][3].substring(0,gameState[0][1].length()-8);
            }
            if (temp2 > bestPathScore) {
                best = place;
                bestPathScore = temp2;
            }
        }
        output.add(best);
        output.add(Integer.toString(bestPathScore));
        return output;
    }

    /**
     * Get the most useless card that does not create a path or gives the least value path
     * Hand must not be empty
     * @param gameState the game state
     * @param hand a player's hand
     * @return the most useless card in hand to be discarded
     * Author : Vincent
     */
    // Check for least path score
    // Tiebreaker is the number of placements
    // Less placements mean more useless (less flexibility)
    public static String uselessCard(String[][] gameState, String hand) {
        if (hand.length() == 3) {
            return hand;
        }
        var player = gameState[0][0].charAt(0);
        var cards = cardStringToList(hand);
        var flexibility = 100;
        var worstCard = hand.substring(0,2);
        var worstScore = 100;
        for (var card : cards) {
            var placements = getAllValidPlacements(gameState, card);
            var flex = placements.size();
            for (var place : placements) {
                var temp = 0;
                if (player == 'A') {
                    gameState[0][1] += place;
                    temp = getHighestViablePathScore(gameState, player, card.charAt(0));
                    gameState[0][1] = gameState[0][1].substring(0, gameState[0][1].length() - 8);
                } else {
                    gameState[0][3] += place;
                    temp = getHighestViablePathScore(gameState, player, card.charAt(0));
                    gameState[0][3] = gameState[0][3].substring(0, gameState[0][1].length() - 8);
                }
                if (temp < worstScore || (temp == worstScore && flex < flexibility)) {
                    worstCard = card;
                    worstScore = temp;
                    flexibility = flex;
                }
            }
        }
        return worstCard;
    }

    /**
     * Convert a string of cards to a list of cards
     * @param card a string of cards
     * @return a list of cards
     * Author : Vincent
     */
    public static ArrayList<String> cardStringToList(String card) {
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0; i < card.length() - 1; i += 2) {
            cards.add(card.substring(i,i+2));
        }
        return cards;
    }
}