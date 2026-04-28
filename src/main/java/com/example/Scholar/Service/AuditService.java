package com.example.Scholar.Service;

public interface AuditService {
    void log(String action, String entityName, String entityId, String details);
}
