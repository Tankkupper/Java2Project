package java2.controller;

import java2.entity.RepositoryEntity;
import java2.mapper.RepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepositoryController {

    @Autowired
    RepositoryMapper repositoryMapper;


    @GetMapping("/repository")
    public List<RepositoryEntity> findAllRepository(){
        return repositoryMapper.selectAll();
    }

    @GetMapping("/repository/{id}")
    public RepositoryEntity getRepository(@PathVariable int id) {
        return repositoryMapper.selectById(id);
    }
}
