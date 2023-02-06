package service;

import dto.SockRequest;
import exception.InsufficientQuantityException;
import exception.WrongSocksException;
import model.Color;
import model.Size;
import model.Socks;

import java.util.HashMap;
import java.util.Map;

public class SocksServiceImpl implements SocksService {
    private final Map<Socks, Integer> storage = new HashMap<>();


    @Override
    public void addSocks(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Socks socks = mapToSocks(sockRequest);
        if (storage.containsKey(socks)) {
            storage.put(socks, storage.get(socks) + sockRequest.getQuantity());
        } else {
            storage.put(socks, sockRequest.getQuantity());
        }
    }

    @Override
    public void decreaseSocks(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Socks socks = mapToSocks(sockRequest);
        int socksQuantity = storage.getOrDefault(socks, 0);
        if (socksQuantity >= sockRequest.getQuantity()) {
            storage.put(socks, socksQuantity - sockRequest.getQuantity());
        } else {
            throw new InsufficientQuantityException("Нельзя израсходовать или списать больше носков, чем имеется на складе");
        }
    }

    @Override
    public int getSocksQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Socks, Integer> entry : storage.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPart() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPart() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    @Override
    public void validateRequest(SockRequest sockRequest) {
        if (sockRequest.getColor() == null || sockRequest.getSize() == null) {
            throw new WrongSocksException("Укажите цвет и размер");
        }
        if (sockRequest.getCottonPart() < 0 || sockRequest.getCottonPart() > 100) {
            throw new WrongSocksException("Процент содержания хлопка должен быть больше 0 и меньше 100");
        }
        if (sockRequest.getQuantity() <= 0) {
            throw new WrongSocksException("Количество носков должно быть больше нуля");
        }

    }

    @Override
    public Socks mapToSocks(SockRequest sockRequest) {
        return new Socks(sockRequest.getColor(), sockRequest.getSize(), sockRequest.getCottonPart());
    }
}
