package com.lugew.winsin.core.controller;

import com.lugew.winsin.core.entity.Entity;

import java.util.List;

/**
 * 控制层接口
 *
 * @author 夏露桂
 * @since 2021/1/14 10:07
 */
public interface CrudController<T extends Entity<ID>, ID> extends Controller<T, ID> {

    List<T> getList(T entity);

    Object getListPaging(T entity, Integer pageIndex, Integer pageSize);

    T get(ID id);

    boolean add(T entity);

    boolean update(T entity);

    boolean delete(ID id);
}
