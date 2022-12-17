package java2.entity;

public class IssuesInfo {
    private int open;
    private int close;

    public IssuesInfo(int open, int close) {
        this.open = open;
        this.close = close;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "IssuesInfo{" +
                "open=" + open +
                ", close=" + close +
                '}';
    }
}
