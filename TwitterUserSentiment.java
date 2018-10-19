package twitterusersentiment;

import java.io.*;
import java.util.Scanner;

/**
 * Tester class for testing the build class.
 * @author leo
 */
public class TwitterUserSentiment {     //

    public static void main(String[] args) throws IOException {
        
        Build build1 = new Build();     //makes a new object of the build class with the default constructor that declares a twitter object

        build1.getTweets();     //gets the user's positive tweets

        Scanner fileIn = new Scanner(new File("positive-words.txt"));     //reads from the positive words text file adding to an array list
        
        while (fileIn.hasNext()) {      //while not end of file
            String newWord = fileIn.nextLine();     //get next line 
            build1.addPositiveWords(newWord);   //push next line to the add positive words method
        }

        build1.getPositiveTweets();     //calls the get positive tweets method
        
        System.out.println(build1.positiveTweetReturn());    //calls method that returns the amount of positive tweets 
        
    }

}
