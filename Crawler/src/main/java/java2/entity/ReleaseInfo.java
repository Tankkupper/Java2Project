package java2.entity;

public class ReleaseInfo {
    int releaseNum;
    double avgCommitNum;

    public ReleaseInfo(int releaseNum, double avgCommitNum) {
        this.releaseNum = releaseNum;
        this.avgCommitNum = avgCommitNum;
    }

    public int getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(int releaseNum) {
        this.releaseNum = releaseNum;
    }

    public double getAvgCommitNum() {
        return avgCommitNum;
    }

    public void setAvgCommitNum(double avgCommitNum) {
        this.avgCommitNum = avgCommitNum;
    }

    @Override
    public String toString() {
        return "ReleaseInfo{" +
                "releaseNum=" + releaseNum +
                ", avgCommitNum=" + avgCommitNum +
                '}';
    }
}
