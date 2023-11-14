/**
 * The `Division` class represents a division or department within an organization.
 */
package org.example;

public class Division {
    private Integer m_id;
    private String m_name;

    /**
     * Constructs a new `Division` object with the specified name.
     *
     * @param name The name of the division.
     */
    public Division(String name) {
        m_id = name.hashCode();
        m_name = name;
    }

    /**
     * Retrieves the unique identifier of the division.
     *
     * @return The ID of the division.
     */
    public Integer getId() {
        return m_id;
    }

    /**
     * Retrieves the name of the division.
     *
     * @return The name of the division.
     */
    public String getName() {
        return m_name;
    }
}
