package com.example.elastic.repository;

import com.example.elastic.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author: zhouwuping
 * @date:Create in 15:54 2018/12/27
 */
@Component
public interface PersonRepository extends ElasticsearchRepository<Person,String> {

}
