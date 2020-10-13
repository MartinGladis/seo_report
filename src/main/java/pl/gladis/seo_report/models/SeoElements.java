package pl.gladis.seo_report.models;

public final class SeoElements {
    private String url;
    private String title;
    private String headers;
    private String canonicalTag;
    private String altInImages;
    private String metaDescription;
    private String metaKeyowrds;
    private String robots;
    private String sitemaps;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getCanonicalTag() {
        return canonicalTag;
    }

    public void setCanonicalTag(String canonicalTag) {
        this.canonicalTag = canonicalTag;
    }

    public String getAltInImages() {
        return altInImages;
    }

    public void setAltInImages(String altInImages) {
        this.altInImages = altInImages;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeyowrds() {
        return metaKeyowrds;
    }

    public void setMetaKeyowrds(String metaKeyowrds) {
        this.metaKeyowrds = metaKeyowrds;
    }

    public String getRobots() {
        return robots;
    }

    public void setRobots(String robots) {
        this.robots = robots;
    }

    public String getSitemaps() {
        return sitemaps;
    }

    public void setSitemaps(String sitemaps) {
        this.sitemaps = sitemaps;
    }
}
