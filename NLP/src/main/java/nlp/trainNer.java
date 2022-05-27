package nlp;

import opennlp.tools.namefind.*;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;
import java.nio.charset.Charset;

public class trainNer {



        static String modelFile = "en-ner-number.bin";

        public static void main(String[] args) throws IOException {

            InputStreamFactory isf = new InputStreamFactory() {
                public InputStream createInputStream() throws IOException {
                    return new FileInputStream("en-ner-number.train");
                }
            };

            Charset charset = Charset.forName("UTF-8");
            ObjectStream<String> lineStream = new PlainTextByLineStream(isf, charset);
            ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

            TokenNameFinderModel model;
            TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();

            try {
                model = NameFinderME.train("en", null, sampleStream, TrainingParameters.defaultParams(),
                        nameFinderFactory);
            } finally {
                sampleStream.close();
            }

            BufferedOutputStream modelOut = null;

            try {
                modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
                model.serialize(modelOut);
            } finally {
                if (modelOut != null)
                    modelOut.close();
            }
        }
    }

