package ru.dchinyaev.aopProjectTraining.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dchinyaev.aopProjectTraining.annotation.TrackAsyncTime;
import ru.dchinyaev.aopProjectTraining.entity.Employee;
import ru.dchinyaev.aopProjectTraining.service.EmployeeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final Set<String> changesSet;

    @PostMapping("/create")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee){
        employeeService.saveNewEmployee(employee);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> allEmployee(){
        return ResponseEntity.ok(employeeService.employeeList());
    }
    @GetMapping("findBy/{id}")
    public ResponseEntity<Employee> getClient(@PathVariable Long id) {
        Employee employeeToFind = employeeService.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(employeeToFind);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.changeEmployee(employee, id);
        return ResponseEntity.ok("employee changed");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @TrackAsyncTime
    public void safeChanged(String changes){
        changesSet.add(changes);
        System.out.println();
    }
}
