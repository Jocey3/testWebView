package model;

import android.content.SharedPreferences;

public class Model {
    private final SharedPreferences settings;
    private static final String SAVED_LINK = "Link";

    public Model(SharedPreferences settings) {
        this.settings = settings;
    }

    public String getLink() {
        return settings.getString(SAVED_LINK, "https://support.appsflyer.com/");

    }

    public void saveLink(String link) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(SAVED_LINK, link);
        editor.apply();
    }
}
