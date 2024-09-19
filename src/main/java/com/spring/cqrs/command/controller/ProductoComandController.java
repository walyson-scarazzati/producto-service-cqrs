package com.spring.cqrs.command.controller;

import com.spring.cqrs.command.commands.CreateProductoCommand;
import com.spring.cqrs.command.model.ProductoRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoComandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String guardarProducto(@RequestBody ProductoRestModel productoRestModel){
        CreateProductoCommand createProductoCommand =
                CreateProductoCommand.builder()
                        .productoId(UUID.randomUUID().toString())
                        .nombre(productoRestModel.getNombre())
                        .precio(productoRestModel.getPrecio())
                        .cantidad(productoRestModel.getCantidad())
                        .build();
        String result = commandGateway.sendAndWait(createProductoCommand);
        return result;
    }
}
