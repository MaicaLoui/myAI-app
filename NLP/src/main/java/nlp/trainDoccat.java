package nlp;

import opennlp.tools.doccat.*;
import opennlp.tools.ml.AbstractTrainer;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class trainDoccat {
    public static void main(String[] args) throws IOException {

        // read the training data
        InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("en-doccat-custom.train"));
        ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
        ObjectStream sampleStream = new DocumentSampleStream(lineStream);

        // define the training parameters
        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 10);
        params.put(TrainingParameters.CUTOFF_PARAM, 0);
        params.put(AbstractTrainer.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);

        // create a model from traning data
        DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
        System.out.println("\nModel is successfully trained.");

        // save the model to local
        BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("en-doccat-custom.bin"));
        model.serialize(modelOut);


        }
    }
