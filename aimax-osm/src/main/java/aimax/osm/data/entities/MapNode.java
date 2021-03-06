package aimax.osm.data.entities;

import java.util.List;

/**
 * Represents a node within the map. Nodes
 * can be part of a way, points of interest, or markers.
 * A full java bean interface is provided. So it is
 * easy, to serialize objects using <code>XMLEncoder</code>.
 * @author Ruediger Lunde
 */
public interface MapNode extends MapEntity {
	
	/** Checks whether latitude and longitude values have been set. */
	boolean hasPosition();
	/** Sets latitude and longitude values for the node. */
	void setPosition(float lat, float lon);
	/** Returns the latitude value. */
	float getLat();
	/** Returns the longitude value. */
	float getLon();

	/** Provides read-only access to the maintained list of way references. */
	List<WayRef> getWayRefs();
}
