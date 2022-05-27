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

public class testingpurposes {
    public static void main(String[] args) throws IOException {
  //  public static ArrayList<Intent> nlp(String sentence) throws IOException {


        List<TokenNameFinderModel> tokenNameFinderModels = new ArrayList<TokenNameFinderModel>();


        InputStream nerCustom = new FileInputStream("en-ner-custom.bin");
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(nerCustom);
        tokenNameFinderModels.add(tokenNameFinderModel);

        InputStream nerCarBrand = new FileInputStream("en-ner-carBrand.bin");
        TokenNameFinderModel tokenNameFinderModel2 = new TokenNameFinderModel(nerCarBrand);
        tokenNameFinderModels.add(tokenNameFinderModel2);

        InputStream nerMoney = new FileInputStream("en-ner-money.bin");
        TokenNameFinderModel tokenNameFinderModel3 = new TokenNameFinderModel(nerMoney);
        tokenNameFinderModels.add(tokenNameFinderModel3);

        InputStream nerPerson = new FileInputStream("en-ner-person.bin");
        TokenNameFinderModel tokenNameFinderModel4 = new TokenNameFinderModel(nerPerson);
        tokenNameFinderModels.add(tokenNameFinderModel4);


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

        String s = "what is the weather like today in Curacao";


        Intent intent = new Intent();
        double[] outcome = categorizer.categorize(tokenizer.tokenize(s));

        double max = outcome[0];
        for(double percentage : outcome) {
            if (percentage > max) {
                max = percentage;
            }
        }


        if ((Math.round(max * 100)) >= 70 && (Math.round (max * 100)) <= 99 || Math.round(max * 100) == 100) {
            intent.setName(categorizer.getBestCategory(outcome));
            intent.getName();
            intent.setEntities(new ArrayList<Entity>());
        }else if(((Math.round (max * 100)) >= 70 && (Math.round (max * 100)) <= 99) && intent.getEntities().isEmpty() ){
            intent.setName(categorizer.getBestCategory(outcome));
            intent.getName();
            intent.setEntities(new ArrayList<Entity>());
        }else if((Math.round (max * 100)) < 70){
            intent.setName("unknownIntent");
            intent.getName();
            intent.setEntities(new ArrayList<Entity>());
        }



        String[] tokens = tokenizer.tokenize(s);
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


        nerCustom.close();
        nerCarBrand.close();
        nerPerson.close();
        intentModel.close();
        tokenModel.close();


        ArrayList<String> check = new ArrayList<>();
        check.add(s);

        System.out.println(check);

    }
}
