package me.zhengjie.modules.v_order.service.impl;

import me.zhengjie.modules.v_order.domain.VOrder;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.v_order.repository.VOrderRepository;
import me.zhengjie.modules.v_order.service.VOrderService;
import me.zhengjie.modules.v_order.service.dto.VOrderDTO;
import me.zhengjie.modules.v_order.service.mapper.VOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-25
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class VOrderServiceImpl implements VOrderService {

    @Autowired
    private VOrderRepository vOrderRepository;

    @Autowired
    private VOrderMapper vOrderMapper;

    @Override
    public VOrderDTO findById(Integer id) {
        Optional<VOrder> vOrder = vOrderRepository.findById(id);
        ValidationUtil.isNull(vOrder,"VOrder","id",id);
        return vOrderMapper.toDto(vOrder.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VOrderDTO create(VOrder resources) {
        return vOrderMapper.toDto(vOrderRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VOrder resources) {
        Optional<VOrder> optionalVOrder = vOrderRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalVOrder,"VOrder","id",resources.getId());

        VOrder vOrder = optionalVOrder.get();
        // 此处需自己修改
        resources.setId(vOrder.getId());
        vOrderRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        vOrderRepository.deleteById(id.intValue());
    }
}