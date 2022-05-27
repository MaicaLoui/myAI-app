package nlp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;

import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;
import opennlp.tools.tokenize.Tokenizer;


public class docCat {

    public static void main(String[] args) throws IOException {

        List<TokenNameFinderModel> tokenNameFinderModels = new ArrayList<TokenNameFinderModel>();


        InputStream ner = new FileInputStream("en-ner-custom.bin");
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(ner);
        tokenNameFinderModels.add(tokenNameFinderModel);

        InputStream intent = new FileInputStream("en-doccat-custom.bin");
        DoccatModel modeldoc = new DoccatModel(intent);
        DocumentCategorizer categorizer = new DocumentCategorizerME(modeldoc);

        //  DocumentCategorizerME categorizer = new DocumentCategorizerME(doccatModel);
        NameFinderME[] nameFinderMEs = new NameFinderME[tokenNameFinderModels.size()];
        for (int i = 0; i < tokenNameFinderModels.size(); i++) {
            nameFinderMEs[i] = new NameFinderME(tokenNameFinderModels.get(i));
        }
        System.out.println(" ");
        String s = "what day is it today";

        System.out.println("sentence : " + s);
        InputStream tokenModel = new FileInputStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(tokenModel);
        Tokenizer tokenizer = new TokenizerME(model);


        double[] outcome = categorizer.categorize(tokenizer.tokenize(s));

        System.out.print("{ intent: '" + categorizer.getBestCategory(outcome) + "', enities: { ");


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


        ner.close();
        intent.close();
        tokenModel.close();


    }

}
