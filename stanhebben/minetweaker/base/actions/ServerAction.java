package stanhebben.minetweaker.base.actions;

import java.net.SocketAddress;
import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.IUndoableAction;

public class ServerAction implements IUndoableAction {
	private SocketAddress address;
	private byte[] scripts;
	
	public ServerAction(SocketAddress address, byte[] scripts) {
		this.address = address;
		this.scripts = scripts;
	}

	@Override
	public void apply() {
		MineTweaker.instance.signalServerStart(address, scripts);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		MineTweaker.instance.signalServerEnd();
	}

	public String describe() {
		return "-- Starting Server --";
	}

	public String describeUndo() {
		return "-- Stopped Server --";
	}
}
