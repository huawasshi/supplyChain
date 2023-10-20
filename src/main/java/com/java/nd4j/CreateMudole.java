/*
package com.java.nd4j;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.records.reader.impl.transform.TransformProcessRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.util.ndarray.RecordConverter;
import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.DataSetIteratorSplitter;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.impl.LossMCXENT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CreateMudole {
    private static final Logger log = LoggerFactory.getLogger("CreateMudole.class");

    private static Schema generateSchema(){
        final Schema schema = new Schema.Builder()
                .addColumnString("id")
                .addColumnInteger("amount")
                .addColumnString("area")
                .addColumnString("company")
                .addColumnString("courier_number")
                .addColumnString("delivery_date")
                .addColumnString("listing_date")
                .addColumnString("manufactor")
                .addColumnString("manufacture_date")
                .addColumnString("name")
                .addColumnString("person")
                .addColumnString("state")
                .addColumnString("tel")
                .addColumnString("telephone")
                .addColumnString("traceability_id")
                .addColumnString("validity_date")
                .build();
        return schema;

    }

    private static RecordReader applyTransform(RecordReader recordReader, Schema schema){
        final TransformProcess transformProcess = new TransformProcess.Builder(schema)
                .removeColumns("id","company","manufactor","name","person","tel","telephone","traceability_id","amount",
                        "area","delivery_date","listing_date","manufacture_date")
                .build();
        final TransformProcessRecordReader transformProcessRecordReader = new TransformProcessRecordReader(recordReader,transformProcess);
        return transformProcessRecordReader;

    }

    private static RecordReader generateReader(File file) throws IOException, InterruptedException {
        final RecordReader recordReader = new CSVRecordReader(1,'\t');
        recordReader.initialize(new FileSplit(file));
        final RecordReader transformProcessRecordReader=applyTransform(recordReader,generateSchema());
        return transformProcessRecordReader;
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        final int labelIndex=16;
        final int batchSize=4;
        final int numClasses=3;
        final INDArray weightsArray = Nd4j.create(new double[]{0.57, 0.75});

        final RecordReader recordReader = generateReader(new ClassPathResource("111.csv").getFile());
        final DataSetIterator dataSetIterator = new RecordReaderDataSetIterator.Builder(recordReader,batchSize)
                .classification(labelIndex,numClasses)
                .build();

        final DataNormalization dataNormalization = new NormalizerStandardize();
        dataNormalization.fit(dataSetIterator);
        dataSetIterator.setPreProcessor(dataNormalization);
        final DataSetIteratorSplitter dataSetIteratorSplitter = new DataSetIteratorSplitter(dataSetIterator,1000,0.9);

        log.info("Building Model------------------->>>>>>>>>");

        final MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
                .weightInit(WeightInit.RELU_UNIFORM)
                .updater(new Adam(0.015D))
                .list()
                .layer(new DenseLayer.Builder().nIn(11).nOut(6).activation(Activation.RELU).dropOut(0.9).build())
                .layer(new DenseLayer.Builder().nIn(6).nOut(6).activation(Activation.RELU).dropOut(0.9).build())
                .layer(new DenseLayer.Builder().nIn(6).nOut(4).activation(Activation.RELU).dropOut(0.9).build())
                .layer(new OutputLayer.Builder(new LossMCXENT(weightsArray)).nIn(4).nOut(2).activation(Activation.SOFTMAX).build())
                .build();

        final UIServer uiServer = UIServer.getInstance();
        final StatsStorage statsStorage = new InMemoryStatsStorage();

        final MultiLayerNetwork multiLayerNetwork = new MultiLayerNetwork(configuration);
        multiLayerNetwork.init();
        multiLayerNetwork.setListeners(new ScoreIterationListener(100),
                new StatsListener(statsStorage));
        uiServer.attach(statsStorage);

        multiLayerNetwork.fit(dataSetIteratorSplitter.getTrainIterator(),100);

        final Evaluation evaluation =  multiLayerNetwork.evaluate(dataSetIteratorSplitter.getTestIterator(),Arrays.asList("0","1"));
        System.out.println(evaluation.stats());

        final File file = new File("model.zip");
        ModelSerializer.writeModel(multiLayerNetwork,file,true);
        ModelSerializer.addNormalizerToModel(file,dataNormalization);


    }
}
*/
