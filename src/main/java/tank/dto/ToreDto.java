package tank.dto;

import java.io.Serializable;

public class ToreDto implements Serializable {
    private static final long serialVersionUID = 6529685098267757691L;
    public float Y;
    public float X;
    public float alpha = 0.0F;

    public ToreDto(float y, float x) {
        Y = y;
        X = x;
    }
}
