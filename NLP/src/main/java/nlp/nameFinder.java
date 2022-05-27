package nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class nameFinder {
    public static void main (String[] args)  {

        try {
            System.out.println("Finding entities belonging to category : person name");
            new nameFinder().findName();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // find place
        try {
            System.out.println("Finding entities belonging to category : place name");
            new nameFinder().findLocation();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to find locations in the sentence
     * @throws IOException
     */
    public void findName() throws IOException {
        InputStream is = new FileInputStream("ner-custom-model.bin");

        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);

        // input string array
        String[] sentence = new String[]{
                "Aren't",
                "you",
                "a",
                "friend",
                "of",
                "Natalie"
        };

        Span nameSpans[] = nameFinder.find(sentence);

        // nameSpans contain all the possible entities detected
        for(Span s: nameSpans){
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for(int index=s.getStart();index<s.getEnd();index++){
                System.out.print(sentence[index]+" ");
            }
            System.out.println();
        }
    }
    /**
     * method to find locations in the sentence
     * @throws IOException
     */
    public void findLocation() throws IOException {
        InputStream is = new FileInputStream("en-ner-location.bin");

        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);

        // input string array
        String[] sentence = new String[]{
                "John",
                "Smith",
                "is",
                "from",
                "Netherlands",
                "."
        };

        Span nameSpans[] = nameFinder.find(sentence);

        // nameSpans contain all the possible entities detected
        for(Span s: nameSpans){
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for(int index=s.getStart();index<s.getEnd();index++){
                System.out.print(sentence[index]+" ");
            }
            System.out.println();
        }
    }

    public void findEntityDate() throws IOException {
        InputStream is = new FileInputStream("en-ner-date.bin");

        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);

        InputStream modelIn = null;


        modelIn = new FileInputStream("en-token.bin");
        TokenizerModel model1 = new TokenizerModel(modelIn);
        TokenizerME tokenizer = new TokenizerME(model1);

        String sentence = "what year is January 10, 2010";

        String[] tokens = tokenizer.tokenize(sentence);


        Span nameSpans[] = nameFinder.find(tokens);

        // nameSpans contain all the possible entities detected
        for (Span s : nameSpans) {
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for (int index = s.getStart(); index < s.getEnd(); index++) {
                System.out.print(tokens[index] + " ");
            }
            System.out.println();
        }



    }

    public void findEntityTime() throws IOException {
        InputStream is = new FileInputStream("en-ner-time.bin");

        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);

        InputStream modelIn = null;


        modelIn = new FileInputStream("en-token.bin");
        TokenizerModel model1 = new TokenizerModel(modelIn);
        TokenizerME tokenizer = new TokenizerME(model1);

        String sentence = "2 hour later";

        String[] tokens = tokenizer.tokenize(sentence);


        Span nameSpans[] = nameFinder.find(tokens);

        // nameSpans contain all the possible entities detected
        for (Span s : nameSpans) {
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for (int index = s.getStart(); index < s.getEnd(); index++) {
                System.out.print(tokens[index] + " ");
            }
            System.out.println();
        }



    }

}



