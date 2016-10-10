package ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers;

import java.awt.Image;
import java.io.DataOutputStream;
import java.io.IOException;

/**
@author Alexey
*/
public class NamedImage {
    
    private Image image;
    private String name;

    public NamedImage(Image image, String name) {
        this.image = image;
        this.name = name;
    }

    public NamedImage() {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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
