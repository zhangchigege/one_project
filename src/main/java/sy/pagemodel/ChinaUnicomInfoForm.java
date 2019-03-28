package sy.pagemodel;

/**
 * 联通信息
 * @author cc
 *
 */
public class ChinaUnicomInfoForm {
    private String chinauid;
    //名字
    private String chinaupersonname;
    //性别
    private String chinaupersonsex;
    //身份证
    private String chinaupapernum;
    //地址
    private String chinaupaperaddress;

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
}