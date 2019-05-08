package me.zhengjie.modules.userlog.service.impl;

import me.zhengjie.modules.userlog.domain.Userlog;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.userlog.repository.UserlogRepository;
import me.zhengjie.modules.userlog.service.UserlogService;
import me.zhengjie.modules.userlog.service.dto.UserlogDTO;
import me.zhengjie.modules.userlog.service.mapper.UserlogMapper;
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
public class UserlogServiceImpl implements UserlogService {

    @Autowired
    private UserlogRepository userlogRepository;

    @Autowired
    private UserlogMapper userlogMapper;

    @Override
    public UserlogDTO findById(Integer id) {
        Optional<Userlog> userlog = userlogRepository.findById(id);
        ValidationUtil.isNull(userlog,"Userlog","id",id);
        return userlogMapper.toDto(userlog.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserlogDTO create(Userlog resources) {
        return userlogMapper.toDto(userlogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Userlog resources) {
        Optional<Userlog> optionalUserlog = userlogRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalUserlog,"Userlog","id",resources.getId());

        Userlog userlog = optionalUserlog.get();
        // 此处需自己修改
        resources.setId(userlog.getId());
        userlogRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        userlogRepository.deleteById(id.intValue());
    }
}