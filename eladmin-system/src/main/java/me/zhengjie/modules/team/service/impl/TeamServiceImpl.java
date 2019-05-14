package me.zhengjie.modules.team.service.impl;

import me.zhengjie.modules.team.domain.Team;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.team.repository.TeamRepository;
import me.zhengjie.modules.team.service.TeamService;
import me.zhengjie.modules.team.service.dto.TeamDTO;
import me.zhengjie.modules.team.service.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-14
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public TeamDTO findById(String id) {
        Optional<Team> team = teamRepository.findById(id);
        ValidationUtil.isNull(team,"Team","id",id);
        return teamMapper.toDto(team.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TeamDTO create(Team resources) {
        return teamMapper.toDto(teamRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Team resources) {
        Optional<Team> optionalTeam = teamRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTeam,"Team","id",resources.getId());

        Team team = optionalTeam.get();
        // 此处需自己修改
        resources.setId(team.getId());
        teamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        teamRepository.deleteById(id);
    }
}