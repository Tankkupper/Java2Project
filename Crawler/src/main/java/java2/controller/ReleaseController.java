package java2.controller;


import java2.entity.ReleaseEntity;
import java2.entity.ReleaseInfo;
import java2.mapper.ReleaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReleaseController {

    @Autowired
    ReleaseMapper releaseMapper;


    @GetMapping("/repository/{id}/releases/info")
    public ReleaseInfo getInfo(@PathVariable int id) {
        int releaseNum = releaseMapper.releaseNum(id);
        Double avg = releaseMapper.avgCommitNum(id);
        if (avg == null) {
            avg = 0.0;
        }
        return new ReleaseInfo(releaseNum, avg);
    }

    @GetMapping("/repository/{id}/releases/limit/{limit}/page/{page}")
    public List<ReleaseEntity> getEntity(@PathVariable int id,
                                         @PathVariable int limit,
                                         @PathVariable int page){
        // the page begin with 1
        return releaseMapper.limitAndPage(id, limit, (page-1)*limit);
    }
}
