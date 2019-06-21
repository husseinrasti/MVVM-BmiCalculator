package ir.radicalcode.app.bmi.data.entity;


public class ArticleModel {

    private int id;
    private String title;
    private String desc;
    private byte[] pic;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc( String desc ) {
        this.desc = desc;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic( byte[] pic ) {
        this.pic = pic;
    }
}
