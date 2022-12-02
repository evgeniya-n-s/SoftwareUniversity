package com.example.gamestore.entites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(name = "trailer_id", nullable = false)
    private String trailer;
    @Column(name = "image_url", nullable = false)
    private String thumbnailURL;
    private float size;
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;


    public Game() {
    }

    public Game(String title, String trailer, String thumbnailURL, float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
