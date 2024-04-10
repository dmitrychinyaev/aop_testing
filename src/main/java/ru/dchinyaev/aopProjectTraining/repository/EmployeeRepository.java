package ru.dchinyaev.aopProjectTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dchinyaev.aopProjectTraining.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
