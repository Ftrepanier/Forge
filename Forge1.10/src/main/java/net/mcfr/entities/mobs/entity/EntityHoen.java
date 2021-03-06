package net.mcfr.entities.mobs.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.mcfr.McfrItems;
import net.mcfr.entities.mobs.EntityBurrowed;
import net.mcfr.entities.mobs.ai.EntityAIGoToBurrow;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Hoen.
 *
 * @author Mc-Fr
 */
public class EntityHoen extends EntityBurrowed {
  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet();
  private int ticks;

  public EntityHoen(World worldIn) {
    super(worldIn);
    setSize(0.8F, 1.0F);
    this.ticks = 0;
    setPathPriority(PathNodeType.WATER, 0.0F);
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
    this.tasks.addTask(2, new EntityAIAvoidEntity<>(this, EntityGalt.class, 15.0F, 1.0F, 1.3F));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(3, new EntityAITempt(this, 1.0D, true, TEMPTATION_ITEMS));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
    this.tasks.addTask(5, new EntityAIGoToBurrow(this, 1.0D, 3));
    this.tasks.addTask(6, new EntityAIWander(this, 1.0D, 10));
    this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityHoen.class, 6.0F));
    this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(9, new EntityAILookIdle(this));
  }

  @Override
  public float getEyeHeight() {
    return this.height;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.ENTITY_CHICKEN_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound() {
    return SoundEvents.ENTITY_CHICKEN_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_CHICKEN_DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, Block blockIn) {
    playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
  }

  @Override
  public EntityHoen createChild(EntityAgeable ageable) {
    return new EntityHoen(this.worldObj);
  }

  @Override
  public boolean isBreedingItem(@Nullable ItemStack stack) {
    return stack != null && TEMPTATION_ITEMS.contains(stack.getItem());
  }

  @Override
  public void updatePassenger(Entity passenger) {
    super.updatePassenger(passenger);
    float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
    float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
    passenger.setPosition(this.posX + 0.1F * f, this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.posZ - 0.1F * f1);

    if (passenger instanceof EntityLivingBase) {
      ((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
    }
  }

  public int getTicks() {
    return this.ticks;
  }

  public void incrementTicks() {
    this.ticks++;
  }

  @Override
  public List<ItemStack> getLoots() {
    List<ItemStack> itemList = new ArrayList<>();

    itemList.add(new ItemStack(McfrItems.RAW_HUNTED_POULTRY, getRandomQuantity(14.2F)));
    itemList.add(new ItemStack(Items.FEATHER, getRandomQuantity(6.4F)));

    return itemList;
  }
}