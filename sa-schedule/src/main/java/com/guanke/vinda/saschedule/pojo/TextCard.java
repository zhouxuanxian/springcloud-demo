package com.guanke.vinda.saschedule.pojo;
/**
 * 文本卡片
 * @author
 *
 */
public class TextCard {
    //是 标题，不超过128个字节，超过会自动截断
    private String title;
    //是    描述，不超过512个字节，超过会自动截断
    private String description;
    //是    点击后跳转的链接。
    private String url;

    private String btntxt;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getBtntxt() {
        return btntxt;
    }

    public void setBtntxt(String btntxt) {
        this.btntxt = btntxt;
    }
}
