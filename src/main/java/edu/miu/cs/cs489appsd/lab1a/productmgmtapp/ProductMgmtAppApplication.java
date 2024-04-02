package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.Helper.ProductNameComparator;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.Model.Product;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ProductMgmtAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMgmtAppApplication.class, args);
        List<Product> products = new ArrayList();
        products.add(new Product(3128874119L, "Banana","2023-01-24", 124, 0.55));
        products.add(new Product(2927458265L, "Apple","2022-12-09", 18, 1.09));
        products.add(new Product(9189927460L, "Carrot","2023-03-31", 89, 2.99));
        Collections.sort(products, new ProductNameComparator());

        printProductsInSJON(products);
        printProductsInXML(products);
        printProductsInCSV(products);
    }

    private static void printProductsInCSV(List<Product> products) {
        String csv = new String("productId, name, dateSupplied, quantityInStock, unitPrice \n");
        for(Product p:products) {
            String line = ""+p.getId()+", "+p.getName()+", "+p.getDateSupplied()+", "+p.getQuantityInStock()+", "+p.getUnitPrice()+"\n";
            csv = csv.concat(line);
        }

        System.out.println(csv);
    }

    public static void printProductsInXML(List<Product> products) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element productMaster = doc.createElement("products");
            doc.appendChild(productMaster);

            for(Product p:products) {
                Element product = doc.createElement("product");
                Attr productId = doc.createAttribute("productId");
                Attr productName = doc.createAttribute("name");
                Attr dateSupplied = doc.createAttribute("dateSupplied");
                Attr quantityInStock = doc.createAttribute("quantityInStock");
                Attr unitPrice = doc.createAttribute("unitPrice");

                productId.setValue(p.getId().toString());
                productName.setValue(p.getName());
                dateSupplied.setValue(p.getDateSupplied());
                quantityInStock.setValue(String.valueOf(p.getQuantityInStock()));
                unitPrice.setValue(String.valueOf(p.getUnitPrice()));

                product.setAttributeNode(productId);
                product.setAttributeNode(productName);
                product.setAttributeNode(dateSupplied);
                product.setAttributeNode(quantityInStock);
                product.setAttributeNode(unitPrice);

                productMaster.appendChild(product);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("products.xml"));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }


    }
    public static void printProductsInSJON(List<Product> products) {
        JSONArray jsonOutput = new JSONArray();

        for (Product p : products) {
            JSONObject productJson = new JSONObject();
            productJson.put("productId", p.getId());
            productJson.put("name", p.getName());
            productJson.put("dateSupplied", p.getDateSupplied());
            productJson.put("quantityInStock", p.getQuantityInStock());
            productJson.put("unitPrice", p.getUnitPrice());
            jsonOutput.put(productJson);
        }

        System.out.println(jsonOutput);
    }
}
