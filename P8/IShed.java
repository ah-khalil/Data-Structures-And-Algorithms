import java.util.*;	// for Iterator and Iterable

/**
 * IShed is the interface for all Sheds in the STMining application
 *
 * @author	Patrick Peursum
 * @version	5.0
 */


public interface IShed extends Iterable<OrePile> {
   public String getName();
   public void setName(String newName);
   public int getOreType();
   
   public void addPile(OrePile newPile);
   public double satisfyOrder(ShipmentOrder order);
   
   public double calcTotalOreWeight();
   public double calcTotalMetalWeight();

   public Iterator<OrePile> iterator();
}
