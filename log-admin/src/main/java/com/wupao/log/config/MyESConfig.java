package com.wupao.log.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther: wzz
 * @packageName com.wupao.log.config
 * @Date: 2019/6/21 10:30
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class MyESConfig {


    private String host;

    private String port;

    private String clustername;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getClustername() {
        return clustername;
    }

    public void setClustername(String clustername) {
        this.clustername = clustername;
    }

    @Bean
    public TransportClient client() throws UnknownHostException {

        Settings settings = Settings.builder().put("cluster.name", clustername).
                put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), Integer.parseInt(port)));
        return client;
    }
}