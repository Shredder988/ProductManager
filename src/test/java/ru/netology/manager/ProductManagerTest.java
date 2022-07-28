package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(1, "wind", 800, "Oleg");
    Product book2 = new Book(2, "Lost", 600, "Victor");
    Product smartphone1 = new Smartphone(3, "10", 6000, "Huawei");
    Product smartphone2 = new Smartphone(4, "13",   10000, "Apple");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
    }

    @Test
    public void shouldAddProduct() {

        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextInName() {

        Product[] expected = {book1};
        Product[] actual = manager.searchBy("wind");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextInAuthor() {

        Product[] expected = {book2};
        Product[] actual = manager.searchBy("Victor");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByManufacturer() {

        Product[] expected = {smartphone2};
        Product[] actual = manager.searchBy("Apple");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByNumberInName() {

        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = manager.searchBy("1");

        assertArrayEquals(expected, actual);
    }
}