package nlp;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {

        try(FileWriter fw = new FileWriter("test.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("When i got home today, my mother asked me for the number of Pizza Hut.\n" +
                    "i couldn't remember it so i looked it up, and found it.\n" +
                    "at first i found the number <START:number> 1234567 <END> and after a while a found <START:number> 1234567 <END> .\n" +
                    "my mother first called the number <START:number> 1234567 <END> but no one picked up.\n" +
                    "so we tried the other number which was <START:number> 1234567 <END> . \n" +
                    "after waiting on the line of <START:number> 1234567 <END> we hung up because no one was answering the phone.\n" +
                    "\n" +
                    "so i called my friend to see if the number <START:number> 7654321 <END> was right.\n" +
                    "when she saw the number <START:number> 7654321 <END> she confirmed that i had the wrong number.\n" +
                    "she gave me the official Pizza Hut number which is <START:number> 7654321 <END> .\n" +
                    "when i called <START:number> 7654321 <END> the lady at the line said, Pizza Hut what can i help you with.\n" +
                    "guess what number i was dailing .\n" +
                    "<START:number> 7654321 <END>\n" +
                    "She asked me if i also needed the address.\n" +
                    "\n" +
                    "when i was walking to buy my movie ticker i stumble uppon an old class mate.\n" +
                    "we were so happy to see each other since he had left to go study abroad.\n" +
                    "but i had to ask for his number because the movie was about to start.\n" +
                    "whats your number ?\n" +
                    "<START:number> 1234567 <END>\n" +
                    "he stuttered and said once again. \n" +
                    "my number is <START:number> 7654321 <END> and what is your?\n" +
                    "mine is <START:number> 7654321 <END> but you can text me on this number <START:number> 1234567 <END> .\n" +
                    "okay i will call you on <START:number> 1234567 <END> and text you on <START:number> 1234567 <END> .");
            //more code
          //  out.println("more text");
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}
