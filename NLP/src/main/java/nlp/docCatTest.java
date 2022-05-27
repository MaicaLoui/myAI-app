package nlp;

import java.io.*;
import java.util.ArrayList;
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


public class docCatTest {

    public static void main(String[] args) throws IOException {

         //String s = "monday is the day we all love";
       String s = "my birth place is Curacao";
       /* if ((s.contains("$") & s.matches(".*\\d.*"))) {

            System.out.println("sign");

        }
          *//*  System.out.println(s);
            newNumberNer newNum = new newNumberNer();
            newNum.numberNer(s);

            testTrainDoc.main(args);*//*
         else {
            System.out.println("text");
            System.out.println(s);
        }*/
        boolean yes = s.matches(".*\\d.*");
        boolean yes2 = s.matches(".*$.*");

        if (yes) {
            String numberOnly = s.replaceAll("[^0-9]", "");
            int total =numberOnly.length();
            //System.out.println(total);
           // int number = Integer.parseInt(numberOnly);
           /* int count = 0;
            for(int i = 0; i < numberOnly.length(); i++) {
                if(numberOnly.charAt(i) != ' ') {
                    count++;
                }
            }
*/
            if (total == 7){
              // String num= Integer.toString(number);
              s = numberOnly;
            }else {
                s= s;
            }
        }

        /*if (     number >= 7){
            System.out.println("number");
        } else {
            System.out.println(s);
        }
       // System.out.println(s.replaceAll("\\s+",""));
        System.out.println(yes2);
      //  System.out.println(numberOnly);*/


        List<TokenNameFinderModel> tokenNameFinderModels = new ArrayList<TokenNameFinderModel>();

        InputStream nertime = new FileInputStream("test.bin");
        TokenNameFinderModel tokenNameFinderModel3 = new TokenNameFinderModel(nertime);
        tokenNameFinderModels.add(tokenNameFinderModel3);

        InputStream nerCustom = new FileInputStream("en-ner-custom.bin");
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(nerCustom);
        tokenNameFinderModels.add(tokenNameFinderModel);

            InputStream nerDate = new FileInputStream("en-ner-date.bin");
            TokenNameFinderModel tokenNameFinderModel2 = new TokenNameFinderModel(nerDate);
            tokenNameFinderModels.add(tokenNameFinderModel2);


        InputStream nerPerson = new FileInputStream("en-ner-person.bin");
        TokenNameFinderModel tokenNameFinderModel4 = new TokenNameFinderModel(nerPerson);
        tokenNameFinderModels.add(tokenNameFinderModel4);

         /*   InputStream nerMoney = new FileInputStream("en-ner-money.bin");
            TokenNameFinderModel tokenNameFinderModel5 = new TokenNameFinderModel(nerMoney);
            tokenNameFinderModels.add(tokenNameFinderModel5);

            InputStream nerBrand = new FileInputStream("en-ner-carBrand.bin");
            TokenNameFinderModel tokenNameFinderModel6 = new TokenNameFinderModel(nerBrand);
            tokenNameFinderModels.add(tokenNameFinderModel6);
*/

        InputStream intent = new FileInputStream("en-doccat-custom.bin");
        DoccatModel intentModel = new DoccatModel(intent);
        DocumentCategorizer categorizer = new DocumentCategorizerME(intentModel);

        //  DocumentCategorizerME categorizer = new DocumentCategorizerME(doccatModel);
        NameFinderME[] nameFinderMEs = new NameFinderME[tokenNameFinderModels.size()];
        for (int i = 0; i < tokenNameFinderModels.size(); i++) {
            nameFinderMEs[i] = new NameFinderME(tokenNameFinderModels.get(i));
        }


        System.out.println("sentence : " + s);
        InputStream tokenModel = new FileInputStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(tokenModel);
        Tokenizer tokenizer = new TokenizerME(model);


        double[] outcome = categorizer.categorize(tokenizer.tokenize(s));

        System.out.print("{ intent: ");


        double max = outcome[0];
        for (double percentage : outcome) {
            if (percentage > max) {
                max = percentage;

            }
           /* System.out.println("max: "+max);
            System.out.println("percentage: "+ percentage);*/
        }

        if ((Math.round(max * 100)) >= 70 && (Math.round(max * 100)) <= 99 || Math.round(max * 100) == 100) {
            System.out.print(categorizer.getBestCategory(outcome));
            System.out.print("[" + Math.round(max * 100) + "]");
            System.out.print("  if1");

        } else if ((Math.round(max * 100)) < 70) {
            System.out.print(categorizer.getBestCategory(outcome));
            System.out.print("[" + Math.round(max * 100) + "]");
            System.out.print("    if2");
        }

        System.out.print(" enities: { ");

        int found = 0;
        String[] tokens = tokenizer.tokenize(s);
        for (NameFinderME nameFinderME : nameFinderMEs) {
            Span[] spans = nameFinderME.find(tokens);
            String[] names = Span.spansToStrings(spans, tokens);

            for (int i = 0; i < spans.length; i++) {

                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(spans[i].getType() + ": '" + names[i] + "' ");

            }/*if (s.matches(".*\\d.*")) {
                s = s.replaceAll("[^0-9]", "");
                if (spans.length == 1) {
                    break;
                } else if (spans.length == 0) {
                    found++;
                    if (found == 1) {
                        System.out.println(s);
                        newNumberNer newNum = new newNumberNer();
                        newNum.numberNer(s);
                        testTrainDoc.main(args);
                    }
                }
            }*/
        }
        System.out.println("} }");
        System.out.println(outcome[0]);

        nerCustom.close();
        ///nerDate.close();
        nerPerson.close();
        //nerMoney.close();
        intent.close();
        tokenModel.close();


    }

}
