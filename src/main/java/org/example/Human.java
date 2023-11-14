/**
 * The `Human` class represents an individual with various attributes such as ID, name, gender, division,
 * salary, and date of birth.
 */
package org.example;

import java.time.LocalDate;

public class Human {
    private Integer m_id;
    private String m_name;
    private String m_sex;
    private Division m_division;
    private Integer m_salary;
    private LocalDate m_dateOfBirth;

    /**
     * Constructs a new `Human` object with the specified attributes.
     *
     * @param id           The unique identifier for the human.
     * @param name         The name of the human.
     * @param sex          The gender of the human.
     * @param division     The division to which the human belongs.
     * @param salary       The salary of the human.
     * @param dateOfBirth  The date of birth of the human.
     */
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

    /**
     * Retrieves the unique identifier of the human.
     *
     * @return The ID of the human.
     */
    public Integer getId() {
        return m_id;
    }

    /**
     * Retrieves the name of the human.
     *
     * @return The name of the human.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Retrieves the gender of the human.
     *
     * @return The gender of the human.
     */
    public String getGender() {
        return m_sex;
    }

    /**
     * Retrieves the salary of the human.
     *
     * @return The salary of the human.
     */
    public Integer getSalary() {
        return m_salary;
    }

    /**
     * Retrieves the division to which the human belongs.
     *
     * @return The division of the human.
     */
    public Division getDivision() {
        return m_division;
    }

    /**
     * Retrieves the date of birth of the human.
     *
     * @return The date of birth of the human.
     */
    public LocalDate getDateOfBirth() {
        return m_dateOfBirth;
    }
}
