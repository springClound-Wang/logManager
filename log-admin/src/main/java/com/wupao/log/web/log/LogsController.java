package com.wupao.log.web.log;

import com.wupao.log.utils.Constants;
import com.wupao.log.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/logs")
@Slf4j
public class LogsController {
    @Autowired
    private TransportClient client;

    @GetMapping("/logsList")
    public String logsList(String index, HttpServletRequest request) {
        request.setAttribute(Constants.INDEX, index);
        return "/logs/logsList";
    }
    @RequestMapping("/count")
    public  List count(@RequestParam(value = "list[]") String[] list, String index)throws Exception{
        List hitsMap = new ArrayList<>();
        for (int i=0;i<list.length-1;i++) {
            QueryBuilder queryBuilder= QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("message", "error_info")).must(QueryBuilders.rangeQuery("@timestamp")
                            .gte(list[i])
                            .lt(list[i+1]));
            SearchRequestBuilder search=client.prepareSearch(index);
            search.setQuery(queryBuilder);
            SearchResponse response =search.execute().actionGet();
            SearchHits hits = response.getHits();
            hitsMap.add(hits.getTotalHits());
        }
        return hitsMap;
    }
    @ResponseBody
    @RequestMapping("/searchCourseWithKeyWord")
    public List<Map<String, Object>> searchCourseWithKeyWord(String keyWord, String startTime, String endTime, @RequestParam("index") String index,
        @RequestParam(value = "countSize",defaultValue = "40",required = false) Integer countSize) throws Exception{
        List<Map<String, Object>> hitsMap = new ArrayList<>();
        QueryBuilder queryBuilder;

        if(StringUtils.isEmpty(keyWord)&& StringUtils.isEmpty(startTime)&&StringUtils.isEmpty(endTime)){
            queryBuilder= QueryBuilders.matchAllQuery();
        }else if(StringUtils.isNotEmpty(keyWord)&&StringUtils.isEmpty(startTime)&&StringUtils.isEmpty(endTime)){
            queryBuilder= QueryBuilders.matchQuery("message", keyWord);

        }else if(StringUtils.isNotEmpty(keyWord)&&StringUtils.isNotEmpty(startTime)&&StringUtils.isEmpty(endTime)) {
            Object start=DateUtil.convertToUTC(DateUtil.fomatPaseTime(startTime));
            queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("message", keyWord)).must(QueryBuilders.rangeQuery("@timestamp")
                            .gte(start));
        }else if(StringUtils.isEmpty(keyWord)&&StringUtils.isNotEmpty(startTime)&&StringUtils.isEmpty(endTime)) {
            Object start=DateUtil.convertToUTC(DateUtil.fomatPaseTime(startTime));
            queryBuilder = QueryBuilders.boolQuery().
                    must(QueryBuilders.rangeQuery("@timestamp")
                            .gte(start));
        }
        else if(StringUtils.isNotEmpty(keyWord)&&StringUtils.isEmpty(startTime)&&StringUtils.isNotEmpty(endTime)) {
            Object end=DateUtil.convertToUTC(DateUtil.fomatPaseTime(endTime));
            queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("message", keyWord)).must(QueryBuilders.rangeQuery("@timestamp")
                            .lte(end));
        }  else if(StringUtils.isEmpty(keyWord)&&StringUtils.isEmpty(startTime)&&StringUtils.isNotEmpty(endTime)) {
            Object end=DateUtil.convertToUTC(DateUtil.fomatPaseTime(endTime));
            queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.rangeQuery("@timestamp")
                            .lte(end));
        }else{
            Object start=DateUtil.convertToUTC(DateUtil.fomatPaseTime(startTime));
            Object end=DateUtil.convertToUTC(DateUtil.fomatPaseTime(endTime));
            queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("message", keyWord)).must(QueryBuilders.rangeQuery("@timestamp")
                            .gte(start)
                            .lte(end));
        }


        //设置高亮显示
        HighlightBuilder hiBuilder=new HighlightBuilder();
        hiBuilder.preTags("<span style=\"color:red\">");
        hiBuilder.postTags("</span>");
        hiBuilder.field("message");
        SearchRequestBuilder search=client.prepareSearch(index);
        search.setQuery(queryBuilder);
        search.setSize(countSize);
        search.addSort("@timestamp", SortOrder.DESC);
        SearchResponse response =search.highlighter(hiBuilder).execute().actionGet();

        SearchHits hits = response.getHits();

        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField nameField = highlightFields.get("message");
            if(nameField!=null){
                Text[] fragments = nameField.fragments();
                String nameTmp ="";
                for(Text text:fragments){
                    nameTmp+=text;
                }
                //将高亮片段组装到结果中去
                map.put("message",nameTmp);
            }
            String time= DateUtil.simpleFomate(DateUtil.fomatPaseZ((String)map.get("@timestamp")));
            map.put("time",time);
            map.remove("@timestamp");
            map.remove("@version");
            hitsMap.add(map);
        }
        return hitsMap;
    }
    /**
     * @return java.util.Set
     * @description 获取所有索引
     * @author wzz
     * @date 2019/6/21
     * @param[]
     */
    @RequestMapping("/getAllIndices")
    @ResponseBody
    public Set getAllIndices() {

        ActionFuture<IndicesStatsResponse> isr = client.admin().indices().stats(new IndicesStatsRequest().all());
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        Map<String, IndexStats> indexStatsMap = isr.actionGet().getIndices();
        Set<String> set = isr.actionGet().getIndices().keySet();
        return set;
    }

}
