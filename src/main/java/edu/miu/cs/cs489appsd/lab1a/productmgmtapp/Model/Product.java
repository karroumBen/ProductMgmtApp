package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.Model;


import java.util.Objects;

public class Product {
    public Product(){}
    public Product(Long id, String name, String dateSupplied, int quantityInStock, double unitPrice) {
        this.id = id;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    private Long id;
    private String name;
    private String dateSupplied;
    private int quantityInStock;
    private double unitPrice;

    public Long getId() {
        return id;
    }

    public void er (Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(String dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }



    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateSupplied='" + dateSupplied + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", unitPrice=" + unitPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantityInStock == product.quantityInStock && Double.compare(unitPrice, product.unitPrice) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(dateSupplied, product.dateSupplied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateSupplied, quantityInStock, unitPrice);
    }
}
