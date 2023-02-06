package service;

import dto.SockRequest;
import model.Color;
import model.Size;
import model.Socks;
import org.springframework.stereotype.Service;

@Service
public interface SocksService {
    void addSocks(SockRequest sockRequest);

    void decreaseSocks(SockRequest sockRequest);

    int getSocksQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax);

    void validateRequest(SockRequest sockRequest);

    Socks mapToSocks(SockRequest sockRequest);
}
