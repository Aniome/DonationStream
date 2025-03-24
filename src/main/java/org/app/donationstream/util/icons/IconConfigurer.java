package org.app.donationstream.util.icons;

import javafx.scene.image.ImageView;
import org.kordamp.ikonli.javafx.FontIcon;

public class IconConfigurer {
    public static FontIcon getDataReportsIcon() {
        return new FontIcon(Icons.DATA_REPORTS_ICON.getIcon());
    }

    public static FontIcon getSettingsIcon() {
        return new FontIcon(Icons.SETTINGS_ICON.getIcon());
    }

    private static ImageView buildImageView(double iconSize, String icon) {
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(iconSize);
        imageView.setFitWidth(iconSize);
        return imageView;
        //return buildImageView(iconSize, Icons.SETTINGS_ICON.getIcon());
    }
}
