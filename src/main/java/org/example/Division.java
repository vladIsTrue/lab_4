package org.example;

public class Division {
    private Integer m_id;
    private String m_name;

    public Division(String name) {
        m_id = name.hashCode();
        m_name = name;
    }
    public Integer getId() {
        return m_id;
    }
    public String getName() {
        return m_name;
    }
}
