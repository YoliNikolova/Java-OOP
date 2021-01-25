package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class ControllerImpl implements Controller {

    private Collection<Player> civilPlayers;
    private ArrayDeque<Gun> guns;
    private Player mainPlayer;


    public ControllerImpl() {
        this.civilPlayers = new ArrayList<>();
        this.guns = new ArrayDeque<>();
        this.mainPlayer = new MainPlayer();

    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.add(player);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        if (type.equals("Pistol")) {
            gun = new Pistol(name);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name);
        } else {
            return ConstantMessages.GUN_TYPE_INVALID;
        }
        this.guns.offer(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {

        if (this.guns.isEmpty()) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        Gun gun = this.guns.peek();
        if (name.equals("Vercetti")) {
            this.mainPlayer.getGunRepository().add(gun);
            this.guns.poll();
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        }

        boolean flag = false;
        Player playerCivil = null;
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.getName().equals(name)) {
                playerCivil = civilPlayer;
                flag = true;
            }
        }
        if (!flag) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        playerCivil.getGunRepository().add(gun);
        this.guns.poll();
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {
        Neighbourhood n = new GangNeighbourhood();
        n.action(mainPlayer, civilPlayers);
        int totalCivils = this.civilPlayers.size();
        boolean flag = true;
        int countDeadPlayer = 0;
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.getLifePoints() != 50) {
                flag = false;
                break;
            }
        }
        if (mainPlayer.getLifePoints() == 100 && flag) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        } else {

            Iterator<Player> it = civilPlayers.iterator();
            while (it.hasNext()) {
                if (!it.next().isAlive()) {
                    countDeadPlayer++;
                    it.remove();
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(ConstantMessages.FIGHT_HAPPENED).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, countDeadPlayer)).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, totalCivils - countDeadPlayer));
            return sb.toString().trim();
        }
    }
}
