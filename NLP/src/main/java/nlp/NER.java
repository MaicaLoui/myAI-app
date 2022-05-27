package nlp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;

import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;
import opennlp.tools.tokenize.Tokenizer;

public class NER {

    public static void main(String[] args) throws IOException {

        List<TokenNameFinderModel> tokenNameFinderModels = new ArrayList<TokenNameFinderModel>();


        InputStream customNer = new FileInputStream("en-ner-custom.bin");
        TokenNameFinderModel customModel = new TokenNameFinderModel(customNer);
        tokenNameFinderModels.add(customModel);

        InputStream personNer = new FileInputStream("en-ner-person.bin");
        TokenNameFinderModel personModel = new TokenNameFinderModel(personNer);
        tokenNameFinderModels.add(personModel);

        InputStream dateNer = new FileInputStream("en-ner-date.bin");
        TokenNameFinderModel dateModel = new TokenNameFinderModel(dateNer);
        tokenNameFinderModels.add(dateModel);

        InputStream tokenModel = new FileInputStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(tokenModel);
        Tokenizer tokenizer = new TokenizerME(model);


        NameFinderME[] nameFinderMEs = new NameFinderME[tokenNameFinderModels.size()];
        for (int i = 0; i < tokenNameFinderModels.size(); i++) {
            nameFinderMEs[i] = new NameFinderME(tokenNameFinderModels.get(i));
        }

        System.out.println(" ");
        String s = "my birhdate is on 21 of January 1997";

        System.out.println("sentence : " + s);

        System.out.print("Enities: { ");


        String[] tokens = tokenizer.tokenize(s);
        for (NameFinderME nameFinderME : nameFinderMEs) {
            Span[] spans = nameFinderME.find(tokens);
            String[] names = Span.spansToStrings(spans, tokens);
            for (int i = 0; i < spans.length; i++) {
                if (i > 0) {
                    System.out.print(", ");

                }
                System.out.print(spans[i].getType() + ": '" + names[i] + "' ");
            }
        }
        System.out.println("} }");

        customNer.close();
        tokenModel.close();


    }


}
