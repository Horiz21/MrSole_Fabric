package com.frankie.mr_sole.item;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class ModItems {
    public static final Item SOLE = registerItem("sole", new Item(new FabricItemSettings().food(ModFoodComponents.SOLE)), ItemGroups.FOOD_AND_DRINK, Items.COOKED_SALMON);
    public static final Item COOKED_SOLE = registerItem("cooked_sole", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_SOLE)), ItemGroups.FOOD_AND_DRINK, SOLE);
    public static final Item MUSIC_DISC_DEEP_OCEAN = registerItem("music_disc_deep_ocean", new ModMusicDiscItem(6, ModSounds.MUSIC_DISC_DEEP_OCEAN, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 151), ItemGroups.TOOLS, Items.MUSIC_DISC_PIGSTEP);
    public static final Item MUSIC_DISC_IMAGINATION = registerItem("music_disc_imagination", new ModMusicDiscItem(7, ModSounds.MUSIC_DISC_IMAGINATION, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 150), ItemGroups.TOOLS, MUSIC_DISC_DEEP_OCEAN);
    public static final Item MUSIC_DISC_SEA_LEVEL = registerItem("music_disc_sea_level", new ModMusicDiscItem(8, ModSounds.MUSIC_DISC_SEA_LEVEL, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 136), ItemGroups.TOOLS, MUSIC_DISC_IMAGINATION);
    public static final Item SOLE_BUCKET = registerItem("sole_bucket", new Item(new FabricItemSettings()), ItemGroups.TOOLS, Items.TROPICAL_FISH_BUCKET);

    // 注册物品的功能
    private static Item registerItem(String name, Item item, ItemGroup itemGroup, Item preItem) {
        // 注册这个物品，然后使之在创造模式的某个物品组中出现
        Registry.register(Registries.ITEM, new Identifier(MrSole.MOD_ID, name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.addAfter(preItem, item));
        // 返回创建的这个物品
        return item;
    }

    public static void init() {
    }
}
