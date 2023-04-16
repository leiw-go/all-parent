package cn.leiwspider;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.*;
import java.io.IOException;
import org.slf4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello, maven");

    }
}

class AppleDevicePriceScraper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppleDevicePriceScraper.class);
    private static final String APPLE_WEBSITE_URL = "https://www.apple.com";

    public static void main(String[] args) {
        try {
            // Fetch the Apple website
            Document appleWebsite = Jsoup.connect(APPLE_WEBSITE_URL).get();
            System.out.println(appleWebsite);

            // Find the links to all the device categories
            Elements deviceCategories = appleWebsite.select("a[href^='/shop/category']");
            System.out.println(deviceCategories);

            // For each device category, fetch the prices of all devices
            for (int i = 0; i < deviceCategories.size(); i++) {
                String deviceCategoryUrl = APPLE_WEBSITE_URL + deviceCategories.get(i).attr("href");
                Document deviceCategoryPage = Jsoup.connect(deviceCategoryUrl).get();
                Elements devices = deviceCategoryPage.select("a[href^='/shop/product']");
                for (int j = 0; j < devices.size(); j++) {
                    String deviceUrl = APPLE_WEBSITE_URL + devices.get(j).attr("href");
                    Document devicePage = Jsoup.connect(deviceUrl).get();
                    String deviceName = devicePage.select("h1[data-bypass]").first().text();
                    String devicePrice = devicePage.select("span[class^='current_price']").first().text();
                    LOGGER.info("Device: {}, Price: {}", deviceName, devicePrice);
                }
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
