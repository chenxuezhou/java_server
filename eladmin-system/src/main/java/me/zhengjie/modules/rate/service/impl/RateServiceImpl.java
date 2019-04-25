package me.zhengjie.modules.rate.service.impl;

import me.zhengjie.modules.rate.domain.Rate;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.rate.repository.RateRepository;
import me.zhengjie.modules.rate.service.RateService;
import me.zhengjie.modules.rate.service.dto.RateDTO;
import me.zhengjie.modules.rate.service.mapper.RateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-24
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RateMapper rateMapper;

    @Override
    public RateDTO findById(Integer id) {
        Optional<Rate> rate = rateRepository.findById(id);
        ValidationUtil.isNull(rate,"Rate","id",id);
        return rateMapper.toDto(rate.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RateDTO create(Rate resources) {
        return rateMapper.toDto(rateRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Rate resources) {
        Optional<Rate> optionalRate = rateRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalRate,"Rate","id",resources.getId());

        Rate rate = optionalRate.get();
        // 此处需自己修改
        resources.setId(rate.getId());
        rateRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        rateRepository.deleteById(id.intValue());
    }
}