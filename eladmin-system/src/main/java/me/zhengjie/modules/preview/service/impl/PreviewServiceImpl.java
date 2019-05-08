package me.zhengjie.modules.preview.service.impl;

import me.zhengjie.modules.preview.domain.Preview;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.preview.repository.PreviewRepository;
import me.zhengjie.modules.preview.service.PreviewService;
import me.zhengjie.modules.preview.service.dto.PreviewDTO;
import me.zhengjie.modules.preview.service.mapper.PreviewMapper;
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
public class PreviewServiceImpl implements PreviewService {

    @Autowired
    private PreviewRepository previewRepository;

    @Autowired
    private PreviewMapper previewMapper;

    @Override
    public PreviewDTO findById(Integer id) {
        Optional<Preview> preview = previewRepository.findById(id);
        ValidationUtil.isNull(preview,"Preview","id",id);
        return previewMapper.toDto(preview.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PreviewDTO create(Preview resources) {
        return previewMapper.toDto(previewRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Preview resources) {
        Optional<Preview> optionalPreview = previewRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPreview,"Preview","id",resources.getId());

        Preview preview = optionalPreview.get();
        // 此处需自己修改
        resources.setId(preview.getId());
        previewRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        previewRepository.deleteById(id.intValue());
    }
}