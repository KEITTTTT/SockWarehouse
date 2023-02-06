package controller;

import dto.SockRequest;
import exception.InsufficientQuantityException;
import exception.WrongSocksException;
import model.Color;
import model.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SocksService;

public class SocksController {
    private final SocksService socksService;


    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }
    @ExceptionHandler(WrongSocksException.class)
    public ResponseEntity<String> handleInvalidException (WrongSocksException wrongSocksException) {
        return ResponseEntity.badRequest().body(wrongSocksException.getMessage());
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<String> handleInvalidException (InsufficientQuantityException insufficientQuantityException) {
        return ResponseEntity.badRequest().body(insufficientQuantityException.getMessage());
    }

    @PostMapping
    public void addSocks(@RequestBody SockRequest sockRequest) {
        socksService.addSocks(sockRequest);
    }

    @PutMapping
    public void issueSocks(@RequestBody SockRequest sockRequest) {
        socksService.decreaseSocks(sockRequest);
    }
    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "color") Color color,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return socksService.getSocksQuantity(color, size, cottonMin, cottonMax);
    }
    @DeleteMapping
    public void removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
        socksService.decreaseSocks(sockRequest);
    }
}
