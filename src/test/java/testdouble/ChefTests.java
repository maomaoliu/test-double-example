package testdouble;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChefTests {
    // 使用真实对象
    @Test
    public void should_store_veg_to_fridge_if_fridge_is_not_full() {
        Fridge fridge = new Fridge();
        Chef chef = new Chef(fridge);

        boolean isStored = chef.storeVeg("cabbage", "A big cabbage.");

        assertTrue(isStored);
    }

    // 使用真实对象
    @Test
    public void should_not_store_veg_to_fridge_if_fridge_is_full() {
        Fridge fridge = buildFullFridge();
        Chef chef = new Chef(fridge);

        boolean isStored = chef.storeVeg("cabbage", "A big cabbage.");

        assertFalse(isStored);
    }

    private Fridge buildFullFridge() {
        Fridge fridge = new Fridge();
        try {
            fridge.put("cookie", "delicious cookie");
            fridge.put("eggs", "a lot of eggs");
        } catch (FridgeFullException ignored) {
        }
        return fridge;
    }
}
