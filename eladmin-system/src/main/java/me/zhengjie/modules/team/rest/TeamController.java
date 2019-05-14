package me.zhengjie.modules.team.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.team.domain.Team;
import me.zhengjie.modules.team.service.TeamService;
import me.zhengjie.modules.team.service.dto.TeamDTO;
import me.zhengjie.modules.team.service.query.TeamQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-14
*/
@RestController
@RequestMapping("api")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamQueryService teamQueryService;

    private static final String ENTITY_NAME = "team";

    @Log("查询Team")
    @GetMapping(value = "/team")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTeams(TeamDTO resources, Pageable pageable){
        return new ResponseEntity(teamQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Team")
    @PostMapping(value = "/team")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Team resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(teamService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Team")
    @PutMapping(value = "/team")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Team resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        teamService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Team")
    @DeleteMapping(value = "/team/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String id){
        teamService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}