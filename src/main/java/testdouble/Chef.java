package testdouble;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Chef {
    private Fridge fridge;

    public boolean storeVeg(String name, Object something) {
        fridge.open();
        try {
            fridge.put(name, something);
            return true;
        } catch (FridgeFullException e) {
            return false;
        } finally {
            fridge.close();
        }
    }
}
