package com.frogniche.nichesmobs.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.function.Consumer;

public class NBTUtils {


    public static void setIntIfPossible(Consumer<Integer> setter, String name, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getInt(name));
        }
    }

    public static void setDoubleIfPossible(Consumer<Double> setter, String name, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getDouble(name));
        }
    }

    public static void setFloatIfPossible(Consumer<Float> setter, String name, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getFloat(name));
        }
    }

    public static void setBooleanIfPossible(Consumer<Boolean> setter, String name, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getBoolean(name));
        }
    }

    public static void setCompoundTagIfPossible(Consumer<CompoundTag> setter, String name, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getCompound(name));
        }
    }

    public static void setListIfPossible(Consumer<ListTag> setter, String name, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getList(name, 0));
        }
    }

    public static void setListIfPossible(Consumer<ListTag> setter, String name, int type, CompoundTag nbt) {
        if (nbt.contains(name)) {
            setter.accept(nbt.getList(name, type));
        }
    }
}
