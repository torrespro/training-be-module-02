package com.backbase.training;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MimeTypeDetector implements AggregationStrategy {

    @Handler
    public String detect(GenericFile<File> body) throws Exception {
        return Files.probeContentType(Paths.get(body.getAbsoluteFilePath()));
    }

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        oldExchange.getIn().setHeader("mimeType", newExchange.getIn().getBody(String.class));
        return oldExchange;
    }
}
