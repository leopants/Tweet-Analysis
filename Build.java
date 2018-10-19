package twitterusersentiment;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.*;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.ResponseList;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Class that creates a Twitter object that can obtain a user's tweets.
 */
public class Build {

    protected Twitter twitter;
    private final ConfigurationBuilder configBuilder;
    private String userName = JOptionPane.showInputDialog("What is your Twitter user name?");
    private String[] userTweets = new String[20];
    private int counter = 0;
    private ArrayList<String> positiveWords = new ArrayList<>();
    private int totalPositiveTweets = 0;
    private String[] wordInTweet;

    /**
     * Constructor that creates a TwitterFactory object which can get
     * information from Twitter API.
     */
    public Build() {

        configBuilder = new ConfigurationBuilder();     //declare variables to use the twitter API
        configBuilder.setDebugEnabled(true);
        configBuilder.setOAuthConsumerKey("x44of67X6r0TQiffYO2hcQACF");
        configBuilder.setOAuthConsumerSecret("sZjZvcpTqPjJ2FNO5hZ9wYN9c1kN2Prsoq0agjy6IrHO5CDf7d");
        configBuilder.setOAuthAccessToken("1664287795-tUFqc3LqrgJ1N43iLobEN6XoRg9eoPmwdF2gIzN");
        configBuilder.setOAuthAccessTokenSecret("OxIDyuaLVJnaP41U49Kc9yCJHsVPY4ZZnhVOMGb1QF2Lm");

        TwitterFactory tf = new TwitterFactory(configBuilder.build());     //after config builder is initialized a Twitter Factory object is made
        twitter = tf.getInstance();
    }

    /**
     * Gets a user's tweets and saves them to an array of Strings.
     */
    public void getTweets() {
        try {
            ResponseList<Status> statuses = twitter.getUserTimeline(userName);   //statuses gets the last 20 tweets of userName
            for (Status status : statuses) {    //for every status in the array 
                String fmt = "@" + status.getUser().getScreenName() + " - " + status.getText();   //unused but fmt can print the tweets that were pulled
                userTweets[counter++] = status.getText();    //add the tweet to the userTweets array 

            }
        } catch (TwitterException e) {
        }
    }

    /**
     * Method to add the words from the positive words text file to an array
     * list.
     */
    public void addPositiveWords(String newWord) {
        positiveWords.add(newWord);     //used by main to make an arraylist from a text file of lexigraphically ordered words
    }

    /**
     * Binary search algorithm.
     */
    public int binarySearch(ArrayList<String> arr, String word) {   //binary search algorithmn for finding if a word from the tweet is in the list of positive words
        int l = 0, r = arr.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid 
            if (arr.get(m).equals(word)) {
                return 1;

            }

            // If x greater, ignore left half 
            if (arr.get(m).compareTo(word) < 0) {
                l = m + 1;
            } // If x is smaller, ignore right half 
            else {
                r = m - 1;
            }
        }

        // if we reach here, then element was  
        // not present 
        return -1;
    }

    /**
     * Method that gets a user's positive tweets incrementing a counter of total
     * positive tweets.
     */
    public void getPositiveTweets() {     //method to count the total amount of positive tweets
        for (int i = 0; i < counter; i++) {     //for every tweet
            String currentTweet = userTweets[i];
            for (int j = 0; j < currentTweet.length(); j++) {   //for every word in that current tweet
                wordInTweet = currentTweet.split(" ");   //declare a new array that adds every word from the tweet 
            }
            for (String currentWord : wordInTweet) {    //for every word in the wordInTweet array
                int searchOutcome = binarySearch(positiveWords, currentWord);   //binary search the word with the list
                if (searchOutcome == 1) {     //if that word is found then increment positive tweets by one
                    totalPositiveTweets++;

                }
            }
        }

    }

    /**
     * Returns total positive tweets divided by the number checked.
     */
    public String positiveTweetReturn() {
        return userName + ", " + totalPositiveTweets + " out of 20 of your tweets are positive.";    //final statement that returns the amount of positive tweets
    }

}
