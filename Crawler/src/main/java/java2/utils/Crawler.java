package java2.utils;

import com.alibaba.fastjson2.JSONArray;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Crawler {
    private final static String scheme = "https";
    private final static String host = "api.github.com";
    private final static String token = "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";
    private final URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host);
    private final Logger log = Logger.getLogger(Crawler.class);

    private final CloseableHttpClient client = HttpClients.createDefault();

    private final int MaxRequestTime = 10000;

    private String owner;

    private String repository;
    public Crawler(String owner, String repository) throws URISyntaxException, IOException, ParseException {
        this.owner = owner;
        this.repository = repository;
        URI uri = uriBuilder
                .setPath("/user")
                .build();
        HttpGet get = new HttpGet(uri);

        // important the token field is not access_token
        this.setDefaultHeader(get);
        CloseableHttpResponse response = client.execute(get);

        // code : 401
        //{"message":"Requires authentication","documentation_url":"https://docs.github.com/rest/reference/users#get-the-authenticated-user"}
        // code : 200 success verify identity

        String text = EntityUtils.toString(response.getEntity());
        if (response.getCode() == 200) {
            log.info("Crawler initialization is complete");
            log.debug(text);
            response.close();
        } else {
            log.error("Crawler initialization failed");
            log.debug(text);
            response.close();
            throw new RuntimeException();
        }
    }


    public JSONArray getIssues() throws URISyntaxException, IOException, ParseException, InterruptedException {
        this.setUriBuilderPath("issues");
        this.setParameter("state", "all");
        return getJsonArray();
    }
    public JSONArray getContributors() throws URISyntaxException, IOException, ParseException, InterruptedException {
        this.setUriBuilderPath("contributors");
        return getJsonArray();
    }

    public JSONArray getCommits() throws URISyntaxException, IOException, ParseException, InterruptedException {
        this.setUriBuilderPath("commits");
        return getJsonArray();
    }

    public JSONArray getReleases() throws URISyntaxException, IOException, ParseException, InterruptedException {
        this.setUriBuilderPath("releases");
        return getJsonArray();
    }

    private JSONArray getJsonArray() throws URISyntaxException, IOException, ParseException, InterruptedException {
        this.setParameter("per_page", "100");
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i <= MaxRequestTime; i++) {
            this.setParameter("page", i + "");
            HttpGet get = this.getHttpGet();
            CloseableHttpResponse response =  client.execute(get);
            String text = this.getContent(response);
            if (text == null || text.equals("[]")) {
                break;
            }
            jsonArray.addAll(JSONArray.parse(text));
            response.close();
            Thread.sleep(500);
        }
        log.debug(jsonArray.toJSONString());
        return jsonArray;
    }
    private void setDefaultHeader(HttpGet get) {
        get.setHeader("Authorization", "Bearer " + token);
        get.setHeader("Accept", "application/vnd.github+json");
    }
    private void clearURIBuilder() {
       uriBuilder.clearParameters().setPath("");
    }

    private void setUriBuilderPath(String path) throws URISyntaxException {
        clearURIBuilder();
        uriBuilder.setPath("/repos/" + owner + "/" + repository + "/" + path);
    }

    private void setParameter(String name, String value) {
        uriBuilder.setParameter(name, value);
    }

    private HttpGet getHttpGet() throws URISyntaxException {
        HttpGet get = new HttpGet(uriBuilder.build());
        this.setDefaultHeader(get);
        return get;
    }

    private String getContent(CloseableHttpResponse response) throws IOException, ParseException {
        return EntityUtils.toString(response.getEntity());
    }

}
