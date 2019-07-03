package service.repositories.analytics;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import service.entities.analytics.AverageReport;

import javax.persistence.*;
import java.util.List;

@Repository
public class AverageReportRepository implements IAverageReportRepository{
    @PersistenceContext
    EntityManager entityManager;

    public List<AverageReport> getReport(){
        // EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("average_entity");
        // EntityManager entityManager = entityFactory.createEntityManager();

        // TODO use non deprecated method
        // TODO make correct mapping to projection
        return entityManager.createNativeQuery ("select avg(datediff(\n" +
                "        ifnull(\n" +
                "                rt.return_timestamp,\n" +
                "                current_date()),\n" +
                "        rt.get_timestamp)) as avgDuration\n" +
                "     , ri.car_model as carModel\n" +
                "     , ro.rental_office_name as rentalOfficeName\n" +
                "from rents rt\n" +
                "         join rental_offices ro on rt.get_rental_office_id = ro.rental_office_id\n" +
                "         join rented_items ri on rt.rented_item_id = ri.rented_item_id\n" +
                "group by\n" +
                "    ri.car_model, ro.rental_office_name\n" +
                "order by car_model, rental_office_name")
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(AverageReport.class))
                .getResultList();
    }
}
