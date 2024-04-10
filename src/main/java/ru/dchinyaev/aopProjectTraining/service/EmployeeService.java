package ru.dchinyaev.aopProjectTraining.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dchinyaev.aopProjectTraining.annotation.TrackAsyncTime;
import ru.dchinyaev.aopProjectTraining.annotation.TrackTime;
import ru.dchinyaev.aopProjectTraining.repository.EmployeeChangesRepository;
import ru.dchinyaev.aopProjectTraining.repository.EmployeeRepository;
import ru.dchinyaev.aopProjectTraining.entity.Employee;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeChangesRepository changesRepository;
    @Transactional
    public void saveNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    @Transactional
    @TrackTime
    public List<Employee> employeeList(){
        return employeeRepository.findAll();
    }
    @Transactional
    @TrackTime
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
    @Transactional
    @TrackTime
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
    @Transactional
    @TrackTime
    public void changeEmployee(Employee employee, Long id){
        Employee currentEmployee = findById(id).orElseThrow(RuntimeException::new);
        safeChanged("Данные " + currentEmployee + " изменены на " + employee);
        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setEmail(employee.getEmail());
        saveNewEmployee(currentEmployee);
    }
    @TrackAsyncTime
    public void safeChanged(String change) {
        changesRepository.add(change);
    }

}
