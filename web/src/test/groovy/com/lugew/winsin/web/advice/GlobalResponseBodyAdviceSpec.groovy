package com.lugew.winsin.web.advice

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.lugew.winsin.web.response.R
import org.hibernate.validator.internal.engine.ConstraintViolationImpl
import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.core.MethodParameter
import org.springframework.http.ResponseEntity
import spock.lang.*

import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

/**
 *
 * 全局返回值拦截器
 * @author 夏露桂* @since 2021/1/20 10:19
 */
@Subject(GlobalResponseBodyAdvice)
@Narrative("对返回值统一处理")
class GlobalResponseBodyAdviceSpec extends Specification {
    @Shared
    GlobalResponseBodyAdvice globalResponseBodyAdvice

    def setupSpec() {
        globalResponseBodyAdvice = new GlobalResponseBodyAdvice(null)
    }

    def "返回的R的data部分是body"() {
        given: "方法返回值是void"
        MethodParameter methodParameter = Stub()
        methodParameter.getParameterType() >> Void.class
        Object body = new Object()

        expect: "返回的R的data部分是body"
        with((R) globalResponseBodyAdvice.beforeBodyWrite(body, methodParameter, null, null, null, null)) {
            getData() == body
        }
    }

    def "指定类型返回值不做处理"() {
        given: "指定类型"
        MethodParameter methodParameter = Stub()
        methodParameter.getParameterType() >> type
        expect: "过滤指定类型"
        supported == globalResponseBodyAdvice.supports(methodParameter, null)
        where: "类型"
        type                 || supported
        R.class              || false
        ResponseEntity.class || false
        Object.class         || true
    }

    @Unroll("属性：name.name，值：#value，信息：#information")
    def 'jpa检验类的错误需提示属性、值和错误信息'() {
        given: '初始化异常'
        ConstraintViolationImpl constraintViolationImpl = ConstraintViolationImpl.forBeanValidation(
                null,
                null,
                null,
                information as String,
                null,
                null,
                null,
                value,
                PathImpl.createPathFromString("name.name"),
                null,
                null
        ) as ConstraintViolationImpl
        Set<ConstraintViolation> constraintViolations = new HashSet<ConstraintViolation>()
        constraintViolations.with {
            add(constraintViolationImpl)
        }
        ConstraintViolationException constraintViolationException =
                new ConstraintViolationException(constraintViolations)

        JSONObject jsonObject = new JSONObject()
        jsonObject.with {
            put("属性", 'name.name')
            put("值", value)
            put("信息", information)
        }
        JSONArray jsonArray = new JSONArray()
        jsonArray.with {
            add(jsonObject)
        }


        when: '捕获异常'
        R<JSONArray> result = globalResponseBodyAdvice.exceptionHandle(constraintViolationException) as R<JSONArray>

        then: '抛出信息'
        with(result) {
            message == "校验失败"
            data == jsonArray
            code == "validate.failed"
        }
        where:
        value | information
        '123' | '123'
        null  | null
    }
}
