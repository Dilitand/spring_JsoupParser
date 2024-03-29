package pageDownloader;

import org.jsoup.nodes.Document;
import pageObjects.PageObject;

public interface PageDownloader {

    PageObject downloadPageContent(String url);
    Document downloadDocument(String url,String blockMessage, boolean b);
}
