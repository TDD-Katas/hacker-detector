package com.sky.detector;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class HackerDetectorFixture {
    HackerDetector hackerDetector;
    
    public HackerDetectorFixture() {
        hackerDetector = new StrategicHackerDetector();
    }
    
    public String parseLine(String line) {
        return hackerDetector.parseLine(line);
    }
}
