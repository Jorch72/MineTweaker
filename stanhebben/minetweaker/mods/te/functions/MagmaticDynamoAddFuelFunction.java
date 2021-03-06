package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getFluid;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class MagmaticDynamoAddFuelFunction extends TweakerFunction {
	public static final MagmaticDynamoAddFuelFunction INSTANCE = new MagmaticDynamoAddFuelFunction();
	
	private MagmaticDynamoAddFuelFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) {
			throw new TweakerExecuteException("adding a magmatic dynamo fuel requires 2 arguments");
		}

		Fluid fluid = getFluid(arguments, 0, "fuel");
		Integer energy = getInt(arguments, 1, "energy");
		
		applyAction("MagmaticFuel", fluid.toString(),
				"fluidName", fluid.getName(),
				"energy", energy);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "magmaticDynamo.addFuel";
	}
}
