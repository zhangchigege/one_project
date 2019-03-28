package sy.model;

import java.util.Date;

public class ChinaUnicomInfo {
    private String chinauid;

    private String chinaupersonname;

    private String chinaupersonsex;

    private String chinaupapernum;

    private String chinaupaperaddress;

    private Date loaddate;

    public String getChinauid() {
        return chinauid;
    }

    public void setChinauid(String chinauid) {
        this.chinauid = chinauid == null ? null : chinauid.trim();
    }

    public String getChinaupersonname() {
        return chinaupersonname;
    }

    public void setChinaupersonname(String chinaupersonname) {
        this.chinaupersonname = chinaupersonname == null ? null : chinaupersonname.trim();
    }

    public String getChinaupersonsex() {
        return chinaupersonsex;
    }

    public void setChinaupersonsex(String chinaupersonsex) {
        this.chinaupersonsex = chinaupersonsex == null ? null : chinaupersonsex.trim();
    }

    public String getChinaupapernum() {
        return chinaupapernum;
    }

    public void setChinaupapernum(String chinaupapernum) {
        this.chinaupapernum = chinaupapernum == null ? null : chinaupapernum.trim();
    }

    public String getChinaupaperaddress() {
        return chinaupaperaddress;
    }

    public void setChinaupaperaddress(String chinaupaperaddress) {
        this.chinaupaperaddress = chinaupaperaddress == null ? null : chinaupaperaddress.trim();
    }

    public Date getLoaddate() {
        return loaddate;
    }

    public void setLoaddate(Date loaddate) {
        this.loaddate = loaddate;
    }
}