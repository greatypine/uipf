package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobconfig extends TSysTimerJobconfigKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

    private String code;

    private Integer type;

    private Integer model;

    private Integer nodeType;

    private Integer excuteId;

    private Integer sort;

    private Boolean status;

    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    private String text;

    private String props1;

    private String props2;

    private String props3;

    private String start;

    private String end;

    private String pathDots;

    private String pathPropsText;

    private Date createtime;

    private Date updatetime;

    private String createuser;

    private String remark;

    private String updateuser;

    private String groupname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getExcuteId() {
        return excuteId;
    }

    public void setExcuteId(Integer excuteId) {
        this.excuteId = excuteId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getProps1() {
        return props1;
    }

    public void setProps1(String props1) {
        this.props1 = props1 == null ? null : props1.trim();
    }

    public String getProps2() {
        return props2;
    }

    public void setProps2(String props2) {
        this.props2 = props2 == null ? null : props2.trim();
    }

    public String getProps3() {
        return props3;
    }

    public void setProps3(String props3) {
        this.props3 = props3 == null ? null : props3.trim();
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start == null ? null : start.trim();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
    }

    public String getPathDots() {
        return pathDots;
    }

    public void setPathDots(String pathDots) {
        this.pathDots = pathDots == null ? null : pathDots.trim();
    }

    public String getPathPropsText() {
        return pathPropsText;
    }

    public void setPathPropsText(String pathPropsText) {
        this.pathPropsText = pathPropsText == null ? null : pathPropsText.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }
}