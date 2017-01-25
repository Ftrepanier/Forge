package net.mcfr.decoration.signs.tileEntities;

import net.mcfr.utils.NBTUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TileEntityWallNote extends TileEntity {
  public static final int LINES_NB = 15;

  private ITextComponent[] text;
  private int lineBeingEdited;

  public TileEntityWallNote() {
    this.text = new ITextComponent[LINES_NB];
    this.lineBeingEdited = -1;
  }

  public ITextComponent[] getText() {
    return this.text;
  }

  public int getLineBeingEdited() {
    return this.lineBeingEdited;
  }

  public void setLineBeingEdited(int lineBeingEdited) {
    this.lineBeingEdited = lineBeingEdited;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);

    NBTTagList lines = compound.getTagList("Lines", NBTUtils.TAG_STRING);
    for (int i = 0; i < LINES_NB; i++)
      this.text[i] = new TextComponentString(lines.getStringTagAt(i));
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);

    NBTTagList lines = new NBTTagList();
    for (int i = 0; i < LINES_NB; i++)
      lines.appendTag(new NBTTagString(getText()[i].getFormattedText()));

    return compound;
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
  }
}
