package com.brugli.broglisowls.entity.custom;

import com.brugli.broglisowls.entity.BroglisOwlsEntityTypes;
import com.brugli.broglisowls.sound.BroglisOwlsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class GreatOwl extends Owl {

    public GreatOwl(EntityType<? extends GreatOwl> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void hoot() {
        Integer soundInt = level().random.nextInt(2);
        if (this.level().random.nextInt(500) == 0) {
            if (this.isBaby()) {
                if (soundInt == 0) {
                    this.playSound(BroglisOwlsSounds.BABY_GREAT_OWL_AMBIENT_1.get());
                }
                else this.playSound(BroglisOwlsSounds.BABY_GREAT_OWL_AMBIENT_2.get());
            }
            if (soundInt == 0) {
                this.playSound(BroglisOwlsSounds.GREAT_OWL_AMBIENT_1.get());
            }
            if (soundInt == 1) {
                this.playSound(BroglisOwlsSounds.GREAT_OWL_AMBIENT_2.get());
            }
            else this.playSound(BroglisOwlsSounds.GREAT_OWL_AMBIENT_3.get());
        }
    }

    @Override
    protected float getSoundVolume() {
        return 1.0F;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        return BroglisOwlsEntityTypes.GREAT_OWL_ENTITY_TYPE.get().create(serverLevel);
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.GOATS_SPAWNABLE_ON) || p_218106_.getBlockState(p_218108_.below()).is(BlockTags.FOXES_SPAWNABLE_ON) || p_218106_.getBlockState(p_218108_.below()).is(Blocks.SPRUCE_LEAVES)) && isBrightEnoughToSpawn(p_218106_, p_218108_);
    }
}
