package com.example.elastic;

import com.example.elastic.entity.Person;
import com.example.elastic.repository.PersonRepository;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticApplicationTests {

    @Autowired
    private TransportClient client;

    /*@Test
    public void testElasticGet() {
        GetRequestBuilder getRequestBuilder = client.prepareGet("accounts", "person", "1");
        GetResponse documentFields = getRequestBuilder.get();
        Map<String, Object> source = documentFields.getSource();
        System.out.println(documentFields);
    }

    @Test
    public void testElasticGet2() {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("desc","数据");
        SearchResponse searchResponse = searchRequestBuilder.setQuery(queryBuilder).get();
        System.out.println(searchResponse);
    }

    //索引 判断索引是否存在
    @Test
    public void isIndexExist() {
        boolean exists = client.admin().indices().prepareExists("accounts").execute().actionGet().isExists();
        System.out.println(exists);
    }*/

    @Test
    public void searchTest(){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must().add(QueryBuilders.matchQuery("user","李四"));
        boolQueryBuilder.must().add(QueryBuilders.matchQuery("desc","java"));
        boolQueryBuilder.filter().add(QueryBuilders.termQuery("id",2));
        boolQueryBuilder.filter().add(QueryBuilders.rangeQuery("id").gt(0));
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).get();
        System.out.println(searchResponse);
    }

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testGetPerson(){
        Iterable<Person> search = personRepository.search(QueryBuilders.matchQuery("title", "工程师"));
        Iterator<Person> iterator = search.iterator();
        while (iterator.hasNext()){
            Person next = iterator.next();
            System.out.println(next);
        }
    }

    /**
     * SpringBoot的类似jpa操作
     */
    @Test
    public void testPutPerson(){
        Person person = new Person();
        person.setId("3");
        person.setUser("王麻子");
        person.setTitle("工程师");
        person.setDesc("java开发");
        Person save = personRepository.save(person);

    }
}
