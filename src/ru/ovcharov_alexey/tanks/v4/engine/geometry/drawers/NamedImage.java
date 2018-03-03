package ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;

/**
@author Alexey
*/
public class NamedImage {
    
    private BufferedImage image;
    private String name;

    public NamedImage(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    public NamedImage() {
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save(DataOutputStream dos) throws IOException {
        dos.writeUTF(name);
    }

    
    
}
