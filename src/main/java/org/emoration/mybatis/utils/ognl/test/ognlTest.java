package org.emoration.mybatis.utils.ognl.test;

import ognl.*;
import org.emoration.mybatis.binding.MapperProxy;
import org.emoration.mybatis.utils.ognl.ExpressionEvaluator;
import org.emoration.mybatis.utils.ognl.test.ognlTestClass.ADemo;
import org.emoration.mybatis.utils.ognl.test.ognlTestClass.BDemo;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/8/10
 */
public class ognlTest {
    public static void main(String[] args) throws OgnlException {
        Object ans = ExpressionEvaluator.evaluateBoolean("a.age == 1 && b.getAge() == 1",
//        Object ans = ExpressionEvaluator.evaluateBoolean("a.getAge() == 1",
                new MapperProxy.ParameterPair[]{
                        new MapperProxy.ParameterPair("a", new ADemo("1", 1)),
                        new MapperProxy.ParameterPair("b", new BDemo("2", 1))
                });
        System.out.println(ans.getClass());
        System.out.println(ans);
    }
}
