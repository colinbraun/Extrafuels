package electrolitic.extrafuels.init.items;

import electrolitic.extrafuels.util.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Colin on 7/17/2016.
 */
public class ItemRefinedCoal extends ItemRegister{

    public ItemRefinedCoal()
    {
        super("refinedCoal");
        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxStackSize(64);
    }

}
