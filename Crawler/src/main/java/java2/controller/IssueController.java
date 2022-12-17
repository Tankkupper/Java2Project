package java2.controller;


import java2.entity.IssuesInfo;
import java2.entity.IssuesSolveTime;
import java2.entity.UsersEntity;
import java2.mapper.IssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class IssueController {

    @Autowired
    IssueMapper issueMapper;

    @GetMapping("/repository/{id}/issues/info")
    public IssuesInfo getInfo(@PathVariable int id) {
        int open = issueMapper.selectOpenNum(id);
        int close = issueMapper.selectClosedNum(id);
        return new IssuesInfo(open, close);
    }


    @GetMapping("/repository/{id}/issues/time")
    public IssuesSolveTime getTime(@PathVariable int id) {
        int oneHour = issueMapper.solveTimeOneHour(id);
        int oneDay = issueMapper.solveTimeOneDay(id);
        int sevenDay = issueMapper.solveTimeSevenDay(id);
        int oneMonth = issueMapper.solveTimeOneMonth(id);
        int halfYear = issueMapper.solveTimeHalfYear(id);
        long avgSolveTime = issueMapper.solveTimeAvg(id);
        return new IssuesSolveTime(oneHour, oneDay, sevenDay, oneMonth, halfYear, avgSolveTime);
    }
}
