package me.zhengjie.modules.notify.service.impl;

import me.zhengjie.modules.notify.domain.Notify;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.notify.repository.NotifyRepository;
import me.zhengjie.modules.notify.service.NotifyService;
import me.zhengjie.modules.notify.service.dto.NotifyDTO;
import me.zhengjie.modules.notify.service.mapper.NotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-03-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyRepository notifyRepository;

    @Autowired
    private NotifyMapper notifyMapper;

    @Override
    public NotifyDTO findById(Long id) {
        Optional<Notify> notify = notifyRepository.findById(id);
        ValidationUtil.isNull(notify,"Notify","id",id);
        return notifyMapper.toDto(notify.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NotifyDTO create(Notify resources) {
        return notifyMapper.toDto(notifyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Notify resources) {
        Optional<Notify> optionalNotify = notifyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalNotify,"Notify","id",resources.getId());

        Notify notify = optionalNotify.get();
        // 此处需自己修改
        resources.setId(notify.getId());
        notifyRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        notifyRepository.deleteById(id);
    }
}