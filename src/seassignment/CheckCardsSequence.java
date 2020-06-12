/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seassignment;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vishal
 */
public class CheckCardsSequence {

    public static void main(String args[]) {
        CheckCardsSequence cardsSequence = new CheckCardsSequence();
        //Pass the sequence to check
        boolean validSequence = cardsSequence.checkSequence("H#10,H#Q,H#K,H#A");
        //Print Sequence Valid or not
        System.out.println("Valid sequence : " + validSequence);
    }

    public boolean checkSequence(String cards) {
        boolean validSequence = true;
        /**
         *******************************************************************
         * Code for replace A-1,J-11,Q-12 and K-12
         * *****************************************************************
         */
        Map<String, String> replacements = new HashMap<String, String>() {
            {
                put("A", "1");
                put("J", "11");
                put("Q", "12");
                put("K", "13");
            }
        };
        // create the pattern joining the keys with '|'
        String regexp = "A|J|Q|K";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(cards);

        //Finding letters and replaced with their value
        while (m.find()) {
            m.appendReplacement(sb, replacements.get(m.group()));
        }
        m.appendTail(sb);

        /**
         * ****************************************************************
         * Split the modified String by # and , and check the sequence
         * *****************************************************************
         */
        String arrCards[] = sb.toString().split("[^\\w]");

        /*******************************************************************
         * Starts from second string and suits name present at even position
         * and numbers present at odd position in the array
         *******************************************************************
         */
        for (int i = 2; i < arrCards.length; i++) {
            //Check suits in if condition and number sequence in else condition
            if (i % 2 == 0) {
                //Check current suit with previous
                if (!(arrCards[i].equals(arrCards[i - 2]))) {
                    validSequence = false;
                    break;
                }
            } else {
                //Check current card number with previos card number they are in sequence if
                //there difference is 1 Written special condition for ace if diference not 1
                if ((Integer.parseInt(arrCards[i]) - Integer.parseInt(arrCards[i - 2])) != 1) {
                    validSequence = false;
                    //Special Case for ace if last card is ace then difference should  be 12
                    if (Integer.parseInt(arrCards[i]) == Integer.parseInt(arrCards[arrCards.length - 1])
                            && Integer.parseInt(arrCards[i-2]) - Integer.parseInt(arrCards[i]) == 12) {
                        validSequence = true;
                    }
                    break;
                }
            }
        }
        return validSequence;
    }
}
