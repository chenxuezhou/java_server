package me.zhengjie.modules.campaign.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.campaign.domain.Campaign;
import me.zhengjie.modules.campaign.service.CampaignService;
import me.zhengjie.modules.campaign.service.dto.CampaignDTO;
import me.zhengjie.modules.campaign.service.query.CampaignQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-03-12
*/
@RestController
@RequestMapping("api")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignQueryService campaignQueryService;

    private static final String ENTITY_NAME = "campaign";

    @Log("��ѯCampaign")
    @GetMapping(value = "/campaign")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCampaigns(CampaignDTO resources, Pageable pageable){
        return new ResponseEntity(campaignQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("����Campaign")
    @PostMapping(value = "/campaign")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Campaign resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(campaignService.create(resources),HttpStatus.CREATED);
    }

    @Log("�޸�Campaign")
    @PutMapping(value = "/campaign")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Campaign resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        campaignService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("ɾ��Campaign")
    @DeleteMapping(value = "/campaign/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        campaignService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}