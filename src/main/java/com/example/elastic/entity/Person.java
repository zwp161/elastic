package com.example.elastic.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: zhouwuping
 * @date:Create in 15:56 2018/12/27
 */
@Data
@Document(indexName = "accounts",type ="person")
public class Person {
    @Id
    private String id;
    @Field(analyzer = "ik",type= FieldType.Text)
    private String user;
    @Field(analyzer = "ik",type= FieldType.Text)
    private String title;
    @Field(analyzer = "ik",type= FieldType.Text)
    private String desc;
}
