package stanhebben.minetweaker.base.actions;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements furnace.removeRecipe with simple items.
 * 
 * @author Stan Hebben
 */
public final class FurnaceRemoveRecipeAction implements IUndoableAction {
	private final Integer id;
	private final Object value;
	
	public FurnaceRemoveRecipeAction(Integer id) {
		this.id = id;
		value = FurnaceRecipes.smelting().getSmeltingList().get(id);
	}

	@Override
	public void apply() {
		FurnaceRecipes.smelting().getSmeltingList().remove(id);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		FurnaceRecipes.smelting().getSmeltingList().put(id, value);
	}

	public String describe() {
		return "Removing the furnace recipe turning " + (new ItemStack(id, 1, 0)).getDisplayName() + " into " + ((ItemStack)value).getDisplayName();
	}

	public String describeUndo() {
		return "Restoring the furnace recipe turning " + (new ItemStack(id, 1, 0)).getDisplayName() + " into " + ((ItemStack)value).getDisplayName();
	}
}
