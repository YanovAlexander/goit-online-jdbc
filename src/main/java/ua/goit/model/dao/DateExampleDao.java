package ua.goit.model.dao;

import java.time.Instant;

public class DateExampleDao {
    private Integer id;
    private Instant dateExample;

    public DateExampleDao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDateExample() {
        return dateExample;
    }

    public void setDateExample(Instant dateExample) {
        this.dateExample = dateExample;
    }
}
