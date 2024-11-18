package com.historialmedico.historialmedico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Socket;

@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis-test")
    public String testRedis() {
        try {
            redisTemplate.opsForValue().set("testKey", "testValue");
            String value = redisTemplate.opsForValue().get("testKey");
            return "Conexión exitosa a Redis. Valor almacenado: " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error conectando a Redis: " + e.getMessage();
        }
    }

    @GetMapping("/test-connection")
    public String testConnection() {
        try {
            Socket socket = new Socket("redis", 6379); // Nombre del contenedor y puerto de Redis
            socket.close();
            return "Conexión exitosa a Redis";
        } catch (Exception e) {
            return "Error conectando a Redis: " + e.getMessage();
        }
    }

}