package jig.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import jig.bing.BingService;
import jig.bing.ImageDownloader;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestBuilder;
import jig.bing.search.ImageResponse;
import jig.config.AccountKey;
import jig.config.Config;
import org.apache.log4j.Logger;

/**
 * Main class to be used in jar.
 *
 * Takes in ImageRequest term and download directory as args. First run will ask user to properly
 * configure the JIG Config file with Account Key and default download directory if it isn't already
 * configured.
 *
 * Preceding runs will download images to the default directory or directory given as argument if
 * the Config's account key is valid.
 */
public class Jig {

  private static Logger logger = Logger.getLogger(Jig.class);
  private final String INVALID_ARGUMENTS_ERROR =
      "Usage: java -jar path/to/jar <search-term> <download-directory>";

  public static void main(String[] args) {
    Jig jig = new Jig();
    Config config = jig.getConfig();
    ImageRequestBuilder builder = new ImageRequestBuilder()
        .setSearchTerm("cat")
        .setNumberOfImages(5);
    ImageRequest request = builder.buildRequest();
    BingService bing = new BingService(config);
    ImageDownloader downloader = new ImageDownloader();
    try {
      ImageResponse response = bing.search(request);
      Collection<BufferedImage> images = downloader.downloadImages(response.getImageUrls());
      for (BufferedImage image : images) {
        jig.drawImage(image);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Config getConfig() {
    Properties configProperties = loadConfigProperties();
    AccountKey accountKey = new AccountKey(configProperties.getProperty("account.key"));
    return new Config(accountKey);
  }

  private Properties loadConfigProperties() {
    InputStream configStream =
        ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
    Properties properties = new Properties();
    try {
      properties.load(configStream);
    } catch (IOException e) {
      System.err.print("Couldn't load config.properties");
    }
    return properties;
  }

  private void drawImage(BufferedImage image) {

  }

  private boolean isValidSearchTerm(String searchTerm) {
    return true;
  }

  private boolean isvalidDownloadDirectory(String downloadDiretory) {
    return true;
  }
}
