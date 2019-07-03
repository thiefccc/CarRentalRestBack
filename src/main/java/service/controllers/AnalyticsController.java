package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.entities.analytics.AverageReport;
import service.repositories.analytics.IAverageReportRepository;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    @Autowired
    IAverageReportRepository averageReportRepository;

    @GetMapping(path = "/average")
    public List<AverageReport> getAverageReport(){
        return averageReportRepository.getReport();
    }
}

// https://stackoverflow.com/questions/33225313/native-sql-from-spring-hibernate-without-entity-mapping