package actions;

import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import java.util.EnumSet;
import units.battle.Liveable;
import units.battle.Breaking;

/**
 *
 * @author alex
 */
public class BreakingStraightMove extends AbstractMoveActionWithCollision {

    public BreakingStraightMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryMap map) {
        calculateDesirePosition(movable, movable.getSpeed());
        int dRightX = dLeftX + movable.getWidth();
        int dDownY = dTopY + movable.getHeight();
        if (intoMap(dRightX, dDownY, map)) {
            int tileSize = map.getTileSize();
            for (int x = dLeftX; x < dRightX;) {
                for (int y = dTopY; y < dDownY;) {
                    Material material = map.getTile(x, y);
                    if (impassable.contains(material)) {
                        Breaking breaking = (Breaking) movable;
                        if (breaking.getBreakingStrength().isBreak(material)) {
                            map.setTile(x, y, Material.TERRA);
                            breaking.setLive(false);
                        }
                        return false;
                    }
                    if (y >= dDownY - tileSize) {
                        ++y;
                    } else {
                        y += tileSize;
                    }
                }
                if (x >= dRightX - tileSize) {
                    ++x;
                } else {
                    x += tileSize;
                }
            }
            movable.setLocation(dLeftX, dTopY);
            return true;
        }
        ((Liveable) movable).setLive(false);
        return false;
    }

}
