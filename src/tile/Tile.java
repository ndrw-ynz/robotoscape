package tile;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage image;
    private final boolean allowCollision;

    public Tile(BufferedImage image, boolean allowCollision) {
        this.image = image;
        this.allowCollision = allowCollision;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean getAllowCollision() {
        return allowCollision;
    }

}
