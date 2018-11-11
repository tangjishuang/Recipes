package recipes.two_strill.com.recipes.ui.bean;

/**
 * Created by SD on 2018/8/25.
 */

public class SearchParamsBean {

    //menu=西瓜&dtype=json&pn=0&rn=10&albums=&key=69517ba5aaa57d42b55780cc22f852ce

    private String menu;
    private String dtype;
    private String pn;
    private String rn;
    private String key;

    public SearchParamsBean(String menu, String dtype, String pn, String rn, String key) {
        this.menu = menu;
        this.dtype = dtype;
        this.pn = pn;
        this.rn = rn;
        this.key = key;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
