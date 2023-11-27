package hse.todolist.entities;

import jakarta.persistence.*;

@Entity
public class List {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "list_id")
    private int listId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "content")
    private String content;

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        List list = (List) o;

        if (listId != list.listId) return false;
        if (name != null ? !name.equals(list.name) : list.name != null) return false;
        if (status != null ? !status.equals(list.status) : list.status != null) return false;
        if (content != null ? !content.equals(list.content) : list.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = listId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
