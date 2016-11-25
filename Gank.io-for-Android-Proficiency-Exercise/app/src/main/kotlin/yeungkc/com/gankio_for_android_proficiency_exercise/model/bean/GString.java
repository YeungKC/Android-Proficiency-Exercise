package yeungkc.com.gankio_for_android_proficiency_exercise.model.bean;

//@Entity(
//        generateGettersSetters = true
//)
public class GString {
//    @Id(autoincrement = true)
    private Long id;
//    @Index
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GString gString = (GString) o;

        return value != null ? value.equals(gString.value) : gString.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
