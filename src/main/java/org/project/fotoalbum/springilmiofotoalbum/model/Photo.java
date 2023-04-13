package org.project.fotoalbum.springilmiofotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    @Size(max = 50, message = "The name can have at least 50 chars")
    @Size(min = 3, message = "The name must be longer than 3 chars")
    private String title;
    @Lob
    @Size(max = 255, message = "This field can have at least 255 chars")
    private String description;
    @Column(nullable = false)
    @NotBlank(message = "The url field cannot be empty")
    @Lob
    @Size(max = 255, message = "This field can have at least 255 chars")
    private String url;
    @Column(nullable = false)
    @NotNull
    private boolean visible;

    @ManyToMany
    @JoinTable(name = "categories_photos", joinColumns = @JoinColumn(name = "photo_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
