package java2.controller;


import java2.entity.RepositoryEntity;
import java2.entity.UsersEntity;
import java2.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    UsersMapper usersMapper;

    @GetMapping("/repository/{id}/users")
    public List<UsersEntity> getUsers(@PathVariable int id) {
        return usersMapper.selectAll(id);
    }

    @GetMapping("/repository/{id}/users/limit/{limit}")
    public List<UsersEntity> getUsersWithPage(@PathVariable int id,
                                              @PathVariable int limit) {
        return usersMapper.selectWithLimit(id, limit);
    }
}
