package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/7 11:47:57
 * @Description:
 */
public class Main1 {

    // 使用自定义注解
    @Test
    public void test() {}
}

// 定义一个注解, 看起来和接口很像, 只不过多了一个@符, 注解也会被编译成class文件
// 注解的定义也需要一些元注解(meta-annotation)
// Target: 定义注解可以在哪里应用, 如: 在方法上还是字段上
// Retention: 定义注解可以在哪里使用, 如: 源代码(SOURCE), class文件(CLASS)或者是运行时(RUNTIME)
// 一个不包含任何元素的注解称之为: 标记注解, 例如我们的Test
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test { }