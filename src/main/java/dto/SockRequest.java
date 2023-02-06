package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Color;
import model.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SockRequest {

    private Color color;

    private Size size;

    private int cottonPart;

    private int quantity;

}
