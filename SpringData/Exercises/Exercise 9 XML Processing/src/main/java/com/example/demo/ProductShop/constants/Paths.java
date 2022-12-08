package com.example.demo.ProductShop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USER_XML_PATH = Path.of("src","main","resources","filesXML","users.xml");
    public static final Path PRODUCT_XML_PATH = Path.of("src","main","resources","filesXML","products.xml");
    public static final Path CATEGORY_XML_PATH = Path.of("src","main","resources","filesXML","categories.xml");

    public static final Path PRODUCTS_IN_RANGE_XML_PATH = Path.of("src", "main", "resources", "outputXML", "products-in-range.xml");
    public static final Path USERS_SOLD_PRODUCTS_XML_PATH = Path.of("src","main","resources","outputXML","users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCTS_XML_PATH = Path.of("src","main","resources","outputXML","categories-by-products.xml");
    public static final Path USERS_AND_PRODUCTS_XML_PATH = Path.of("src","main","resources","outputXML","users-and-products.xml");
}
