package com.github.emalock3.mahout.example.ur;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Main {
  public static void main(String... args) throws IOException, URISyntaxException, TasteException {
    File dataModelFile = new File(Main.class.getResource("/dataset.csv").toURI());
    DataModel model = new FileDataModel(dataModelFile);
    UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
    UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
    UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    recommender.recommend(2, 3).stream().forEach(System.out::println);
  }
}
