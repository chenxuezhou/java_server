package me.zhengjie.modules.market.service.impl;

import me.zhengjie.modules.market.domain.Market;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.market.repository.MarketRepository;
import me.zhengjie.modules.market.service.MarketService;
import me.zhengjie.modules.market.service.dto.MarketDTO;
import me.zhengjie.modules.market.service.mapper.MarketMapper;
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
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MarketMapper marketMapper;

    @Override
    public MarketDTO findById(Long id) {
        Optional<Market> market = marketRepository.findById(id);
        ValidationUtil.isNull(market,"Market","id",id);
        return marketMapper.toDto(market.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MarketDTO create(Market resources) {
        return marketMapper.toDto(marketRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Market resources) {
        Optional<Market> optionalMarket = marketRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMarket,"Market","id",resources.getId());

        Market market = optionalMarket.get();
        // 此处需自己修改
        resources.setId(market.getId());
        marketRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        marketRepository.deleteById(id);
    }
}