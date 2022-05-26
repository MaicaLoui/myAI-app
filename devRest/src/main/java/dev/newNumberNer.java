package dev;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class newNumberNer {

    public void numberNer(String sentence){
        try (
                FileWriter fw = new FileWriter("en-ner-number.train", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("When i got home today, my mother asked me for the number of Pizza Hut.\n" +
                    "i couldn't remember it so i looked it up, and found it.\n" +
                    "at first i found the number <START:number> "+sentence+" <END> and after a while a found <START:number> "+sentence+" <END> .\n" +
                    "my mother first called the number <START:number> "+sentence+" <END> but no one picked up.\n" +
                    "so we tried the other number which was <START:number> "+sentence+" <END> . \n" +
                    "after waiting on the line of <START:number> "+sentence+" <END> we hung up because no one was answering the phone.\n" +
                    "\n" +
                    "so i called my friend to see if the number <START:number> "+sentence+" <END> was right.\n" +
                    "when she saw the number <START:number> "+sentence+" <END> she confirmed that i had the wrong number.\n" +
                    "she gave me the official Pizza Hut number which is <START:number> "+sentence+" <END> .\n" +
                    "when i called <START:number> "+sentence+" <END> the lady at the line said, Pizza Hut what can i help you with.\n" +
                    "guess what number i was dailing .\n" +
                    "<START:number> "+sentence+" <END>\n" +
                    "She asked me if i also needed the address.\n" +
                    "\n" +
                    "when i was walking to buy my movie ticker i stumble uppon an old class mate.\n" +
                    "we were so happy to see each other since he had left to go study abroad.\n" +
                    "but i had to ask for his number because the movie was about to start.\n" +
                    "whats your number ?\n" +
                    "<START:number> "+sentence+" <END>\n" +
                    "he stuttered and said once again. \n" +
                    "my number is <START:number> "+sentence+" <END> and what is your?\n" +
                    "mine is <START:number> "+sentence+" <END> but you can text me on this number <START:number> "+sentence+" <END> .\n" +
                    "okay i will call you on <START:number> "+sentence+" <END> and text you on <START:number> "+sentence+" <END> ." +
                    "\n");

        } catch (
                IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}
