package service.repositories.analytics;

import service.entities.analytics.AverageReport;

import java.util.List;

public interface IAverageReportRepository {
    List<AverageReport> getReport();
}
