package utils.urls;

public enum Link {
    ANDERSENCOURSE_LOGIN("https://qa-course-01.andersenlab.com/login"),
    ANDERSENCOURSE_REGISTRATION("https://qa-course-01.andersenlab.com/registration");
    private String link;
    public String getLink() {return link;}
    Link(String link) {
        this.link = link;
    }
}
