package com.example.gamestore.dtos.game;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.gamestore.messages.ValidationMessages.*;
import static com.example.gamestore.messages.ValidationMessages.DESCRIPTION_MUST_20_SYMBOLS;

public class GameEditDTO {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    private Long id;

    public GameEditDTO() {
    }

    public GameEditDTO(String title, BigDecimal price, float size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
        validation();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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

    public void validation() {
        if (Character.isLowerCase(getTitle().charAt(0)) || this.title.length() < 3 || this.title.length() > 100) {
            throw new IllegalArgumentException(NOT_VALID_GAME);
        }

        if (this.price.longValue() < 0) {
            throw new IllegalArgumentException(PRICE_MUST_POSITIVE_NUMBER);
        }

        if (this.size < 0.0) {
            throw new IllegalArgumentException(SIZE_MUST_POSITIVE_NUMBER);
        }

        boolean rightURL = thumbnailURL.startsWith("https://") || thumbnailURL.startsWith("http://");
        if (!rightURL) {
            throw new IllegalArgumentException(THUMBNAIL_URL_MUST_START_WITH);
        }
        if (this.trailer.length() != 11) {
            throw new IllegalArgumentException(TRAILER_ID_MUST_BE_11_SYMBOL);
        }
        if (this.description.length() < 20) {
            throw new IllegalArgumentException(DESCRIPTION_MUST_20_SYMBOLS);
        }
    }

    @Override
    public String toString() {
        return "Edited " + this.title;
    }
}
