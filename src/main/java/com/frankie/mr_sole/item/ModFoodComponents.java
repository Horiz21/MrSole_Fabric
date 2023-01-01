package com.frankie.mr_sole.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent SOLE = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build();
    public static final FoodComponent COOKED_SOLE =(new FoodComponent.Builder()).hunger(6).saturationModifier(0.8F).build();
}
