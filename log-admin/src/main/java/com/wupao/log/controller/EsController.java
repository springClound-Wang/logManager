package com.wupao.log.controller;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wzz
 * @packageName com.wupao.log.controller
 * @Date: 2019/7/12 10:03
 * @Description:
 */
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private TransportClient client;

    /**
     * 增加
     * @param name
     * @param age
     * @param date
     * @param country
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "country") String country
    ){
        try {
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name", name)
                    .field("age", age)
                    .field("country", country)
                    .endObject();
            // prepareIndex构建索引
            IndexResponse result = this.client.prepareIndex("people", "man")
                    .setSource(content)
                    .get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("/keyWord")
    public List<Map<String, Object>> searchCourseWithKeyWord(String keyWord) throws Exception{

        List<Map<String, Object>> hitsMap = new ArrayList<>();
        QueryBuilder queryBuilder= QueryBuilders.matchQuery("name", keyWord);


        //设置高亮显示
        HighlightBuilder hiBuilder=new HighlightBuilder();
        hiBuilder.preTags("<span style=\"color:red\">");
        hiBuilder.postTags("</span>");
        hiBuilder.field("name");
        SearchRequestBuilder search=client.prepareSearch("people");
        search.setQuery(queryBuilder);
        search.setSize(20);
        SearchResponse response =search.highlighter(hiBuilder).execute().actionGet();

        SearchHits hits = response.getHits();

        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField nameField = highlightFields.get("name");
            if(nameField!=null){
                Text[] fragments = nameField.fragments();
                String nameTmp ="";
                for(Text text:fragments){
                    nameTmp+=text;
                }
                //将高亮片段组装到结果中去
                map.put("name",nameTmp);
            }
            hitsMap.add(map);
        }
        return hitsMap;
    }
}
