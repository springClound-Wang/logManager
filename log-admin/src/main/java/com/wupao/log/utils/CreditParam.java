package com.wupao.log.utils;

import java.lang.reflect.Field;

/**
 * @项目名称：lyd-channel
 * @类名称：ChannelParam
 * @类描述：贷款列表查询条件
 * @创建人：肖龙祥
 * @创建时间：2017年12月21日 下午4:00:39 
 * @version：
 */
public class CreditParam extends BasePageParam{
	
	private Integer id;

    private String userName;

    private String startDate;

    private String endDate;
    
    private String umobile;
    
    private Integer gusetuserId;
    
    private String channel;
    
    /**渠道 名称**/
	private String channelName;
	/**客维员**/
	private String guestName;
    

	public CreditParam() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

	public String getUmobile() {
		return umobile;
	}

	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	

	public Integer getGusetuserId() {
		return gusetuserId;
	}

	public void setGusetuserId(Integer gusetuserId) {
		this.gusetuserId = gusetuserId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@Override
	public String toString() {
		 Field[] fields = this.getClass().getDeclaredFields();
         StringBuffer sb = new StringBuffer();
         sb.append(getClass().getName() + ":{\n");
         int index=0;
         for (Field field : fields) {
       	  index++;
             try {
           		  sb.append(field.getName() + " : ");
                     sb.append(field.get(this));
                     if(index!=fields.length){
                   	  sb.append(",");
                     }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("}");
        return sb.toString();
	}

}
