package com.lugew.winsin.web;

import com.lugew.winsin.core.controller.Controller;
import com.lugew.winsin.core.entity.Entity;
import com.lugew.winsin.core.exception.Exception;
import com.lugew.winsin.core.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author 夏露桂
 * @since 2021/1/15 15:36
 */

@Standard
public abstract class AbstractController<T extends Entity<ID>, S extends Service<T, ID>, ID extends Serializable> implements Controller<T, ID> {
    @Autowired
    @SuppressWarnings("ALL")
    private S service;

    public S getService() {
        return service;
    }

    public void throwException(String key, Object... arguments) {
        throw new Exception(key, arguments);
    }
}
