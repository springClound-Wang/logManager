package com.wupao.log.controller;

import com.wupao.log.pojo.Permission;
import com.wupao.log.pojo.Role;
import com.wupao.log.service.AuthService;
import com.wupao.log.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: wzz
 * @packageName com.wupao.log.controller
 * @Date: 2019/6/21 10:45
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private AuthService authService;
    //注入transportClient
    @Autowired
    private TransportClient client;
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
    /**
     * @return java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
     * @description 关键字查询
     * @author wzz
     * @date 2019/6/21
     * @param[keyWord]
     */
    @RequestMapping("/searchCourseWithKeyWord")
    public   List<Map<String, Object>> searchCourseWithKeyWord(String keyWord,String startTime,String endTime) throws Exception{

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
        SearchRequestBuilder search=client.prepareSearch("mlyd-web");
        search.setQuery(queryBuilder);
        search.setSize(40);
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
    public List getAllIndices() {
        List indexList=new ArrayList();
        ActionFuture<IndicesStatsResponse> isr = client.admin().indices().stats(new IndicesStatsRequest().all());
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        Map<String, IndexStats> indexStatsMap = isr.actionGet().getIndices();
        Set<String> set = isr.actionGet().getIndices().keySet();
        // 获取当前登陆用户
       /* Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();*/
        Integer userId = 19;//user.getId();
        List<Role> roles = this.authService.getRoleByUser(userId);
        if (null != roles && roles.size() > 0) {
            for (Role role : roles) {
                // 角色对应的权限数据
                List<Permission> perms = this.authService.findPermsByRoleId(role.getId());
                if (null != perms && perms.size() > 0) {
                    // 授权角色下所有权限
                    for (Permission perm : perms) {
                        for (String str : set) {
                           if(perm.getCode().equals(str)){
                               indexList.add(str);
                           }
                        }
                    }
                }
            }
        }
        return indexList;
    }


    @RequestMapping("/highlighter")
    public  SearchHits highlighter(String keyWord) throws Exception{
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(keyWord, "message");
        HighlightBuilder hiBuilder=new HighlightBuilder();
        hiBuilder.preTags("<h2>");
        hiBuilder.postTags("</h2>");
        hiBuilder.field("message");
        // 搜索数据
        SearchResponse response = client.prepareSearch("system-aa")
                .setQuery(queryBuilder)
                .highlighter(hiBuilder)
                .execute().actionGet();
        for (SearchHit searchHit : response.getHits()) {
            System.out.println(searchHit);

        }
        return response.getHits();
    }

    /**
     * 更新
     */
    @RequestMapping("/update1")
    public void updateIndex() throws  Exception{

    }
    @RequestMapping("/deleteIndex")
    public  boolean deleteIndex(String index) {
        if(!isIndexExist(index)) {
            System.out.println("Index is not exits!");
        }
        AcknowledgedResponse dResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (dResponse.isAcknowledged()) {
            System.out.println("delete index " + index + "  successfully!");
        } else {
            System.out.println("Fail to delete index " + index);
        }
        return dResponse.isAcknowledged();
    }
    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public  boolean isIndexExist(String index) {
        IndicesExistsResponse inExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        if (inExistsResponse.isExists()) {
            System.out.println("Index [" + index + "] is exist!");
        } else {
            System.out.println("Index [" + index + "] is not exist!");
        }
        return inExistsResponse.isExists();
    }
}



