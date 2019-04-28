package me.zhengjie.modules.testlist.service.impl;

import me.zhengjie.modules.testlist.domain.Testlist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.testlist.repository.TestlistRepository;
import me.zhengjie.modules.testlist.service.TestlistService;
import me.zhengjie.modules.testlist.service.dto.TestlistDTO;
import me.zhengjie.modules.testlist.service.mapper.TestlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-28
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TestlistServiceImpl implements TestlistService {

    @Autowired
    private TestlistRepository testlistRepository;

    @Autowired
    private TestlistMapper testlistMapper;

    @Override
    public TestlistDTO findById(Integer id) {
        Optional<Testlist> testlist = testlistRepository.findById(id);
        ValidationUtil.isNull(testlist,"Testlist","id",id);
        return testlistMapper.toDto(testlist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestlistDTO create(Testlist resources) {
        return testlistMapper.toDto(testlistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Testlist resources) {
        Optional<Testlist> optionalTestlist = testlistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTestlist,"Testlist","id",resources.getId());

        Testlist testlist = optionalTestlist.get();
        // 此处需自己修改
        resources.setId(testlist.getId());
        testlistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        testlistRepository.deleteById(id.intValue());
    }
}