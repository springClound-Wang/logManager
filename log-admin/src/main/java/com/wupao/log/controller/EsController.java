package com.wupao.log.controller;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
     * 添加文档
     *
     * @param id   book id
     * @param name book name
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("id") String id, @RequestParam("name") String name) {
        try {
            // 构造ES的文档，这里注意startObject()开始构造，结束构造一定要加上endObject()
            XContentBuilder content = XContentFactory.jsonBuilder().startObject().
                    field("id", id)
                    .field("name", name)
                    .endObject();
            IndexResponse result = client.prepareIndex("book", "novel")
                    .setSource(content).get();
            return new ResponseEntity<>(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
