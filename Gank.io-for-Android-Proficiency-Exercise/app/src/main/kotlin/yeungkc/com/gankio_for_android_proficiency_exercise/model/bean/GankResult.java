package yeungkc.com.gankio_for_android_proficiency_exercise.model.bean;

//public class GankResult implements AutoBean {
//
//    private long id;
//    private String desc;
//    private Date publishedAt;
//    private String type;
//    private String url;
//    private String who;
//    private List<GString> images;
//
//    @Override
//    public int getItemType() {
//        if (images == null || images.isEmpty()) {
//            return GankAdapter.GANK_TYPE;
//        } else {
//            return images.size() > 1 ?
//                    GankAdapter.GANK_VP_PIC_TYPE : GankAdapter.GANK_PIC_TYPE;
//        }
//    }
//
//    @Override
//    public long getItemId() {
//        return hashCode();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        GankResult that = (GankResult) o;
//
//        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
//        if (publishedAt != null ? !publishedAt.equals(that.publishedAt) : that.publishedAt != null)
//            return false;
//        if (type != null ? !type.equals(that.type) : that.type != null) return false;
//        if (url != null ? !url.equals(that.url) : that.url != null) return false;
//        if (who != null ? !who.equals(that.who) : that.who != null) return false;
//        return images != null ? images.equals(that.images) : that.images == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = desc != null ? desc.hashCode() : 0;
//        result = 31 * result + (publishedAt != null ? publishedAt.hashCode() : 0);
//        result = 31 * result + (type != null ? type.hashCode() : 0);
//        result = 31 * result + (url != null ? url.hashCode() : 0);
//        result = 31 * result + (who != null ? who.hashCode() : 0);
//        result = 31 * result + (images != null ? images.hashCode() : 0);
//        return result;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public Date getPublishedAt() {
//        return publishedAt;
//    }
//
//    public void setPublishedAt(Date publishedAt) {
//        this.publishedAt = publishedAt;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getWho() {
//        return who;
//    }
//
//    public void setWho(String who) {
//        this.who = who;
//    }
//
//    public List<GString> getImages() {
//        return images;
//    }
//
//    public void setImages(List<GString> images) {
//        this.images = images;
//    }
//}
