package com.github.sparkzxl.database.injection;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Objects;
import com.github.sparkzxl.database.annonation.InjectionField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * description: 字段表增强属性
 *
 * @author: zhouxinlei
 * @date: 2020-08-21 18:00:28
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class InjectionFieldExtPo extends InjectionFieldPo {

    /**
     * 动态查询值
     */
    private Set<Serializable> keys = new HashSet<>();


    public InjectionFieldExtPo(InjectionField rf) {
        super(rf);
    }

    public InjectionFieldExtPo(InjectionFieldPo po, Set<Serializable> keys) {
        this.api = po.getApi();
        this.feign = po.getFeign();
        this.key = po.getKey();
        this.method = po.getMethod();
        this.beanClass = po.getBeanClass();
        this.keys = keys;
    }

    public InjectionFieldExtPo(InjectionField rf, Set<Serializable> keys) {
        super(rf);
        this.keys = keys;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InjectionFieldExtPo that = (InjectionFieldExtPo) o;

        boolean isEquals = Objects.equal(method, that.method);

        if (StrUtil.isNotEmpty(api)) {
            isEquals = isEquals && Objects.equal(api, that.api);
        } else {
            isEquals = isEquals && Objects.equal(feign, that.feign);
        }

        boolean isEqualsKeys = keys.size() == that.keys.size() && keys.containsAll(that.keys);

        return isEquals && isEqualsKeys;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(api, feign, method, keys);
    }
}
