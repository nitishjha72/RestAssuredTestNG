package spotify.oauth2.pojo;

public class Followers {
    private Object href;
    private int total;

    public Object getHref() {
        return href;
    }

    public void setHref(Object href) {
        this.href = href;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}