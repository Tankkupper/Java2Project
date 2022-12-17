package java2.entity;

import java.sql.Timestamp;

public class IssuesSolveTime {
    private int oneHour;
    private int oneDay;
    private int sevenDay;
    private int oneMonth;
    private int halfYear;

    private long avgSolveTime;

    public IssuesSolveTime(int oneHour, int oneDay, int sevenDay, int oneMonth, int halfYear, long avgSolveTime) {
        this.oneHour = oneHour;
        this.oneDay = oneDay;
        this.sevenDay = sevenDay;
        this.oneMonth = oneMonth;
        this.halfYear = halfYear;
        this.avgSolveTime = avgSolveTime;
    }

    public int getOneHour() {
        return oneHour;
    }

    public void setOneHour(int oneHour) {
        this.oneHour = oneHour;
    }

    public int getOneDay() {
        return oneDay;
    }

    public void setOneDay(int oneDay) {
        this.oneDay = oneDay;
    }

    public int getSevenDay() {
        return sevenDay;
    }

    public void setSevenDay(int sevenDay) {
        this.sevenDay = sevenDay;
    }

    public int getOneMonth() {
        return oneMonth;
    }

    public void setOneMonth(int oneMonth) {
        this.oneMonth = oneMonth;
    }

    public int getHalfYear() {
        return halfYear;
    }

    public void setHalfYear(int halfYear) {
        this.halfYear = halfYear;
    }

    public long getAvgSolveTime() {
        return avgSolveTime;
    }

    public void setAvgSolveTime(long avgSolveTime) {
        this.avgSolveTime = avgSolveTime;
    }


    @Override
    public String toString() {
        return "IssuesSolveTime{" +
                "oneHour=" + oneHour +
                ", oneDay=" + oneDay +
                ", sevenDay=" + sevenDay +
                ", oneMonth=" + oneMonth +
                ", halfYear=" + halfYear +
                ", avgSolveTime=" + avgSolveTime +
                '}';
    }
}
