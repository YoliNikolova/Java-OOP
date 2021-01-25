package viceCity.models.guns;

public class Pistol extends BaseGun{
    private final static int BULLETSPERBARREL = 10;
    private final static int TOTALBULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETSPERBARREL, TOTALBULLETS);
    }

    @Override
    public int fire() {
        if(getTotalBullets()>0) {
            if (getBulletsPerBarrel() <= 0 || getBulletsPerBarrel() < 1) {
                setBulletsPerBarrel(10);
                int currTotal = getTotalBullets();
                setTotalBullets(currTotal - 10);
            }
        }
        if(getBulletsPerBarrel()>=1) {
            int currentBarrel = this.getBulletsPerBarrel();
            setBulletsPerBarrel(currentBarrel - 1);
            setCanFire(true);
            return 1;
        }
        setCanFire(false);
        return 0;
    }
}
