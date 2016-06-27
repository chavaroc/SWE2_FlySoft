package hm.edu.swe2.flysoft.crawler;

import java.io.File;
import java.net.URISyntaxException;
import java.util.EnumSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the functionality of the file crawler
 * @author Philipp Chavaroche
 * @version 27.06.2016
 */
public class FileCrawlerTest {
    
    /*@Test
    public void TestCrawlOnTimeTable() throws URISyntaxException{
        FileCrawler crawler = new FileCrawler(
        EnumSet.of(CrawlTableType.OnTime));
        assertTrue("There should be no file names before called 'crawl'", crawler.getCrawledFileNames().size() == 0);
        crawler.crawl();
        assertTrue("There should be one file name after called 'crawl'", crawler.getCrawledFileNames().size() == 1);
        for(String fileName : crawler.getCrawledFileNames()){
            new File(fileName).delete();
        }
    }*/
    
    @Test
    public void TestCrawlMarketTable() throws URISyntaxException{
        FileCrawler crawler = new FileCrawler(
        EnumSet.of(CrawlTableType.T100MarketDomestic));
        assertTrue("There should be no file names before called 'crawl'", crawler.getCrawledFileNames().size() == 0);
        crawler.crawl();
        assertTrue("There should be one file name after called 'crawl'", crawler.getCrawledFileNames().size() == 1);
        for(String fileName : crawler.getCrawledFileNames()){
            new File(fileName).delete();
        }
    }
    
    /*@Test
    public void TestCrawlMarketAndOnTimeTable() throws URISyntaxException{
        FileCrawler crawler = new FileCrawler(
        EnumSet.of(CrawlTableType.T100MarketDomestic, CrawlTableType.OnTime));
        assertTrue("There should be no file names before called 'crawl'", crawler.getCrawledFileNames().size() == 0);
        crawler.crawl();
        assertTrue("There should be one file name after called 'crawl'", crawler.getCrawledFileNames().size() == 2);
        for(String fileName : crawler.getCrawledFileNames()){
            new File(fileName).delete();
        }
    }*/
    
}
