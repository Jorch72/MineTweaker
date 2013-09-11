/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

//#ifdef MC152
import buildcraft.api.recipes.RefineryRecipe;
//#else
//+import buildcraft.api.recipes.RefineryRecipes;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.mods.buildcraft.BuildCraftUtil;

/**
 *
 * @author Stanneke
 */
public class RefineryAddRecipeAction implements IUndoableAction {
	private TweakerFluidStack output;
	private int energy;
	private int delay;
	private TweakerFluidStack input1;
	private TweakerFluidStack input2;
	
	public RefineryAddRecipeAction(
			TweakerFluidStack output,
			int energy,
			int delay,
			TweakerFluidStack input1,
			TweakerFluidStack input2) {
		this.output = output;
		this.energy = energy;
		this.delay = delay;
		this.input1 = input1;
		this.input2 = input2;
	}

	public void apply() {
		//#ifdef MC152
		RefineryRecipe.registerRefineryRecipe(new RefineryRecipe(input1.get(), input2 == null ? null : input2.get(), output.get(), energy, delay));
		//#else
		//+RefineryRecipes.addRecipe(input1.get(), input2 == null ? null : input2.get(), output.get(), energy, delay);
		//#endif
	}

	public boolean canUndo() {
		return BuildCraftUtil.getRefineryRecipes() != null;
	}

	public void undo() {
		BuildCraftUtil.getRefineryRecipes().remove(BuildCraftUtil.getRefineryRecipes().last());
	}

	public String describe() {
		return "Adding a refinery recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		return "Removing a refinery recipe for " + output.getDisplayName();
	}
}
