package me.zhengjie.modules.meet.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.meet.domain.Meeting;
import me.zhengjie.modules.meet.service.dto.MeetingDTO;
import me.zhengjie.modules.meet.repository.MeetingRepository;
import me.zhengjie.modules.meet.service.mapper.MeetingMapper;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "meeting")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MeetingQueryService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingMapper meetingMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MeetingDTO meeting, Pageable pageable){
        Page<Meeting> page = meetingRepository.findAll(new Spec(meeting),pageable);
        return PageUtil.toPage(page.map(meetingMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MeetingDTO meeting){
        return meetingMapper.toDto(meetingRepository.findAll(new Spec(meeting)));
    }

    class Spec implements Specification<Meeting> {

        private MeetingDTO meeting;

        public Spec(MeetingDTO meeting){
            this.meeting = meeting;
        }

        @Override
        public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(meeting.getHostPer())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("host_per").as(String.class),"%"+meeting.getHostPer()+"%"));
                }
                if(!ObjectUtils.isEmpty(meeting.getAddress())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("address").as(String.class),"%"+meeting.getAddress()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}