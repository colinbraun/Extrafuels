package electrolitic.extrafuels.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Colin on 7/17/2016.
 */
public class ExtraFuelsCreativeTab extends CreativeTabs {

    public ExtraFuelsCreativeTab(){
        super("ExtraFuels");
    }

    @Override
    public Item getTabIconItem() {
        return Items.COAL;
    }
}
