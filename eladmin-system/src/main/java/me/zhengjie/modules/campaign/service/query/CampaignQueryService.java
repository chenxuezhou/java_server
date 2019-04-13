package me.zhengjie.modules.campaign.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.campaign.domain.Campaign;
import me.zhengjie.modules.campaign.service.dto.CampaignDTO;
import me.zhengjie.modules.campaign.repository.CampaignRepository;
import me.zhengjie.modules.campaign.service.mapper.CampaignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "campaign")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CampaignQueryService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    /**
     * ��ҳ
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CampaignDTO campaign, Pageable pageable){
        Page<Campaign> page = campaignRepository.findAll(new Spec(campaign),pageable);
        return PageUtil.toPage(page.map(campaignMapper::toDto));
    }

    /**
    * ����ҳ
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CampaignDTO campaign){
        return campaignMapper.toDto(campaignRepository.findAll(new Spec(campaign)));
    }

    class Spec implements Specification<Campaign> {

        private CampaignDTO campaign;

        public Spec(CampaignDTO campaign){
            this.campaign = campaign;
        }

        @Override
        public Predicate toPredicate(Root<Campaign> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(campaign.getCreateTime())){
                    /**
                    * ��ȷ
                    */
                    list.add(cb.equal(root.get("create_time").as(Timestamp.class),campaign.getCreateTime()));
                }
                if(!ObjectUtils.isEmpty(campaign.getTitle())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+campaign.getTitle()+"%"));
                }
                if(!ObjectUtils.isEmpty(campaign.getNumber())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("number").as(String.class),"%"+campaign.getNumber()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}