package com.brugli.broglisowls.item;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.item.custom.ItemOwlEgg;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisOwlsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BroglisOwls.MODID);

    public static final RegistryObject<ItemOwlEgg> ITEM_OWL_EGG = ITEMS.register("item_owl_egg", ItemOwlEgg::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
