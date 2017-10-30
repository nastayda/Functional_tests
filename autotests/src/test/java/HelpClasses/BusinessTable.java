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
    public int getId() {
        return id;
    }

    //Объект
    @Column(name = "object_name")
    private String objectName;
    public String getObjectName() {
        return objectName;
    }

    //Заказчик
    @Column(name = "client_name")
    private String clientName;
    public String getClientName() {
        return clientName;
    }

    //ФИО отвественного responsibleName
    @Column(name = "responsible_name")
    private String responsibleName;
    public String getResponsibleName() {
        return responsibleName;
    }

    //Подрядчик contractorName
    @Column(name = "contractor_name")
    private String contractorName;
    public String getContractorName() {
        return contractorName;
    }

    //Вид работ workType
    @Column(name = "work_type")
    private String workType;
    public String getWorkType() {
        return workType;
    }

    //Адрес objectAdress
    @Column(name = "object_adress")
    private String objectAdress;
    public String getObjectAdress() {
        return objectAdress;
    }
    //Договор № clientContractNumber
    @Column(name = "client_contract_number")
    private String clientContractNumber;
    public String getClientContractNumber() {
        return clientContractNumber;
    }

    //Дата договора clientContractDate
    @Column(name = "client_contract_date", columnDefinition = "DATE")
    private LocalDate clientContractDate;
    public LocalDate getClientContractDate() {
        return clientContractDate;
    }

    //Цена договора clientContractPrice
    @Column(name = "client_contract_price", columnDefinition = "BIGINT")
    private Long clientContractPrice;
    public Long getClientContractPrice() {
        return clientContractPrice;
    }

    @Override
    public String toString() {
        return  id +" "+
                objectName +" "+
                clientName +" "+
                responsibleName +" "+
                contractorName +" "+
                workType +" "+
                objectAdress +" "+
                clientContractNumber +" "+
                clientContractDate +" "+
                clientContractPrice;
    }
}
