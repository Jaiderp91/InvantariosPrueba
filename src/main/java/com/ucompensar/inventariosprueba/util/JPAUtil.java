/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ucompensar.inventariosprueba.util;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    //variable para conectar EntityManager con la unidad de persistencia
    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("InventarioPU");

    //conexion
    public static EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

    //atajo para obtener un EntityManager
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    //Desconectar
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
