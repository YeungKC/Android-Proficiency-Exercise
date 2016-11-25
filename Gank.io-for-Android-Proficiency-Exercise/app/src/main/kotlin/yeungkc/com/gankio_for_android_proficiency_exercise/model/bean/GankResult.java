package yeungkc.com.gankio_for_android_proficiency_exercise.model.bean;

import java.util.Date;
import java.util.List;

import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.GankAdapter;

//@Entity(
//        generateGettersSetters = true
//)
public class GankResult implements AutoBean {

//    @Id(autoincrement = true)
    private Long id;
//    @Index(unique = true)
    private String _id;
    private Date createdAt;
    private String desc;
    private Date publishedAt;
    private String source;
//    @Index
    private String type;
    private String url;
    private boolean used;
    private String who;
//    @ToMany(referencedJoinProperty = "id")
    private List<GString> images;

    @Override
    public int getItemType() {
        if (images == null || images.isEmpty()) {
            return GankAdapter.GANK_TYPE;
        } else {
            return images.size() > 1 ?
                    GankAdapter.GANK_VP_PIC_TYPE : GankAdapter.GANK_PIC_TYPE;
        }
    }

    @Override
    public long getItemId() {
        return hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GankResult that = (GankResult) o;

        return _id != null ? _id.equals(that._id) : that._id == null;

    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<GString> getImages() {
        return images;
    }

    public void setImages(List<GString> images) {
        this.images = images;
    }
}
