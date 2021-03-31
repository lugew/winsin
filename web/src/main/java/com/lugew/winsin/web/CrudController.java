package com.lugew.winsin.web;

import com.lugew.winsin.core.entity.Entity;
import com.lugew.winsin.core.service.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * 增删改查控制器
 *
 * @author 夏露桂
 * @since 2021/1/15 15:36
 */
public abstract class CrudController<T extends Entity<ID>, S extends Service<T, ID>, ID extends Serializable>
        extends AbstractController<T, S, ID> implements com.lugew.winsin.core.controller.CrudController<T, ID> {

    @PostMapping({"/getList"})
    public List<T> getList(@RequestBody T entity) {
        return getService().getList(entity);
    }


    @Override
    @PostMapping("/getListPaging")
    public Object getListPaging(@RequestBody T entity, @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return getService().getPagingList(entity, pageIndex, pageSize);
    }

    @Override
    @GetMapping({"/get"})
    public T get(ID id) {
        return getService().get(id);
    }

    @Override
    @PostMapping({"/add"})
    public boolean add(@RequestBody T entity) {
        return getService().insert(entity);
    }

    @Override
    @PostMapping({"/update"})
    public boolean update(@RequestBody T entity) {
        return getService().update(entity);
    }

    @Override
    @GetMapping({"/delete"})
    public boolean delete(ID id) {
        return getService().delete(id);
    }

}
