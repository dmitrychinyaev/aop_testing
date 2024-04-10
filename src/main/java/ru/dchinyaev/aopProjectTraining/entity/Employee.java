package ru.dchinyaev.aopProjectTraining.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_aop_test")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 2, max = 15, message = "Поле должно включать от 2 до 15 символов")
    private String firstName;
    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 2, max = 15, message = "Поле должно включать от 2 до 15 символов")
    private String lastName;
    @Email(message = "Должен быть валидный e-mail")
    private String email;
}
