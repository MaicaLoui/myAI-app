package nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TokenFinder {
    public static void main (String[] args) throws IOException {
        try {
            System.out.println("Finding Entities :");
            new TokenFinder().findEntity();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

            public void findEntity() throws IOException {

                    InputStream tokenStream = new FileInputStream(new File("en-token.bin"));
                    TokenizerModel tm = new TokenizerModel(tokenStream);
                    TokenizerME tokenizer = new TokenizerME(tm);


                    InputStream is = new FileInputStream("en-ner-custom.bin");
                    TokenNameFinderModel tnfm = new TokenNameFinderModel(is);

                    is.close();

                    NameFinderME nf = new NameFinderME(tnfm);

                    String sentence = "I want information about opening a personal account at this bank in Curacao";

                    String[] tokens = tokenizer.tokenize(sentence);


                    Span nameSpans[] = nf.find(tokens);

                    // nameSpans contain all the possible entities detected
                    for (Span s : nameSpans) {
                        System.out.print(s.toString());
                        System.out.print("  :  ");
                        
                        for (int index = s.getStart(); index < s.getEnd(); index++) {
                            System.out.print(tokens[index] + " ");
                        }
                        System.out.println();
                    }




        }




    }

