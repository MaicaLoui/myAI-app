package dev;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OpenNLP {
    private static final Logger log = LoggerFactory.getLogger(OpenNLP.class);

    private final TokenNameFinderModel tokenNameFinderModel;
    private final TokenNameFinderModel tokenNameFinderModel2;
    private final TokenNameFinderModel tokenNameFinderModel3;
    private final TokenNameFinderModel tokenNameFinderModel4;
    private final TokenNameFinderModel tokenNameFinderModel5;
    private final TokenNameFinderModel tokenNameFinderModel6;
    List<TokenNameFinderModel> tokenNameFinderModels = new ArrayList<TokenNameFinderModel>();

    public OpenNLP() throws IOException {

        try {
            InputStream nerNumber = new FileInputStream("en-ner-number.bin");
            tokenNameFinderModel5 = new TokenNameFinderModel(nerNumber);
            tokenNameFinderModels.add(tokenNameFinderModel5);

            InputStream nerCustom = new FileInputStream("en-ner-custom.bin");
            tokenNameFinderModel = new TokenNameFinderModel(nerCustom);
            tokenNameFinderModels.add(tokenNameFinderModel);

            InputStream nerCarBrand = new FileInputStream("en-ner-carBrand.bin");
            tokenNameFinderModel2 = new TokenNameFinderModel(nerCarBrand);
            tokenNameFinderModels.add(tokenNameFinderModel2);

            InputStream nerMoney = new FileInputStream("en-ner-money.bin");
            tokenNameFinderModel3 = new TokenNameFinderModel(nerMoney);
            tokenNameFinderModels.add(tokenNameFinderModel3);

            InputStream nerPerson = new FileInputStream("en-ner-person.bin");
            tokenNameFinderModel4 = new TokenNameFinderModel(nerPerson);
            tokenNameFinderModels.add(tokenNameFinderModel4);

            InputStream nerDate = new FileInputStream("en-ner-date.bin");
            tokenNameFinderModel6 = new TokenNameFinderModel(nerDate);
            tokenNameFinderModels.add(tokenNameFinderModel6);


            nerNumber.close();
            nerCustom.close();
            nerCarBrand.close();
            nerPerson.close();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public Intent nlp(String sentence) throws IOException {
        Boolean isInteger = true;
        try {
            Integer.valueOf(sentence);

        } catch (Exception e) {
            isInteger = false;
        }




        InputStream intentModel = new FileInputStream("en-doccat-custom.bin");
        DoccatModel modeldoc = new DoccatModel(intentModel);
        DocumentCategorizer categorizer = new DocumentCategorizerME(modeldoc);

        NameFinderME[] nameFinderMEs = new NameFinderME[tokenNameFinderModels.size()];
        for (int i = 0; i < tokenNameFinderModels.size(); i++) {
            nameFinderMEs[i] = new NameFinderME(tokenNameFinderModels.get(i));
        }

        InputStream tokenModel = new FileInputStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(tokenModel);
        Tokenizer tokenizer = new TokenizerME(model);

        Intent intent = new Intent();

        double[] outcome = categorizer.categorize(tokenizer.tokenize(sentence));

        double max = outcome[0];
        for (double percentage : outcome) {
            if (percentage > max) {
                max = percentage;
            }
        }




        if ((Math.round(max * 100)) >= 70 && (Math.round(max * 100)) <= 99 || Math.round(max * 100) == 100) {
            intent.setName(categorizer.getBestCategory(outcome));
            intent.setEntities(new ArrayList<Entity>());
        } else if (((Math.round(max * 100)) >= 70 && (Math.round(max * 100)) <= 99) && intent.getEntities().isEmpty()) {
            intent.setName(categorizer.getBestCategory(outcome));
            intent.setEntities(new ArrayList<Entity>());
        } else if ((Math.round(max * 100)) < 70) {
            intent.setName("unknownIntent");
            intent.setEntities(new ArrayList<Entity>());
        }

        String[] tokens = tokenizer.tokenize(sentence);

        extractIntent(nameFinderMEs, intent, tokens);

        if (isInteger) {
            if (intent.getEntities() != null && intent.getEntities().size() > 0) {

            } else {
                newNumberNer newNum = new newNumberNer();
                newNum.numberNer(sentence);

                trainNumNer.main(new String[0]);

                extractIntent(nameFinderMEs, intent, tokens);

                InputStream nerNumber = new FileInputStream("en-ner-number.bin");
                TokenNameFinderModel tokenNameFinderModel2 = new TokenNameFinderModel(nerNumber);
                tokenNameFinderModels.add(tokenNameFinderModel2);
            }
        }

        intentModel.close();
        tokenModel.close();

        return intent;

    }

    private void extractIntent(NameFinderME[] nameFinderMEs, Intent intent, String[] tokens) {
        for (NameFinderME nameFinderME : nameFinderMEs) {

            Span[] spans = nameFinderME.find(tokens);
            String[] names = Span.spansToStrings(spans, tokens);
            for (int i = 0; i < spans.length; i++) {
                Entity temp = new Entity();
                temp.setKey(spans[i].getType());

                temp.setValue(names[i]);
                intent.getEntities().add(temp);

            }

        }
    }

}
