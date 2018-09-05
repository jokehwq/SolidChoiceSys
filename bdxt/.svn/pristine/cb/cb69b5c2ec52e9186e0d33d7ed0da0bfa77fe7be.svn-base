package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CommentInfoReq extends PageInfoReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395315L;

    @ApiModelProperty(value = "资讯ID")
    @NotNull
    private String newsId;

    @Override
    public String toString() {
        return "CommentInfoReq{" +
                "newsId='" + newsId  +
                '}';
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
