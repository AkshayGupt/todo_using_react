package WebCrawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Crawler implements Runnable {

    private BlockingQueue<WebPage> webPages;

    Crawler(final BlockingQueue<WebPage> webPages) {
        this.webPages = webPages;
    }

    @Override
    public void run() {
        try {
            WebPage page = webPages.poll(1, TimeUnit.SECONDS);
            while (page != null) {
                System.out.println(Thread.currentThread().getName() + " content from url: " + page.getUrl() + "\n" + page.getBody());
                page.getChildPages().forEach(childPage -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " adding child page : " + childPage.getUrl());
                        webPages.put(childPage);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                page = webPages.poll(1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " failed");
        } finally {
            System.out.println(Thread.currentThread().getName() + " ended.");
        }

    }
}
