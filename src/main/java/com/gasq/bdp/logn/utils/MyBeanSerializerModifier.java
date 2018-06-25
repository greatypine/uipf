package com.gasq.bdp.logn.utils;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class MyBeanSerializerModifier extends BeanSerializerModifier {

    private JsonSerializer<Object> _nullArrayJsonSerializer = new MyNullArrayJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
            List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            // 判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isArrayType(writer)) {
                 //给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullArrayJsonSerializer());
            }
        }
        return beanProperties;
    }

    // 判断是什么类型
    protected boolean isArrayType(BeanPropertyWriter writer) {
        JavaType clazz = writer.getType();
        return clazz.isArrayType() || clazz.equals(List.class) || clazz.equals(Set.class);

    }

    protected JsonSerializer<Object> defaultNullArrayJsonSerializer() {
        return _nullArrayJsonSerializer;
    }
}