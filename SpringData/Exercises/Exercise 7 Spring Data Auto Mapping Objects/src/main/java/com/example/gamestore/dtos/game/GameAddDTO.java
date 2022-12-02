package com.example.gamestore.dtos.game;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.gamestore.messages.ValidationMessages.*;

public class GameAddDTO {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public GameAddDTO() {
    }

    public GameAddDTO(String title, BigDecimal price, float size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        setTitle(title);
        setPrice(price);
        setSize(size);
        setTrailerId(trailer);
        setThumbnailURL(thumbnailURL);
        setDescription(description);
        setReleaseDate(releaseDate);
        validation();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public String getThumbnailURL() {
        return thumbnailURL;
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

    public String setThumbnailURL() {
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setTrailerId(String trailer) {

        this.trailer = trailer;
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
        return "Added " + this.title;
    }
}
