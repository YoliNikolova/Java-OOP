package viceCity.models.players;

public class CivilPlayer extends BasePlayer{

    private final static int LIFEPOINTS = 50;

    public CivilPlayer(String name) {
        super(name, LIFEPOINTS);
    }
}
