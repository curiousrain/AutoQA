package utils.urls;

public enum Link {
    ANDERSENCOURSE("https://qa-course-01.andersenlab.com/login");
    private String link;
    public String getLink() {return link;}
    Link(String link) {
        this.link = link;
    }
}
