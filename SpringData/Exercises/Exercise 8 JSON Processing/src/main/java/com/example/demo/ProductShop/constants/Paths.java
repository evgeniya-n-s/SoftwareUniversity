package com.example.demo.ProductShop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USER_JSON_PATH = Path.of("src","main","resources","files","users.json");
    public static final Path PRODUCT_JSON_PATH = Path.of("src","main","resources","files","products.json");
    public static final Path CATEGORY_JSON_PATH = Path.of("src","main","resources","files","categories.json");
    public static final Path PRODUCTS_IN_RANGE_JSON_PATH = Path.of("src", "main", "resources", "output", "products-in-range.json");
    public static final Path USERS_SOLD_PRODUCTS_JSON_PATH = Path.of("src","main","resources","output","users-sold-products.json");
    public static final Path CATEGORIES_BY_PRODUCTS_JSON_PATH = Path.of("src","main","resources","output","categories-by-products.json");
    public static final Path USERS_AND_PRODUCTS_JSON_PATH = Path.of("src","main","resources","output","users-and-products.json");
}
