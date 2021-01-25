package viceCity.models.guns;

public class Rifle extends BaseGun {
    private final static int BULLETSPERBARREL = 50;
    private final static int TOTALBULLETS = 500;
    public Rifle(String name) {
        super(name, BULLETSPERBARREL, TOTALBULLETS);
    }

    @Override
    public int fire() {
        if(getTotalBullets()>0) {
            if (getBulletsPerBarrel() <= 0 || getBulletsPerBarrel() < 5) {
                int enough = 50 - getBulletsPerBarrel();
                setBulletsPerBarrel(enough);
                int currTotal = getTotalBullets();
                setTotalBullets(currTotal - enough);
            }
        }
        if(getBulletsPerBarrel()>=5) {
            int currentBarrel = this.getBulletsPerBarrel();
            setBulletsPerBarrel(currentBarrel - 5);
            setCanFire(true);
            return 5;
        }
        setCanFire(false);
        return 0;
    }
}
