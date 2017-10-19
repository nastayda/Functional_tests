package HelpClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="business")
public class BusinessTable {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "object_name")
    String objectName;

    @Override
    public String toString() {
        return "BusinessTable{" +
                "id=" + id +
                ", objectName='" + objectName + '\'' +
                '}';
    }
}
