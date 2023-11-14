package org.example;

import java.time.LocalDate;

public class Human {
    private Integer m_id;
    private String m_name;
    private String m_sex;
    private Division m_division;
    private Integer m_salary;
    private LocalDate m_dateOfBirth;

    public Human(Integer id,
                 String name,
                 String sex,
                 Division division,
                 Integer salary,
                 LocalDate dateOfBirth) {
        m_id = id;
        m_name = name;
        m_sex = sex;
        m_division = division;
        m_salary = salary;
        m_dateOfBirth = dateOfBirth;
    }
}
