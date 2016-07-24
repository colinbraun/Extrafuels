package electrolitic.extrafuels.handlers;

import electrolitic.extrafuels.util.Instances;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

/**
 * Created by Colin on 7/23/2016.
 */
public class FuelHandler implements IFuelHandler{

    @Override
    public int getBurnTime(ItemStack fuel) {
        if(fuel.getItem() == Instances.ITEM_REFINED_COAL)
            return 2000;
        return 0;
    }
}
