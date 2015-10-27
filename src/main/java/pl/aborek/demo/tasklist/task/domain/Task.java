package pl.aborek.demo.tasklist.task.domain;

import pl.aborek.demo.tasklist.tag.domain.Tag;
import pl.aborek.demo.tasklist.user.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskState state;

    @ManyToOne
    private User owner;

    @ManyToMany
    private Set<Tag> tags;

    public Task() {
    }

    public Task(String content, User owner) {
        this.content = content;
        this.state = TaskState.TO_DO;
        this.owner = owner;
        this.tags = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskState getState() {
        return state;
    }

    @Transient
    public void markAsTodo() {
        this.state = TaskState.TO_DO;
    }

    @Transient
    public void markAsDone() {
        this.state = TaskState.DONE;
    }

    public User getOwner() {
        return owner;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Transient
    public Set<Tag> addTag(Tag tag) {
        tags.add(tag);
        return tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return !(id != null ? !id.equals(task.id) : task.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", owner=" + owner +
                ", tags=" + tags +
                '}';
    }
}
