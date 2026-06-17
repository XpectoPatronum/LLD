package org.example.realSystems.bookMyShow.model;


import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class Theatre {
    private final String theatreId;
    private final String theatreName;
    private final Map<String,Screen> screens = new HashMap<>();

    public Theatre(String theatreId, String theatreName) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
    }

    public void addScreen(Screen screen) {
        screens.put(screen.getScreenId(), screen);
    }

    public Screen getScreen(String screenId) {
        return screens.get(screenId);
    }

    public List<Screen> getScreens() {
        return new ArrayList<>(screens.values());
    }
}

