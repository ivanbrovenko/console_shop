package com.epam.shop.locale;

import java.util.ResourceBundle;

public class MessagesContainer {
    private ResourceBundle resourceBundle;

    public MessagesContainer() {
        resourceBundle = ResourceBundle.getBundle("messages");
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }
}
