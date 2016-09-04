package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception{
        //创建一个Schema对象(相当于数据库)
        Schema schema = new Schema(1, "com.crystal.androidgreendao.db");
        addNote(schema);
        addCustomerOrder(schema);
        DaoGenerator generator = new DaoGenerator();
        //生成entity和dao
        generator.generateAll(schema, "app/src/main/java-gen");
    }

    private static void addNote(Schema schema) {
        //向Schema中添加一个Entity(相当于数据库中的表)
        Entity note = schema.addEntity("Note");
        //为表添加字段
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }
}
