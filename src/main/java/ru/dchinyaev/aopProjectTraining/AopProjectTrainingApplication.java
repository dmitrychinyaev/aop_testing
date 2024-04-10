package ru.dchinyaev.aopProjectTraining;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.dchinyaev.aopProjectTraining.service.EmployeeService;

@SpringBootApplication
@RequiredArgsConstructor
public class AopProjectTrainingApplication {
	private final EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(AopProjectTrainingApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void demonstrateAsync() {
		employeeService.safeChanged("Иван Иванов заменён на Петра Сидорова");
		employeeService.safeChanged("Илья Сорокин заменён на Кирилла Смирнова");
		employeeService.safeChanged("Роман Горелов заменён на Ольгу Касаткину");
	}
}
