package WebCrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static int counter = 0;

    private static int min = 65, max = 80;

    public static void main(String args[]) {

        final BlockingQueue<WebPage> pages = new LinkedBlockingQueue<>(10000);

        final WebPage root = initialize();
        pages.add(root);
        final int THREAD_COUNT = 1;

        final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        final Crawler crawler = new Crawler(pages);


        final List<Future<?>> futures = new ArrayList<>();
        final long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(executorService.submit(crawler));
        }


        try {
            for (Future<?> future : futures) {
                future.get();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        final long end = System.currentTimeMillis();

        executorService.shutdown();

        System.out.println("Web Crawler ended successfully in "+(end-start)/1000d);
    }

    public static String getRandomString() {
        return Character.toString(min + (int) (Math.random() * ((max - min) + 1)));
//        return Character.toString((char)ThreadLocalRandom.current().nextInt(65, 81))+Integer.toString(counter++);
    }

    public static WebPage initialize() {

        List<WebPage> childPages = getChildPages("educative.com", 0);
        return new WebPage("educative.com", "Educative is a good oranisation.", childPages);
    }

    public static List<WebPage> getChildPages(final String root, final int nesting) {
        if (nesting == 5) {
            return List.of();
        }

        final List<WebPage> childPages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final String url = getRandomString() + getRandomString();
            childPages.add(new WebPage(root + "/" + url, url, getChildPages(root + "/" + url, nesting + 1)));

        }
        return childPages;
    }
}
