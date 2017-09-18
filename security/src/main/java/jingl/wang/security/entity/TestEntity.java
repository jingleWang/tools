package jingl.wang.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Ben on 17/09/2017.
 */
@Entity(name = "test")
public class TestEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "v")
    private Integer v;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
