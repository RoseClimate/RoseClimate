package com.example.roseclimate.models;

import com.example.roseclimate.BuildConfig;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.SentimentOptions;

import java.util.ArrayList;
import java.util.List;

public class PositivityChecker {
    private final Features features;
    private final NaturalLanguageUnderstanding naturalLanguageUnderstanding;
    String APIKey = BuildConfig.IBM_NLP_KEY;

    public PositivityChecker() {
        IamAuthenticator authenticator = new IamAuthenticator(this.APIKey);
        this.naturalLanguageUnderstanding =
            new NaturalLanguageUnderstanding("2021-08-01", authenticator);
        naturalLanguageUnderstanding.setServiceUrl("{url}");


        List<String> targets = new ArrayList<>();
        targets.add("bonds");

        SentimentOptions sentiment = new SentimentOptions.Builder()
            .targets(targets)
            .build();

        this.features = new Features.Builder()
            .sentiment(sentiment)
            .build();
    }

    public String analyzeUrl(String url) {
        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .url(url)
            .features(features)
            .build();

        AnalysisResults response = this.naturalLanguageUnderstanding
            .analyze(parameters)
            .execute()
            .getResult();
        System.out.println(response);
        return response.getSentiment().getDocument().getScore().toString();
    }
}
