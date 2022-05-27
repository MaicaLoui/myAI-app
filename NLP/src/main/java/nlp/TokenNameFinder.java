package nlp;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class TokenNameFinder {

    public static void main (String[] args) throws IOException {


        InputStream is = new FileInputStream("en-doccat-custom.bin");
        DoccatModel modeldoc = new DoccatModel(is);
        DocumentCategorizer categorizer= new DocumentCategorizerME(modeldoc);

        String s = "what is there to do today";
        System.out.println("sentence : " + s);
        InputStream modelIn = new FileInputStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(modelIn);
        Tokenizer tokenizer = new TokenizerME(model);



        double[] outcome = categorizer.categorize(tokenizer.tokenize(s));
        System.out.print(" intent: '" + categorizer.getBestCategory(outcome)+"'");

        try {
            System.out.println("Finding entities belonging to category : person name");
            new TokenNameFinder().findEntity();
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
        try {
            System.out.println("Finding entities belonging to category : date name");
            new nameFinder().findEntityDate();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Finding entities belonging to category : date name");
            new nameFinder().findEntityTime();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to find locations in the sentence
     * @throws IOException
     */
    public void findEntity() throws IOException {
        InputStream is = new FileInputStream("en-ner-person.bin");

        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);

        InputStream modelIn = null;


            modelIn = new FileInputStream("en-token.bin");
            TokenizerModel model1 = new TokenizerModel(modelIn);
            TokenizerME tokenizer = new TokenizerME(model1);

            String sentence = "Charlie is in Orlando but I don't about Mike, ill ask Jane and Monica.";

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



