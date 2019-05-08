package me.zhengjie.modules.users.service.impl;

import me.zhengjie.modules.users.domain.Users;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.users.repository.UsersRepository;
import me.zhengjie.modules.users.service.UsersService;
import me.zhengjie.modules.users.service.dto.UsersDTO;
import me.zhengjie.modules.users.service.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UsersDTO findById(Integer id) {
        Optional<Users> users = usersRepository.findById(id);
        ValidationUtil.isNull(users,"Users","id",id);
        return usersMapper.toDto(users.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UsersDTO create(Users resources) {
        return usersMapper.toDto(usersRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Users resources) {
        Optional<Users> optionalUsers = usersRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalUsers,"Users","id",resources.getId());

        Users users = optionalUsers.get();
        // 此处需自己修改
        resources.setId(users.getId());
        usersRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        usersRepository.deleteById(id.intValue());
    }
}