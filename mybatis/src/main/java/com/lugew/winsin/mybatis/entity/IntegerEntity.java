package com.lugew.winsin.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/1/20 16:24
 */
@Getter
@Setter
public class IntegerEntity extends AbstractEntity<Integer> {
    @TableId(type = IdType.ASSIGN_ID)
    protected Integer id;
}
