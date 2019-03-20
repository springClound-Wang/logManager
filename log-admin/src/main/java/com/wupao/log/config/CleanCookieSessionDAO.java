package com.wupao.log.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

public class CleanCookieSessionDAO extends AbstractSessionDAO{

	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 重写readSession方法;如果session中无匹配，清空浏览器中cookie
	 */
	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session s = doReadSession(sessionId);
        if (s == null) {
        	//清空浏览器cookie
            throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
        }
        return s;
    }
}
