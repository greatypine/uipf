package com.gasq.bdp.logn.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TCustomerImages {
    private Integer id;

    private String customerPhone;

    private Integer orderId;

    private String imgUrl;

    private String title;

    private Integer companyId;

    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public TCustomerImages() {
		super();
	}

	public TCustomerImages(String customerPhone, Integer orderId, String imgUrl, String title, Integer companyId,
			String createUser) {
		super();
		this.customerPhone = customerPhone;
		this.orderId = orderId;
		this.imgUrl = imgUrl;
		this.title = title;
		this.companyId = companyId;
		this.createUser = createUser;
	}
    
}