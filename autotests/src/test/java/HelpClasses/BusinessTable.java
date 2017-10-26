package HelpClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table( name="business")
public class BusinessTable {
    @Id
    @Column(name = "id")
    int id;

    //Объект
    @Column(name = "object_name")
    String objectName;

    //Заказчик
    @Column(name = "client_name")
    String client_name;

    //ФИО отвественного responsible_name
    @Column(name = "responsible_name")
    String responsible_name;

    //Подрядчик contractor_name
    @Column(name = "contractor_name")
    String contractor_name;

    //Вид работ work_type
    @Column(name = "work_type")
    String work_type;

    //Адрес object_adress
    @Column(name = "object_adress")
    String object_adress;

    //Договор № client_contract_number
    @Column(name = "client_contract_number")
    String client_contract_number;

    //Дата договора client_contract_date
    @Column(name = "client_contract_date", columnDefinition = "DATE")
    LocalDate client_contract_date;

    //Цена договора client_contract_price
    @Column(name = "client_contract_price", columnDefinition = "BIGINT")
    Long client_contract_price;

    @Override
    public String toString() {
        return  id +" "+
                objectName +" "+
                client_name +" "+
                responsible_name +" "+
                contractor_name +" "+
                work_type +" "+
                object_adress+" "+
                client_contract_number +" "+
                client_contract_date +" "+
                client_contract_price;
    }
}
