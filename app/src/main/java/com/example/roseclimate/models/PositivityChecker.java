package com.example.roseclimate.models;

import android.util.Log;

import com.example.roseclimate.BuildConfig;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.SentimentOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class PositivityChecker {
    private final Features features;
    private final NaturalLanguageUnderstanding naturalLanguageUnderstanding;
    private final String APIKey = BuildConfig.IBM_NLP_KEY;
    private final String APIUrl = BuildConfig.IBM_NLP_URL;
    public final Double posThreshold = 0.2;

    public PositivityChecker() {
        IamAuthenticator authenticator = new IamAuthenticator(this.APIKey);
        this.naturalLanguageUnderstanding =
            new NaturalLanguageUnderstanding("2021-08-01", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(APIUrl);

        Log.d("hello"  , "hi4");

        SentimentOptions sentiment = new SentimentOptions.Builder()
            .build();

        this.features = new Features.Builder()
            .sentiment(sentiment)
            .build();
    }
    public Boolean articleIsPositive(String url){
        Log.d("hello"  , "hi3");
        CompletableFuture<Boolean> completableFuture
            = CompletableFuture.supplyAsync(() -> _analyzeUrl(url));
        Boolean result = null;
        try {
            result = completableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean _analyzeUrl(String url) {
        Log.d("hello"  , "hi2");
        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .url(url)
            .features(features)
            .build();
        Log.d("hello"  , "hi1");
        AnalysisResults response = this.naturalLanguageUnderstanding
            .analyze(parameters)
            .execute()
            .getResult();
        Log.d("hello"  , String.valueOf(response));
        return (response.getSentiment().getDocument().getLabel().equals("positive") &&
            response.getSentiment().getDocument().getScore() > posThreshold);
    }
}
