package com.wupao.log.web;

import com.wupao.log.pojo.AdminUser;
import com.wupao.log.pojo.Permission;
import com.wupao.log.pojo.Role;
import com.wupao.log.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @项目名称：lyd-admin
 * @包名：com.lyd.admin.web
 * @类描述：路由类
 * @创建人：汪正章
 * @创建时间：2018-04-19 18:52
 * @version：V1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = LoggerFactory
			.getLogger(IndexController.class);
    @Autowired
    private AuthService authService;
    //注入transportClient
    @Autowired
    private TransportClient client;
	@RequestMapping("/index") public String index() {
		logger.debug("-------------index------------");
		return "index";
	}

	@RequestMapping("/home")
    public ModelAndView toHome() {
		logger.debug("-------------home------------");
        ModelAndView mv=new ModelAndView("home");

		//获取索引
        List indexList=new ArrayList();
        ActionFuture<IndicesStatsResponse> isr = client.admin().indices().stats(new IndicesStatsRequest().all());
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        Map<String, IndexStats> indexStatsMap = isr.actionGet().getIndices();
        Set<String> set = isr.actionGet().getIndices().keySet();
        // 获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();
        Integer userId = user.getId();
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
        mv.addObject("indexList",indexList);
		return mv;
	}
	@RequestMapping("/login")
	public String toLogin() {
		logger.debug("===111-------------login------------");
		return "login";
	}

	@RequestMapping("/{page}") public String toPage(
			@PathVariable("page") String page) {
		logger.debug("-------------toindex------------" + page);
		return page;
	}
}
