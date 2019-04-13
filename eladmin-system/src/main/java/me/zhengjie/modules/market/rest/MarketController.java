package me.zhengjie.modules.market.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.market.domain.Market;
import me.zhengjie.modules.market.service.MarketService;
import me.zhengjie.modules.market.service.dto.MarketDTO;
import me.zhengjie.modules.market.service.query.MarketQueryService;
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
public class MarketController {

    @Autowired
    private MarketService marketService;

    @Autowired
    private MarketQueryService marketQueryService;

    private static final String ENTITY_NAME = "market";

    @Log("查询Market")
    @GetMapping(value = "/market")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getMarkets(MarketDTO resources, Pageable pageable){
        return new ResponseEntity(marketQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Market")
    @PostMapping(value = "/market")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Market resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(marketService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Market")
    @PutMapping(value = "/market")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Market resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        marketService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Market")
    @DeleteMapping(value = "/market/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        marketService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}