package ru.dchinyaev.aopProjectTraining.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class EmployeeChangesRepository {
    private final Set<String> employeesChanges = new HashSet<>();

    public void add(String change) {
        employeesChanges.add(change);
    }
}
