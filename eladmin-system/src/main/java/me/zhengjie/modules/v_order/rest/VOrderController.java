package me.zhengjie.modules.v_order.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.v_order.domain.VOrder;
import me.zhengjie.modules.v_order.service.VOrderService;
import me.zhengjie.modules.v_order.service.dto.VOrderDTO;
import me.zhengjie.modules.v_order.service.query.VOrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-29
*/
@RestController
@RequestMapping("api")
public class VOrderController {

    @Autowired
    private VOrderService vOrderService;

    @Autowired
    private VOrderQueryService vOrderQueryService;

    private static final String ENTITY_NAME = "vOrder";

    @Log("查询VOrder")
    @GetMapping(value = "/vOrder")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getVOrders(VOrderDTO resources, Pageable pageable){
        return new ResponseEntity(vOrderQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增VOrder")
    @PostMapping(value = "/vOrder")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody VOrder resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(vOrderService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改VOrder")
    @PutMapping(value = "/vOrder")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody VOrder resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        vOrderService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除VOrder")
    @DeleteMapping(value = "/vOrder/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        vOrderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}