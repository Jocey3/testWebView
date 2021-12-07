package presenter;

import android.content.SharedPreferences;

import model.Model;

public class Presenter {
    private Model model;

    public Presenter(SharedPreferences preferences) {
        model = new Model(preferences);
    }

    public String getLink() {
        return model.getLink();
    }

    public void saveLink(String link) {
        model.saveLink(link);
    }
}
