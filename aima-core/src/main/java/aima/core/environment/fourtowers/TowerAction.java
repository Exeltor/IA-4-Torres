package aima.core.environment.fourtowers;

import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

public class TowerAction extends DynamicAction {

	public static final String PLACE_TOWER = "placeQueenAt";
	public static final String REMOVE_TOWER = "removeQueenAt";
	public static final String MOVE_TOWER = "moveQueenTo";

	public static final String ATTRIBUTE_TOWER_LOC = "location";

	/**
	 * Creates a queen action. Supported values of type are {@link #PLACE_TOWER}
	 * , {@link #REMOVE_TOWER}, or {@link #MOVE_TOWER}.
	 */
	public TowerAction(String type, XYLocation loc) {
		super(type);
		setAttribute(ATTRIBUTE_TOWER_LOC, loc);
	}

	public XYLocation getLocation() {
		return (XYLocation) getAttribute(ATTRIBUTE_TOWER_LOC);
	}

	public int getX() {
		return getLocation().getX();
	}

	public int getY() {
		return getLocation().getY();
	}
	
}
