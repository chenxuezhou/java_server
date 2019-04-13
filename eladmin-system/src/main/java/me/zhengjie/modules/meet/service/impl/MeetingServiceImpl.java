package me.zhengjie.modules.meet.service.impl;

import me.zhengjie.modules.meet.domain.Meeting;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.meet.repository.MeetingRepository;
import me.zhengjie.modules.meet.service.MeetingService;
import me.zhengjie.modules.meet.service.dto.MeetingDTO;
import me.zhengjie.modules.meet.service.mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-13
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public MeetingDTO findById(Long id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);
        ValidationUtil.isNull(meeting,"Meeting","id",id);
        return meetingMapper.toDto(meeting.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MeetingDTO create(Meeting resources) {
        return meetingMapper.toDto(meetingRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Meeting resources) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMeeting,"Meeting","id",resources.getId());

        Meeting meeting = optionalMeeting.get();
        // 此处需自己修改
        resources.setId(meeting.getId());
        meetingRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        meetingRepository.deleteById(id);
    }
}