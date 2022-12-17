package java2.controller;

import java2.entity.CommitDateStatistics;
import java2.mapper.CommitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommitController {

    @Autowired
    CommitMapper commitMapper;

    @GetMapping("/repository/{id}/commits/statistics")
    public CommitDateStatistics getStatistics(@PathVariable int id) {
        int sunday = commitMapper.sundayNum(id);
        int monday = commitMapper.mondayNum(id);
        int tuesday = commitMapper.tuesdayNum(id);
        int wednesday = commitMapper.wednesdayNum(id);
        int thursday = commitMapper.thursdayNum(id);
        int friday = commitMapper.fridayNum(id);
        int saturday =commitMapper.saturdayNum(id);
        // from [0,12)
        int morning = commitMapper.morning(id);
        // from [12, 24)
        int afternoon = commitMapper.afternoon(id);
        return new CommitDateStatistics(sunday, monday, tuesday, wednesday, thursday, friday, saturday, morning, afternoon);
    }
}
