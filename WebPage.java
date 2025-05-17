package WebCrawler;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class WebPage {
    private String url;
    private String body;
    private List<WebPage> childPages;

    WebPage(){
        this.body = "";
        this.childPages = new ArrayList<>();
    }

    WebPage(final String url, final String body, final List<WebPage> webPages) {
        this.url = url;
        this.body = body;
        this.childPages = webPages;
    }
}
