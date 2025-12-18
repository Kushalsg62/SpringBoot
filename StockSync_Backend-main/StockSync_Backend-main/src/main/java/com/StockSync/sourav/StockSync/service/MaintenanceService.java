package com.StockSync.sourav.StockSync.service;

import org.springframework.http.ResponseEntity;

public interface MaintenanceService {
    public ResponseEntity<String> healthCheck();

}
